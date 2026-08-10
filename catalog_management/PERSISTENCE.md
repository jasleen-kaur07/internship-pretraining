# Persistence, REST, and CRUD — A Guide for Interns

This document explains three foundational concepts in backend development using the **catalog-management** project as a live example. By the end, you will understand what persistence means, how REST APIs expose data, and how CRUD operations map to both HTTP and SQL.

---

## 1. What is Persistence?

**Persistence** means storing data so it survives beyond the lifetime of a single program run.

When your Java application starts, all variables live in **RAM (memory)**. When the process exits, that memory is wiped. If you stored a product category in a Java variable and the server restarted, the category would be gone.

A **database** gives us a place to write data that persists on disk — it survives restarts, crashes, and deployments.

```
Without persistence:
  App starts → you create a category → App restarts → category gone

With persistence (PostgreSQL):
  App starts → you create a category → written to DB → App restarts → category still there
```

---

## 2. The Persistence Layer

A well-designed application separates concerns into layers. Each layer has one job:

```
HTTP Request (JSON)
     │
     │  ProductCategoryRequest (DTO)
     ▼
┌─────────────────────────────┐
│      Controller Layer       │  Receives HTTP requests, returns HTTP responses
│  ProductCategoryController  │  Converts DTOs ↔ entities via Mapper
└─────────────┬───────────────┘
              │ passes entity
              ▼
┌─────────────────────────────┐
│       Service Layer         │  Contains business rules and validations
│  ProductCategoryService     │  Works only with entities, not DTOs
└─────────────┬───────────────┘
              │ calls
              ▼
┌─────────────────────────────┐
│     Repository Layer        │  Speaks to the database — the persistence layer
│  ProductCategoryRepository  │  Does NOT contain business logic
└─────────────┬───────────────┘
              │ queries
              ▼
┌─────────────────────────────┐
│         PostgreSQL          │  Stores data on disk permanently
│   table: product_category   │
└─────────────────────────────┘
     │
     │  ProductCategoryResponse (DTO)
     ▼
HTTP Response (JSON)
```

Each layer only knows about the layer directly below it. The Controller talks to the Service; the Service talks to the Repository. The Controller never directly touches the database.

DTOs (Data Transfer Objects) live at the HTTP boundary — they are what the client sends and receives, kept deliberately separate from the database entity.

---

## 3. DTOs — Separating the API Contract from the Database Schema

A **DTO (Data Transfer Object)** is a plain class used to carry data across a boundary — in this case, between the HTTP world and the internal domain model.

### Why not just send the entity directly?

If the controller accepted and returned `ProductCategory` (the JPA entity) directly:

- A client could send `createdDate` in the request body, potentially overwriting a server-managed field
- Renaming a database column (e.g. `category_name` → `name`) would silently break the API for all clients
- Internal fields like audit timestamps or database-specific annotations leak into the public API contract
- The API shape is now locked to the database schema — they can no longer evolve independently

DTOs decouple the two: the database can change without breaking the API, and the API can change without altering the database schema.

### The three classes involved

**`ProductCategoryRequest`** — what the client sends (inbound):

```java
// dto/ProductCategoryRequest.java

@Data
public class ProductCategoryRequest {
    private String categoryName;
    private String parentCategoryCode;
}
```

Notice what is **absent**: `categoryCode` (comes from the URL path, not the body), `createdDate` and `updatedDate` (server-managed, the client must never set these).

**`ProductCategoryResponse`** — what the client receives (outbound):

```java
// dto/ProductCategoryResponse.java

@Data
@Builder
public class ProductCategoryResponse {
    private String categoryCode;
    private String categoryName;
    private String parentCategoryCode;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
```

The response includes the timestamps so the client knows when the record was last changed, but because it is a separate class, we choose explicitly which fields to expose.

**`ProductCategoryMapper`** — converts between the two worlds:

```java
// mapper/ProductCategoryMapper.java

public class ProductCategoryMapper {

    public static ProductCategory toEntity(ProductCategoryRequest request) {
        return ProductCategory.builder()
                .categoryName(request.getCategoryName())
                .parentCategoryCode(request.getParentCategoryCode())
                .build();
    }

    public static ProductCategoryResponse toResponse(ProductCategory entity) {
        return ProductCategoryResponse.builder()
                .categoryCode(entity.getCategoryCode())
                .categoryName(entity.getCategoryName())
                .parentCategoryCode(entity.getParentCategoryCode())
                .createdDate(entity.getCreatedDate())
                .updatedDate(entity.getUpdatedDate())
                .build();
    }
}
```

The mapper is the only place that knows about both sides. The controller calls it; the service never sees a DTO.

### The boundary in the controller

```java
// CREATE — inbound DTO → entity → service → entity → outbound DTO
@PostMapping("/{categoryCode}")
public ResponseEntity<ProductCategoryResponse> createCategory(
        @PathVariable String categoryCode,
        @RequestBody ProductCategoryRequest request) {

    ProductCategory entity = ProductCategoryMapper.toEntity(request);
    entity.setCategoryCode(categoryCode);              // from path, not body

    ProductCategoryResponse response = ProductCategoryMapper.toResponse(
            service.createCategory(entity)
    );
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
}
```

The flow in one line:
```
Request DTO → [Mapper] → Entity → Service/Repository → Entity → [Mapper] → Response DTO
```

---

## 4. The Entity — Mapping Java to a Database Table

An **entity** is a Java class that maps to a database table. Each field maps to a column. Each instance of the class maps to one row.

```java
// entity/ProductCategory.java

@Entity                              // tells JPA: this class = a database table
@Table(name = "product_category")    // the table name in PostgreSQL
public class ProductCategory {

    @Id                              // this field is the PRIMARY KEY
    @Column(name = "category_code")
    private String categoryCode;     // uniquely identifies each category

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "parent_category_code")
    private String parentCategoryCode;  // null if it's a root category

    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;  // set once on insert, never changed

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;  // refreshed on every update
}
```

The corresponding PostgreSQL table that Hibernate auto-creates:

```sql
CREATE TABLE product_category (
    category_code       VARCHAR(50)  PRIMARY KEY,
    category_name       VARCHAR(200) NOT NULL,
    parent_category_code VARCHAR(50),
    created_date        TIMESTAMP    NOT NULL,
    updated_date        TIMESTAMP
);
```

### @PrePersist and @PreUpdate

These are lifecycle hooks — JPA calls them automatically at the right moment:

```java
@PrePersist              // runs just before INSERT
void onInsert() {
    this.createdDate = LocalDateTime.now();
    this.updatedDate = LocalDateTime.now();
}

@PreUpdate               // runs just before UPDATE
void onUpdate() {
    this.updatedDate = LocalDateTime.now();
}
```

You never have to set these fields manually. The framework handles it.

---

## 5. The Repository — Talking to the Database

The **Repository** is the persistence layer. It contains all database operations.

```java
// repository/ProductCategoryRepository.java

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, String> {

    // Spring Data generates the SQL for this automatically from the method name:
    //   SELECT * FROM product_category WHERE parent_category_code = ?
    List<ProductCategory> findByParentCategoryCode(String parentCategoryCode);
}
```

`JpaRepository<ProductCategory, String>` gives you these operations for free — no SQL to write:

| Method | SQL equivalent |
|---|---|
| `findAll()` | `SELECT * FROM product_category` |
| `findById("ELEC")` | `SELECT * FROM product_category WHERE category_code = 'ELEC'` |
| `save(category)` | `INSERT INTO ...` or `UPDATE ...` |
| `deleteById("ELEC")` | `DELETE FROM product_category WHERE category_code = 'ELEC'` |
| `existsById("ELEC")` | `SELECT COUNT(*) FROM product_category WHERE category_code = 'ELEC'` |

Spring Data JPA generates the actual SQL and JDBC calls at runtime. You write Java; it handles the database.

---

## 6. CRUD — The Four Fundamental Operations

CRUD stands for **Create, Read, Update, Delete** — the four things you can do with any data store.

| Operation | SQL | HTTP Method | Our endpoint |
|---|---|---|---|
| Create | `INSERT` | `POST` | `POST /api/categories` |
| Read (all) | `SELECT *` | `GET` | `GET /api/categories` |
| Read (one) | `SELECT WHERE` | `GET` | `GET /api/categories/{code}` |
| Update | `UPDATE` | `PUT` | `PUT /api/categories/{code}` |
| Delete | `DELETE` | `DELETE` | `DELETE /api/categories/{code}` |

---

## 7. REST — Exposing CRUD Over HTTP

**REST (Representational State Transfer)** is a style for designing APIs over HTTP. The key ideas:

1. **Resources are nouns, not verbs** — the URL names the thing, the HTTP method describes the action
   - Good: `POST /api/categories` (create a category)
   - Bad: `POST /api/createCategory`

2. **HTTP methods carry meaning**
   - `GET` — read, never changes data
   - `POST` — create a new resource
   - `PUT` — replace/update an existing resource
   - `DELETE` — remove a resource

3. **HTTP status codes carry meaning**
   - `200 OK` — request succeeded
   - `201 Created` — resource was created
   - `204 No Content` — success, nothing to return (used for DELETE)
   - `404 Not Found` — resource doesn't exist
   - `409 Conflict` — resource already exists (duplicate key)

---

## 8. Walking Through Each CRUD Operation

### CREATE — `POST /api/categories/{categoryCode}`

`categoryCode` is the stable identifier for a category — it belongs in the URL, not the request body. The body carries only the data the client is providing.

**Request:**
```http
POST /api/categories/ELEC
Content-Type: application/json

{
  "categoryName": "Electronics",
  "parentCategoryCode": null
}
```

**What happens step by step:**
```
1. HTTP POST hits ProductCategoryController.createCategory()
2. Controller maps ProductCategoryRequest → ProductCategory entity (via Mapper)
   categoryCode is taken from the URL path, not the body
3. Controller calls ProductCategoryService.createCategory(entity)
4. Service checks: does "ELEC" already exist? → repository.existsById("ELEC")
   → SQL: SELECT COUNT(*) FROM product_category WHERE category_code = 'ELEC'
5. If not found → repository.save(entity)
   → @PrePersist fires, sets createdDate and updatedDate
   → SQL: INSERT INTO product_category VALUES ('ELEC', 'Electronics', null, now(), now())
6. Controller maps ProductCategory entity → ProductCategoryResponse (via Mapper)
7. Returns HTTP 201 Created with the response DTO as JSON
```

**Response:**
```json
HTTP/1.1 201 Created

{
  "categoryCode": "ELEC",
  "categoryName": "Electronics",
  "parentCategoryCode": null,
  "createdDate": "2026-08-06T10:30:00",
  "updatedDate": "2026-08-06T10:30:00"
}
```

The response includes `createdDate` and `updatedDate` even though the client never sent them — the server set them via `@PrePersist`, and the response DTO exposes them.

---

### READ — `GET /api/categories` and `GET /api/categories/{code}`

**Get all:**
```http
GET /api/categories
```
```
Service calls repository.findAll()
→ SQL: SELECT * FROM product_category
→ Returns a list of all rows as JSON
```

**Get one:**
```http
GET /api/categories/ELEC
```
```
Service calls repository.findById("ELEC")
→ SQL: SELECT * FROM product_category WHERE category_code = 'ELEC'
→ Returns HTTP 200 with the category, or HTTP 404 if not found
```

**Get children (bonus endpoint):**
```http
GET /api/categories/parent/ELEC
```
```
Repository calls findByParentCategoryCode("ELEC")
→ SQL: SELECT * FROM product_category WHERE parent_category_code = 'ELEC'
→ Returns all subcategories of Electronics
```

---

### UPDATE — `PUT /api/categories/{code}`

**Request:**
```http
PUT /api/categories/ELEC
Content-Type: application/json

{
  "categoryName": "Consumer Electronics",
  "parentCategoryCode": null
}
```

**What happens:**
```
1. Service finds the existing category by code (404 if missing)
2. Updates only the mutable fields (name, parentCategoryCode)
   Note: categoryCode and createdDate are NOT changed
3. repository.save(existing)
   → @PreUpdate fires, sets updatedDate = now()
   → SQL: UPDATE product_category SET category_name = 'Consumer Electronics',
                                      updated_date = now()
          WHERE category_code = 'ELEC'
4. Returns HTTP 200 with the updated entity
```

Why not update `categoryCode`? It's the primary key — changing it would mean the record is a different thing entirely. Codes are stable identifiers.

---

### DELETE — `DELETE /api/categories/{code}`

**Request:**
```http
DELETE /api/categories/ELEC
```

**What happens:**
```
1. Service checks the category exists (404 if not)
2. repository.deleteById("ELEC")
   → SQL: DELETE FROM product_category WHERE category_code = 'ELEC'
3. Returns HTTP 204 No Content (success, nothing to return)
```

---

## 9. How Spring Data JPA Connects to PostgreSQL

The connection is configured in `application.properties`:

```properties
# tells JDBC where to find the database
spring.datasource.url=jdbc:postgresql://localhost:5432/catalog_db
spring.datasource.username=postgres
spring.datasource.password=postgres

# update = create/alter tables automatically to match our entities
spring.jpa.hibernate.ddl-auto=update

# print the generated SQL so you can learn from it
spring.jpa.show-sql=true
```

**The chain from config to query:**

```
application.properties
       │
       ▼ Spring Boot reads this at startup
DataSource (connection pool)
       │
       ▼ JPA uses DataSource to get connections
Hibernate (JPA implementation)
       │
       ▼ translates entity operations to SQL
JDBC Driver (postgresql.jar)
       │
       ▼ sends SQL over TCP
PostgreSQL
```

You configure the connection once. After that, `repository.save(category)` in your Java code results in SQL reaching the database — the entire middle stack is handled by Spring and Hibernate.

---

## 10. The Self-Referential Category Tree

A product category can belong to a parent category. Both are stored in the same table — this is a **self-referential relationship**.

```
ROOT CATEGORIES (parentCategoryCode = null):
  ELEC  → Electronics
  CLTH  → Clothing

SUBCATEGORIES:
  MOBL  → Mobile Phones   (parentCategoryCode = "ELEC")
  LAPT  → Laptops         (parentCategoryCode = "ELEC")
  MENS  → Men's Wear      (parentCategoryCode = "CLTH")
```

In the database this is one flat table:

```
category_code | category_name  | parent_category_code
--------------+----------------+---------------------
ELEC          | Electronics    | NULL
CLTH          | Clothing       | NULL
MOBL          | Mobile Phones  | ELEC
LAPT          | Laptops        | ELEC
MENS          | Men's Wear     | CLTH
```

The query `GET /api/categories/parent/ELEC` returns `[MOBL, LAPT]`.

---

## 11. JPA and Hibernate — How the Magic Works

You call `repository.save(category)` and a row appears in the database. Here is every layer between your Java code and the SQL.

```
Your Code
------------------------------------------------------------
repository.save(category)
          │
          ▼

Spring Data JPA
------------------------------------------------------------
Repository implementation created automatically at startup.
Reads method names like findByParentCategoryCode() and
generates the query — you never write this code.
          │
          ▼

JPA
------------------------------------------------------------
entityManager.persist(category)
JPA is only a specification (a set of interfaces).
Defines @Entity, @Id, @Column, @PrePersist etc.
          │
          ▼

Hibernate
------------------------------------------------------------
The actual implementation of JPA.
Fires @PrePersist → sets createdDate and updatedDate.
Generates SQL:

  INSERT INTO product_category
    (category_code, category_name, parent_category_code, created_date, updated_date)
  VALUES
    ('ELEC', 'Electronics', null, now(), now())
          │
          ▼

JDBC
------------------------------------------------------------
PreparedStatement.executeUpdate()
Low-level Java API that sends SQL over a connection.
          │
          ▼

PostgreSQL JDBC Driver
------------------------------------------------------------
Translates the JDBC call into the PostgreSQL wire protocol
and sends it over TCP to the database.
          │
          ▼

PostgreSQL
------------------------------------------------------------
Executes the SQL. Row inserted.
```

---

### JPA vs Hibernate

**JPA** is the standard — a set of rules and annotations (`@Entity`, `@Id`, `@Column`) that any compliant library must support.

**Hibernate** is the library that actually does the work. Spring Boot includes it by default.

You write JPA annotations. Hibernate reads them and generates SQL. If you ever needed to swap Hibernate for another JPA provider, your entity code would not change.

---

### How `save()` Decides INSERT vs UPDATE

`repository.save(entity)` does not always run an INSERT. Hibernate checks the `@Id` field:

- **null** → INSERT
- **has a value** → runs a SELECT first to check if the row exists, then INSERT or UPDATE

In this project, `categoryCode` is always set from the URL path before `save()` is called. So Hibernate always runs a SELECT first. This is why `createCategory()` calls `existsById()` upfront — to return a clean 409 instead of letting Hibernate silently decide.

---

### What `ddl-auto=update` Does

At startup, Hibernate compares your `@Entity` classes to the actual tables in PostgreSQL and adjusts automatically:

| Setting | Behaviour | When to use |
|---|---|---|
| `update` | Creates/alters tables to match entities | Local development |
| `validate` | Crashes if schema doesn't match | Staging |
| `none` | Does nothing | Production |
| `create-drop` | Creates on start, drops on stop | Tests |

Never use `update` in production — use a migration tool like **Flyway** or **Liquibase** instead, which apply versioned SQL scripts that are reviewed before running.

---

### Transactions

A transaction is a unit of work that either fully succeeds or fully rolls back. Annotate a service method with `@Transactional` to wrap everything inside it in one transaction:

```java
@Transactional
public ProductCategory updateCategory(String categoryCode, ProductCategory updated) {
    ProductCategory existing = repository.findById(categoryCode) ...
    existing.setCategoryName(updated.getCategoryName());
    return repository.save(existing);
    // if anything above throws → the entire transaction rolls back
}
```

Without `@Transactional`, each repository call is its own transaction. With it, they all share one.

---

## 12. Quick Reference

**To run this project locally:**
1. Install PostgreSQL and create database: `CREATE DATABASE catalog_db;`
2. Update credentials in `src/main/resources/application.properties`
3. Run: `./mvnw spring-boot:run`
4. The table is created automatically on first start (`ddl-auto=update`)

**Sample API calls (curl):**
```bash
# Create (categoryCode goes in the URL, not the body)
curl -X POST http://localhost:8080/api/categories/ELEC \
  -H "Content-Type: application/json" \
  -d '{"categoryName":"Electronics"}'

# Read all
curl http://localhost:8080/api/categories

# Read one
curl http://localhost:8080/api/categories/ELEC

# Update
curl -X PUT http://localhost:8080/api/categories/ELEC \
  -H "Content-Type: application/json" \
  -d '{"categoryName":"Consumer Electronics"}'

# Delete
curl -X DELETE http://localhost:8080/api/categories/ELEC
```

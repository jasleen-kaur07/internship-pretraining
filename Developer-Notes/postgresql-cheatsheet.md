# PostgreSQL Cheat Sheet

## Start PostgreSQL

```bash
brew services start postgresql@15
```

Starts the PostgreSQL server.

---

## Stop PostgreSQL

```bash
brew services stop postgresql@15
```

Stops the PostgreSQL server.

---

## Restart PostgreSQL

```bash
brew services restart postgresql@15
```

Restarts PostgreSQL.

---

## Check PostgreSQL Status

```bash
brew services list
```

Shows all Homebrew services and their status.

---

## Connect to PostgreSQL

```bash
psql postgres
```

Connects to PostgreSQL.

---

## List All Databases

```sql
\l
```

Displays all databases.

---

## Create Database

```sql
CREATE DATABASE student_management;
```

Creates a new database.

---

## Connect to a Database

```sql
\c student_management
```

Switches to the selected database.

---

## Show All Tables

```sql
\dt
```

Displays all tables.

---

## Describe a Table

```sql
\d student
```

Shows the table structure.

---

## Execute SQL File

```sql
\i Assignment-02-SQL-NoSQL/PostgreSQL/schema.sql
```

Runs all SQL commands inside the file.

---

## Exit PostgreSQL

```sql
\q
```

Exits PostgreSQL.

---

# Database Commands

## Create Table

```sql
CREATE TABLE student (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100)
);
```

---

## Drop Table

```sql
DROP TABLE student;
```

Deletes a table.

---

## Drop Table with Dependencies

```sql
DROP TABLE student CASCADE;
```

Deletes the table and all dependent objects.

---

## Delete All Data

```sql
TRUNCATE TABLE student;
```

Deletes all rows.

---

## Delete All Data and Reset IDs

```sql
TRUNCATE TABLE student RESTART IDENTITY CASCADE;
```

Deletes all rows and resets auto-increment IDs.

---

# CRUD Operations

## Insert One Record

```sql
INSERT INTO student(name,email)
VALUES ('Jasleen','jasleen@gmail.com');
```

---

## Insert Multiple Records

```sql
INSERT INTO student(name,email)
VALUES
('Rahul','rahul@gmail.com'),
('Priya','priya@gmail.com');
```

---

## View All Records

```sql
SELECT *
FROM student;
```

---

## View Specific Columns

```sql
SELECT name,email
FROM student;
```

---

## Filter Records

```sql
SELECT *
FROM student
WHERE id=1;
```

---

## Update Record

```sql
UPDATE student
SET phone='9999999999'
WHERE id=1;
```

---

## Delete Record

```sql
DELETE FROM student
WHERE id=1;
```

---

# Constraints

## Primary Key

```sql
PRIMARY KEY(id)
```

Uniquely identifies each row.

---

## Foreign Key

```sql
FOREIGN KEY(student_id)
REFERENCES student(id)
```

Creates a relationship between two tables.

---

## UNIQUE

```sql
email VARCHAR(255) UNIQUE
```

Prevents duplicate values.

---

## NOT NULL

```sql
name VARCHAR(100) NOT NULL
```

Does not allow NULL values.

---

## CHECK

```sql
CHECK(score >= 0 AND score <= 100)
```

Restricts allowed values.

---

# Sorting

## Ascending

```sql
SELECT *
FROM student
ORDER BY name ASC;
```

---

## Descending

```sql
SELECT *
FROM student
ORDER BY name DESC;
```

---

# Aggregate Functions

## COUNT

```sql
SELECT COUNT(*)
FROM student;
```

---

## AVG

```sql
SELECT AVG(score)
FROM marks;
```

---

## SUM

```sql
SELECT SUM(score)
FROM marks;
```

---

## MAX

```sql
SELECT MAX(score)
FROM marks;
```

---

## MIN

```sql
SELECT MIN(score)
FROM marks;
```

---

# GROUP BY

```sql
SELECT city,
COUNT(*)
FROM address
GROUP BY city;
```

---

# HAVING

```sql
SELECT student_id,
COUNT(*)
FROM enrollment
GROUP BY student_id
HAVING COUNT(*) > 3;
```

---

# JOINS

## INNER JOIN

```sql
SELECT s.name, c.name
FROM student s
JOIN enrollment e
ON s.id = e.student_id
JOIN course c
ON e.course_id = c.id;
```

---

## LEFT JOIN

```sql
SELECT *
FROM student s
LEFT JOIN address a
ON s.id = a.student_id;
```

---

# ALTER TABLE

## Add Column

```sql
ALTER TABLE student
ADD COLUMN graduated BOOLEAN;
```

---

## Update Column Value

```sql
UPDATE student
SET graduated = TRUE
WHERE id = 1;
```

---

# Useful Queries

## Current Database

```sql
SELECT current_database();
```

---

## Current User

```sql
SELECT current_user;
```

---

## PostgreSQL Version

```sql
SELECT version();
```

---

## Show All Tables

```sql
\dt
```

---

## Describe Table

```sql
\d table_name
```

---

## Show All Databases

```sql
\l
```

---

# Git Commands

## Check Status

```bash
git status
```

---

## Add Files

```bash
git add .
```

---

## Commit Changes

```bash
git commit -m "Your commit message"
```

---

## Push to GitHub

```bash
git push origin main
```

---

# Learning Order

1. SQL Basics
2. CREATE DATABASE
3. CREATE TABLE
4. Constraints
5. INSERT
6. SELECT
7. UPDATE
8. DELETE
9. WHERE
10. ORDER BY
11. Aggregate Functions
12. GROUP BY
13. HAVING
14. JOINS
15. ALTER TABLE
16. Practice Queries
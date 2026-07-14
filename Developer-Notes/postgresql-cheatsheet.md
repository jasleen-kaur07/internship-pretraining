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

Restarts the PostgreSQL server.

---

## Check PostgreSQL Status

```bash
brew services list
```

Shows whether PostgreSQL is running.

---

## Connect to PostgreSQL

```bash
psql postgres
```

Connects to the default PostgreSQL database.

---

## Connect to our database

```sql
\c student_management
```

Switches to our Student Management database.

---

## Exit PostgreSQL

```sql
\q
```

Quits PostgreSQL and returns to the terminal.

## Execute a SQL File

```sql
\i Assignment-02-SQL-NoSQL/PostgreSQL/schema.sql
```

Executes all SQL statements inside the specified file.
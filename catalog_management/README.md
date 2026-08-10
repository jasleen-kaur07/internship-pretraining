# Catalog Management — Setup Guide

## Prerequisites

You need two things installed: **Java 25** and **PostgreSQL 14**.

---

### 1. Install Homebrew (if you don't have it)

Homebrew is the package manager we use to install everything else on Mac.

Open Terminal and run:
```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

Verify:
```bash
brew --version
```

---

### 2. Install Java 25 (Amazon Corretto)

```bash
brew install --cask corretto@25
```

After installing, set it as your active Java version:
```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 25)
```

To make this permanent, add the line above to your `~/.zshrc`:
```bash
echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 25)' >> ~/.zshrc
source ~/.zshrc
```

Verify:
```bash
java -version
# should show: openjdk version "25..."
```

---

### 3. Install PostgreSQL 14

```bash
brew install postgresql@14
```

Add it to your PATH (so `psql` and `pg_ctl` commands work):
```bash
echo 'export PATH="/usr/local/opt/postgresql@14/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

Initialize the database cluster (first time only):
```bash
initdb /usr/local/var/postgres
```

Start PostgreSQL:
```bash
pg_ctl -D /usr/local/var/postgres -l /usr/local/var/log/postgresql.log start
```

Verify it's running:
```bash
pg_isready
# should show: /tmp:5432 - accepting connections
```

Create the application database:
```bash
psql postgres -c "CREATE DATABASE catalog_db;"
```

---

### 4. Clone and run the project

```bash
git clone <repo-url>
cd catalog-management
```

Run the app (no Maven installation needed — the project includes a wrapper):
```bash
./mvnw spring-boot:run
```

On first start, Hibernate automatically creates the `product_category` table in `catalog_db`. You will see the SQL printed in the terminal.

---

### 5. Verify it's working

Open your browser and go to:

```
http://localhost:8080/swagger-ui.html
```

You should see the Swagger UI with all the API endpoints listed. Use it to create, read, update, and delete product categories directly from the browser.

---

## Stopping and restarting PostgreSQL

```bash
# Stop
pg_ctl -D /usr/local/var/postgres stop

# Start
pg_ctl -D /usr/local/var/postgres -l /usr/local/var/log/postgresql.log start
```

---

## Common issues

**`pg_ctl: command not found`**
PostgreSQL isn't in your PATH. Run the `export PATH` line from step 3 and try again.

**`connection refused` on port 5432**
PostgreSQL isn't running. Run the `pg_ctl ... start` command from step 3.

**`./mvnw: Permission denied`**
```bash
chmod +x mvnw
```

**`JAVA_HOME` not set or wrong version**
```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 25)
```

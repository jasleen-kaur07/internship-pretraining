# MongoDB Cheat Sheet

## Start MongoDB

```bash
brew services start mongodb/brew/mongodb-community@8.0
```

Starts the MongoDB server.

---

## Stop MongoDB

```bash
brew services stop mongodb/brew/mongodb-community@8.0
```

Stops the MongoDB server.

---

## Restart MongoDB

```bash
brew services restart mongodb/brew/mongodb-community@8.0
```

Restarts MongoDB.

---

## Check MongoDB Status

```bash
brew services list
```

Shows whether MongoDB is running.

---

## Connect to MongoDB

```bash
mongosh
```

Opens the MongoDB Shell.

---

## Exit MongoDB

```javascript
exit
```

Exits the MongoDB Shell.

---

# Database Commands

## Create / Switch Database

```javascript
use student_management
```

Creates the database if it doesn't exist and switches to it.

---

## Show Current Database

```javascript
db
```

Displays the current database.

---

## Show All Databases

```javascript
show dbs
```

Lists all databases.

---

## Delete Current Database

```javascript
db.dropDatabase()
```

Deletes the current database.

---

# Collection Commands

## Create Collection

```javascript
db.createCollection("student")
```

Creates a new collection.

---

## Show Collections

```javascript
show collections
```

Lists all collections.

---

## Delete Collection

```javascript
db.student.drop()
```

Deletes the collection.

---

# CRUD Operations

## Insert One Document

```javascript
db.student.insertOne({
    name: "Jasleen",
    email: "jasleen@gmail.com"
})
```

---

## Insert Multiple Documents

```javascript
db.student.insertMany([
    {
        name: "Rahul"
    },
    {
        name: "Priya"
    }
])
```

---

## View All Documents

```javascript
db.student.find()
```

---

## Pretty Output

```javascript
db.student.find().pretty()
```

Displays documents in a readable format.

---

## Find One Document

```javascript
db.student.findOne({
    name: "Jasleen"
})
```

---

## Filter Documents

```javascript
db.student.find({
    name: "Jasleen"
})
```

---

## Update One Document

```javascript
db.student.updateOne(
{
    name: "Jasleen"
},
{
    $set: {
        phone: "9999999999"
    }
}
)
```

---

## Update Multiple Documents

```javascript
db.student.updateMany(
{},
{
    $set: {
        graduated: false
    }
}
)
```

---

## Replace Document

```javascript
db.student.replaceOne(
{
    name: "Jasleen"
},
{
    name: "Jasleen",
    age: 21
}
)
```

---

## Delete One Document

```javascript
db.student.deleteOne({
    name: "Rahul"
})
```

---

## Delete Multiple Documents

```javascript
db.student.deleteMany({
    graduated: true
})
```

---

# Embedded Documents

## Add Embedded Document

```javascript
db.student.updateOne(
{
    name: "Jasleen"
},
{
    $set: {
        address: {
            city: "Noida",
            state: "Uttar Pradesh"
        }
    }
}
)
```

---

## Update Embedded Field

```javascript
db.student.updateOne(
{
    name: "Jasleen"
},
{
    $set: {
        "address.city": "Delhi"
    }
}
)
```

---

# Arrays

## Add One Value

```javascript
db.student.updateOne(
{
    name: "Jasleen"
},
{
    $push: {
        courses: "Java"
    }
}
)
```

---

## Add Multiple Values

```javascript
db.student.updateOne(
{
    name: "Jasleen"
},
{
    $push: {
        courses: {
            $each: [
                "MongoDB",
                "Spring Boot"
            ]
        }
    }
}
)
```

---

## Remove Value

```javascript
db.student.updateOne(
{
    name: "Jasleen"
},
{
    $pull: {
        courses: "Java"
    }
}
)
```

---

# Comparison Operators

## Greater Than

```javascript
db.student.find({
    age: {
        $gt: 18
    }
})
```

---

## Less Than

```javascript
db.student.find({
    age: {
        $lt: 18
    }
})
```

---

## Greater Than or Equal

```javascript
db.student.find({
    age: {
        $gte: 18
    }
})
```

---

## Less Than or Equal

```javascript
db.student.find({
    age: {
        $lte: 18
    }
})
```

---

## Equal

```javascript
db.student.find({
    age: 18
})
```

---

## Not Equal

```javascript
db.student.find({
    age: {
        $ne: 18
    }
})
```

---

# Logical Operators

## AND

```javascript
db.student.find({
    $and: [
        {
            age: 20
        },
        {
            city: "Delhi"
        }
    ]
})
```

---

## OR

```javascript
db.student.find({
    $or: [
        {
            city: "Delhi"
        },
        {
            city: "Noida"
        }
    ]
})
```

---

# Sorting

## Ascending

```javascript
db.student.find().sort({
    name: 1
})
```

---

## Descending

```javascript
db.student.find().sort({
    name: -1
})
```

---

# Limit

```javascript
db.student.find().limit(3)
```

Returns only the first 3 documents.

---

# Count Documents

```javascript
db.student.countDocuments()
```

Counts all documents.

---

# Aggregation

## Group

```javascript
db.student.aggregate([
{
    $group: {
        _id: "$address.city",
        totalStudents: {
            $sum: 1
        }
    }
}
])
```

---

## Average

```javascript
db.student.aggregate([
{
    $unwind: "$marks"
},
{
    $group: {
        _id: "$name",
        averageMarks: {
            $avg: "$marks.score"
        }
    }
}
])
```

---

## Sum

```javascript
db.student.aggregate([
{
    $unwind: "$marks"
},
{
    $group: {
        _id: "$name",
        totalMarks: {
            $sum: "$marks.score"
        }
    }
}
])
```

---

# Useful Commands

## Load JavaScript File

```javascript
load("Assignment-02-SQL-NoSQL/MongoDB/queries.js")
```

Executes all commands inside the file.

---

## Remove All Documents

```javascript
db.student.deleteMany({})
```

Deletes all documents but keeps the collection.

---

## Drop Database

```javascript
db.dropDatabase()
```

Deletes the current database completely.

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

1. MongoDB Basics
2. Database & Collections
3. Documents
4. insertOne()
5. insertMany()
6. find()
7. updateOne()
8. updateMany()
9. deleteOne()
10. deleteMany()
11. Embedded Documents
12. Arrays
13. Comparison Operators
14. Logical Operators
15. sort()
16. limit()
17. countDocuments()
18. Aggregation
19. Practice Queries
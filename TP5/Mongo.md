# 🍃 MongoDB Project Guide

![MongoDB](https://img.shields.io/badge/MongoDB-6.0+-green.svg)
![Windows](https://img.shields.io/badge/Windows-10-blue.svg)
![JavaScript](https://img.shields.io/badge/JavaScript-ES6+-yellow.svg)

## 📝 Overview

This comprehensive guide covers everything you need to know about MongoDB, including:

- 🚀 Installation and setup
- ⚙️ Server configuration
- 💾 Database operations
- 🔄 CRUD operations
- 🛠️ Best practices

## ⚙️ Prerequisites

- 🪟 Windows operating system
- 🔑 Administrative access
- 📚 Basic understanding of database concepts

## 🚀 Step-by-Step Guide

### 1️⃣ Installation

#### 📁 Directory Setup

Create the required data directory:

```bash
mkdir C:\data\db
```

### 2️⃣ Server Configuration

#### 🖥️ Starting MongoDB Server

```bash
# Navigate to MongoDB bin directory
cd path/to/mongodb/bin

# Start the MongoDB server
mongod.exe
```

### 3️⃣ Client Setup

#### 🖥️ Starting MongoDB Client

```bash
# In a new terminal window
mongo.exe
```

### 4️⃣ Database Operations

#### 💾 Creating and Using Database

```javascript
// Create or switch to database
use info

// Insert a document
db.products.insertOne({
    // Document data
})
```

#### 🔄 Basic CRUD Operations

##### 📖 Reading Data

```javascript
// Find all documents
db.produits.find();

// Find first document
db.produits.findOne();

// Find by specific field
db.produits.find({ nom: "Thinkpad X230" }, { _id: 1 });

// Find by ID
db.produits.findOne({ _id: ObjectId("661e1a12abc123456789abcd") });

// Find with conditions
db.produits.find({ prix: { $gt: 13723 } });

// Find by boolean field
db.produits.findOne({ ultrabook: true });

// Find by pattern matching
db.produits.findOne({ nom: /Macbook/ });
```

##### 🗑️ Deleting Data

```javascript
// Delete multiple documents
db.produits.deleteMany({ fabriquant: "Apple" });

// Delete single document
db.produits.deleteOne({ _id: ObjectId("661e1a12abc123456789abcd") });
```

## ✨ Best Practices

| Practice          | Description                              |
| ----------------- | ---------------------------------------- |
| 💾 Data Backup    | Always backup before delete operations   |
| 📑 Indexing       | Use appropriate indexes for performance  |
| 📝 Naming         | Follow MongoDB naming conventions        |
| ⚠️ Error Handling | Implement proper error handling          |
| 🔄 Transactions   | Use transactions for critical operations |

## 🛠️ Troubleshooting

| Issue                 | Solution                             |
| --------------------- | ------------------------------------ |
| 🚫 Server Won't Start | Check data directory and permissions |
| 🔌 Connection Issues  | Verify server is running             |
| 📝 Error Messages     | Check MongoDB logs                   |

## 📚 Additional Resources

- 📖 [MongoDB Documentation](https://www.mongodb.com/docs/)
- ☁️ [MongoDB Atlas](https://www.mongodb.com/cloud/atlas)
- 🎓 [MongoDB University](https://university.mongodb.com/)

---

<div align="center">
  <sub>Built with ❤️ by Your Name</sub>
</div>

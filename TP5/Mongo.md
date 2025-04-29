# MongoDB Project Guide

## Overview

This guide covers setting up and working with MongoDB, including installation, server configuration, and basic database operations.

## Prerequisites

- Windows operating system
- Administrative access
- Basic understanding of database concepts

## Step 1: Installation

### Directory Setup

Create the required data directory:

```bash
mkdir C:\data\db
```

## Step 2: Server Configuration

### Starting MongoDB Server

```bash
# Navigate to MongoDB bin directory
cd path/to/mongodb/bin

# Start the MongoDB server
mongod.exe
```

## Step 3: Client Setup

### Starting MongoDB Client

```bash
# In a new terminal window
mongo.exe
```

## Step 4: Database Operations

### Creating and Using Database

```javascript
// Create or switch to database
use info

// Insert a document
db.products.insertOne({
    // Document data
})
```

### Basic CRUD Operations

#### Reading Data

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

#### Deleting Data

```javascript
// Delete multiple documents
db.produits.deleteMany({ fabriquant: "Apple" });

// Delete single document
db.produits.deleteOne({ _id: ObjectId("661e1a12abc123456789abcd") });
```

## Best Practices

1. Always backup your data before performing delete operations
2. Use appropriate indexes for better query performance
3. Follow MongoDB naming conventions
4. Implement proper error handling
5. Use transactions for critical operations

## Troubleshooting

- If server won't start, check if the data directory exists and has proper permissions
- For connection issues, verify the server is running
- Check MongoDB logs for error messages

## Additional Resources

- [MongoDB Documentation](https://www.mongodb.com/docs/)
- [MongoDB Atlas](https://www.mongodb.com/cloud/atlas)
- [MongoDB University](https://university.mongodb.com/)

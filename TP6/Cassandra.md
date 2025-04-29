# 🗄️ NoSQL Database: Apache Cassandra

![Cassandra](https://img.shields.io/badge/Cassandra-4.0+-blue.svg)
![Docker](https://img.shields.io/badge/Docker-20.10+-green.svg)
![CQL](https://img.shields.io/badge/CQL-3.4+-purple.svg)

## 📝 Overview

This guide covers working with Apache Cassandra, including:

- 🚀 Installation and setup
- 💻 CQL shell usage
- 🏗️ Database creation
- 📊 Data import
- 🔍 Query execution

## 🚀 Step-by-Step Guide

### 1️⃣ Installation

#### 🐳 Docker Setup

```bash
# Run Cassandra container
docker run --name mon-cassandra -d -p 9042:9042 cassandra

# Verify container is running
docker ps
```

### 2️⃣ CQL Shell

#### 💻 Accessing CQL Shell

```bash
# Connect to CQL shell
docker exec -it mon-cassandra cqlsh
```

### 3️⃣ Database and Tables Creation

#### 🏗️ Creating Keyspace and Tables

```sql
-- Create keyspace
CREATE KEYSPACE IF NOT EXISTS resto_NY
WITH REPLICATION = { 'class': 'SimpleStrategy', 'replication_factor': 1 };

USE resto_NY;

-- Create Restaurant table
CREATE TABLE Restaurant (
    id INT,
    Name VARCHAR,
    borough VARCHAR,
    BuildingNum VARCHAR,
    Street VARCHAR,
    ZipCode INT,
    Phone TEXT,
    CuisineType VARCHAR,
    PRIMARY KEY (id)
);

-- Create index
CREATE INDEX fk_Restaurant_cuisine ON Restaurant (CuisineType);

-- Create Inspection table
CREATE TABLE Inspection (
    idRestaurant INT,
    InspectionDate DATE,
    ViolationCode VARCHAR,
    ViolationDescription VARCHAR,
    CriticalFlag VARCHAR,
    Score INT,
    Grade VARCHAR,
    PRIMARY KEY (idRestaurant, InspectionDate)
);

-- Create index
CREATE INDEX fk_Inspection_Restaurant ON Inspection (Grade);
```

### 4️⃣ Data Import

#### 📥 Importing Datasets

1. 🔍 Get container ID:

```bash
docker ps
```

2. 📂 Copy files to container:

```bash
docker cp file_path container_ID
```

3. 📊 Import data in CQL shell:

```sql
USE resto_NY;

-- Import Restaurant data
COPY Restaurant (id, name, borough, buildingnum, street, zipcode, phone, cuisinetype)
FROM '/restaurants.csv' WITH DELIMITER=',';

-- Import Inspection data
COPY Inspection (idrestaurant, inspectiondate, violationcode, violationdescription, criticalflag, score, grade)
FROM '/restaurants_inspections.csv' WITH DELIMITER=',';
```

4. ✅ Verify data import:

```sql
SELECT count(*) FROM Restaurant;
SELECT count(*) FROM Inspection;
```

## 🔍 Query Examples

### 📊 Basic Queries

```sql
-- List all restaurants
SELECT * FROM Restaurant;

-- List restaurant names
SELECT name FROM Restaurant;

-- Get specific restaurant details
SELECT name, borough FROM Restaurant WHERE id = 41569764;
```

### 🔎 Advanced Queries

```sql
-- Get inspection details
SELECT inspectiondate, grade
FROM Inspection
WHERE idrestaurant = 41569764;

-- Find French restaurants
SELECT name FROM Restaurant
WHERE CuisineType = 'French'
ALLOW FILTERING;

-- Brooklyn restaurants
SELECT name FROM Restaurant
WHERE borough = 'BROOKLYN'
ALLOW FILTERING;
```

### 📈 Complex Queries

```sql
-- High-scoring inspections
SELECT grade, score FROM Inspection
WHERE idrestaurant = 41569764 AND score >= 10
ALLOW FILTERING;

-- Grade analysis
SELECT grade FROM Inspection
WHERE score > 30 AND grade IN ('A', 'B', 'C')
ALLOW FILTERING;

-- Count high-scoring inspections
SELECT count(*) FROM Inspection
WHERE score > 30 AND grade IN ('A', 'B', 'C')
ALLOW FILTERING;
```

## 📚 Additional Resources

- 📖 [Cassandra Documentation](https://cassandra.apache.org/doc/latest/)
- 🐳 [Docker Documentation](https://docs.docker.com/)
- 💻 [CQL Reference](https://cassandra.apache.org/doc/latest/cql/)

---

<div align="center">
  <sub>Built with ❤️ by Your Name</sub>
</div>

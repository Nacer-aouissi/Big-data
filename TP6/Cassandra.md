# NoSQL dabatase <Cassandra>

creating and working in db using cassandra

## steps :

- Install tion
- cqlsh
- db_creatio
- datasets
- importing data
- Queries

### Installation :

the first thing to do is to make sure that we've docker and it running successfuly
than launching in terminal this command :

- <docker run --name mon-cassandra -d -p 9042:9042 cassandra>
  it imports the cassandra container to docker and run it
  to make sure it's running use the next command :
- <docker ps>

### cqlsh :

to enter cqlsh in terminal we run the next command :

- <docker exec -it mon-cassandra cqlsh>
  this command open cassandra cql powershell

### database and tables creation :

in cqlsh run this command :

CREATE KEYSPACE IF NOT EXISTS resto_NY
WITH REPLICATION = { 'class': 'SimpleStrategy', 'replication_factor': 1 };

USE resto_NY;

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

CREATE INDEX fk_Restaurant_cuisine ON Restaurant (CuisineType);

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

CREATE INDEX fk_Inspection_Restaurant ON Inspection (Grade);

- this command creates a db named resto_NY than create 2 tables named : Restaurant and Inspection

### imoorting datasets to database :

- first of all : run docker ps to get cassandra's container id
- second go where the dataset in and get file path
- third launch this commands :
  <docker cp file_path container_ID >
- after uploading datasets to the container open cqlsh and run :
  USE resto_NY;

COPY Restaurant (id, name, borough, buildingnum, street, zipcode, phone, cuisinetype)
FROM '/restaurants.csv' WITH DELIMITER=',';

COPY Inspection (idrestaurant, inspectiondate, violationcode, violationdescription, criticalflag, score, grade)
FROM '/restaurants_inspections.csv' WITH DELIMITER=',';

- this copy the files u have to the tables u created before
- than check if the data copied successfuly or not by typing this querys :

SELECT count(_) FROM Restaurant;
SELECT count(_) FROM Inspection;

## Queries :

- List all restaurants : SELECT \* FROM Restaurant;
- List only restaurant names : SELECT name FROM Restaurant;
- Get name and borough of the restaurant with ID 41569764 :
  SELECT name, borough FROM Restaurant WHERE id = 41569764;
- Get inspection dates and grades for restaurant with ID 41569764 :
  SELECT inspectiondate, grade FROM Inspection WHERE idrestaurant = 41569764;
- List names of restaurants that serve French cuisine
 SELECT name FROM Restaurant WHERE CuisineType = 'French' ALLOW FILTERING;
- List names of restaurants located in BROOKLYN :
  SELECT name FROM Restaurant WHERE borough = 'BROOKLYN' ALLOW FILTERING;
- Get grades and scores for inspections of restaurant 41569764 with a score of at least 10
  SELECT grade, score FROM Inspection
  WHERE idrestaurant = 41569764 AND score >= 10 ALLOW FILTERING;
- Get non-null grades from inspections where the score is greater than 30 :
  SELECT grade FROM Inspection 
WHERE score > 30 AND grade IN ('A', 'B', 'C') ALLOW FILTERING;
SELECT grade FROM Inspection 
WHERE score > 30 AND grade IS NOT NULL ALLOW FILTERING;
- Count how many rows the previous query would return :
SELECT count(*) FROM Inspection 
WHERE score > 30 AND grade IN ('A', 'B', 'C') ALLOW FILTERING;

CREATE DATABASE des;

USE des;

CREATE TABLE IF NOT EXISTS customer(
	id INTEGER NOT NULL AUTO_INCREMENT,
	fullName VARCHAR(50),
	address VARCHAR(250),
	landMark VARCHAR(20),
	pinCode VARCHAR(8),
	email VARCHAR(50),
	contactNo VARCHAR(13),
	gender VARCHAR(7),
	maritialStatus VARCHAR(8),
	dob VARCHAR(50),
	anniversary VARCHAR(50),
	city VARCHAR(50),
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS users(
	id INTEGER NOT NULL AUTO_INCREMENT,
	userid VARCHAR(50),
	password VARCHAR(20),
	role VARCHAR(10),
	PRIMARY KEY(id)
);

INSERT INTO users (userid,password,role) VALUES ("admin","admin","Admin");
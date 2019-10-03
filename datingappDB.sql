DROP DATABASE IF EXISTS datingapp;
CREATE DATABASE datingapp;
USE datingapp;
CREATE TABLE datingprofiles(
	profile_id INT NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birthday DATE NOT NULL,
    age int NOT NULL,
    picture_path VARCHAR(150),
    PRIMARY KEY(profile_id)
);
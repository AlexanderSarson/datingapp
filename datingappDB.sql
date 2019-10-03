DROP DATABASE IF EXISTS datingapp;
CREATE DATABASE datingapp;
USE datingapp;
CREATE TABLE datingprofiles(
	profile_id INT NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birthday DATE NOT NULL,
    picture_path VARCHAR(150),
    PRIMARY KEY(profile_id)
);

INSERT INTO datingprofiles(profile_id, first_name, last_name, birthday, picture_path) VALUES(1, "FIRSTname", "lastname", 21-07-1989, "path");
INSERT INTO datingprofiles(profile_id, first_name, last_name, birthday, picture_path) VALUES(2, "FIRSTname2", "lastname2", 21-07-2000, "path");
INSERT INTO datingprofiles(profile_id, first_name, last_name, birthday, picture_path) VALUES(3, "FIRSTname3", "lastname3", 21-07-1970, "path");
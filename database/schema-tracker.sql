-- Multi-User Movie Catalogue — Schema
-- MySQL 8.0+
-- Run this first to create the database and table structure.

CREATE DATABASE IF NOT EXISTS multi_user_catalogue;
USE multi_user_catalogue;

DROP TABLE IF EXISTS user_movies;

CREATE TABLE user_movies (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(45) DEFAULT NULL,
  releaseYear INT DEFAULT NULL,
  genre VARCHAR(45) DEFAULT NULL,
  director VARCHAR(50) DEFAULT NULL,
  rating INT DEFAULT NULL,
  favourite TINYINT DEFAULT '0',
  userFullname VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

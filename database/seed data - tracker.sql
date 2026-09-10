-- Multi-User Movie Catalogue — Seed Data
-- Run after schema.sql. Gives each of the three demo users a movie
-- so the app isn't empty on first run.

USE multi_user_catalogue;

INSERT INTO user_movies (title, releaseYear, genre, director, rating, favourite, userFullname) VALUES
('Amadeus', 1984, 'Drama', 'Milos Forman', 5, 1, 'Ahmet Fanaz'),
('Fantastic Mr. Fox', 2009, 'Comedy/Drama', 'Wes Anderson', 4, 0, 'Mushab Budak'),
('La La Land', 2016, 'Musical', 'Damien Chazelle', 4, 1, 'Yusuf Gungor');

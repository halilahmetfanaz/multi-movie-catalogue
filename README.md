# Multi-User Movie Catalogue

A desktop Java Swing application backed by MySQL. Multiple users can each keep
their own movie catalogue — add movies, rate them, mark favourites, and browse
a shared list of titles per user.

> **Status: work in progress.** This started as a course exercise and is functional, but I'm actively refactoring it. See Roadmap below for what's planned.

## Features

- Add, edit, and delete movies per user
- Rate movies (0–5) and mark favourites
- Filter and view a user's favourite movies in a separate window
- Data persisted in MySQL via JDBC

## Tech Stack

- Java (Swing for the GUI)
- MySQL (JDBC)

## Project Structure

```
database/
├── schema.sql                  # creates the database and user_movies table
└── seed-data.sql               # optional sample rows
src/
├── db.properties.example       # copy to db.properties and fill in your own credentials
└── multimoviedb/
    ├── MainMoviePage.java       # main window: list, add, edit, delete, favourites
    ├── AddMovies.java           # add-movie form
    ├── EditMovie.java           # edit-movie form (rating / favourite)
    ├── ShowFavourites.java      # favourites window
    ├── MovieRecord.java         # movie model (getters/setters)
    └── MovieDataContex.java     # data access layer (JDBC queries)
```

## Setup

1. Run `database/schema.sql` against your local MySQL server to create the
   `multi_user_catalogue` database and `user_movies` table. Optionally run
   `database/seed-data.sql` afterward for some sample rows.
2. Copy `src/db.properties.example` to `src/db.properties` and fill in your
   local MySQL username/password. `db.properties` is gitignored — never
   commit real credentials.
3. Import as an Eclipse (or any Java IDE) project and run `MainMoviePage.java`.

## Roadmap

Things I'm actively working on, roughly in priority order:

-  Replace `printStackTrace()` + TODO comments with real error handling
      (user-facing dialogs instead of silent failures)
-  Replace `setBounds()` / `setLayout(null)` with a real layout manager
-  Reuse a single DB connection instead of opening a new one per query

## Notes

- The user dropdown (Ahmet Fanaz / Mushab Budak / Yusuf Gungor) is a
  placeholder roster from when this was a small group exercise — not a real
  authentication system.

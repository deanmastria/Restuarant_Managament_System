package org.example;

import org.example.utils.DatabaseInitializer;

public class TestDatabase {
    public static void main(String[] args) {
        // Initialize the database
        DatabaseInitializer.initialize();

        // At this point, your database should be initialized with all the tables.
        // You can verify this by checking the SQLite database file.
    }
}

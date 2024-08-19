package org.example.utils;


import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initialize() {
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {

            // Create Users table
            String sql = "CREATE TABLE IF NOT EXISTS Users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT NOT NULL UNIQUE," +
                    "passwordHash TEXT NOT NULL," +
                    "role TEXT NOT NULL)";
            stmt.execute(sql);

            // Create MenuItems table
            sql = "CREATE TABLE IF NOT EXISTS MenuItems (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "description TEXT," +
                    "preparationTime INTEGER," +
                    "price REAL NOT NULL," +
                    "ingredients TEXT)";
            stmt.execute(sql);

            // Create Orders table
            sql = "CREATE TABLE IF NOT EXISTS Orders (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "userId INTEGER NOT NULL," +
                    "items TEXT NOT NULL," +
                    "totalPrice REAL NOT NULL," +
                    "status TEXT NOT NULL," +
                    "FOREIGN KEY (userId) REFERENCES Users(id))";
            stmt.execute(sql);

            // Create Tables table
            sql = "CREATE TABLE IF NOT EXISTS Tables (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "size INTEGER NOT NULL," +
                    "status TEXT NOT NULL)";
            stmt.execute(sql);

            // Create Inventory table
            sql = "CREATE TABLE IF NOT EXISTS Inventory (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "ingredientName TEXT NOT NULL," +
                    "quantity INTEGER NOT NULL)";
            stmt.execute(sql);

            // Create Sales table
            sql = "CREATE TABLE IF NOT EXISTS Sales (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "orderId INTEGER NOT NULL," +
                    "revenue REAL NOT NULL," +
                    "date TEXT NOT NULL," +
                    "FOREIGN KEY (orderId) REFERENCES Orders(id))";
            stmt.execute(sql);

            System.out.println("Database has been initialized.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}








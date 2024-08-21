package org.example.utils;

import java.sql.Statement;

public class TableCreation {

    public static void createUsersTable(Statement stmt) throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS Users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT NOT NULL UNIQUE," +
                "passwordHash TEXT NOT NULL," +
                "role TEXT NOT NULL)";
        stmt.execute(sql);
        System.out.println("Users table created successfully.");
    }

    public static void createMenuItemsTable(Statement stmt) throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS MenuItems (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "description TEXT," +
                "preparationTime INTEGER," +
                "price REAL NOT NULL," +
                "ingredients TEXT)";
        stmt.execute(sql);
        System.out.println("MenuItems table created successfully.");
    }

    public static void createOrdersTable(Statement stmt) throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS Orders (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "userId INTEGER NOT NULL," +
                "items TEXT NOT NULL," +
                "totalPrice REAL NOT NULL," +
                "status TEXT NOT NULL," +
                "FOREIGN KEY (userId) REFERENCES Users(id))";
        stmt.execute(sql);
        System.out.println("Orders table created successfully.");
    }

    public static void createTablesTable(Statement stmt) throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS Tables (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "size INTEGER NOT NULL," +
                "status TEXT NOT NULL)";
        stmt.execute(sql);
        System.out.println("Tables table created successfully.");
    }

    public static void createInventoryTable(Statement stmt) throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS Inventory (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ingredientName TEXT NOT NULL," +
                "quantity INTEGER NOT NULL)";
        stmt.execute(sql);
        System.out.println("Inventory table created successfully.");
    }

    public static void createSalesTable(Statement stmt) throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS Sales (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "orderId INTEGER NOT NULL," +
                "revenue REAL NOT NULL," +
                "date TEXT NOT NULL," +
                "FOREIGN KEY (orderId) REFERENCES Orders(id))";
        stmt.execute(sql);
        System.out.println("Sales table created successfully.");
    }
}

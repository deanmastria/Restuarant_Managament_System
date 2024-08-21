package org.example.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DataSeeder {

    public static void seedTables(Connection conn) {
        String[] tableTypes = {"Booth", "Round Table", "Outside Table"};
        int[] tableSizes = {4, 6, 4};  // Booth: 4 seats, Round Table: 6 seats, Outside Table: 4 seats
        int[] tableQuantities = {4, 4, 3};  // Booth: 4 tables, Round Table: 4 tables, Outside Table: 3 tables

        try {
            String sql = "INSERT INTO Tables(size, status) VALUES(?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (int i = 0; i < tableTypes.length; i++) {
                    for (int j = 0; j < tableQuantities[i]; j++) {
                        pstmt.setInt(1, tableSizes[i]);
                        pstmt.setString(2, "Available");  // Default status to 'Available'
                        pstmt.executeUpdate();
                    }
                }
            }
            System.out.println("Tables data has been seeded.");
        } catch (Exception e) {
            System.err.println("Failed to seed tables data: " + e.getMessage());
        }
    }

    public static void seedInventory(Connection conn) {
        String[] iceCreamFlavors = {
                "Vanilla", "Chocolate", "Strawberry", "Mint Chocolate Chip",
                "Cookies and Cream", "Rocky Road", "Butter Pecan", "Coffee",
                "Pistachio", "Mango", "Caramel Swirl", "Coconut",
                "Peanut Butter Cup", "Banana", "Rum Raisin", "Blueberry"
        };

        String[] toppings = {
                "Sprinkles", "Chocolate Chips", "Whipped Cream", "Cherries",
                "Caramel Sauce", "Hot Fudge", "Nuts", "Marshmallows",
                "Oreos", "Gummy Bears"
        };

        String[] sorbetFlavors = {"Lemon", "Raspberry", "Mango", "Pineapple"};
        String[] lemonadeFlavors = {"Classic Lemonade", "Strawberry Lemonade", "Peach Lemonade"};
        String[] sodaFlavors = {"Cola", "Orange Soda", "Root Beer", "Ginger Ale", "Lemon-Lime Soda"};
        String milk = "Milk";  // For milkshakes

        try {
            String sql = "INSERT INTO Inventory(ingredientName, quantity) VALUES(?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // Insert ice cream flavors
                for (String flavor : iceCreamFlavors) {
                    pstmt.setString(1, flavor + " Ice Cream");
                    pstmt.setInt(2, 100);  // Set initial quantity
                    pstmt.executeUpdate();
                }

                // Insert toppings
                for (String topping : toppings) {
                    pstmt.setString(1, topping);
                    pstmt.setInt(2, 15);  // Set initial quantity
                    pstmt.executeUpdate();
                }

                // Insert sorbet flavors
                for (String sorbet : sorbetFlavors) {
                    pstmt.setString(1, sorbet + " Sorbet");
                    pstmt.setInt(2, 70);  // Set initial quantity
                    pstmt.executeUpdate();
                }

                // Insert lemonade flavors
                for (String lemonade : lemonadeFlavors) {
                    pstmt.setString(1, lemonade);
                    pstmt.setInt(2, 50);  // Set initial quantity
                    pstmt.executeUpdate();
                }

                // Insert soda flavors
                for (String soda : sodaFlavors) {
                    pstmt.setString(1, soda);
                    pstmt.setInt(2, 50);  // Set initial quantity
                    pstmt.executeUpdate();
                }

                // Insert milk for milkshakes
                pstmt.setString(1, milk);
                pstmt.setInt(2, 200);  // Set initial quantity
                pstmt.executeUpdate();
            }
            System.out.println("Inventory data has been seeded.");
        } catch (Exception e) {
            System.err.println("Failed to seed inventory data: " + e.getMessage());
        }
    }

    public static void seedMenuItems(Connection conn) {
        // Seed ice cream menu items
        seedIceCreamMenuItems(conn);

        // Seed toppings menu items
        seedToppingMenuItems(conn);

        // Seed sorbet menu items
        seedSorbetMenuItems(conn);

        // Seed lemonade menu items
        seedLemonadeMenuItems(conn);

        // Seed soda menu items
        seedSodaMenuItems(conn);
    }

    public static void seedIceCreamMenuItems(Connection conn) {
        String[] iceCreamFlavors = {
                "Vanilla", "Chocolate", "Strawberry", "Mint Chocolate Chip",
                "Cookies and Cream", "Rocky Road", "Butter Pecan", "Coffee",
                "Pistachio", "Mango", "Caramel Swirl", "Coconut",
                "Peanut Butter Cup", "Banana", "Rum Raisin", "Blueberry"
        };

        try {
            String sql = "INSERT INTO MenuItems(name, description, preparationTime, price, ingredients) VALUES(?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (String flavor : iceCreamFlavors) {
                    pstmt.setString(1, flavor + " Ice Cream");
                    pstmt.setString(2, "Delicious " + flavor + " ice cream.");
                    pstmt.setInt(3, 5); // Preparation time in minutes
                    pstmt.setDouble(4, 2.99); // Price in dollars
                    pstmt.setString(5, flavor + ", Milk, Sugar"); // Example ingredients
                    pstmt.executeUpdate();
                }
            }
            System.out.println("Ice cream menu items seeded.");
        } catch (Exception e) {
            System.err.println("Failed to seed ice cream menu items: " + e.getMessage());
        }
    }

    public static void seedToppingMenuItems(Connection conn) {
        String[] toppings = {
                "Sprinkles", "Chocolate Chips", "Whipped Cream", "Cherries",
                "Caramel Sauce", "Hot Fudge", "Nuts", "Marshmallows",
                "Oreos", "Gummy Bears"
        };

        try {
            String sql = "INSERT INTO MenuItems(name, description, preparationTime, price, ingredients) VALUES(?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (String topping : toppings) {
                    pstmt.setString(1, topping);
                    pstmt.setString(2, "Tasty " + topping + " topping.");
                    pstmt.setInt(3, 2); // Preparation time in minutes
                    pstmt.setDouble(4, 0.99); // Price in dollars
                    pstmt.setString(5, topping + ", Sugar"); // Example ingredients
                    pstmt.executeUpdate();
                }
            }
            System.out.println("Topping menu items seeded.");
        } catch (Exception e) {
            System.err.println("Failed to seed topping menu items: " + e.getMessage());
        }
    }

    public static void seedSorbetMenuItems(Connection conn) {
        String[] sorbetFlavors = {"Lemon", "Raspberry", "Mango", "Pineapple"};

        try {
            String sql = "INSERT INTO MenuItems(name, description, preparationTime, price, ingredients) VALUES(?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (String sorbet : sorbetFlavors) {
                    pstmt.setString(1, sorbet + " Sorbet");
                    pstmt.setString(2, "Refreshing " + sorbet + " sorbet.");
                    pstmt.setInt(3, 5); // Preparation time in minutes
                    pstmt.setDouble(4, 3.99); // Price in dollars
                    pstmt.setString(5, sorbet + ", Water, Sugar"); // Example ingredients
                    pstmt.executeUpdate();
                }
            }
            System.out.println("Sorbet menu items seeded.");
        } catch (Exception e) {
            System.err.println("Failed to seed sorbet menu items: " + e.getMessage());
        }
    }

    public static void seedLemonadeMenuItems(Connection conn) {
        String[] lemonadeFlavors = {"Classic Lemonade", "Strawberry Lemonade", "Peach Lemonade"};

        try {
            String sql = "INSERT INTO MenuItems(name, description, preparationTime, price, ingredients) VALUES(?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (String lemonade : lemonadeFlavors) {
                    pstmt.setString(1, lemonade);
                    pstmt.setString(2, lemonade + " flavored lemonade.");
                    pstmt.setInt(3, 3); // Preparation time in minutes
                    pstmt.setDouble(4, 1.99); // Price in dollars
                    pstmt.setString(5, lemonade + ", Water, Sugar, Lemon"); // Example ingredients
                    pstmt.executeUpdate();
                }
            }
            System.out.println("Lemonade menu items seeded.");
        } catch (Exception e) {
            System.err.println("Failed to seed lemonade menu items: " + e.getMessage());
        }
    }

    public static void seedSodaMenuItems(Connection conn) {
        String[] sodaFlavors = {"Cola", "Orange Soda", "Root Beer", "Ginger Ale", "Lemon-Lime Soda"};

        try {
            String sql = "INSERT INTO MenuItems(name, description, preparationTime, price, ingredients) VALUES(?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (String soda : sodaFlavors) {
                    pstmt.setString(1, soda);
                    pstmt.setString(2, soda + " flavored soda.");
                    pstmt.setInt(3, 2); // Preparation time in minutes
                    pstmt.setDouble(4, 1.49); // Price in dollars
                    pstmt.setString(5, soda + ", Water, Sugar, Flavoring"); // Example ingredients
                    pstmt.executeUpdate();
                }
            }
            System.out.println("Soda menu items seeded.");
        } catch (Exception e) {
            System.err.println("Failed to seed soda menu items: " + e.getMessage());
        }
    }
}



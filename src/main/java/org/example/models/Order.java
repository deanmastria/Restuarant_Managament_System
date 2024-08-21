package org.example.models;

import java.util.List;

public class Order {
    private int id;
    private int userId;
    private List<OrderItem> items;
    private double totalPrice;
    private String status;
    private int tableId;  // Add this field to hold the assigned table ID

    // Constructors, getters, and setters

    public Order(int userId, List<OrderItem> items, double totalPrice, String status) {
        this.userId = userId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTableId() {
        return tableId;
    }

    // Set the table for this order
    public void setTable(int tableId) {
        this.tableId = tableId;
    }
}
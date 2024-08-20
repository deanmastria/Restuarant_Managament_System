package org.example.models;

import java.io.Serializable;
import java.util.List;

public class MenuItem implements Serializable {
    private String name;          // Name of the menu item
    private String description;   // Description of the item
    private int preparationTime;  // Preparation time in minutes
    private double price;          // Price of the item
    private List<String> ingredients; // List of ingredients for the item

    // Constructor
    public MenuItem(String name, String description, int preparationTime, double price, List<String> ingredients) {
        this.name = name;
        this.description = description;
        this.preparationTime = preparationTime;
        this.price = price;
        this.ingredients = ingredients;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    // Override toString for a better representation of the MenuItem
    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", preparationTime=" + preparationTime +
                ", price=" + price +
                ", ingredients=" + ingredients +
                '}';
    }
}

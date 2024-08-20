package org.example.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class MenuItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;          // Name of the menu item
    private String description;   // Description of the item
    private int preparationTime;  // Preparation time in minutes
    private double price;         // Price of the item
    private List<String> ingredients; // List of ingredients for the item

    // Constructor with validation
    public MenuItem(String name, String description, int preparationTime, double price, List<String> ingredients) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty.");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description must not be null or empty.");
        }
        if (preparationTime < 0) {
            throw new IllegalArgumentException("Preparation time must be non-negative.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price must be non-negative.");
        }
        if (ingredients == null || ingredients.isEmpty()) {
            throw new IllegalArgumentException("Ingredients must not be null or empty.");
        }

        this.name = name;
        this.description = description;
        this.preparationTime = preparationTime;
        this.price = price;
        this.ingredients = ingredients;
    }

    // Getters and Setters with validation
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty.");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description must not be null or empty.");
        }
        this.description = description;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        if (preparationTime < 0) {
            throw new IllegalArgumentException("Preparation time must be non-negative.");
        }
        this.preparationTime = preparationTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be non-negative.");
        }
        this.price = price;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        if (ingredients == null || ingredients.isEmpty()) {
            throw new IllegalArgumentException("Ingredients must not be null or empty.");
        }
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

    // Override equals and hashCode for correct behavior in collections
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return preparationTime == menuItem.preparationTime &&
                Double.compare(menuItem.price, price) == 0 &&
                Objects.equals(name, menuItem.name) &&
                Objects.equals(description, menuItem.description) &&
                Objects.equals(ingredients, menuItem.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, preparationTime, price, ingredients);
    }
}


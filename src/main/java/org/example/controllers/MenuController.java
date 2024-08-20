package org.example.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.models.MenuItem;
import org.example.services.MenuService;

import java.util.Arrays;
import java.util.List;

public class MenuController {

    @FXML
    private TextField nameField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField preparationTimeField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField ingredientsField;

    @FXML
    private ListView<MenuItem> menuListView;

    private final MenuService menuService = new MenuService();
    private ObservableList<MenuItem> menuItems;

    @FXML
    public void initialize() {
        loadMenuItems();
    }

    private void loadMenuItems() {
        List<MenuItem> items = menuService.getAllMenuItems();
        menuItems = FXCollections.observableArrayList(items);
        menuListView.setItems(menuItems);
    }

    @FXML
    public void handleAddMenuItem(MouseEvent event) {
        String name = nameField.getText();
        String description = descriptionField.getText();
        int preparationTime = Integer.parseInt(preparationTimeField.getText());
        double price = Double.parseDouble(priceField.getText());
        List<String> ingredients = Arrays.asList(ingredientsField.getText().split(","));

        MenuItem menuItem = new MenuItem(name, description, preparationTime, price, ingredients);
        menuService.addMenuItem(menuItem);

        menuItems.add(menuItem);
        clearFields();
    }

    @FXML
    public void handleUpdateMenuItem(MouseEvent event) {
        MenuItem selectedItem = menuListView.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            String name = nameField.getText();
            String description = descriptionField.getText();
            int preparationTime = Integer.parseInt(preparationTimeField.getText());
            double price = Double.parseDouble(priceField.getText());
            List<String> ingredients = Arrays.asList(ingredientsField.getText().split(","));

            MenuItem updatedItem = new MenuItem(name, description, preparationTime, price, ingredients);
            menuService.updateMenuItem(selectedItem.getName(), updatedItem);

            loadMenuItems();  // Reload the menu items to reflect the update
            clearFields();
        } else {
            showAlert("No item selected", "Please select an item to update.");
        }
    }

    @FXML
    public void handleDeleteMenuItem(MouseEvent event) {
        MenuItem selectedItem = menuListView.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            menuService.deleteMenuItem(selectedItem.getName());
            menuItems.remove(selectedItem);
        } else {
            showAlert("No item selected", "Please select an item to delete.");
        }
    }

    @FXML
    public void handleMenuItemSelection(MouseEvent event) {
        MenuItem selectedItem = menuListView.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            nameField.setText(selectedItem.getName());
            descriptionField.setText(selectedItem.getDescription());
            preparationTimeField.setText(String.valueOf(selectedItem.getPreparationTime()));
            priceField.setText(String.valueOf(selectedItem.getPrice()));
            ingredientsField.setText(String.join(",", selectedItem.getIngredients()));
        }
    }

    private void clearFields() {
        nameField.clear();
        descriptionField.clear();
        preparationTimeField.clear();
        priceField.clear();
        ingredientsField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
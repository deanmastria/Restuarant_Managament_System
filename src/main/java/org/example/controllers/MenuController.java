package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.models.MenuItem;
import org.example.services.MenuService;

import java.io.IOException;
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

    @FXML
    private Button menuButton;

    @FXML
    private Button orderButton;

    @FXML
    private Button tableButton;

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
        if (validateInputs()) {
            String name = nameField.getText();
            String description = descriptionField.getText();
            int preparationTime = Integer.parseInt(preparationTimeField.getText());
            double price = Double.parseDouble(priceField.getText());
            List<String> ingredients = Arrays.asList(ingredientsField.getText().split(","));

            MenuItem menuItem = new MenuItem(name, description, preparationTime, price, ingredients);
            menuService.addMenuItem(menuItem);

            menuItems.add(menuItem);
            clearFields();
            showAlert("Success", "Menu item added successfully.");
        }
    }

    @FXML
    public void handleUpdateMenuItem(MouseEvent event) {
        MenuItem selectedItem = menuListView.getSelectionModel().getSelectedItem();

        if (selectedItem != null && validateInputs()) {
            String name = nameField.getText();
            String description = descriptionField.getText();
            int preparationTime = Integer.parseInt(preparationTimeField.getText());
            double price = Double.parseDouble(priceField.getText());
            List<String> ingredients = Arrays.asList(ingredientsField.getText().split(","));

            MenuItem updatedItem = new MenuItem(name, description, preparationTime, price, ingredients);
            menuService.updateMenuItem(selectedItem.getName(), updatedItem);

            loadMenuItems();  // Reload the menu items to reflect the update
            clearFields();
            showAlert("Success", "Menu item updated successfully.");
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
            clearFields();
            showAlert("Success", "Menu item deleted successfully.");
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

    // Navigation to Menu Management
    @FXML
    public void navigateToMenuManagement() {
        navigateToScene("/fxml/menu.fxml");
    }

    // Navigation to Order Management
    @FXML
    public void navigateToOrderManagement() {
        navigateToScene("/fxml/order.fxml");
    }

    // Navigation to Table Management
    @FXML
    public void navigateToTableManagement() {
        navigateToScene("/fxml/table.fxml");
    }

    private void navigateToScene(String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) menuButton.getScene().getWindow();  // Get the current stage
            stage.setScene(new Scene(root));  // Set the new scene
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Navigation Error", "Failed to load the requested screen.");
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

    private boolean validateInputs() {
        if (nameField.getText().isEmpty() || descriptionField.getText().isEmpty() ||
                preparationTimeField.getText().isEmpty() || priceField.getText().isEmpty() ||
                ingredientsField.getText().isEmpty()) {
            showAlert("Invalid Input", "All fields must be filled out.");
            return false;
        }

        try {
            Integer.parseInt(preparationTimeField.getText());
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Preparation time must be a valid integer.");
            return false;
        }

        try {
            Double.parseDouble(priceField.getText());
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Price must be a valid number.");
            return false;
        }

        return true;
    }
}


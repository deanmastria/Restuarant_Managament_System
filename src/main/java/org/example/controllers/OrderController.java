package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.models.Order;
import org.example.models.Table;
import org.example.models.OrderItem;
import org.example.services.OrderService;
import org.example.services.TableService;

import java.util.ArrayList;
import java.util.List;

public class OrderController {

    @FXML
    private ListView<Order> orderListView;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private ComboBox<Table> tableComboBox;

    @FXML
    private Button createOrderButton;

    @FXML
    private Button updateStatusButton;

    @FXML
    private Button deleteOrderButton;

    @FXML
    private Button assignTableButton;

    private final OrderService orderService = new OrderService();
    private final TableService tableService = new TableService();

    @FXML
    public void initialize() {
        // Populate the order list
        loadOrders();

        // Populate the status combo box
        statusComboBox.getItems().addAll("Waiting", "Preparing", "Ready", "Completed");

        // Populate the table combo box
        loadAvailableTables();

        // Add event listeners to the buttons
        createOrderButton.setOnMouseClicked(event -> handleCreateOrder());
        updateStatusButton.setOnMouseClicked(event -> handleUpdateOrderStatus());
        deleteOrderButton.setOnMouseClicked(event -> handleDeleteOrder());
        assignTableButton.setOnMouseClicked(event -> handleAssignOrderToTable());
    }

    private void loadOrders() {
        List<Order> orders = orderService.getAllOrders();
        orderListView.getItems().clear();
        orderListView.getItems().addAll(orders);
    }

    private void loadAvailableTables() {
        List<Table> tables = tableService.getAvailableTables();
        tableComboBox.getItems().clear();
        tableComboBox.getItems().addAll(tables);
    }

    @FXML
    public void handleCreateOrder() {
        // Example user ID (this should come from your user session or input)
        int userId = 1;

        // Example list of order items
        List<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem("Burger", 2));  // 2 Burgers
        items.add(new OrderItem("Fries", 1));   // 1 Fries

        // Calculate the total price based on the items
        double totalPrice = 0;
        for (OrderItem item : items) {
            totalPrice += item.getPrice() * item.getQuantity();
        }

        // Set the initial status of the order
        String initialStatus = "Waiting";

        // Create the Order object
        Order newOrder = new Order(userId, items, totalPrice, initialStatus);

        // Persist the order using the OrderService
        Order createdOrder = orderService.createOrder(newOrder);

        // Update the UI if the order was successfully created
        if (createdOrder != null) {
            orderListView.getItems().add(createdOrder); // Add the new order to the ListView
            showAlert("Order created successfully.");
        } else {
            showAlert("Failed to create order.");
        }
    }

    @FXML
    public void handleUpdateOrderStatus() {
        Order selectedOrder = orderListView.getSelectionModel().getSelectedItem();
        String selectedStatus = statusComboBox.getValue();

        if (selectedOrder != null && selectedStatus != null) {
            selectedOrder.setStatus(selectedStatus);
            orderService.updateOrderStatus(selectedOrder.getId(), selectedStatus); // Pass the correct parameters
            orderListView.refresh(); // Refresh the list view to reflect the updated status
            showAlert("Order status updated successfully.");
        } else {
            showAlert("Please select an order and status.");
        }
    }

    @FXML
    public void handleDeleteOrder() {
        Order selectedOrder = orderListView.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            orderService.deleteOrder(selectedOrder);
            orderListView.getItems().remove(selectedOrder); // Remove from the list view
            showAlert("Order deleted successfully.");
        } else {
            showAlert("Please select an order to delete.");
        }
    }

    @FXML
    public void handleAssignOrderToTable() {
        Order selectedOrder = orderListView.getSelectionModel().getSelectedItem();
        Table selectedTable = tableComboBox.getValue();

        if (selectedOrder != null && selectedTable != null) {
            selectedOrder.setTable(selectedTable.getId()); // Set the table ID, not the Table object
            tableService.assignTableToOrder(selectedTable.getId(), selectedOrder); // Pass the table ID
            showAlert("Order assigned to table successfully.");
        } else {
            showAlert("Please select an order and a table.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.models.Order;
import org.example.models.MenuItem;
import org.example.services.OrderService;
import org.example.services.MenuService;

import java.util.List;

public class OrderController {

    @FXML
    private ListView<Order> orderListView;

    @FXML
    private ComboBox<String> statusComboBox;

    private final OrderService orderService = new OrderService();
    private final MenuService menuService = new MenuService();
    private ObservableList<Order> orders;

    @FXML
    public void initialize() {
        loadOrders();
        statusComboBox.setItems(FXCollections.observableArrayList("waiting", "preparing", "ready"));
    }

    private void loadOrders() {
        List<Order> orderList = orderService.getAllOrders();
        orders = FXCollections.observableArrayList(orderList);
        orderListView.setItems(orders);
    }

    @FXML
    public void handleUpdateOrderStatus(MouseEvent event) {
        Order selectedOrder = orderListView.getSelectionModel().getSelectedItem();
        String status = statusComboBox.getValue();

        if (selectedOrder != null && status != null) {
            orderService.updateOrderStatus(selectedOrder.getId(), status);
            loadOrders();  // Reload the orders to reflect the updated status
        } else {
            showAlert("No order or status selected", "Please select an order and a status.");
        }
    }

    @FXML
    public void handleDeleteOrder(MouseEvent event) {
        Order selectedOrder = orderListView.getSelectionModel().getSelectedItem();

        if (selectedOrder != null) {
            orderService.deleteOrder(selectedOrder.getId());
            orders.remove(selectedOrder);
        } else {
            showAlert("No order selected", "Please select an order to delete.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
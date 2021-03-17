package utils;

import entities.Customer;
import entities.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;
import java.util.List;

public class TableHelper {

    public static void setCustomerTable(List<Customer> customerList,
                                          TableView<Customer> table,
                                          TableColumn<Customer, String> nameCol,
                                          TableColumn<Customer, String> phoneCol,
                                          TableColumn<Customer, String> emailCol,
                                          TableColumn<Customer, String> addressCol,
                                          TableColumn<Customer, String> typeCol) {
        table.getItems().clear();
        ObservableList<Customer> data = FXCollections.observableList(customerList);

        // Associate data with columns
        nameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        // Add item table

        table.getItems().clear();
        table.setItems(data);
    }

    public static void setOrderTable(List<Order> orderList, TableView<Order> table,
                                     TableColumn<Order, String> typeCol,
                                     TableColumn<Order, String> cusCol,
                                     TableColumn<Order, String> statusCol,
                                     TableColumn<Order, String> dateCol) {
        table.getItems().clear();
        ObservableList<Order> data = FXCollections.observableList(orderList);

        // Associate data with columns
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        cusCol.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("created_date"));

        // Add item table
        table.getItems().clear();
        table.setItems(data);
    }
}

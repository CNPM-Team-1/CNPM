package utils;

import entities.Customer;
import entities.Receipt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
/*
    public static void setReceiptTable(List<Receipt> receiptList,
                                        TableView<Receipt> contentTable,
                                        TableColumn<Receipt, Date> dateCol,
                                        TableColumn<Receipt, String> nameCol,
                                        TableColumn<Receipt, Integer> quantityCol,
                                        TableColumn<Receipt, Integer> amountCol) {
        table.getItems().clear();
        ObservableList<Receipt> data = FXCollections.observableList(receiptList);

        // Associate data with columns
        dateCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        // Add item table

        table.getItems().clear();
        table.setItems(data);
    } */
}

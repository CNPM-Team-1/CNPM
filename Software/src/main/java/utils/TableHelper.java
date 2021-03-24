package utils;

import dataModel.OrdersModel;
import entities.Orders;
import dataModel.MerchandiseModel;
import entities.Customer;
import entities.Receipt;
import entities.Merchandise;
import entities.Permissions;
import entities.Roles;
import entities.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

    public static void setEmployeeTable(List<Employee> employeeList,
                                        TableView<Employee> table,
                                        TableColumn<Employee, String> nameCol,
                                        TableColumn<Employee, String> phoneCol,
                                        TableColumn<Employee, String> emailCol,
                                        TableColumn<Employee, Date> dateOfBirthCol) {
        table.getItems().clear();
        ObservableList<Employee> data = FXCollections.observableList(employeeList);

        // Associate data with columns
        nameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<>("birthDay"));

        // Add item table
        table.getItems().clear();
        table.setItems(data);
    }

    public static void setPermissionNameTable(List<Permissions> permissionsList,
                                        TableView<Permissions> table,
                                        TableColumn<Permissions, String> nameCol) {
        table.getItems().clear();
        ObservableList<Permissions> data = FXCollections.observableList(permissionsList);

        // Associate data with columns
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Add item table
        table.getItems().clear();
        table.setItems(data);
    }

    public static void setRolesTable(List<Roles> rolesList,
                                     TableView<Roles> table,
                                     TableColumn<Roles, String> nameCol,
                                     TableColumn<Roles, Date> createdDateCol) {
        table.getItems().clear();
        ObservableList<Roles> data = FXCollections.observableList(rolesList);

        // Associate data with columns
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        createdDateCol.setCellValueFactory(new PropertyValueFactory<>("createdDate"));

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

    public static void setOrdersTable(List<OrdersModel> ordersModelList,
                                     TableView<OrdersModel> table,
                                     TableColumn<OrdersModel, Date> createdDateCol,
                                     TableColumn<OrdersModel, String> customerNameCol,
                                     TableColumn<OrdersModel, Integer> totalQuantityCol,
                                     TableColumn<OrdersModel, Integer> totalAmountCol,
                                     TableColumn<OrdersModel, String> statusCol,
                                     TableColumn<OrdersModel, String> typeCol) {
        table.getItems().clear();
        ObservableList<OrdersModel> data = FXCollections.observableList(ordersModelList);

        // Associate data with columns
        createdDateCol.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        totalQuantityCol.setCellValueFactory(new PropertyValueFactory<>("totalQuantity"));
        totalAmountCol.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("ordersType"));

        // Add item table
        table.getItems().clear();
        table.setItems(data);
    }
    
    public static void setMerchandiseTable(List<Merchandise> merchandiseList,
                                        TableView<Merchandise> table,
                                        TableColumn<Merchandise, String> nameCol,
                                        TableColumn<Merchandise, String> branchCol,
                                        TableColumn<Merchandise, Integer> priceCol) {
        table.getItems().clear();
        // Add comma for price and import price
        for (Merchandise item : merchandiseList) {
            item.setImportPrice(item.getImportPrice() != null ? NumberHelper.addComma(item.getImportPrice()) : null);
            item.setPrice(item.getPrice() != null ? NumberHelper.addComma(item.getPrice()) : null);
        }
        ObservableList<Merchandise> data = FXCollections.observableList(merchandiseList);

        // Associate data with columns
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        branchCol.setCellValueFactory(new PropertyValueFactory<>("branch"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Add item table
        table.getItems().clear();
        table.setItems(data);
    }
}

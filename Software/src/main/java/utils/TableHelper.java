package utils;

import controllers.OrderAddController;
import dataModel.OrdersAddTableModel;
import dataModel.OrdersModel;
import entities.Orders;
import dataModel.MerchandiseModel;
import entities.Customer;
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

    public static void setOrdersModelTable(List<OrdersModel> ordersModelList,
                                           TableView<OrdersModel> table,
                                           TableColumn<OrdersModel, Date> createdDateCol,
                                           TableColumn<OrdersModel, String> customerNameCol,
                                           TableColumn<OrdersModel, String> descriptionCol,
                                           TableColumn<OrdersModel, String> totalAmountCol,
                                           TableColumn<OrdersModel, String> statusCol,
                                           TableColumn<OrdersModel, String> typeCol) {
        table.getItems().clear();
        ObservableList<OrdersModel> data = FXCollections.observableList(ordersModelList);

        // Associate data with columns
        createdDateCol.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        totalAmountCol.setCellValueFactory(new PropertyValueFactory<>("sumAmount"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Add item table
        table.getItems().clear();
        table.setItems(data);
    }

    public static void setMerchandiseTable(List<Merchandise> merchandiseList,
                                           TableView<Merchandise> table,
                                           TableColumn<Merchandise, String> nameCol,
                                           TableColumn<Merchandise, String> typeCol,
                                           TableColumn<Merchandise, Integer> quantityCol,
                                           TableColumn<Merchandise, String> priceCol) {
        table.getItems().clear();
        // Add comma for price and import price
        for (Merchandise item : merchandiseList) {
            item.setImportPrice(item.getImportPrice() != null ? NumberHelper.addComma(item.getImportPrice()) : null);
            item.setPrice(item.getPrice() != null ? NumberHelper.addComma(item.getPrice()) : null);
        }
        ObservableList<Merchandise> data = FXCollections.observableList(merchandiseList);

        // Associate data with columns
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Add item table
        table.getItems().clear();
        table.setItems(data);
    }

    public static void setOrdersAddTable(List<OrdersAddTableModel> ordersAddTableModelList,
                                         TableView<OrdersAddTableModel> table,
                                         TableColumn<OrdersAddTableModel, String> merchandiseCol,
                                         TableColumn<OrdersAddTableModel, Integer> quantityCol,
                                         TableColumn<OrdersAddTableModel, String> amountCol,
                                         TableColumn<OrdersAddTableModel, String> sumAmountCol) {
        ObservableList<OrdersAddTableModel> data = FXCollections.observableList(ordersAddTableModelList);

        // Associate data with columns
        merchandiseCol.setCellValueFactory(new PropertyValueFactory<>("merchandiseName"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        sumAmountCol.setCellValueFactory(new PropertyValueFactory<>("sumAmount"));

        // Add item table
        table.setItems(data);
    }
}

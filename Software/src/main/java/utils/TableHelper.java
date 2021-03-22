package utils;

import entities.Customer;
import entities.Merchandise;
import entities.Permissions;
import entities.Roles;
import entities.Employee;
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
    public static void setMerchandiseTable(List<Merchandise> MerchandiseList,
                                        TableView<Merchandise> table,
                                        TableColumn<Merchandise, String> id,
                                        TableColumn<Merchandise, String> name,
                                        TableColumn<Merchandise, String> type,
                                        TableColumn<Merchandise, String> branch,
                                        TableColumn<Merchandise, Integer> price,
                                        TableColumn<Merchandise, Integer> importPrice) {
        table.getItems().clear();
        ObservableList<Merchandise> data = FXCollections.observableList(MerchandiseList);

        // Associate data with columns
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        branch.setCellValueFactory(new PropertyValueFactory<>("branch"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        importPrice.setCellValueFactory(new PropertyValueFactory<>("import_price"));

        // Add item table

        table.getItems().clear();
        table.setItems(data);
    }
}

package utils;

import entities.Customer;
import entities.Merchandise;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

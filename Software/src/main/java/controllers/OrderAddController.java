package controllers;

import com.jfoenix.controls.JFXButton;

import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.CustomerRepository;
import repositories.MerchandiseRepository;
import utils.*;

import javax.swing.*;
import java.net.URL;
import java.time.ZoneId;
import java.util.*;

public class OrderAddController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton close_button;

    @FXML
    private ImageView close;

    @FXML
    private Label full_name;

    @FXML
    private ComboBox<String> customerHolder;

    @FXML
    private Label phone;

    @FXML
    private TextField phoneHolder;

    @FXML
    private Label address;

    @FXML
    private TextField addressHolder;

    @FXML
    private Label address1;

    @FXML
    private ComboBox<String> merchandiseHolder;

    @FXML
    private Label address11;

    @FXML
    private TextField phoneHolder1;

    @FXML
    void close(MouseEvent event) {
        StageHelper.closeStage(event);
        // Unhide host
        AnchorPane host = MainNavigatorController.instance.getHost();
        host.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();

        // Add item to Customer Combox
        List<Customer> customerList = CustomerRepository.getAll(session);
        for (Customer item : customerList) {
            customerHolder.getItems().add(item.getFullName());
        }
        customerHolder.setValue("Chọn khách hàng");

        // Add item to Merchandise Combox
        session = factory.openSession();
        List<Merchandise> merchandiseList = MerchandiseRepository.getAll(session);
        for (Merchandise item : merchandiseList) {
            merchandiseHolder.getItems().add(item.getName());
        }
        merchandiseHolder.setValue("Chọn hàng hóa");
    }

    @FXML
    void setCustomer(ActionEvent event) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();

        List<Customer> customerList = CustomerRepository.getAll(session);

        // Get chosen customer
        String name = customerHolder.getValue();

        // Get chosen customer from customerList
        Customer chooseCustomer = customerList.stream().filter(t -> t.getFullName().equals(name)).findFirst().get();

        // Set phone/addressHolder
        phoneHolder.setText(chooseCustomer.getPhone());
        addressHolder.setText(chooseCustomer.getAddress());
    }

}

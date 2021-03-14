package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entities.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.AlertBoxHelper;
import utils.HibernateUtils;
import utils.StageHelper;
import utils.UUIDHelper;
import validation.CustomerValidation;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerAddController implements Initializable {
    @FXML
    private AnchorPane host;
    @FXML
    private Label full_name;
    @FXML
    private JFXTextField full_nameTextField;
    @FXML
    private Label email;
    @FXML
    private JFXTextField emailTextField;
    @FXML
    private Label phone;
    @FXML
    private JFXTextField phoneTextField;
    @FXML
    private Label type;
    @FXML
    private JFXComboBox<?> typeComboBox;
    @FXML
    private JFXButton addButton;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private Label address;
    @FXML
    private JFXTextField addressTextField;
    @FXML
    private ImageView close;
    @FXML
    private TextField nameHolder;
    @FXML
    private TextField emailHolder;
    @FXML
    private TextField phoneHolder;
    @FXML
    private TextField addressHolder;
    @FXML
    private ComboBox<String> typeHolder;
    @FXML
    private Label errorMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeHolder.getItems().addAll("Khách hàng", "Nhà cung cấp");
    }

    @FXML
    void save(ActionEvent event) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Customer customer = new Customer();
        customer.setId(UUIDHelper.generateType4UUID().toString());
        customer.setFullName(nameHolder.getText());
        customer.setEmail(emailHolder.getText());
        customer.setPhone(phoneHolder.getText());
        customer.setAddress(addressHolder.getText());
        customer.setType(typeHolder.getValue());

        List<String> validateInsert = CustomerValidation.validateInsert(session, customer);
        if (validateInsert.size() == 0) {
            StageHelper.closeStage(event);

            // Show alert box
            AlertBoxHelper.showMessageBox("Thêm thành công");

            // Save new customer
            session.save(customer);
            session.getTransaction().commit();

            // Refresh content table
            CustomerCategoryController.getInstance().refresh();
        } else {
            errorMessage.setText(validateInsert.get(0));
        }
        // Unhide add customer button
        JFXButton button = CustomerCategoryController.instance.getAddButton();
        button.setDisable(false);
    }

    @FXML
    void close(MouseEvent event) {
        StageHelper.closeStage(event);
        // Unhide add customer button
        JFXButton button = CustomerCategoryController.instance.getAddButton();
        button.setDisable(false);
    }
}

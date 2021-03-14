package controllers;

import com.jfoenix.controls.JFXButton;
import entities.Customer;
import holders.CustomerHolder;
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
import validation.CustomerValidation;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerUpdateController implements Initializable {
    @FXML
    private AnchorPane host;
    @FXML
    private Label full_name;
    @FXML
    private Label email;
    @FXML
    private TextField emailHolder;
    @FXML
    private TextField nameHolder;
    @FXML
    private Label phone;
    @FXML
    private Label type;
    @FXML
    private TextField phoneHolder;
    @FXML
    private ComboBox<String> typeHolder;
    @FXML
    private JFXButton updateButton;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private Label address;
    @FXML
    private ImageView close;
    @FXML
    private TextField addressHolder;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private Label errorMessage;

    // Get Customer from CustomerCategoryController select(MouseEvent event)
    CustomerHolder customerHolder = CustomerHolder.getInstance();
    Customer customer = customerHolder.getCustomer();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Add value to type Combobox
        typeHolder.getItems().addAll("Khách hàng", "Nhà cung cấp");

        // Set Customer in update window
        if (customer != null) {
            nameHolder.setText(customer.getFullName());
            emailHolder.setText(customer.getEmail());
            phoneHolder.setText(customer.getPhone());
            addressHolder.setText(customer.getAddress());
            if (customer.getType() != null) {
                typeHolder.setValue((customer.getType().equals("Khách hàng")) ? "Khách hàng" : "Nhà cung cấp");
            }
        }
    }

    @FXML
    void close(MouseEvent event) {
        // Clear customer holder
        customerHolder.setCustomer(null);
        StageHelper.closeStage(event);

        // Unhide host
        AnchorPane host = MainNavigatorController.instance.getHost();
        host.setDisable(false);
    }

    @FXML
    void update(ActionEvent event) {
        // Create session
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Customer customer = customerHolder.getCustomer();
        customer.setFullName(nameHolder.getText());
        customer.setEmail(emailHolder.getText());
        customer.setPhone(phoneHolder.getText());
        customer.setAddress(addressHolder.getText());
        customer.setType(typeHolder.getValue());

        List<String> validateUpdate = CustomerValidation.validateUpdate(session, customer);
        if (validateUpdate.size() == 0) {
            StageHelper.closeStage(event);

            // Show alert box
            AlertBoxHelper.showMessageBox("Cập nhật thành công");

            // Update customer info
            session = factory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(customer);
            session.getTransaction().commit();

            // Refresh content table
            CustomerCategoryController.getInstance().refresh();

            // Set customer holder
            customerHolder.setCustomer(customer);

            // Unhide host
            AnchorPane host = MainNavigatorController.instance.getHost();
            host.setDisable(false);
        } else {
            errorMessage.setText(validateUpdate.get(0));
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
        }
    }

    @FXML
    void delete(ActionEvent event) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        StageHelper.closeStage(event);

        // Show alert box
        AlertBoxHelper.showMessageBox("Xoá thành công");

        // Delete customer
        Customer customer = customerHolder.getCustomer();
        session.delete(customer);
        session.getTransaction().commit();

        // Refresh content table
        CustomerCategoryController.getInstance().refresh();

        // Clear customer holder
        customerHolder.setCustomer(null);

        // Unhide host
        AnchorPane host = MainNavigatorController.instance.getHost();
        host.setDisable(false);
    }
}

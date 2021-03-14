package controllers;

import com.jfoenix.controls.JFXButton;
import entities.Customer;
import holders.CustomerHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import repositories.CustomerRepository;
import utils.HibernateUtils;
import utils.StageHelper;
import utils.TableHelper;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

@Service
public class CustomerCategoryController implements Initializable {

    @FXML
    private TextField searchBar;
    @FXML
    private JFXButton searchButton;
    @FXML
    private JFXButton addButton;
    @FXML
    private TableView<Customer> contentTable;
    @FXML
    private TableColumn<Customer, String> nameCol;
    @FXML
    private TableColumn<Customer, String> phoneCol;
    @FXML
    private TableColumn<Customer, String> emailCol;
    @FXML
    private TableColumn<Customer, String> addressCol;
    @FXML
    private TableColumn<Customer, String> typeCol;

    // For other class call function from this class
    public static CustomerCategoryController instance;

    public CustomerCategoryController() {
        instance = this;
    }

    public static CustomerCategoryController getInstance() {
        return instance;
    }
    public JFXButton getAddButton() { return this.addButton; };
    ///

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();

        List<Customer> customerList = CustomerRepository.getAll(session);
        TableHelper.setCustomerTable(customerList, contentTable, nameCol, phoneCol, emailCol, addressCol, typeCol);
    }

    @FXML
    void insert(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/CustomerAdd.fxml")));
            StageHelper.startStage(root);
            // Hide add customer button
            addButton.setDisable(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    @FXML
    void search(ActionEvent event) {
        try {
            SessionFactory factory = HibernateUtils.getSessionFactory();
            Session session = factory.getCurrentSession();

            String keySearch = searchBar.getText();
            List<Customer> customerList = CustomerRepository.getByNameOrPhone(session, keySearch);
            TableHelper.setCustomerTable(customerList, contentTable, nameCol, phoneCol, emailCol, addressCol, typeCol);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    @FXML
    void select(MouseEvent event) {
        if (event.getClickCount() == 2) {
            try {
                Customer customer = contentTable.getSelectionModel().getSelectedItem();
                contentTable.getSelectionModel().clearSelection();
                // Store Customer to use in another class
                if (customer != null) {
                    CustomerHolder customerHolder = CustomerHolder.getInstance();
                    customerHolder.setCustomer(customer);
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/CustomerUpdate.fxml")));
                    StageHelper.startStage(root);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println(Arrays.toString(ex.getStackTrace()));
            }
        }
    }

    // Refresh table
    public void refresh() {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();

        List<Customer> customerList = CustomerRepository.getAll(session);
        TableHelper.setCustomerTable(customerList, contentTable, nameCol, phoneCol, emailCol, addressCol, typeCol);
    }
}
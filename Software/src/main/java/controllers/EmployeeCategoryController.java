package controllers;

import com.jfoenix.controls.JFXButton;
import entities.Customer;
import entities.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.CustomerRepository;
import repositories.EmployeeRepository;
import utils.HibernateUtils;
import utils.StageHelper;
import utils.TableHelper;

import java.net.URL;
import java.util.*;

public class EmployeeCategoryController implements Initializable {

    @FXML
    private TextField searchBar;

    @FXML
    private JFXButton searchButton;

    @FXML
    private JFXButton addButton;

    @FXML
    private TableView<Employee> contentTable;

    @FXML
    private TableColumn<Employee, String> nameCol;

    @FXML
    private TableColumn<Employee, String> phoneCol;

    @FXML
    private TableColumn<Employee, String> emailCol;

    @FXML
    private TableColumn<Employee, Date> dateOfBirthCol;

    // For other class call function from this class
    public static EmployeeCategoryController instance;

    public EmployeeCategoryController() {
        instance = this;
    }

    public static EmployeeCategoryController getInstance() {
        return instance;
    }
    ///

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();

        List<Employee> employeeList = EmployeeRepository.getAll(session);
        TableHelper.setEmployeeTable(employeeList, contentTable, nameCol, phoneCol, emailCol, dateOfBirthCol);
    }

    @FXML
    void insert(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/EmployeeAdd.fxml")));
            StageHelper.startStage(root);
            // Hide host
            AnchorPane host = MainNavigatorController.instance.getHost();
            host.setDisable(true);
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
            List<Employee> employeeList = EmployeeRepository.getByNamePhoneEmail(session, keySearch);
            TableHelper.setEmployeeTable(employeeList, contentTable, nameCol, phoneCol, emailCol, dateOfBirthCol);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    @FXML
    void select(MouseEvent event) {

    }
}

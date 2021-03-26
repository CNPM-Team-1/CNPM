package controllers;

import entities.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import repositories.EmployeeRepository;
import utils.BCryptHelper;
import utils.HibernateUtils;
import utils.StageHelper;

import java.util.Arrays;
import java.util.Objects;

public class LoginController {

    @FXML
    private Label status;
    @FXML
    private TextField userEmail;
    @FXML
    private PasswordField userPassword;
    @FXML
    private Button loginButton;
    @FXML
    private ImageView close;
    @FXML
    private ImageView minimize;

    public Employee curEmployee;

    // For other class to access this class
    public static LoginController instance;

    public LoginController() { instance = this; }

    public static LoginController getInstance() { return instance; }
    ///

    @FXML
    void close(MouseEvent mouseEvent) {
        StageHelper.closeStage(mouseEvent);
    }

    @FXML
    void minimizeWindow(MouseEvent event) {
        StageHelper.minimizeStage(event);
    }

    @FXML
    void login(ActionEvent actionEvent) {
        try {
            SessionFactory factory = HibernateUtils.getSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            if (userEmail.getText().isEmpty()) {
                status.setText("Chưa nhập email");
            } else if (userPassword.getText().isEmpty()) {
                status.setText("Chưa nhập mật khẩu");
            } else {
                // Get employee by email
                Employee employee = EmployeeRepository.getByEmail(userEmail.getText(), session);
                // Check if password is valid
                if (employee != null && BCryptHelper.check(userPassword.getText(), employee.getPassword())) {
                    // set logged in employee
                    curEmployee = employee;

                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/MainNavigator.fxml")));
                    StageHelper.closeStage(actionEvent);
                    StageHelper.startStage(root);

                } else {
                    status.setText("Sai email hoặc mật khẩu");
                }
            }
            if (session.getTransaction().getStatus() != TransactionStatus.COMMITTED) {
                session.getTransaction().commit();
            }
        } catch (Exception ex) {
            status.setText("Lỗi đăng nhập");
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    @FXML
    void enterLogin(ActionEvent actionEvent) {
        this.login(actionEvent);
    }

}

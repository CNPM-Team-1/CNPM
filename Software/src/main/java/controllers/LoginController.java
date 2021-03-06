package controllers;

import entities.Employee;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import repositories.EmployeeRepository;
import utils.HibernateUtils;

import java.util.Timer;
import java.util.TimerTask;

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
    void close() {
        Platform.exit();
    }

    @FXML
    void login() throws InterruptedException {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        if (userEmail.getText().isEmpty()) {
            status.setText("Chưa nhập email");
        } else if (userPassword.getText().isEmpty()) {
            status.setText("Chưa nhập mật khẩu");
        } else {
            Employee employee = EmployeeRepository.getByEmail(userEmail.getText(), session);
            if (employee != null && employee.getPassword().equals(userPassword.getText())) {
                System.out.println("Đăng nhập thành công");
                // TODO: change stage to menu stage
            } else {
                status.setText("Sai email hoặc mật khẩu");
            }
        }

        if (session.getTransaction().getStatus() != TransactionStatus.COMMITTED) {
            session.getTransaction().commit();
        }
    }

    @FXML
    void enterLogin(KeyEvent event) throws InterruptedException {
        if (event.getCode() == KeyCode.ENTER) {
            login();
        }
    }

}

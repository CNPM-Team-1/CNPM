package controllers;

import com.jfoenix.controls.JFXButton;
import entities.Employee;
import entities.EmployeeRoles;
import entities.Roles;
import holders.EmployeeHolder;
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
import repositories.EmployeeRolesRepository;
import repositories.RolesRepository;
import utils.AlertBoxHelper;
import utils.HibernateUtils;
import utils.StageHelper;
import utils.UUIDHelper;
import validation.EmployeeValidation;

import java.net.URL;
import java.time.ZoneId;
import java.util.*;

public class EmployeeUpdateController implements Initializable {

    @FXML
    private AnchorPane host;

    @FXML
    private TextField emailHolder;

    @FXML
    private TextField nameHolder;

    @FXML
    private ComboBox<String> roleHolder;

    @FXML
    private TextField phoneHolder;

    @FXML
    private DatePicker dateOfBirthHolder;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private ImageView close;

    @FXML
    private Label errorMessage;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private JFXButton updateButton;

    // Get Employee from EmployeeCategoryController select(MouseEvent event)
    EmployeeHolder employeeHolder = EmployeeHolder.getInstance();
    Employee employee = employeeHolder.getEmployee();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            // Add item to Roles ComboBox
            List<Roles> rolesList = RolesRepository.getAll(session);
            for (Roles item : rolesList) {
                roleHolder.getItems().add(item.getName());
            }
            // Set default value
            session = factory.openSession();
            EmployeeRoles employeeRoles = EmployeeRolesRepository.getByEmployeeId(session, employee.getId());
            roleHolder.setValue(rolesList.stream().filter(t -> t.getId().equals(employeeRoles.getRoles().getId())).findFirst().get().getName());
            // Set employee in update window
            Set zoneId = ZoneId.getAvailableZoneIds();
            if (employee != null) {
                nameHolder.setText(employee.getFullName());
                phoneHolder.setText(employee.getPhone());
                emailHolder.setText(employee.getEmail());
                // Change Date to LocalDate
                Date safeDate = new Date(employee.getBirthDay().getTime());
                dateOfBirthHolder.setValue(safeDate.toInstant().atZone(ZoneId.of("Etc/GMT-8")).toLocalDate());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    @FXML
    void close(MouseEvent event) {
        // Clear employee holder
        employeeHolder.setEmployee(null);
        StageHelper.closeStage(event);

        // Unhide host
        AnchorPane host = MainNavigatorController.instance.getHost();
        host.setDisable(false);
    }

    @FXML
    void delete(ActionEvent event) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();

        // Close stage
        StageHelper.closeStage(event);

        // Show alert box
        AlertBoxHelper.showMessageBox("Xoá thành công");

        // Delete employee_roles
        EmployeeRoles employeeRoles = EmployeeRolesRepository.getByEmployeeId(session, employee.getId());
        session = factory.openSession();
        session.beginTransaction();
        session.delete(employeeRoles);

        // Delete employee
        session.delete(employee);
        session.getTransaction().commit();

        // Refresh content table
        EmployeeCategoryController.getInstance().refresh();

        // Clear customer holder
        employeeHolder.setEmployee(null);

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

        // Get new employee
        employee.setFullName(nameHolder.getText());
        employee.setPhone(phoneHolder.getText());
        employee.setEmail(emailHolder.getText());
        employee.setBirthDay(Date.from(dateOfBirthHolder.getValue().atStartOfDay(ZoneId.of("Etc/GMT+8")).toInstant()));

        List<String> validateUpdate = EmployeeValidation.validateUpdate(session, employee);
        if (validateUpdate.size() == 0) {
            // Update employee
            session = factory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(employee);
            session.getTransaction().commit();
            // Delete then save new employee_roles
            session = factory.openSession();
            EmployeeRolesRepository.deleteByEmployeeId(session, employee.getId());

            session = factory.openSession();
            Roles roles = RolesRepository.getByName(session, roleHolder.getValue());
            EmployeeRoles employeeRoles = new EmployeeRoles();
            employeeRoles.setId(UUIDHelper.generateType4UUID().toString());
            employeeRoles.setRoles(roles);
            employeeRoles.setEmployee(employee);
            session = factory.openSession();
            session.beginTransaction();
            session.save(employeeRoles);
            session.getTransaction().commit();

            // Refresh content table
            EmployeeCategoryController.getInstance().refresh();
            // Set employee holder
            employeeHolder.setEmployee(employee);
            // Close Stage
            StageHelper.closeStage(event);
            // Show alert box
            AlertBoxHelper.showMessageBox("Cập nhật thành công");
            // Unhide host
            AnchorPane host = MainNavigatorController.instance.getHost();
            host.setDisable(false);
        } else {
            errorMessage.setText(validateUpdate.get(0));
        }
    }
}

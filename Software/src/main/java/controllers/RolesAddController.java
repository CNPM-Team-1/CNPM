package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import entities.Permissions;
import entities.Roles;
import entities.RolesDetail;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import repositories.PermissionRepository;
import utils.*;
import validation.RolesValidation;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RolesAddController implements Initializable {
    @FXML
    private AnchorPane host;
    @FXML
    private Label full_name;
    @FXML
    private TextField nameHolder;
    @FXML
    private JFXButton addButton;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private ImageView close;
    @FXML
    private Label errorMessage;
    @FXML
    private TableView<Permissions> permissionTable;
    @FXML
    private JFXListView<String> selectedPermissionList;
    @FXML
    private TableColumn<Permissions, String> nameCol;

    private List<String> selectedPermission = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        List<Permissions> permissionsList = PermissionRepository.getAll(session);
        TableHelper.setPermissionNameTable(permissionsList, permissionTable, nameCol);
    }

    @FXML
    void close(MouseEvent event) {
        StageHelper.closeStage(event);
        // Unhide host
        AnchorPane host = MainNavigatorController.instance.getHost();
        host.setDisable(false);
    }

    @FXML
    void save(ActionEvent event) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        Roles roles = new Roles();
        roles.setId(UUIDHelper.generateType4UUID().toString());
        roles.setName(nameHolder.getText());

        List<String> validateInsert = RolesValidation.validateInsert(sessionFactory, roles);
        if (validateInsert.size() == 0) {
            // Close stage
            StageHelper.closeStage(event);
            // Show alert box
            AlertBoxHelper.showMessageBox("Thêm thành công");
            // Save new roles
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(roles);
            session.getTransaction().commit();
            // Save roles_detail
            for (String item : selectedPermission) {
                session = sessionFactory.openSession();
                Permissions permissions = PermissionRepository.getByName(session, item);
                RolesDetail rolesDetail = new RolesDetail();
                rolesDetail.setId(UUIDHelper.generateType4UUID().toString());
                rolesDetail.setRoles(roles);
                rolesDetail.setPermissions(permissions);

                session = sessionFactory.openSession();
                session.beginTransaction();
                session.save(rolesDetail);
                session.getTransaction().commit();
            }
            // Refresh content table
            RolesCategoryController.getInstance().refresh();
            // Unhide host
            AnchorPane host = MainNavigatorController.instance.getHost();
            host.setDisable(false);
        } else {
            errorMessage.setText(validateInsert.get(0));
            if (session.getTransaction().getStatus() != TransactionStatus.COMMITTED) {
                session.getTransaction().commit();
            }
        }
    }

    @FXML
    void select(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Permissions permissions = permissionTable.getSelectionModel().getSelectedItem();
            permissionTable.getSelectionModel().clearSelection();

            selectedPermission.add(permissions.getName());
            // Remove duplicate items
            selectedPermission = selectedPermission.stream().distinct().collect(Collectors.toList());
            populateSelectedPermissionList();
        }
    }

    @FXML
    void removeSelected(MouseEvent event) {
        if (event.getClickCount() == 2) {
            selectedPermission.remove(selectedPermissionList.getSelectionModel().getSelectedItem());
            populateSelectedPermissionList();
        }
    }

    void populateSelectedPermissionList() {
        selectedPermissionList.getItems().clear();
        for (String item : selectedPermission) {
            selectedPermissionList.getItems().add(item);
        }
    }
}

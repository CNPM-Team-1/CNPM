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
import repositories.PermissionRepository;
import repositories.RolesRepository;
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
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();

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
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        String id = UUIDHelper.generateType4UUID().toString();
        Roles roles = new Roles();
        roles.setId(id);
        roles.setName(nameHolder.getText());

        List<String> validateInsert = RolesValidation.validateInsert(session, roles);
        if (validateInsert.size() == 0) {
            StageHelper.closeStage(event);

            // Show alert box
            AlertBoxHelper.showMessageBox("Thêm thành công");

            // Save new roles
            session.save(roles);

            // Save roles_detail
            for (String item : selectedPermission) {
                Permissions permissions = PermissionRepository.getByName(session, item);
                RolesDetail rolesDetail = new RolesDetail();
                rolesDetail.setId(UUIDHelper.generateType4UUID().toString());
                rolesDetail.setRoleId(id);
                rolesDetail.setPermissionCode(permissions.getCode());
                session.save(rolesDetail);
            }
            session.getTransaction().commit();

            // Refresh content table
            RolesCategoryController.getInstance().refresh();

            // Unhide host
            AnchorPane host = MainNavigatorController.instance.getHost();
            host.setDisable(false);
        } else {
            errorMessage.setText(validateInsert.get(0));
            session.getTransaction().commit();
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

package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import entities.Permissions;
import entities.Roles;
import entities.RolesDetail;
import holders.RolesHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.PermissionRepository;
import repositories.RolesDetailRepository;
import utils.*;
import validation.RolesValidation;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RolesUpdateController implements Initializable {
    @FXML
    private AnchorPane host;
    @FXML
    private Label full_name;
    @FXML
    private TextField nameHolder;
    @FXML
    private JFXButton updateButton;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private ImageView close;
    @FXML
    private Label errorMessage;
    @FXML
    private JFXListView<String> selectedPermissionList;
    @FXML
    private TableView<Permissions> permissionTable;
    @FXML
    private TableColumn<Permissions, String> nameCol;
    @FXML
    private JFXButton deleteButton;

    private List<String> selectedPermission = new ArrayList<>();

    // Get Roles from RolesCategoryController select(MouseEvent event)
    RolesHolder rolesHolder = RolesHolder.getInstance();
    Roles roles = rolesHolder.getRoles();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();

        // Add value to permissionTable
        List<Permissions> permissionsList = PermissionRepository.getAll(session);
        TableHelper.setPermissionNameTable(permissionsList, permissionTable, nameCol);
        // Add value to selectedPermissionList
        List<RolesDetail> rolesDetailList = RolesDetailRepository.getByRolesId(roles.getId());
        for (RolesDetail item : rolesDetailList) {
            selectedPermission.add(permissionsList.stream().filter(t -> t.getCode().equals(item.getPermissions().getCode())).findFirst().get().getName());
        }
        populateSelectedPermissionList();
        // Set Roles name
        nameHolder.setText(roles.getName());
    }

    @FXML
    void close(MouseEvent event) {
        // clear rolesHolder
        rolesHolder.setRoles(null);
        StageHelper.closeStage(event);

        // Unhide host
        AnchorPane host = MainNavigatorController.instance.getHost();
        host.setDisable(false);
    }

    @FXML
    void delete(ActionEvent event) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session;

        if (RolesValidation.validateDelete(factory, roles).size() == 0) {
            // Delete RolesDetail
            session = factory.openSession();
            RolesDetailRepository.deleteByRoleId(session, roles.getId());
            // Delete Roles
            session = factory.openSession();
            session.beginTransaction();
            session.delete(roles);
            session.getTransaction().commit();

            // Refresh content table
            RolesCategoryController.getInstance().refresh();
            // clear rolesHolder
            rolesHolder.setRoles(null);
            // Close stage
            StageHelper.closeStage(event);
            // Show alert box
            AlertBoxHelper.showMessageBox("Xoá thành công");
            // Unhide host
            AnchorPane host = MainNavigatorController.instance.getHost();
            host.setDisable(false);
        } else {
            errorMessage.setText(RolesValidation.validateDelete(factory, roles).get(0));
        }

    }

    @FXML
    void removeSelected(MouseEvent event) {
        if (event.getClickCount() == 2) {
            selectedPermission.remove(selectedPermissionList.getSelectionModel().getSelectedItem());
            populateSelectedPermissionList();
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
    void update(ActionEvent event) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();

        Roles roles = rolesHolder.getRoles();
        roles.setName(nameHolder.getText());
        List<String> validateUpdate = RolesValidation.validateUpdate(factory, roles);
        if (validateUpdate.size() == 0) {
            // Update Roles Info
            session = factory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(roles);
            session.getTransaction().commit();
            // Update Roles Detail
            session = factory.openSession();
            RolesDetailRepository.deleteByRoleId(session, roles.getId());
            for (String item : selectedPermission){
                session = factory.openSession();
                Permissions permissions = PermissionRepository.getByName(session, item);

                RolesDetail rolesDetail = new RolesDetail();
                rolesDetail.setId(UUIDHelper.generateType4UUID().toString());
                rolesDetail.setRoles(roles);
                rolesDetail.setPermissions(permissions);
                session = factory.openSession();
                session.beginTransaction();
                session.save(rolesDetail);
                session.getTransaction().commit();
            }
            // Close stage
            StageHelper.closeStage(event);
            // Show alert box
            AlertBoxHelper.showMessageBox("Cập nhật thành công");
            // Refresh content table
            RolesCategoryController.getInstance().refresh();
            // Set roles holder
            rolesHolder.setRoles(roles);
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

    void populateSelectedPermissionList() {
        selectedPermissionList.getItems().clear();
        for (String item : selectedPermission) {
            selectedPermissionList.getItems().add(item);
        }
    }
}

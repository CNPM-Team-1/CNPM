package controllers;

import com.jfoenix.controls.JFXButton;
import entities.Roles;
import holders.RolesHolder;
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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.RolesRepository;
import utils.HibernateUtils;
import utils.StageHelper;
import utils.TableHelper;

import java.net.URL;
import java.util.*;

public class RolesCategoryController implements Initializable {

    @FXML
    private TextField searchBar;

    @FXML
    private JFXButton searchButton;

    @FXML
    private JFXButton addButton;

    @FXML
    private TableView<Roles> contentTable;

    @FXML
    private TableColumn<Roles, String> nameCol;

    @FXML
    private TableColumn<Roles, Date> createdDateCol;

    // For other class call function from this class
    public static RolesCategoryController instance;

    public RolesCategoryController() {
        instance = this;
    }

    public static RolesCategoryController getInstance() {
        return instance;
    }
    ///

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();

        List<Roles> rolesList = RolesRepository.getAll(session);
        TableHelper.setRolesTable(rolesList, contentTable, nameCol, createdDateCol);
    }

    @FXML
    void insert(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/RolesAdd.fxml")));
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

            session.beginTransaction();
            List<Roles> rolesList = RolesRepository.getLikeName(session, searchBar.getText());
            TableHelper.setRolesTable(rolesList, contentTable, nameCol, createdDateCol);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    @FXML
    void select(MouseEvent event) {
        if (event.getClickCount() == 2) {
            try {
                Roles roles = contentTable.getSelectionModel().getSelectedItem();
                contentTable.getSelectionModel().clearSelection();
                // Store Roles to use in another class
                if (roles != null) {
                    RolesHolder rolesHolder = RolesHolder.getInstance();
                    rolesHolder.setRoles(roles);
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/RolesUpdate.fxml")));
                    StageHelper.startStage(root);
                    // Hide host
                    AnchorPane host = MainNavigatorController.instance.getHost();
                    host.setDisable(true);
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

        List<Roles> rolesList = RolesRepository.getAll(session);
        TableHelper.setRolesTable(rolesList, contentTable, nameCol, createdDateCol);
    }
}

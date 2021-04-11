package controllers;

import com.jfoenix.controls.JFXButton;
import entities.WorkShift;
import holders.WorkShiftHolder;
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
import org.hibernate.SessionFactory;
import repositories.WorkShiftRepository;
import utils.HibernateUtils;
import utils.StageHelper;
import utils.TableHelper;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class WorkShiftCategoryController implements Initializable {
    @FXML
    private AnchorPane host;
    @FXML
    private TextField searchBar;
    @FXML
    private JFXButton searchButton;
    @FXML
    private JFXButton workTableButton;
    @FXML
    private JFXButton addButton;

    @FXML
    private TableView<WorkShift> contentTable;
    @FXML
    private TableColumn<WorkShift, String> dateCol;
    @FXML
    private TableColumn<WorkShift, String> nameCol;
    @FXML
    private TableColumn<WorkShift, String> timeInCol;
    @FXML
    private TableColumn<WorkShift, String> timeOutCol;

    // For other class to call function from this class
    public static WorkShiftCategoryController instance;
    public WorkShiftCategoryController() { instance = this; }
    public static WorkShiftCategoryController getInstance() { return instance; }
    ////

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

        List<WorkShift> allWorkShift = WorkShiftRepository.getAll(sessionFactory);
        TableHelper.setWorkShiftTable(allWorkShift, contentTable, dateCol, nameCol, timeInCol, timeOutCol);
    }

    @FXML
    void openWorkTable(ActionEvent event) {
        MainNavigatorController.getInstance().openTimeTable(null);
    }

    @FXML
    void save(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/WorkShiftAdd.fxml")));
            StageHelper.startStage(root);
            //Hide host
            AnchorPane host = MainNavigatorController.instance.getHost();
            host.setDisable(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void search(ActionEvent event) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

        List<WorkShift> filteredWorkShift = WorkShiftRepository.getLikeName(sessionFactory, searchBar.getText());
        TableHelper.setWorkShiftTable(filteredWorkShift, contentTable, dateCol, nameCol, timeInCol, timeOutCol);
    }

    @FXML
    void select(MouseEvent event) {
        if (event.getClickCount() == 2) {
            try {
                WorkShift chosenWorkShift = contentTable.getSelectionModel().getSelectedItem();
                contentTable.getSelectionModel().clearSelection();
                // Store WorkShift to use in another class
                if (chosenWorkShift != null) {
                    WorkShiftHolder workShiftHolder = WorkShiftHolder.getInstance();
                    workShiftHolder.setWorkShift(chosenWorkShift);
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/WorkShiftUpdate.fxml")));
                    StageHelper.startStage(root);
                    // Hide host
                    MainNavigatorController.instance.getHost().setDisable(true);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    void requestFocus(MouseEvent event) {
        host.requestFocus();
    }
}

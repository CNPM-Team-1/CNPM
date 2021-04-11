package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import utils.StageHelper;

import java.net.URL;
import java.util.*;

public class MainNavigatorController implements Initializable {

    static List<AnchorPane> grid = new ArrayList<>();

    @FXML
    private AnchorPane Host;
    @FXML
    private JFXButton orderButton;
    @FXML
    private JFXButton customerButton;
    @FXML
    private JFXButton receiptButton;
    @FXML
    private JFXButton employeeButton;
    @FXML
    private JFXButton merchandiseButton;
    @FXML
    private JFXButton statisticButton;
    @FXML
    private JFXButton rolesButton;
    @FXML
    private JFXButton timeTableButton;
    @FXML
    private ImageView close;
    @FXML
    private ImageView minimize;
    @FXML
    private AnchorPane contentPanel;
    @FXML
    private VBox leftNav;

    //  For other class call function from this class
    public static MainNavigatorController instance;
    public MainNavigatorController() { instance = this; }
    public static MainNavigatorController getInstance() { return instance; }
    public AnchorPane getHost() { return this.Host; }
    //////////////////

    @FXML
    void close(MouseEvent mouseEvent) {
        StageHelper.closeStage(mouseEvent);
    }

    @FXML
    void minimizeWindow(MouseEvent event) {
        StageHelper.minimizeStage(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            grid.add(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/OrderCategory.fxml"))));
            grid.add(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/CustomerCategory.fxml"))));
            grid.add(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/ReceiptCategory.fxml"))));
            grid.add(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/EmployeeCategory.fxml"))));
            grid.add(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/MerchandiseCategory.fxml"))));
            grid.add(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/Statistic.fxml"))));
            grid.add(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/RolesCategory.fxml"))));
            grid.add(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/ImportsCategory.fxml"))));
            grid.add(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/WorkTableCategory.fxml"))));
            grid.add(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/WorkShiftCategory.fxml"))));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    @FXML
    void openOrderCategory(ActionEvent actionEvent) {
        contentPanel.getChildren().clear();
        contentPanel.getChildren().add(grid.get(0));
    }

    @FXML
    void openCustomerCategory(ActionEvent event) {
        contentPanel.getChildren().clear();
        contentPanel.getChildren().add(grid.get(1));
    }

    @FXML
    void openReceiptCategory(ActionEvent event) {
        contentPanel.getChildren().clear();
        contentPanel.getChildren().add(grid.get(2));
    }

    @FXML
    void openEmployeeCategory(ActionEvent event) {
        contentPanel.getChildren().clear();
        contentPanel.getChildren().add(grid.get(3));
    }

    @FXML
    void openMerchandiseCategory(ActionEvent event) {
        contentPanel.getChildren().clear();
        contentPanel.getChildren().add(grid.get(4));
    }

    @FXML
    void openStatistic(ActionEvent event) {
        contentPanel.getChildren().clear();
        contentPanel.getChildren().add(grid.get(5));
    }

    @FXML
    void openRolesCategory(ActionEvent event) {
        contentPanel.getChildren().clear();
        contentPanel.getChildren().add(grid.get(6));
    }

    @FXML
    void openImportsCategory(ActionEvent event) {
        contentPanel.getChildren().clear();
        contentPanel.getChildren().add(grid.get(7));
    }

    @FXML
    void openTimeTable(ActionEvent event) {
        contentPanel.getChildren().clear();
        contentPanel.getChildren().add(grid.get(8));
    }

    void openShiftTable() {
        contentPanel.getChildren().clear();
        contentPanel.getChildren().add(grid.get(9));
    }
}

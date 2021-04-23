package controllers;

import com.jfoenix.controls.JFXButton;
import entities.Employee;
import entities.Permissions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import repositories.PermissionRepository;
import utils.StageHelper;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

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
    private JFXButton importsButton;
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
            this.hideInaccessibleFeatures();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    private void hideInaccessibleFeatures() {
        Employee currentEmployee = new Employee(LoginController.getInstance().curEmployee);
        List<String> curEmployeePermissions = PermissionRepository.getEmployeePermissions(currentEmployee.getId());
        List<Permissions> allPermissions = PermissionRepository.getAll();
        if (curEmployeePermissions != null && allPermissions != null) {
            for (String permissionCode : curEmployeePermissions) {
                allPermissions.removeIf(t -> t.getCode().equals(permissionCode));
            }

            if (allPermissions.size() > 0) {
                for (Permissions item : allPermissions) {
                    switch (item.getCode()) {
                        case "CUSTOMER_MANAGEMENT" -> leftNav.getChildren().remove(customerButton);
                        case "EMPLOYEE_MANAGEMENT" -> leftNav.getChildren().remove(employeeButton);
                        case "IMPORT_MANAGEMENT" -> leftNav.getChildren().remove(importsButton);
                        case "MERCHANDISE_MANAGEMENT" -> leftNav.getChildren().remove(merchandiseButton);
                        case "ORDER_MANAGEMENT" -> leftNav.getChildren().remove(orderButton);
                        case "RECEIPT_MANAGEMENT" -> leftNav.getChildren().remove(receiptButton);
                        case "ROLES_MANAGEMENT" -> leftNav.getChildren().remove(rolesButton);
                        case "STATISTIC" -> leftNav.getChildren().remove(statisticButton);
                        case "WORK_SHIFT_MANAGEMENT" -> leftNav.getChildren().remove(timeTableButton);
                    }
                }
            }
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

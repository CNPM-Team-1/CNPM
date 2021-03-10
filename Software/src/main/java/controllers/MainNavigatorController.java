package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import utils.StageHelper;

import java.net.URL;
import java.util.*;

public class MainNavigatorController implements Initializable {

    static List<AnchorPane> grid = new ArrayList<>();
    static int idx_cur = 0;

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
    private ImageView close;

    @FXML
    private AnchorPane contentPanel;

    @FXML
    void close(MouseEvent mouseEvent) {
        StageHelper.closeStage(mouseEvent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            grid.add((AnchorPane) FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/OrderCategory.fxml"))));
            grid.add((AnchorPane) FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/CustomerCategory.fxml"))));
            grid.add((AnchorPane) FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/ReceiptCategory.fxml"))));
            grid.add((AnchorPane) FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/EmployeeCategory.fxml"))));
            grid.add((AnchorPane) FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/MerchandiseCategory.fxml"))));
            grid.add((AnchorPane) FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/Statistic.fxml"))));
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

}

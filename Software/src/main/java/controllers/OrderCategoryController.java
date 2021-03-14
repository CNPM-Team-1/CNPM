package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import utils.StageHelper;

public class OrderCategoryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton button_add;

    @FXML
    private JFXButton button_update;

    @FXML
    private JFXTextField search_textfield;

    @FXML
    void addOrder(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/OrderAdd.fxml")));
            StageHelper.startStage(root);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    @FXML
    void search(ActionEvent event) {

    }
}

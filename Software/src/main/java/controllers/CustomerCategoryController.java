package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import utils.StageHelper;

import java.util.Arrays;
import java.util.Objects;

public class CustomerCategoryController {

    @FXML
    private TextField searchBar;

    @FXML
    private JFXButton searchButton;

    @FXML
    private JFXButton addButton;

    @FXML
    void addButtonActionListener(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/CustomerAdd.fxml")));
            StageHelper.startStage(root);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    @FXML
    void searchButtonActionListener(ActionEvent event) {

    }

}

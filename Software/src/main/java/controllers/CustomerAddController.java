package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import utils.StageHelper;

public class CustomerAddController {

    @FXML
    private Label full_name;

    @FXML
    private JFXTextField full_nameTextField;

    @FXML
    private Label email;

    @FXML
    private JFXTextField emailTextField;

    @FXML
    private Label phone;

    @FXML
    private JFXTextField phoneTextField;

    @FXML
    private Label type;

    @FXML
    private JFXComboBox<?> typeComboBox;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private Label address;

    @FXML
    private JFXTextField addressTextField;

    @FXML
    private ImageView close;

    @FXML
    void addButtonController(ActionEvent event) {

    }

    @FXML
    void cancelButtonActionListener(ActionEvent event) {

    }

    @FXML
    void close(MouseEvent mouseEvent) {
        StageHelper.closeStage(mouseEvent);
    }

}

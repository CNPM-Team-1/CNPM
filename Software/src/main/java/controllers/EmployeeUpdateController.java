package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import utils.StageHelper;

public class EmployeeUpdateController {

    @FXML
    private AnchorPane host;

    @FXML
    private TextField emailHolder;

    @FXML
    private TextField nameHolder;

    @FXML
    private ComboBox<?> roleHolder;

    @FXML
    private TextField phoneHolder;

    @FXML
    private DatePicker dateOfBirthHolder;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private ImageView close;

    @FXML
    private Label errorMessage;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private JFXButton updateButton;

    @FXML
    void close(MouseEvent event) {
        StageHelper.closeStage(event);
    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {

    }

}

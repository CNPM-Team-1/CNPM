package controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class OrderUpdateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton close_button;

    @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }
}

package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class OrderCategoryController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton add_order_button;

    @FXML
    private JFXButton update_order_button;

    @FXML
    private JFXTextField search_textfield;

    @FXML
    void add_order(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }

    @FXML
    void update_order(ActionEvent event) {

    }

    @FXML
    public void initialize() {
        assert add_order_button != null : "fx:id=\"add_order_button\" was not injected: check your FXML file 'OrderCategory.fxml'.";
        assert update_order_button != null : "fx:id=\"update_order_button\" was not injected: check your FXML file 'OrderCategory.fxml'.";
        assert search_textfield != null : "fx:id=\"search_textfield\" was not injected: check your FXML file 'OrderCategory.fxml'.";

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

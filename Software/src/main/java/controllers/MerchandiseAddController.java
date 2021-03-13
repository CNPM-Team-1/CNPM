package controllers;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;

public class MerchandiseAddController extends JFrame {

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private Label idLB;

    @FXML
    private Label typeLB;

    @FXML
    private Label priceLB;

    @FXML
    private Label imLB;

    @FXML
    private Label nameLB;

    @FXML
    private Label branchLB;

    @FXML
    private TextField idTF;

    @FXML
    private TextField branchTF;

    @FXML
    private TextField nameTF;

    @FXML
    private TextField priceTF;

    @FXML
    private TextField importPriceTF;

    @FXML
    private TextField typeTF;

    @FXML
    void Add(ActionEvent event) {

    }

    @FXML
    void Cancel(ActionEvent event) {

    }

}

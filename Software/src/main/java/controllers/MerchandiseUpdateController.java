package controllers;

import antlr.ASTNULLType;
import com.jfoenix.controls.JFXButton;
import entities.Merchandise;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utils.StageHelper;

import java.sql.*;


public class MerchandiseUpdateController {


    @FXML
    private JFXButton UpdateButton;

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
    void Cancel(ActionEvent event) {
        StageHelper.closeStage(event);
    }

    @FXML
    void Update(ActionEvent event) {

        final String DB_URL = "jdbc:mysql://localhost:8889/cnpm-cellphones";
        String username = "root";
        String password = "root";
        try {
            // Create a connection to the database.
            Connection conn = DriverManager.getConnection(DB_URL, username, password);
            Statement stmt = conn.createStatement();
            String sqlStatement = "UPDATE MERCHANDISE set id = '" + idTF.getText() + "'," + "name = '" + nameTF.getText() + "',"
                    + "type = '" + typeTF.getText() + "'," + " branch = '" + branchTF.getText() + "'," + "price =" + priceTF.getText()
                    + ",import_price =" + importPriceTF.getText() + " where id = '" + idTF.getText() + "';";
            System.out.println(sqlStatement);
            stmt.executeUpdate(sqlStatement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
        @FXML
    public void setTextMerchandise(Merchandise m){
        idTF.setText(m.getId());
        nameTF.setText(m.getName());
        typeTF.setText(m.getType());
        branchTF.setText(m.getBranch());
        priceTF.setText(String.valueOf(m.getPrice()));
        importPriceTF.setText(String.valueOf(m.getImportPrice()));
    }


}
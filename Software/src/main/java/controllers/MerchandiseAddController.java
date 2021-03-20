package controllers;


import com.jfoenix.controls.JFXButton;
import entities.Merchandise;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.hibernate.Session;
import utils.HibernateUtils;
import utils.StageHelper;

import javax.swing.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MerchandiseAddController extends JFrame  {

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
    private Connection conn;

    @FXML
    void Add(ActionEvent event) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        System.out.println(formatter.format(date));
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        //Add new Employee object
        Merchandise emp = new Merchandise();
        emp.setId(idTF.getText());
        emp.setName(nameTF.getText());
        emp.setType(typeTF.getText());
        emp.setBranch(branchTF.getText());
        emp.setPrice(Integer.parseInt(priceTF.getText()));
        emp.setImportPrice(Integer.parseInt(importPriceTF.getText()));
        emp.setCreatedDate(date);
        emp.setUpdatedDate(date);
        session.save(emp);
        session.getTransaction().commit();
    }
    @FXML
    void Cancel(ActionEvent event) {
        StageHelper.closeStage(event);
    }
}

package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Customer;
import entities.Orders;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.OrderRepository;
import utils.HibernateUtils;
import utils.StageHelper;
import utils.TableHelper;

import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;

public class OrderCategoryController implements Initializable {

    @FXML
    private TextField search_Bar;

    @FXML
    private JFXButton searchButton;

    @FXML
    private JFXButton addButton;

    @FXML
    private TableView<Orders> contentTable;

    @FXML
    private TableColumn<Orders, String> dateCol;

    @FXML
    private TableColumn<Orders, String> cusCol;

    @FXML
    private TableColumn<Orders, String> quantityCol;

    @FXML
    private TableColumn<Orders, String> amountCol;

    @FXML
    private TableColumn<Orders, String> statusCol;

    @FXML
    private TableColumn<Orders, String> typeCol;

    // For other class cal function from this class
    public static OrderCategoryController instance;

    public OrderCategoryController() {
        instance = this;
    }

    public static OrderCategoryController getInstance() {
        return instance;
    }
    ///

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();

        List<Orders> orderList = OrderRepository.getAll(session);
        TableHelper.setOrderTable(orderList, contentTable, statusCol, typeCol, cusCol, dateCol);
    }

    @FXML
    void addOrder(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/OrderAdd.fxml")));
            StageHelper.startStage(root);
            //Hide host
            AnchorPane host = MainNavigatorController.instance.getHost();
            host.setDisable(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

//    @FXML
//    void search(ActionEvent event) {
//        try {
//            SessionFactory factory = HibernateUtils.getSessionFactory();
//            Session session = factory.getCurrentSession();
//
//            String keySearch = search_Bar.getText();
//            List<Orders> ordersList = OrderRepository.getByCustomerId(session, keySearch);
//            TableHelper.setOrderTable(ordersList, contentTable, statusCol, typeCol, cusCol, dateCol);
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            System.out.println(Arrays.toString(ex.getStackTrace()));
//        }
//    }

}

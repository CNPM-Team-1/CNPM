package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Order;
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

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class OrderCategoryController implements Initializable {

    @FXML
    private JFXButton button_add;
    @FXML
    private JFXButton button_update;
    @FXML
    private JFXTextField search_textfield;
    @FXML
    private TableView<Order> contentTable;
    @FXML
    private TableColumn<Order, String> dateCol;
    @FXML
    private TableColumn<Order, String> cusCol;
    @FXML
    private TableColumn<Order, String> quantityCol;
    @FXML
    private TableColumn<Order, String> amountCol;
    @FXML
    private TableColumn<Order, String> statusCol;
    @FXML
    private TableColumn<Order, String> typeCol;

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

        List<Order> orderList = OrderRepository.getAll(session);
        TableHelper.setOrderTable(orderList, contentTable, typeCol, cusCol, statusCol, dateCol);
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

    @FXML
    void search(ActionEvent event) {

    }

}

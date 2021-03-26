package controllers;

import com.jfoenix.controls.JFXButton;
import dataModel.OrdersModel;
import entities.Orders;
import entities.OrdersDetail;
import holders.OrdersHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.OrdersDetailRepository;
import repositories.OrdersRepository;
import utils.HibernateUtils;
import utils.NumberHelper;
import utils.StageHelper;
import utils.TableHelper;

import java.net.URL;
import java.util.*;

public class OrderCategoryController implements Initializable {
    @FXML
    private AnchorPane host;

    @FXML
    private TableView<OrdersModel> contentTable;
    @FXML
    private TableColumn<OrdersModel, Date> createdDateCol;
    @FXML
    private TableColumn<OrdersModel, String> customerNameCol;
    @FXML
    private TableColumn<OrdersModel, String> descriptionCol;
    @FXML
    private TableColumn<OrdersModel, String> totalAmountCol;
    @FXML
    private TableColumn<OrdersModel, String> statusCol;
    @FXML
    private TableColumn<OrdersModel, String> typeCol;

    public Boolean ordersAddUpdateIsShow = false;

    @FXML
    private TextField searchBar;
    @FXML
    private JFXButton searchButton;
    @FXML
    private JFXButton addButton;

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
        Session session = factory.openSession();

        List<Orders> ordersList = OrdersRepository.getAll(session);
        List<OrdersModel> ordersModelList = new ArrayList<>();
        for (Orders item : ordersList) {
            session = factory.openSession();
            List<OrdersDetail> ordersDetailList = OrdersDetailRepository.getByOrdersId(session, item.getId());

            OrdersModel ordersModel = new OrdersModel();
            ordersModel.setCreatedDate(item.getCreatedDate());
            ordersModel.setCustomerName(item.getCustomer().getFullName());
            ordersModel.setDescription(item.getDescription());
            ordersModel.setSumAmount(NumberHelper.addComma(String.valueOf(ordersDetailList.stream().mapToInt(OrdersDetail::getAmount).sum())));
            ordersModel.setStatus(item.getStatus());
            ordersModel.setType(item.getType());
            ordersModel.setOrders(item);

            ordersModelList.add(ordersModel);
        }
        // Populate table
        TableHelper.setOrdersModelTable(ordersModelList, contentTable, createdDateCol, customerNameCol, descriptionCol, totalAmountCol, statusCol, typeCol);
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
        try {
            SessionFactory factory = HibernateUtils.getSessionFactory();
            Session session = factory.getCurrentSession();

            String keySearch = searchBar.getText();
            List<Orders> ordersList = OrdersRepository.getLikeCustomerName(session, keySearch);
            List<OrdersModel> ordersModelList = new ArrayList<>();
            for (Orders item : ordersList) {
                session = factory.openSession();
                List<OrdersDetail> ordersDetailList = OrdersDetailRepository.getByOrdersId(session, item.getId());

                OrdersModel ordersModel = new OrdersModel();
                ordersModel.setCreatedDate(item.getCreatedDate());
                ordersModel.setCustomerName(item.getCustomer().getFullName());
                ordersModel.setDescription(item.getDescription());
                ordersModel.setSumAmount(NumberHelper.addComma(String.valueOf(ordersDetailList.stream().mapToInt(OrdersDetail::getAmount).sum())));
                ordersModel.setStatus(item.getStatus());
                ordersModel.setType(item.getType());
                ordersModel.setOrders(item);

                ordersModelList.add(ordersModel);
            }
            // Populate table
            TableHelper.setOrdersModelTable(ordersModelList, contentTable, createdDateCol, customerNameCol, descriptionCol, totalAmountCol, statusCol, typeCol);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    @FXML
    void select(MouseEvent event) {
        if (event.getClickCount() == 2) {
            try {
                Orders orders = contentTable.getSelectionModel().getSelectedItem().getOrders();
                contentTable.getSelectionModel().clearSelection();
                // Store Orders to use in another class
                if (orders != null) {
                    OrdersHolder ordersHolder = OrdersHolder.getInstance();
                    ordersHolder.setOrders(orders);
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/OrderUpdate.fxml")));
                    StageHelper.startStage(root);
                    // Hide host
                    AnchorPane host = MainNavigatorController.instance.getHost();
                    host.setDisable(true);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println(Arrays.toString(ex.getStackTrace()));
            }
        }
    }

    @FXML
    void requestFocus(MouseEvent event) {
        host.requestFocus();
    }

    void refresh() {
        this.initialize(null, null);
    }
}

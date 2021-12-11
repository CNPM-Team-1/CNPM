package controllers;

import dataModel.OrdersModel;
import dataModel.search.OrderSearchModel;
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

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class OrderCategoryController implements Initializable {
    @FXML
    private AnchorPane host;
    @FXML
    private TextField searchBar;

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

    public static String searchCustomerName;
    public static Date searchFromDate;
    public static Date searchToDate;
    public static String searchType;
    public static String searchStatus;

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
        List<Orders> ordersList = OrdersRepository.getAll();
        List<OrdersModel> ordersModelList = new ArrayList<>();
        for (Orders item : ordersList) {
            List<OrdersDetail> ordersDetailList = OrdersDetailRepository.getByOrdersId(item.getId());

            OrdersModel ordersModel = new OrdersModel();
            ordersModel.setCreatedDate(item.getCreatedDate());
            ordersModel.setCustomerName(item.getCustomer().getFullName());
            ordersModel.setDescription(item.getDescription());
            ordersModel.setSumAmount(NumberHelper.addComma(String.valueOf(ordersDetailList.stream().mapToLong(OrdersDetail::getAmount).sum())));
            ordersModel.setStatus(item.getStatus());
            ordersModel.setType(item.getType());
            ordersModel.setOrders(item);
            ordersModelList.add(ordersModel);
        }
        // Populate table
        TableHelper.setOrdersModelTable(ordersModelList, contentTable, createdDateCol, customerNameCol, descriptionCol, totalAmountCol, statusCol, typeCol);
    }

    @FXML
    void openAdvanceSearch(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/OrderAdvanceSearch.fxml")));
            StageHelper.startStage(root);
            // HIDE HOST
            MainNavigatorController.instance.getHost().setDisable(true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    @FXML
    void addOrder(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/OrderAdd.fxml")));
        StageHelper.startStage(root);
        //Hide host
        MainNavigatorController.instance.getHost().setDisable(true);
    }

    @FXML
    void select(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) {
            Orders orders = contentTable.getSelectionModel().getSelectedItem().getOrders();
            contentTable.getSelectionModel().clearSelection();
            // Store Orders to use in another class
            if (orders != null) {
                OrdersHolder.getInstance().setOrders(orders);
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/OrderUpdate.fxml")));
                StageHelper.startStage(root);
                // Hide host
                MainNavigatorController.instance.getHost().setDisable(true);
            }
        }
    }

    @FXML
    void refresh(ActionEvent event) {
        searchCustomerName = null;
        searchFromDate = null;
        searchToDate = null;
        searchType = null;
        searchStatus = null;

        this.initialize(null, null);
    }

    public void applyFilter(List<Orders> filterResult, OrderSearchModel searchModel) {
        if (filterResult != null) {
            searchCustomerName = searchModel.getCustomerName();
            searchFromDate = searchModel.getFromDate();
            searchToDate = searchModel.getToDate();
            searchType = searchModel.getOrderType();
            searchStatus = searchModel.getOrderStatus();

            List<OrdersModel> ordersModelList = new ArrayList<>();
            for (Orders item : filterResult) {
                List<OrdersDetail> ordersDetailList = OrdersDetailRepository.getByOrdersId(item.getId());

                OrdersModel ordersModel = new OrdersModel();
                ordersModel.setCreatedDate(item.getCreatedDate());
                ordersModel.setCustomerName(item.getCustomer().getFullName());
                ordersModel.setDescription(item.getDescription());
                ordersModel.setSumAmount(NumberHelper.addComma(String.valueOf(ordersDetailList.stream().mapToLong(OrdersDetail::getAmount).sum())));
                ordersModel.setStatus(item.getStatus());
                ordersModel.setType(item.getType());
                ordersModel.setOrders(item);
                ordersModelList.add(ordersModel);
            }
            // Populate table
            TableHelper.setOrdersModelTable(ordersModelList, contentTable, createdDateCol, customerNameCol, descriptionCol, totalAmountCol, statusCol, typeCol);
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

package controllers;

import entities.Customer;
import entities.Orders;
import entities.OrdersDetail;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import dataModel.OrdersModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.CustomerRepository;
import repositories.OrdersDetailRepository;
import repositories.OrdersRepository;
import utils.HibernateUtils;
import utils.StageHelper;
import utils.TableHelper;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class OrderCategoryController implements Initializable {

    @FXML
    private TableView<OrdersModel> contentTable;
    @FXML
    private TableColumn<OrdersModel, Date> createdDateCol;
    @FXML
    private TableColumn<OrdersModel, String> customerNameCol;
    @FXML
    private TableColumn<OrdersModel, Integer> totalQuantityCol;
    @FXML
    private TableColumn<OrdersModel, Integer> totalAmountCol;
    @FXML
    private TableColumn<OrdersModel, String> statusCol;
    @FXML
    private TableColumn<OrdersModel, String> typeCol;

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

        // get all orders and orders detail and customer
        List<Orders> ordersList = OrdersRepository.getAll(session);
        session = factory.openSession();
        List<OrdersDetail>  ordersDetailList = OrdersDetailRepository.getAll(session);
        session = factory.openSession();
        List<Customer> customerList = CustomerRepository.getAll(session);

        // Check isNull
        assert ordersList != null : "Orders list is empty";
        assert ordersDetailList != null : "Orders Detail list is empty";
        assert customerList != null : "Customer list is empty";

        // Set orders model
        List<OrdersModel> ordersModelList = new ArrayList<>();
        for (Orders item : ordersList) {
            List<OrdersDetail> ordersDetails = ordersDetailList.stream().filter(t -> t.getOrderId().equals(item.getId())).collect(Collectors.toList());
            Customer ordersCustomer = customerList.stream().filter(t -> t.getId().equals(item.getCustomerId())).findAny().get();
            Integer sumQuantity = ordersDetails.stream().mapToInt(OrdersDetail::getQuantity).sum();
            Integer sumAmount = ordersDetails.stream().mapToInt(OrdersDetail::getAmount).sum();

            OrdersModel ordersModel = new OrdersModel();
            ordersModel.setCreatedDate(item.getCreatedDate());
            ordersModel.setCustomerName(ordersCustomer.getFullName());
            ordersModel.setTotalQuantity(sumQuantity);
            ordersModel.setTotalAmount(sumAmount);
            ordersModel.setStatus(item.getStatus());
            ordersModel.setOrdersType(item.getType());
            ordersModelList.add(ordersModel);
        }

        // Populate table
        TableHelper.setOrdersTable(ordersModelList, contentTable, createdDateCol, customerNameCol, totalQuantityCol, totalAmountCol, statusCol, typeCol);
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

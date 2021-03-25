package controllers;

import com.jfoenix.controls.JFXButton;
import dataModel.OrdersDetailModel;
import dataModel.ReceiptModel;
import dataModel.ReceiptOrdersModel;
import entities.*;
import holders.ReceiptModelHolder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.*;
import utils.HibernateUtils;
import utils.NumberHelper;
import utils.StageHelper;
import utils.TableHelper;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ReceiptDetailController implements Initializable {
    @FXML
    private TextField customerHolder;
    @FXML
    private TextField phoneHolder;

    // Orders table
    @FXML
    private TableView<ReceiptOrdersModel> ordersTable;
    @FXML
    private TableColumn<ReceiptOrdersModel, Date> dateCol;
    @FXML
    private TableColumn<ReceiptOrdersModel, String> descriptionCol;
    @FXML
    private TableColumn<ReceiptOrdersModel, String> employeeCol;

    @FXML
    private TextField addressHolder;

    // Detail table
    @FXML
    private TableView<OrdersDetailModel> detailTable;
    @FXML
    private TableColumn<OrdersDetailModel, String> merchandiseCol;
    @FXML
    private TableColumn<OrdersDetailModel, Integer> quantityCol;
    @FXML
    private TableColumn<OrdersDetailModel, Integer> amountCol;
    @FXML
    private TableColumn<OrdersDetailModel, Integer> finalAmountCol;

    @FXML
    private JFXButton closeButton;
    @FXML
    private TextField sumQuantityHolder;
    @FXML
    private TextField sumAmountHolder;

    // Get ReceiptModel from ReceiptCategoryController select(MouseEvent event)
    ReceiptModelHolder receiptModelHolder = ReceiptModelHolder.getInstance();
    ReceiptModel receiptModel = receiptModelHolder.getReceiptModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (receiptModel != null) {
            SessionFactory factory = HibernateUtils.getSessionFactory();
            Session session = factory.openSession();

            // Set customer
            session.beginTransaction();
            Customer customer = CustomerRepository.getByName(session, receiptModel.getCustomerName());
            session.getTransaction().commit();
            customerHolder.setText(receiptModel.getCustomerName());
            phoneHolder.setText(customer.getPhone());
            addressHolder.setText(customer.getAddress());

            // Set orders
            session = factory.openSession();
            Orders orders = OrdersRepository.getById(session, receiptModel.getOrdersId());
            session = factory.openSession();
            List<Employee> employeeList = EmployeeRepository.getAll(session);

            List<ReceiptOrdersModel> receiptOrdersModelList = new ArrayList<>();
            ReceiptOrdersModel receiptOrdersModel = new ReceiptOrdersModel(orders);
            receiptOrdersModel.setEmployeeName(employeeList.stream().filter(t -> t.getId().equals(orders.getEmployeeId())).findFirst().get().getFullName());
            receiptOrdersModelList.add(receiptOrdersModel);

            TableHelper.setReceiptOrdersModelTable(receiptOrdersModelList, ordersTable, dateCol, descriptionCol, employeeCol);

            // Set orders detail
            session = factory.openSession();
            List<OrdersDetail> ordersDetailList = OrdersDetailRepository.getByOrdersId(session, orders.getId());
            session = factory.openSession();
            List<Merchandise> merchandiseList = MerchandiseRepository.getAll(session);

            List<OrdersDetailModel> ordersDetailModelList = new ArrayList<>();
            for (OrdersDetail item : ordersDetailList) {
                Merchandise merchandise = merchandiseList.stream().filter(t -> t.getId().equals(item.getMerchandiseId())).findFirst().orElse(null);

                OrdersDetailModel ordersDetailModel = new OrdersDetailModel();
                ordersDetailModel.setMerchandiseName(merchandise.getName());
                ordersDetailModel.setQuantity(item.getQuantity());
                ordersDetailModel.setAmount(NumberHelper.addComma(merchandise.getPrice()));
                ordersDetailModel.setFinalAmount(NumberHelper.addComma(String.valueOf(item.getAmount())));
                ordersDetailModelList.add(ordersDetailModel);
            }

            // Set sumQuantity and sumAmount of orders detail
            Integer sumQuantity = ordersDetailList.stream().mapToInt(OrdersDetail::getQuantity).sum();
            int sumAmount = ordersDetailList.stream().mapToInt(OrdersDetail::getAmount).sum();
            sumQuantityHolder.setText(NumberHelper.addComma(String.valueOf(sumQuantity)));
            sumAmountHolder.setText(NumberHelper.addComma(String.valueOf(sumAmount)));

            TableHelper.setOrdersDetailModelTable(ordersDetailModelList, detailTable, merchandiseCol, quantityCol, amountCol, finalAmountCol);

            // Set receipt model holder
            receiptModelHolder.setReceiptModel(null);
        }
    }

    @FXML
    void close(MouseEvent event) {
        StageHelper.closeStage(event);
        // Unhide host
        AnchorPane host = MainNavigatorController.instance.getHost();
        host.setDisable(false);
    }
}

package controllers;

import com.jfoenix.controls.JFXButton;
import dataModel.OrdersDetailModel;
import dataModel.ReceiptOrdersModel;
import entities.*;
import enums.StatusEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.*;
import utils.*;

import java.net.URL;
import java.util.*;

public class ReceiptAddController implements Initializable {
    @FXML
    private ComboBox<String> customerHolder;
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
    private JFXButton saveButton;
    @FXML
    private TextField sumQuantityHolder;
    @FXML
    private TextField sumAmountHolder;

    private Orders chosenOrders;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();

        saveButton.setDisable(true);

        // Add customer to choose customer ComboBox
        customerHolder.setValue("Chọn khách hàng");
        List<Customer> customerList = CustomerRepository.getCustomerHasActiveOrders(session);
        for (Customer item : customerList) {
            customerHolder.getItems().add(item.getFullName());
        }
    }

    @FXML
    void select(MouseEvent event) {
        if (event.getClickCount() == 2) {
            try {
                SessionFactory factory = HibernateUtils.getSessionFactory();
                Session session = factory.openSession();

                Orders orders = new Orders(ordersTable.getSelectionModel().getSelectedItem());
                ordersTable.getSelectionModel().clearSelection();

                // Show chosen orders detail in detailTable
                List<Merchandise> merchandiseList = MerchandiseRepository.getAll(session);
                session = factory.openSession();
                List<OrdersDetail> ordersDetailList = OrdersDetailRepository.getByOrdersId(session, orders.getId());

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

                // Set chosen orders
                chosenOrders = orders;
                TableHelper.setOrdersDetailModelTable(ordersDetailModelList, detailTable, merchandiseCol, quantityCol, amountCol, finalAmountCol);
                saveButton.setDisable(false);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println(Arrays.toString(ex.getStackTrace()));
            }
        }
    }

    @FXML
    void save(ActionEvent event) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.openSession();

        String employeeId = chosenOrders.getEmployeeId();
        String ordersId = chosenOrders.getId();
        String description = chosenOrders.getDescription();

        // Save new receipt
        Receipt receipt = new Receipt();
        receipt.setId(UUIDHelper.generateType4UUID().toString());
        receipt.setOrderId(chosenOrders.getId());
        receipt.setEmployeeId(chosenOrders.getEmployeeId());
        receipt.setDescription(chosenOrders.getDescription());

        session.beginTransaction();
        session.save(receipt);
        session.getTransaction().commit();

        // Close stage
        StageHelper.closeStage(event);

        // Show alert box
        AlertBoxHelper.showMessageBox("Thêm thành công");

        // Refresh content table
        ReceiptCategoryController.getInstance().refresh();

        // Unhide host
        AnchorPane host = MainNavigatorController.instance.getHost();
        host.setDisable(false);


        // Update orders status
        chosenOrders.setStatus(StatusEnum.COMPLETED.toString());
        session.beginTransaction();
        session.saveOrUpdate(chosenOrders);
        session.getTransaction().commit();
        OrderCategoryController.getInstance().initialize(null, null);
    }

    @FXML
    void showChosenCustomer(ActionEvent event) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.openSession();

        // Show customer info
        Customer customer = CustomerRepository.getByName(session, customerHolder.getValue());
        phoneHolder.setText(customer.getPhone());
        addressHolder.setText(customer.getAddress());

        // Show customer orders info
        session = factory.openSession();
        List<Orders> ordersList = OrdersRepository.getByCustomerId(session, customer.getId());
        session = factory.openSession();
        List<Employee> employeeList = EmployeeRepository.getAll(session);
        List<ReceiptOrdersModel> receiptOrdersModelList = new ArrayList<>();
        for (Orders item : ordersList) {
            ReceiptOrdersModel receiptOrdersModel = new ReceiptOrdersModel(item);
            receiptOrdersModel.setEmployeeName(employeeList.stream().filter(t -> t.getId().equals(item.getEmployeeId())).findFirst().get().getFullName());
            receiptOrdersModelList.add(receiptOrdersModel);
        }

        TableHelper.setReceiptOrdersModelTable(receiptOrdersModelList, ordersTable, dateCol, descriptionCol, employeeCol);
    }

    @FXML
    void close(MouseEvent event) {
        StageHelper.closeStage(event);
        // Unhide host
        AnchorPane host = MainNavigatorController.instance.getHost();
        host.setDisable(false);
    }
}

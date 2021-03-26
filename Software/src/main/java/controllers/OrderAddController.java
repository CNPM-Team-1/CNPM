package controllers;

import com.jfoenix.controls.JFXButton;
import dataModel.OrdersAddTableModel;
import entities.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.CustomerRepository;
import repositories.MerchandiseRepository;
import utils.*;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class OrderAddController implements Initializable {
    @FXML
    private AnchorPane host;

    @FXML
    private TableView<OrdersAddTableModel> merchandiseTable;
    @FXML
    private TableColumn<OrdersAddTableModel, String> merchandiseCol;
    @FXML
    private TableColumn<OrdersAddTableModel, Integer> quantityCol;
    @FXML
    private TableColumn<OrdersAddTableModel, String> amountCol;
    @FXML
    private TableColumn<OrdersAddTableModel, String> sumAmountCol;

    @FXML
    private JFXButton saveOrdersButton;
    @FXML
    private JFXButton addMerchandiseButton;
    @FXML
    private JFXButton close_button;
    @FXML
    private ImageView close;
    @FXML
    private ComboBox<String> customerHolder;
    @FXML
    private TextField phoneHolder;
    @FXML
    private JFXButton addNewCustomerButton;
    @FXML
    private TextField addressHolder;
    @FXML
    private ComboBox<String> merchandiseHolder;
    @FXML
    private TextField quantityHolder;
    @FXML
    private JFXButton addNewMerchandiseButton;
    @FXML
    private TextField descriptionHolder;
    @FXML
    private TextField sumOrdersMerchandiseQuantity;
    @FXML
    private TextField sumOrdersMerchandiseAmount;
    @FXML
    private Label errorMessage;

    public List<OrdersAddTableModel> ordersAddTableModelList = new ArrayList<>();

    // For other class cal function from this class
    public static OrderAddController instance;

    public OrderAddController() {
        instance = this;
    }

    public static OrderAddController getInstance() {
        return instance;
    }
    ///

    // Get logged in employee
    Employee loggedInEmployee = LoginController.getInstance().curEmployee;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OrderCategoryController.getInstance().ordersAddUpdateIsShow = true;
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session;

        session = sessionFactory.openSession();
        List<Customer> customerList = CustomerRepository.getAll(session);
        session = sessionFactory.openSession();
        List<Merchandise> merchandiseList = MerchandiseRepository.getHasQuantity(session);

        if (customerList != null && !customerList.isEmpty() && merchandiseList != null && !merchandiseList.isEmpty()) {
            // Add item to Customer Combox
            customerHolder.getItems().clear();
            for (Customer item : customerList) {
                customerHolder.getItems().add(item.getFullName());
            }
            // Add item to Merchandise Combox
            merchandiseHolder.getItems().clear();
            for (Merchandise item : merchandiseList) {
                merchandiseHolder.getItems().add(item.getName());
            }

            customerHolder.setValue("Chọn khách hàng");
            merchandiseHolder.setValue("Chọn hàng hóa");
        }
    }

    @FXML
    void setCustomer(ActionEvent event) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session;

        session = sessionFactory.openSession();
        List<Customer> customerList = CustomerRepository.getAll(session);
        // Get chosen customer from customerList
        if (customerList != null && !customerList.isEmpty()) {
            Customer chosenCustomer = customerList.stream().filter(t -> t.getFullName().equals(customerHolder.getValue())).findFirst().get();
            // Set phoneHolder and addressHolder
            phoneHolder.setText(chosenCustomer.getPhone());
            addressHolder.setText(chosenCustomer.getAddress());
            if (chosenCustomer.getType().equals("Khách hàng")) {
                descriptionHolder.setText("Khách hàng " + chosenCustomer.getFullName() + " mua hàng");
            } else {
                descriptionHolder.setText("Mua hàng từ nhà cung cấp " + chosenCustomer.getFullName());
            }
        }
    }

    @FXML
    void addNewCustomer(ActionEvent event) {
        CustomerCategoryController.getInstance().insert(null);
    }

    @FXML
    void addNewMerchandise(ActionEvent event) {
        MerchandiseCategoryController.getInstance().insert(null);
    }

    @FXML
    void chooseMerchandise(ActionEvent event) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session;

        session = sessionFactory.openSession();
        Merchandise merchandise = MerchandiseRepository.getByName(session, merchandiseHolder.getValue());
        if (merchandise != null && !quantityHolder.getText().isEmpty() && quantityHolder.getText() != null
                && !quantityHolder.getText().equals("0") && NumberHelper.isNumber(quantityHolder.getText())) {
            OrdersAddTableModel ordersAddTableModel = new OrdersAddTableModel();
            ordersAddTableModel.setMerchandiseName(merchandise.getName());
            ordersAddTableModel.setQuantity(Integer.parseInt(quantityHolder.getText()));
            ordersAddTableModel.setAmount(NumberHelper.addComma(merchandise.getPrice()));
            int sumAmount = Integer.parseInt(quantityHolder.getText()) * Integer.parseInt(merchandise.getPrice());
            ordersAddTableModel.setSumAmount(NumberHelper.addComma(Integer.toString(sumAmount)));

            // Remove duplicate merchandise
            ordersAddTableModelList.removeIf(t -> t.getMerchandiseName().equals(merchandise.getName()));
            ordersAddTableModelList.add(ordersAddTableModel);

            TableHelper.setOrdersAddTable(ordersAddTableModelList, merchandiseTable, merchandiseCol, quantityCol, amountCol, sumAmountCol);

            // Update sumQuantity and sumAmount
            int sumQuantity = ordersAddTableModelList.stream().mapToInt(OrdersAddTableModel::getQuantity).sum();
            List<Integer> allAmount = ordersAddTableModelList.stream().map(t -> Integer.parseInt(NumberHelper.removeComma(t.getSumAmount()))).collect(Collectors.toList());
            int sumAllAmount = allAmount.stream().mapToInt(Integer::intValue).sum();

            sumOrdersMerchandiseQuantity.setText(NumberHelper.addComma(String.valueOf(sumQuantity)));
            sumOrdersMerchandiseAmount.setText(NumberHelper.addComma(String.valueOf(sumAllAmount)));
            errorMessage.setText("");

            // Clear merchandiseHolder and quantityHolder
            merchandiseHolder.setValue("Chọn hàng hoá");
            quantityHolder.setText("");
        } else {
            errorMessage.setText(merchandise == null ? "Chưa chọn hàng hoá" : "Chưa chọn số lượng hàng hoá");
        }
    }

    @FXML
    void removeChosenMerchandise(MouseEvent event) {
        if (event.getClickCount() == 2) {
            try {
                OrdersAddTableModel choosenMerchandise = merchandiseTable.getSelectionModel().getSelectedItem();
                merchandiseTable.getSelectionModel().clearSelection();
                ordersAddTableModelList.removeIf(t -> t.getMerchandiseName().equals(choosenMerchandise.getMerchandiseName()));

                TableHelper.setOrdersAddTable(ordersAddTableModelList, merchandiseTable, merchandiseCol, quantityCol, amountCol, sumAmountCol);

                // Update sumQuantity and sumAmount
                int sumQuantity = ordersAddTableModelList.stream().mapToInt(OrdersAddTableModel::getQuantity).sum();
                List<Integer> allAmount = ordersAddTableModelList.stream().map(t -> Integer.parseInt(NumberHelper.removeComma(t.getSumAmount()))).collect(Collectors.toList());
                int sumAllAmount = allAmount.stream().mapToInt(Integer::intValue).sum();

                sumOrdersMerchandiseQuantity.setText(NumberHelper.addComma(String.valueOf(sumQuantity)));
                sumOrdersMerchandiseAmount.setText(NumberHelper.addComma(String.valueOf(sumAllAmount)));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println(Arrays.toString(ex.getStackTrace()));
            }
        }
    }

    @FXML
    void save(ActionEvent event) {
        try {
            SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
            Session session;

            session = sessionFactory.openSession();
            Customer ordersCustomer = CustomerRepository.getByCustomerName(session, customerHolder.getValue());

            // Save new Orders
            Orders orders = new Orders();
            orders.setId(UUIDHelper.generateType4UUID().toString());
            orders.setType(ordersCustomer.getType().equals("Khách hàng") ? "Bán hàng" : "Nhập hàng");
            orders.setEmployee(loggedInEmployee);
            orders.setCustomer(ordersCustomer);
            orders.setStatus("Chưa hoàn tất");
            orders.setDescription(descriptionHolder.getText());

            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(orders);
            session.getTransaction().commit();

            // Save new OrdersDetail
            for (OrdersAddTableModel item : ordersAddTableModelList) {
                OrdersDetail ordersDetail = new OrdersDetail();
                ordersDetail.setId(UUIDHelper.generateType4UUID().toString());
                ordersDetail.setOrders(orders);
                session = sessionFactory.openSession();
                ordersDetail.setMerchandise(MerchandiseRepository.getByName(session, item.getMerchandiseName()));
                ordersDetail.setQuantity(item.getQuantity());
                ordersDetail.setAmount(Integer.parseInt(NumberHelper.removeComma(item.getSumAmount())));

                session = sessionFactory.openSession();
                session.beginTransaction();
                session.save(ordersDetail);
                session.getTransaction().commit();
            }

            // Show alert box
            AlertBoxHelper.showMessageBox("Thêm thành công");
            // Refresh content table
            if (OrderCategoryController.getInstance() != null) {
                OrderCategoryController.getInstance().refresh();
            }
            // Unhide host only when orders add is not show
            AnchorPane host = MainNavigatorController.instance.getHost();
            host.setDisable(false);
            // Close stage
            StageHelper.closeStage(event);
        } catch (Exception ex) {
            errorMessage.setText("Lỗi thêm đơn hàng");
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    @FXML
    void close(MouseEvent event) {
        StageHelper.closeStage(event);
        OrderCategoryController.getInstance().ordersAddUpdateIsShow = false;
        // Unhide host
        AnchorPane host = MainNavigatorController.instance.getHost();
        host.setDisable(false);
    }

    @FXML
    void requestFocus(MouseEvent event) {
        host.requestFocus();
    }
}


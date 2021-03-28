package controllers;

import com.jfoenix.controls.JFXButton;
import dataModel.OrdersAddTableModel;
import entities.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.CustomerRepository;
import repositories.MerchandiseRepository;
import utils.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
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
    private TextField customerHolder;
    @FXML
    private TextField phoneHolder;
    @FXML
    private JFXButton addNewCustomerButton;
    @FXML
    private TextField addressHolder;
    @FXML
    private TextField merchandiseHolder;
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
        List<Merchandise> merchandiseList = MerchandiseRepository.getAll(session);

        if (customerList != null && !customerList.isEmpty() && merchandiseList != null && !merchandiseList.isEmpty()) {
            // Add item to Customer Combox
            AutoCompletionBinding<String> cHolder = TextFields.bindAutoCompletion(customerHolder, customerList.stream().map(Customer::getFullName).collect(Collectors.toList()));
            cHolder.setOnAutoCompleted(stringAutoCompletionEvent -> setCustomer(null));
            // Add item to Merchandise Combox
            TextFields.bindAutoCompletion(merchandiseHolder, merchandiseList.stream().map(Merchandise::getName).collect(Collectors.toList()));
        }
    }

    @FXML
    void setCustomer(ActionEvent event) {
        try {
            SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
            Session session;

            session = sessionFactory.openSession();
            Customer chosenCustomer = CustomerRepository.getByCustomerName(session, customerHolder.getText());
            // Get chosen customer from customerList
            if (chosenCustomer != null) {
                // Set phoneHolder and addressHolder
                phoneHolder.setText(chosenCustomer.getPhone());
                addressHolder.setText(chosenCustomer.getAddress());
                if (chosenCustomer.getType().equals("Khách hàng")) {
                    descriptionHolder.setText("Khách hàng " + chosenCustomer.getFullName() + " mua hàng");
                } else {
                    descriptionHolder.setText("Mua hàng từ nhà cung cấp " + chosenCustomer.getFullName());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
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
        Customer customer = CustomerRepository.getByCustomerName(session, customerHolder.getText());
        session = sessionFactory.openSession();
        Merchandise merchandise = MerchandiseRepository.getByName(session, merchandiseHolder.getText());

        List<String> validateAddMerchandise = this.validateAddMerchandise(customer, merchandise);
        if (validateAddMerchandise.size() == 0) {
            OrdersAddTableModel ordersAddTableModel = new OrdersAddTableModel();
            ordersAddTableModel.setMerchandiseName(merchandise.getName());
            ordersAddTableModel.setQuantity(Integer.parseInt(quantityHolder.getText()));
            ordersAddTableModel.setAmount(NumberHelper.addComma(merchandise.getPrice()));
            Long sumAmount = Long.parseLong(quantityHolder.getText()) * Integer.parseInt(merchandise.getPrice());
            ordersAddTableModel.setSumAmount(NumberHelper.addComma(Long.toString(sumAmount)));
            // Remove duplicate merchandise
            ordersAddTableModelList.removeIf(t -> t.getMerchandiseName().equals(merchandise.getName()));
            ordersAddTableModelList.add(ordersAddTableModel);

            TableHelper.setOrdersAddTable(ordersAddTableModelList, merchandiseTable, merchandiseCol, quantityCol, amountCol, sumAmountCol);
            // Update sumQuantity and sumAmount
            int sumQuantity = ordersAddTableModelList.stream().mapToInt(OrdersAddTableModel::getQuantity).sum();
            List<Long> allAmount = ordersAddTableModelList.stream().map(t -> Long.parseLong(NumberHelper.removeComma(t.getSumAmount()))).collect(Collectors.toList());
            Long sumAllAmount = allAmount.stream().mapToLong(Long::longValue).sum();

            sumOrdersMerchandiseQuantity.setText(NumberHelper.addComma(String.valueOf(sumQuantity)));
            sumOrdersMerchandiseAmount.setText(NumberHelper.addComma(String.valueOf(sumAllAmount)));
            errorMessage.setText("");
            // Clear merchandiseHolder and quantityHolder
            merchandiseHolder.clear();
            quantityHolder.setText("");
        } else {
            errorMessage.setText(validateAddMerchandise.get(0));
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
            Customer ordersCustomer = CustomerRepository.getByCustomerName(session, customerHolder.getText());
            // Save new Orders
            Orders orders = new Orders();
            orders.setId(UUIDHelper.generateType4UUID().toString());
            orders.setType(ordersCustomer.getType().equals("Khách hàng") ? "Bán hàng" : "Nhập hàng");
            orders.setEmployee(loggedInEmployee);
            orders.setCustomer(ordersCustomer);
            orders.setStatus("Chưa hoàn tất");
            if (descriptionHolder.getText().isEmpty()) {
                if (ordersCustomer.getType().equals("Khách hàng")) {
                    descriptionHolder.setText("Khách hàng " + ordersCustomer.getFullName() + " mua hàng");
                } else {
                    descriptionHolder.setText("Mua hàng từ nhà cung cấp " + ordersCustomer.getFullName());
                }
            } else {
                orders.setDescription(descriptionHolder.getText());
            }
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
                ordersDetail.setAmount(Long.parseLong(NumberHelper.removeComma(item.getSumAmount())));

                session = sessionFactory.openSession();
                session.beginTransaction();
                session.save(ordersDetail);
                session.getTransaction().commit();
                // Update quantity in merchandise entity when sell merchandise
                if (ordersDetail.getOrders().getType().equals("Bán hàng")) {
                    ordersDetail.getMerchandise().setQuantity(ordersDetail.getMerchandise().getQuantity() - item.getQuantity());
                    session = sessionFactory.openSession();
                    session.beginTransaction();
                    session.saveOrUpdate(ordersDetail.getMerchandise());
                    session.getTransaction().commit();
                    // Update merchandise category
                    MerchandiseCategoryController.getInstance().refresh();
                }
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

    List<String> validateAddMerchandise(Customer customer, Merchandise merchandise) {
        List<String> errors = new ArrayList<>();

        if (merchandiseHolder.getText().isEmpty()) {
            errors.add("Chưa nhập hàng hoá");
        }
        if (quantityHolder.getText().isEmpty()) {
            errors.add("Chưa nhập số lượng");
        } else {
            if (!NumberHelper.isNumber(quantityHolder.getText())) {
                errors.add("Số lượng phải là chữ số");
            } else {
                if (Integer.parseInt(quantityHolder.getText()) > 1000) {
                    errors.add("Không được nhập số lượng lớn hơn 1000");
                }
                if (customer.getType().equals("Khách hàng")) {
                    if (Integer.parseInt(quantityHolder.getText()) > merchandise.getQuantity()) {
                        errors.add("Không đủ số lượng hàng hoá trong kho");
                    }
                }
            }
        }
        return errors;
    }


}
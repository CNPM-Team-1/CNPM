package controllers;

import com.jfoenix.controls.JFXButton;
import dataModel.OrdersAddTableModel;
import entities.Customer;
import entities.Merchandise;
import entities.Orders;
import entities.OrdersDetail;
import holders.OrdersHolder;
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
import org.controlsfx.control.textfield.TextFields;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.CustomerRepository;
import repositories.MerchandiseRepository;
import repositories.OrdersDetailRepository;
import utils.*;
import validation.OrdersValidation;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class OrderUpdateController implements Initializable {
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
    private JFXButton updateOrdersButton;
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
    private TextField addressHolder;
    @FXML
    private TextField descriptionHolder;
    @FXML
    private TextField merchandiseHolder;
    @FXML
    private TextField quantityHolder;
    @FXML
    private JFXButton addNewMerchandiseButton;
    @FXML
    private TextField sumOrdersMerchandiseQuantity;
    @FXML
    private TextField sumOrdersMerchandiseAmount;
    @FXML
    private Label errorMessage;
    @FXML
    private JFXButton deleteButton;

    public List<OrdersAddTableModel> ordersAddTableModelList = new ArrayList<>();

    // Get Orders from OrderCategoryController select(MouseEvent event)
    OrdersHolder ordersHolder = OrdersHolder.getInstance();
    Orders curOrders = ordersHolder.getOrders();

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
            TextFields.bindAutoCompletion(customerHolder, customerList.stream().map(Customer::getFullName).collect(Collectors.toList()));
            // Add item to Merchandise Combox
            TextFields.bindAutoCompletion(merchandiseHolder, merchandiseList.stream().map(Merchandise::getName).collect(Collectors.toList()));
        }

        // Set customer
        customerHolder.setText(curOrders.getCustomer().getFullName());
        phoneHolder.setText(curOrders.getCustomer().getPhone());
        addressHolder.setText(curOrders.getCustomer().getAddress());
        descriptionHolder.setText(curOrders.getDescription());

        // Set OrdersDetail
        session = sessionFactory.openSession();
        List<OrdersDetail> ordersDetailList = OrdersDetailRepository.getByOrdersId(session, curOrders.getId());
        for (OrdersDetail item : ordersDetailList) {
            OrdersAddTableModel ordersAddTableModel = new OrdersAddTableModel();
            ordersAddTableModel.setMerchandiseName(item.getMerchandise().getName());
            ordersAddTableModel.setQuantity(item.getQuantity());
            ordersAddTableModel.setAmount(NumberHelper.addComma(item.getMerchandise().getPrice()));
            ordersAddTableModel.setSumAmount(NumberHelper.addComma(String.valueOf(item.getAmount())));
            ordersAddTableModelList.add(ordersAddTableModel);
        }

        TableHelper.setOrdersAddTable(ordersAddTableModelList, merchandiseTable, merchandiseCol, quantityCol, amountCol, sumAmountCol);

        // Update sumQuantity and sumAmount
        int sumQuantity = ordersAddTableModelList.stream().mapToInt(OrdersAddTableModel::getQuantity).sum();
        List<Long> allAmount = ordersAddTableModelList.stream().map(t -> Long.parseLong(NumberHelper.removeComma(t.getSumAmount()))).collect(Collectors.toList());
        Long sumAllAmount = allAmount.stream().mapToLong(Long::longValue).sum();

        sumOrdersMerchandiseQuantity.setText(NumberHelper.addComma(String.valueOf(sumQuantity)));
        sumOrdersMerchandiseAmount.setText(NumberHelper.addComma(String.valueOf(sumAllAmount)));
        errorMessage.setText("");
    }

    @FXML
    void setCustomer(ActionEvent event) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session;

        session = sessionFactory.openSession();
        List<Customer> customerList = CustomerRepository.getAll(session);
        // Get chosen customer from customerList
        if (customerList != null && !customerList.isEmpty()) {
            Customer chosenCustomer = customerList.stream().filter(t -> t.getFullName().equals(customerHolder.getText())).findFirst().get();
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
    void update(ActionEvent event) {
        try {
            SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
            Session session = sessionFactory.openSession();
            if (OrdersValidation.validateUpdate(session, curOrders).size() == 0) {
                session = sessionFactory.openSession();
                Customer ordersCustomer = CustomerRepository.getByCustomerName(session, customerHolder.getText());
                // Update orders
                curOrders.setType(ordersCustomer.getType().equals("Khách hàng") ? "Bán hàng" : "Nhập hàng");
                curOrders.setCustomer(ordersCustomer);
                curOrders.setStatus("Chưa hoàn tất");
                curOrders.setDescription(descriptionHolder.getText());

                session = sessionFactory.openSession();
                session.beginTransaction();
                session.saveOrUpdate(curOrders);
                session.getTransaction().commit();
                // Update OrdersDetail
                session = sessionFactory.openSession();
                List<OrdersDetail> oldOrdersDetails = OrdersDetailRepository.getByOrdersId(session, curOrders.getId());
                session = sessionFactory.openSession();
                OrdersDetailRepository.deleteByOrdersId(session, curOrders.getId());
                List<OrdersDetail> newOrdersDetails = new ArrayList<>();
                for (OrdersAddTableModel item : ordersAddTableModelList) {
                    OrdersDetail ordersDetail = new OrdersDetail();
                    ordersDetail.setId(UUIDHelper.generateType4UUID().toString());
                    ordersDetail.setOrders(curOrders);
                    session = sessionFactory.openSession();
                    ordersDetail.setMerchandise(MerchandiseRepository.getByName(session, item.getMerchandiseName()));
                    ordersDetail.getMerchandise().setQuantity(item.getQuantity());
                    ordersDetail.setQuantity(item.getQuantity());
                    ordersDetail.setAmount(Long.parseLong(NumberHelper.removeComma(item.getSumAmount())));
                    newOrdersDetails.add(ordersDetail);

                    session = sessionFactory.openSession();
                    session.beginTransaction();
                    session.save(ordersDetail);
                    session.getTransaction().commit();
                }
                // Update merchandise quantity
                updateMerchandiseQuantity(newOrdersDetails, oldOrdersDetails);
                // Show alert box
                AlertBoxHelper.showMessageBox("Cập nhật thành công");
                // Refresh content table
                if (OrderCategoryController.getInstance() != null) {
                    OrderCategoryController.getInstance().refresh();
                }
                // Unhide host only when orders add is not show
                AnchorPane host = MainNavigatorController.instance.getHost();
                host.setDisable(false);
                // Clear Orders Holder
                OrdersHolder ordersHolder = OrdersHolder.getInstance();
                ordersHolder.setOrders(null);
                // Close stage
                StageHelper.closeStage(event);
                // Update merchandise category
                MerchandiseCategoryController.getInstance().refresh();
            } else {
                errorMessage.setText(OrdersValidation.validateUpdate(session, curOrders).get(0));
            }
        } catch (Exception ex) {
            errorMessage.setText("Lỗi thêm đơn hàng");
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    @FXML
    void delete(ActionEvent event) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        if (OrdersValidation.validateDelete(session, curOrders).size() == 0) {
            // Update merchandise quantity
            session = sessionFactory.openSession();
            List<OrdersDetail> oldOrdersDetails = OrdersDetailRepository.getByOrdersId(session, curOrders.getId());
            updateMerchandiseQuantity(new ArrayList<>(), oldOrdersDetails);
            // Delete orders detail and orders
            session = sessionFactory.openSession();
            OrdersDetailRepository.deleteByOrdersId(session, curOrders.getId());
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(curOrders);
            session.getTransaction().commit();

            // Show alert box
            AlertBoxHelper.showMessageBox("Xoá thành công");
            // Close stage
            StageHelper.closeStage(event);
            // Refresh content table
            OrderCategoryController.getInstance().refresh();
            // Clear Orders Holder
            OrdersHolder ordersHolder = OrdersHolder.getInstance();
            ordersHolder.setOrders(null);
            // Unhide host
            AnchorPane host = MainNavigatorController.instance.getHost();
            host.setDisable(false);
            // Update merchandise category
            MerchandiseCategoryController.getInstance().refresh();
        } else {
            errorMessage.setText(OrdersValidation.validateDelete(session, curOrders).get(0));
        }
    }

    @FXML
    void close(MouseEvent event) {
        StageHelper.closeStage(event);
        OrdersHolder ordersHolder = OrdersHolder.getInstance();
        ordersHolder.setOrders(null);
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
        }
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
        return errors;
    }

    void updateMerchandiseQuantity(List<OrdersDetail> newOrdersDetails, List<OrdersDetail> oldOrdersDetail) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session;

        // for insert or update order detail
        for (OrdersDetail item : newOrdersDetails) {
            int oldQuantity = 0;
            if (oldOrdersDetail != null && oldOrdersDetail.size() > 0) {
                int index = oldOrdersDetail.stream().map(t -> t.getMerchandise().getId()).collect(Collectors.toList()).indexOf(item.getMerchandise().getId());
                if (index != -1) {
                    oldQuantity = oldOrdersDetail.get(index).getQuantity();
                }

                session = sessionFactory.openSession();
                Merchandise merchandise = MerchandiseRepository.getById(session, item.getMerchandise().getId());
                merchandise.setQuantity(merchandise.getQuantity() + (oldQuantity - item.getMerchandise().getQuantity()));
                session = sessionFactory.openSession();
                session.beginTransaction();
                session.saveOrUpdate(merchandise);
                session.getTransaction().commit();
            }
        }

        // for delete order detail
        if (oldOrdersDetail != null) {
            for (OrdersDetail item : oldOrdersDetail) {
                int index = newOrdersDetails.stream().map(t -> t.getId()).collect(Collectors.toList()).indexOf(item.getId());
                if (index == -1 && item.getMerchandise() != null) {
                    session = sessionFactory.openSession();
                    Merchandise merchandise = MerchandiseRepository.getById(session, item.getMerchandise().getId());
                    merchandise.setQuantity(merchandise.getQuantity() + item.getQuantity());
                    session = sessionFactory.openSession();
                    session.beginTransaction();
                    session.saveOrUpdate(merchandise);
                    session.getTransaction().commit();
                }
            }
        }
    }
}
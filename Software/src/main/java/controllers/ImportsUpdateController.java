package controllers;

import com.jfoenix.controls.JFXButton;
import dataModel.OrdersDetailModel;
import dataModel.ReceiptOrdersModel;
import entities.*;
import holders.ImportsHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.ImportsDetailRepository;
import repositories.OrdersDetailRepository;
import utils.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ImportsUpdateController implements Initializable {
    @FXML
    private AnchorPane host;
    @FXML
    private TextField phoneHolder;
    @FXML
    private TextField customerHolder;

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
    @FXML
    private TextField merchandiseHolder;
    @FXML
    private TextField quantityHolder;

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
    private TextField sumQuantityHolder;
    @FXML
    private TextField sumAmountHolder;
    @FXML
    private Label errorMessage;
    @FXML
    private JFXButton deleteImportsButton;
    @FXML
    private JFXButton updateImportsButton;

    // Get Imports from ImportsCategoryController select(MouseEvent event)
    Imports imports = ImportsHolder.getInstance().getImports();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session;
        // Show Customer info
        Customer customer = imports.getOrders().getCustomer();
        customerHolder.setText(customer.getFullName());
        phoneHolder.setText(customer.getPhone());
        addressHolder.setText(customer.getAddress());

        // Show Customer Orders
        List<ReceiptOrdersModel> receiptOrdersModelList = new ArrayList<>();
        ReceiptOrdersModel receiptOrdersModel = new ReceiptOrdersModel();
        receiptOrdersModel.setOrders(imports.getOrders());
        receiptOrdersModel.setCreatedDate(imports.getOrders().getCreatedDate());
        receiptOrdersModel.setDescription(imports.getOrders().getDescription());
        receiptOrdersModel.setEmployeeName(imports.getOrders().getEmployee().getFullName());
        receiptOrdersModelList.add(receiptOrdersModel);
        TableHelper.setReceiptOrdersModelTable(receiptOrdersModelList, ordersTable, dateCol, descriptionCol, employeeCol);

        // Show ImportsDetail
        session = sessionFactory.openSession();
        List<ImportsDetail> importsDetails = ImportsDetailRepository.getByImportsId(session, imports.getId());
        session = sessionFactory.openSession();
        List<OrdersDetail> ordersDetails = OrdersDetailRepository.getByOrdersId(session, imports.getOrders().getId());
        List<OrdersDetailModel> ordersDetailModelList = new ArrayList<>();
        for (ImportsDetail item : importsDetails) {
            OrdersDetail ordersDetail = ordersDetails.stream().filter(t -> t.getMerchandise().getId().equals(item.getMerchandise().getId()))
                    .findFirst().orElse(null);
            OrdersDetailModel ordersDetailModel = new OrdersDetailModel();
            ordersDetailModel.setOrdersDetail(ordersDetail);
            ordersDetailModel.setMerchandiseName(item.getMerchandise().getName());
            ordersDetailModel.setQuantity(item.getQuantity());
            ordersDetailModel.setAmount(NumberHelper.addComma(item.getMerchandise().getPrice()));
            ordersDetailModel.setFinalAmount(NumberHelper.addComma(String.valueOf(item.getAmount())));
            ordersDetailModelList.add(ordersDetailModel);
        }
        // Set SumQuantityHolder and SumAmountHolder
        Integer sumQuantity = ordersDetailModelList.stream().mapToInt(OrdersDetailModel::getQuantity).sum();
        Integer sumAmount = ordersDetailModelList.stream().mapToInt(t -> Integer.parseInt(NumberHelper.removeComma(t.getFinalAmount()))).sum();
        sumQuantityHolder.setText(String.valueOf(sumQuantity));
        sumAmountHolder.setText(NumberHelper.addComma(String.valueOf(sumAmount)));
        // Set DetailTable
        TableHelper.setImportsDetailModelTable(ordersDetailModelList, detailTable, merchandiseCol, quantityCol, amountCol, finalAmountCol);
    }

    @FXML
    void pickMerchandise(MouseEvent event) {
        if (event.getClickCount() == 2) {
            // Show chosen merchandise name and price
            OrdersDetailModel ordersDetailModel = detailTable.getSelectionModel().getSelectedItem();
            ordersTable.getSelectionModel().clearSelection();
            merchandiseHolder.setText(ordersDetailModel.getMerchandiseName());
            quantityHolder.setText(ordersDetailModel.getQuantity().toString());
        }
    }

    @FXML
    void updateOrdersDetail(ActionEvent event) {
        errorMessage.setText("");
        List<OrdersDetailModel> ordersDetailModelList = new ArrayList<>();
        for (OrdersDetailModel item : detailTable.getItems()) {
            OrdersDetailModel ordersDetailModel = new OrdersDetailModel(item);
            ordersDetailModelList.add(ordersDetailModel);
        }
        for (OrdersDetailModel item : ordersDetailModelList) {
            if (item.getMerchandiseName().equals(merchandiseHolder.getText())) {
                if (Integer.parseInt(quantityHolder.getText()) <= item.getOrdersDetail().getQuantity()) {
                    if (!quantityHolder.getText().equals("0")) {
                        item.setQuantity(Integer.parseInt(quantityHolder.getText()));
                        Long amount = Long.parseLong(NumberHelper.removeComma(item.getAmount()));
                        item.setFinalAmount(NumberHelper.addComma(String.valueOf(item.getQuantity() * amount)));
                    } else {
                        errorMessage.setText("Không được chọn số lượng hàng hoá là 0");
                    }
                    break;
                } else {
                    errorMessage.setText("Không được chọn số lượng hàng hoá lớn hơn số lượng của đơn hàng");
                }
            }
        }

        // set SumQuantityHolder and SumAmountHolder
        Integer sumQuantity = ordersDetailModelList.stream().mapToInt(OrdersDetailModel::getQuantity).sum();
        Integer sumAmount = ordersDetailModelList.stream().mapToInt(t -> Integer.parseInt(NumberHelper.removeComma(t.getFinalAmount()))).sum();
        sumQuantityHolder.setText(String.valueOf(sumQuantity));
        sumAmountHolder.setText(NumberHelper.addComma(String.valueOf(sumAmount)));
        TableHelper.setOrdersDetailModelTable(ordersDetailModelList, detailTable, merchandiseCol, quantityCol, amountCol, finalAmountCol);
    }

    @FXML
    void removeMerchandise(ActionEvent event) {
        List<OrdersDetailModel> ordersDetailModelList = new ArrayList<>();
        for (OrdersDetailModel item : detailTable.getItems()) {
            OrdersDetailModel ordersDetailModel = new OrdersDetailModel(item);
            ordersDetailModelList.add(ordersDetailModel);
        }
        ordersDetailModelList.removeIf(item -> item.getMerchandiseName().equals(merchandiseHolder.getText()));
        // set SumQuantityHolder and SumAmountHolder
        Integer sumQuantity = ordersDetailModelList.stream().mapToInt(OrdersDetailModel::getQuantity).sum();
        Integer sumAmount = ordersDetailModelList.stream().mapToInt(t -> Integer.parseInt(NumberHelper.removeComma(t.getFinalAmount()))).sum();
        sumQuantityHolder.setText(String.valueOf(sumQuantity));
        sumAmountHolder.setText(NumberHelper.addComma(String.valueOf(sumAmount)));
        TableHelper.setOrdersDetailModelTable(ordersDetailModelList, detailTable, merchandiseCol, quantityCol, amountCol, finalAmountCol);

        updateImportsButton.setDisable(ordersDetailModelList.size() <= 0);
    }

    @FXML
    void updateImports(ActionEvent event) {

    }

    @FXML
    void deleteImports(ActionEvent event) {

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

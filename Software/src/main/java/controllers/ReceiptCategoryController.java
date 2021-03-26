package controllers;

import com.jfoenix.controls.JFXButton;
import dataModel.ReceiptModel;
import entities.*;
import holders.ReceiptModelHolder;
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
import repositories.*;
import utils.HibernateUtils;
import utils.NumberHelper;
import utils.StageHelper;
import utils.TableHelper;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ReceiptCategoryController implements Initializable {
    @FXML
    private TableView<ReceiptModel> contentTable;
    @FXML
    private TableColumn<ReceiptModel, Date> dateCol;
    @FXML
    private TableColumn<ReceiptModel, String> nameCol;
    @FXML
    private TableColumn<ReceiptModel, String> descriptionCol;
    @FXML
    private TableColumn<ReceiptModel, Integer> quantityCol;
    @FXML
    private TableColumn<ReceiptModel, String> amountCol;
    @FXML
    private TextField searchBar;
    @FXML
    private JFXButton searchButton;
    @FXML
    private JFXButton addButton;

    // For other class call function from this class
    public static ReceiptCategoryController instance;

    public ReceiptCategoryController() {
        instance = this;
    }

    public static ReceiptCategoryController getInstance() {
        return instance;
    }
    ///

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
            Session session;

            // Get all receipt
            session = sessionFactory.openSession();
            List<Receipt> receiptList = ReceiptRepository.getAll(session);
            //Set receipt model
            if (receiptList != null && !receiptList.isEmpty()) {
                List<ReceiptModel> receiptModelList = new ArrayList<>();
                for (Receipt item : receiptList) {
                    session = sessionFactory.openSession();
                    Integer sumAmount = Math.toIntExact(OrdersDetailRepository.getSumAmountById(session, item.getOrders().getId()));
                    session = sessionFactory.openSession();
                    Integer sumQuantity = Math.toIntExact(OrdersDetailRepository.getSumQuantityById(session, item.getOrders().getId()));

                    ReceiptModel receiptModel = new ReceiptModel();
                    receiptModel.setReceipt(item);
                    receiptModel.setCreatedDate(item.getCreatedDate());
                    receiptModel.setCustomerName(item.getOrders().getCustomer().getFullName());
                    receiptModel.setDescription(item.getDescription());
                    receiptModel.setSumQuantity(sumQuantity);
                    receiptModel.setSumAmount(NumberHelper.addComma(String.valueOf(sumAmount)));
                    receiptModelList.add(receiptModel);
                }
                TableHelper.setReceiptTable(receiptModelList, contentTable, dateCol, nameCol, descriptionCol, quantityCol, amountCol);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    @FXML
    void addReceipt(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/ReceiptAdd.fxml")));
            StageHelper.startStage(root);
            // Hide host
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
            SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
            Session session;

            // Get receipt like name
            session = sessionFactory.openSession();
            List<Receipt> receiptList = ReceiptRepository.getLikeCustomerName(session, searchBar.getText());
            //Set receipt model
            if (receiptList != null && !receiptList.isEmpty()) {
                List<ReceiptModel> receiptModelList = new ArrayList<>();
                for (Receipt item : receiptList) {
                    session = sessionFactory.openSession();
                    Integer sumAmount = Math.toIntExact(OrdersDetailRepository.getSumAmountById(session, item.getOrders().getId()));
                    session = sessionFactory.openSession();
                    Integer sumQuantity = Math.toIntExact(OrdersDetailRepository.getSumQuantityById(session, item.getOrders().getId()));

                    ReceiptModel receiptModel = new ReceiptModel();
                    receiptModel.setReceipt(item);
                    receiptModel.setCreatedDate(item.getCreatedDate());
                    receiptModel.setCustomerName(item.getOrders().getCustomer().getFullName());
                    receiptModel.setDescription(item.getDescription());
                    receiptModel.setSumQuantity(sumQuantity);
                    receiptModel.setSumAmount(NumberHelper.addComma(String.valueOf(sumAmount)));
                    receiptModelList.add(receiptModel);
                }
                TableHelper.setReceiptTable(receiptModelList, contentTable, dateCol, nameCol, descriptionCol, quantityCol, amountCol);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    @FXML
    void select(MouseEvent event) {
//        if (event.getClickCount() == 2) {
//            try {
//                ReceiptModel receiptModel = contentTable.getSelectionModel().getSelectedItem();
//                contentTable.getSelectionModel().clearSelection();
//                // Store receipt to use in another class
//                if (receiptModel != null) {
//                    ReceiptModelHolder receiptModelHolder = ReceiptModelHolder.getInstance();
//                    receiptModelHolder.setReceiptModel(receiptModel);
//
//                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/ReceiptDetail.fxml")));
//                    StageHelper.startStage(root);
//                    // Hide host
//                    AnchorPane host = MainNavigatorController.instance.getHost();
//                    host.setDisable(true);
//                }
//            } catch (Exception ex) {
//                System.out.println(ex.getMessage());
//                System.out.println(Arrays.toString(ex.getStackTrace()));
//            }
//        }
    }

    // Refresh table
    public void refresh() {
        initialize(null, null);
    }
}

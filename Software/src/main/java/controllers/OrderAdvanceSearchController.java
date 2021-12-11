package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dataModel.search.OrderSearchModel;
import entities.Merchandise;
import entities.Orders;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import repositories.MerchandiseRepository;
import repositories.OrdersRepository;
import utils.StageHelper;

import java.net.URL;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderAdvanceSearchController implements Initializable {

    @FXML
    private AnchorPane host;

    @FXML
    private TextField customerNameHolder;

    @FXML
    private DatePicker toDatePicker;

    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private JFXComboBox<String> orderStatusPicker;

    @FXML
    private JFXComboBox<String> orderTypePicker;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton closeButton;

    @FXML
    private ImageView close;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // FILL ORDER STATUS PICKER
        orderStatusPicker.getItems().addAll("Tất cả", "Hoàn tất", "Chưa hoàn tất");
        orderStatusPicker.setValue(OrderCategoryController.searchStatus == null
                ? "Tất cả"
                : OrderCategoryController.searchStatus);

        // FILL ORDER TYPE PICKER
        orderTypePicker.getItems().addAll("Tất cả", "Nhập hàng", "Bán hàng");
        orderTypePicker.setValue(OrderCategoryController.searchType == null
                ? "Tất cả"
                : OrderCategoryController.searchType);

        // SET FROM DATE
        if (OrderCategoryController.searchFromDate != null) {
            fromDatePicker.setValue(OrderCategoryController.searchFromDate.toInstant().atZone(ZoneId.of("Etc/GMT-8")).toLocalDate());
        }

        // SET TO DATE
        if (OrderCategoryController.searchToDate != null) {
            toDatePicker.setValue(OrderCategoryController.searchToDate.toInstant().atZone(ZoneId.of("Etc/GMT-8")).toLocalDate());
        }

        // SET CUSTOMER NAME
        if (OrderCategoryController.searchCustomerName != null) {
            customerNameHolder.setText(OrderCategoryController.searchCustomerName);
        }
    }

    @FXML
    void applyFilter(ActionEvent event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        OrderSearchModel orderSearchModel = new OrderSearchModel();
        orderSearchModel.setCustomerName((customerNameHolder.getText() != null || !customerNameHolder.getText().isEmpty())
                ? customerNameHolder.getText()
                : null);
        orderSearchModel.setOrderStatus(orderStatusPicker.getValue());
        orderSearchModel.setOrderType(orderTypePicker.getValue());
        orderSearchModel.setFromDate(fromDatePicker.getValue() != null
                ? Date.from(fromDatePicker.getValue().atStartOfDay(ZoneId.of("Etc/GMT+8")).toInstant())
                : null);
        orderSearchModel.setToDate(toDatePicker.getValue() != null
                ? Date.from(toDatePicker.getValue().atStartOfDay(ZoneId.of("Etc/GMT+8")).toInstant())
                : null);

        List<Orders> searchResult = OrdersRepository.advanceSearch(orderSearchModel);

        // APPLY FILTER
        OrderCategoryController.getInstance().applyFilter(searchResult, orderSearchModel);
//        // Close stage
        StageHelper.closeStage(event);
//        // Show host
        MainNavigatorController.instance.getHost().setDisable(false);
    }

    @FXML
    void close(MouseEvent event) {
        StageHelper.closeStage(event);
        // SHOW HOST
        MainNavigatorController.instance.getHost().setDisable(OrderCategoryController.getInstance().ordersAddUpdateIsShow);
    }

    @FXML
    void requestFocus(MouseEvent event) {
        host.requestFocus();
    }
}

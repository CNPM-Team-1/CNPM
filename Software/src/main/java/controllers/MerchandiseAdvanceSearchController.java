package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dataModel.MerchandiseSearchModel;
import entities.Merchandise;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import repositories.MerchandiseRepository;
import utils.StageHelper;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MerchandiseAdvanceSearchController implements Initializable {
    @FXML
    private AnchorPane host;

    @FXML
    private TextField nameHolder;

    @FXML
    private TextField fromPriceHolder;

    @FXML
    private TextField toPriceHolder;

    @FXML
    private JFXComboBox<String> typePicker;

    @FXML
    private JFXComboBox<String> brandPicker;

    @FXML
    private JFXComboBox<String> inStockPicker;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton closeButton;

    @FXML
    private ImageView close;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // FILL TYPE COMBOBOX
        typePicker.getItems().add("Tất cả");
        List<String> allMerchTypes = MerchandiseRepository.getAllMerchandiseTypes();
        if (allMerchTypes != null && !allMerchTypes.isEmpty()) {
            allMerchTypes.forEach(t -> typePicker.getItems().add(t));
            typePicker.setValue(MerchandiseCategoryController.searchType != null && !MerchandiseCategoryController.searchType.isEmpty()
                    ? MerchandiseCategoryController.searchType
                    : "Tất cả");
        }

        // FILL BRAND COMBOBOX
        brandPicker.getItems().add("Tất cả");
        List<String> allMerchBrands = MerchandiseRepository.getAllMerchandiseBrands();
        if (allMerchBrands != null && !allMerchBrands.isEmpty()) {
            allMerchBrands.forEach(t -> brandPicker.getItems().add(t));
            brandPicker.setValue(MerchandiseCategoryController.searchBrand != null && !MerchandiseCategoryController.searchBrand.isEmpty()
                    ? MerchandiseCategoryController.searchBrand
                    : "Tất cả");
        }

        // FILL IN-STOCK COMBOBOX
        inStockPicker.getItems().add("Tất cả");
        inStockPicker.getItems().add("Có");
        inStockPicker.getItems().add("Không");
        inStockPicker.setValue(MerchandiseCategoryController.searchInStock != null
                ? MerchandiseCategoryController.searchInStock ? "Có" : "Không"
                : "Tất cả");

        // SET FROM PRICE
        if (MerchandiseCategoryController.searchFromPrice != null) {
            fromPriceHolder.setText(String.format("%.0f", MerchandiseCategoryController.searchFromPrice));
        }

        // SET TO PRICE
        if (MerchandiseCategoryController.searchToPrice != null) {
            toPriceHolder.setText(String.format("%.0f", MerchandiseCategoryController.searchToPrice));
        }

        // SET NAME
        if (MerchandiseCategoryController.searchName != null) {
            nameHolder.setText(MerchandiseCategoryController.searchName);
        }
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

    @FXML
    void applyFilter(ActionEvent event) {
        Boolean inStock = switch (inStockPicker.getValue()) {
            case "Có" -> true;
            case "Không" -> false;
            default -> null;
        };

        MerchandiseSearchModel merchandiseSearch = new MerchandiseSearchModel(
                nameHolder.getText(),
                (fromPriceHolder.getText() == null || fromPriceHolder.getText().isEmpty())
                        ? null : Double.valueOf(fromPriceHolder.getText()),
                (toPriceHolder.getText() == null || toPriceHolder.getText().isEmpty())
                        ? null : Double.valueOf(toPriceHolder.getText()),
                typePicker.getValue(),
                brandPicker.getValue(),
                inStock
        );
        MerchandiseSearchModel searchModel = merchandiseSearch.filterData();
        List<Merchandise> searchResult = MerchandiseRepository.advanceSearch(merchandiseSearch.filterData());

        // APPLY FILTER
        MerchandiseCategoryController.getInstance().applyFilter(searchResult, searchModel);
        // Close stage
        StageHelper.closeStage(event);
        // Show host
        MainNavigatorController.instance.getHost().setDisable(false);
    }
}

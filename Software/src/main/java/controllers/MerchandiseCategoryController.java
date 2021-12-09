package controllers;

import dataModel.MerchandiseModel;
import dataModel.MerchandiseSearchModel;
import entities.Merchandise;
import holders.MerchandiseHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import repositories.MerchandiseRepository;
import utils.StageHelper;
import utils.TableHelper;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class MerchandiseCategoryController implements Initializable {
    @FXML
    private AnchorPane host;
    @FXML
    private TableView<MerchandiseModel> contentTable;
    @FXML
    private TableColumn<Merchandise, String> nameCol;
    @FXML
    private TableColumn<Merchandise, String> typeCol;
    @FXML
    private TableColumn<Merchandise, Integer> quantityCol;
    @FXML
    private TableColumn<Merchandise, String> priceCol;

    public static String searchName;
    public static Double searchFromPrice;
    public static Double searchToPrice;
    public static String searchType;
    public static String searchBrand;
    public static Boolean searchInStock;


    // For other class call function from this class
    public static MerchandiseCategoryController instance;

    public MerchandiseCategoryController() {
        instance = this;
    }

    public static MerchandiseCategoryController getInstance() {
        return instance;
    }
    ///

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Merchandise> merchandiseList = MerchandiseRepository.getAll();
        if (merchandiseList != null) {
            TableHelper.setMerchandiseTable(merchandiseList, contentTable, nameCol, typeCol, quantityCol, priceCol);
        }
    }

    @FXML
    void insert(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/MerchandiseAdd.fxml")));
        StageHelper.startStage(root);
        // Hide host
        MainNavigatorController.instance.getHost().setDisable(true);
    }

    @FXML
    void select(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) {
            MerchandiseModel merchandiseModel = contentTable.getSelectionModel().getSelectedItem();
            contentTable.getSelectionModel().clearSelection();
            // Store Merchandise to use in another class
            if (merchandiseModel != null) {
                MerchandiseHolder.getInstance().setMerchandise(merchandiseModel.toMerchandise());
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/MerchandiseUpdate.fxml")));
                StageHelper.startStage(root);
                // Hide host
                MainNavigatorController.instance.getHost().setDisable(true);
            }
        }
    }

    @FXML
    void openAdvanceSearch(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/MerchandiseAdvanceSearch.fxml")));
            StageHelper.startStage(root);
            // HIDE HOST
            MainNavigatorController.instance.getHost().setDisable(true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    // Refresh table
    public void refresh() {
        searchFromPrice = null;
        searchToPrice = null;
        searchType = null;
        searchBrand = null;
        searchInStock = null;

        this.initialize(null, null);
    }

    // APPLY FILTER
    public void applyFilter(List<Merchandise> filterResult, MerchandiseSearchModel searchModel) {
        if (filterResult != null) {
            TableHelper.setMerchandiseTable(filterResult, contentTable, nameCol, typeCol, quantityCol, priceCol);

            searchName = searchModel.getName();
            searchFromPrice = searchModel.getFromPrice();
            searchToPrice = searchModel.getToPrice();
            searchType = searchModel.getType();
            searchBrand = searchModel.getBrand();
            searchInStock = searchModel.getInStock();
        }
    }

    @FXML
    void requestFocus(MouseEvent event) {
        host.requestFocus();
    }
}

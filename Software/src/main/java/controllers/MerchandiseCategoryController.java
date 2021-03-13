package controllers;

import com.jfoenix.controls.JFXButton;
import entities.Merchandise;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.StageHelper;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class MerchandiseCategoryController implements Initializable {

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXButton updateButton;

    @FXML
    private JFXButton deleteButton;
    @FXML
    private TextField idText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField typeText;

    @FXML
    private TextField branchText;

    @FXML
    private TextField priceText;

    @FXML
    private TextField importText;

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label branchLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label importPriceLabel;

    @FXML
    private TableColumn<Merchandise, String> idColumn;

    @FXML
    private TableColumn<Merchandise, String> nameColumn;

    @FXML
    private TableColumn<Merchandise, String> typeColumn;

    @FXML
    private TableColumn<Merchandise, String> BranchColumn;

    @FXML
    private TableColumn<Merchandise, Integer> priceColumn;

    @FXML
    private TableColumn<Merchandise, Integer> ipriceColumn;

    @FXML
    private TableColumn<Merchandise, Date> createColumn;

    @FXML
    private TableColumn<Merchandise, Date> updateColumn;

    @FXML
    private TableColumn<Merchandise, Boolean> editColumn;

    @FXML
    private TableView<Merchandise> tableMerchandise;
    private Connection conn;
    private ObservableList<Merchandise> list;
    @FXML
    void Add(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/MerchandiseAdd.fxml")));
            StageHelper.startStage(root);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }


    }

    @FXML
    void Delete(ActionEvent event) {

    }

    @FXML
    void Update(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    TableViewDisplay();

    }
    public void TableViewDisplay(){

        final String DB_URL = "jdbc:mysql://localhost:8889/cnpm-cellphones";
        String username = "root";
        String password = "root";
        try {
            // Create a connection to the database.
            conn = DriverManager.getConnection(DB_URL,username,password);
            Statement stmt = conn.createStatement();
            list = FXCollections.observableArrayList();
            String sqlStatement = "SELECT * FROM MERCHANDISE";
            ResultSet result = stmt.executeQuery(sqlStatement);
            while (result.next()) {
                Merchandise m = new Merchandise();
                m.setId(result.getString("id"));
                m.setName(result.getString("name"));
                m.setType(result.getString("type"));
                m.setBranch(result.getString("branch"));
                m.setPrice(result.getInt("price"));
                m.setImportPrice(result.getInt("import_price"));
                m.setCreatedDate(result.getDate("created_date"));
                m.setUpdatedDate(result.getDate("updated_date"));
                list.add(m);
            }
            idColumn.setCellValueFactory(new PropertyValueFactory<Merchandise, String>("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<Merchandise, String>("name"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<Merchandise, String>("type"));
            BranchColumn.setCellValueFactory(new PropertyValueFactory<Merchandise, String>("branch"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<Merchandise, Integer>("price"));
            ipriceColumn.setCellValueFactory(new PropertyValueFactory<Merchandise, Integer>("importPrice"));
            createColumn.setCellValueFactory(new PropertyValueFactory<Merchandise, Date>("createdDate"));
            updateColumn.setCellValueFactory(new PropertyValueFactory<Merchandise, Date>("updatedDate"));
            tableMerchandise.setItems(list);
            conn.close();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

    }
}

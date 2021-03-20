package controllers;

import com.jfoenix.controls.JFXButton;
import entities.Merchandise;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.w3c.dom.events.MouseEvent;
import repositories.MerchandiseRepository;
import utils.HibernateUtils;
import utils.StageHelper;
import utils.TableHelper;

import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class MerchandiseCategoryController implements Initializable {

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXButton updateButton;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private JFXButton SearchButton;

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
    private TableView<Merchandise> tableMerchandise;
    private Connection conn;
    private ObservableList<Merchandise> list;
    @FXML
    private TextField searchTF;
    @FXML
    private JFXButton refresh;

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
    public void Update(javafx.scene.input.MouseEvent e) {
        try {
            URL fxmlLocation = getClass().getClassLoader().getResource("fxml/MerchandiseUpdate.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();
            MerchandiseUpdateController controller = loader.<MerchandiseUpdateController>getController();
            Merchandise select = tableMerchandise.getSelectionModel().getSelectedItem();
            controller.setTextMerchandise(select);
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.show();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableViewDisplay();
    }

    public void TableViewDisplay() {

        final String DB_URL = "jdbc:mysql://localhost:8889/cnpm-cellphones";
        String username = "root";
        String password = "root";
        try {
            // Create a connection to the database.
            conn = DriverManager.getConnection(DB_URL, username, password);
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
    @FXML
    void Delete(ActionEvent event) {
        Merchandise select = tableMerchandise.getSelectionModel().getSelectedItem();
        final String DB_URL = "jdbc:mysql://localhost:8889/cnpm-cellphones";
        String username = "root";
        String password = "root";
        try {
            conn = DriverManager.getConnection(DB_URL, username, password);
            Statement stmt = conn.createStatement();
            String sqlStatement = "DELETE FROM MERCHANDISE WHERE id ='" + select.getId() + "'";
            System.out.println(sqlStatement);
            stmt.executeUpdate(sqlStatement);
            list.remove(select);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @FXML
    void Search(ActionEvent event) {
        try {
            SessionFactory factory = HibernateUtils.getSessionFactory();
            Session session = factory.getCurrentSession();

            String keySearch = searchTF.getText();
            List<Merchandise> MerchandiseList = MerchandiseRepository.getAll(session);
            TableHelper.setMerchandiseTable(MerchandiseList, tableMerchandise, idColumn, nameColumn, typeColumn, BranchColumn, priceColumn, ipriceColumn);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    @FXML
    void refresh(ActionEvent event) {
        if(!list.isEmpty()){

            list.removeAll();
        }
        TableViewDisplay();
    }

    
}

package controllers;

import com.jfoenix.controls.JFXButton;
import entities.Merchandise;
import holders.MerchandiseHolder;
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
import repositories.MerchandiseRepository;
import utils.HibernateUtils;
import utils.StageHelper;
import utils.TableHelper;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class MerchandiseCategoryController implements Initializable {

    @FXML
    private TextField searchBar;

    @FXML
    private JFXButton searchButton;

    @FXML
    private JFXButton addButton;

    @FXML
    private TableView<Merchandise> contentTable;

    @FXML
    private TableColumn<Merchandise, String> nameCol;

    @FXML
    private TableColumn<Merchandise, String> branchCol;

    @FXML
    private TableColumn<Merchandise, Integer> priceCol;

    // For other class call function from this class
    public static MerchandiseCategoryController instance;

    public MerchandiseCategoryController() { instance = this; }

    public static MerchandiseCategoryController getInstance() { return instance; }
    ///

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();

        List<Merchandise> merchandiseList = MerchandiseRepository.getAll(session);
        assert merchandiseList != null;
        TableHelper.setMerchandiseTable(merchandiseList, contentTable, nameCol, branchCol, priceCol);
    }

    @FXML
    void insert(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/MerchandiseAdd.fxml")));
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
    void select(MouseEvent event) {
        if (event.getClickCount() == 2) {
            try {
                Merchandise merchandise = contentTable.getSelectionModel().getSelectedItem();
                contentTable.getSelectionModel().clearSelection();
                // Store Merchandise to use in another class
                if (merchandise != null) {
                    MerchandiseHolder merchandiseHolder = MerchandiseHolder.getInstance();
                    merchandiseHolder.setMerchandise(merchandise);
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/MerchandiseUpdate.fxml")));
                    StageHelper.startStage(root);
                    // Hide host
                    AnchorPane host = MainNavigatorController.instance.getHost();
                    host.setDisable(true);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println(Arrays.toString(ex.getStackTrace()));
            }
        }
    }

    @FXML
    void search(ActionEvent event) {
        try {
            SessionFactory factory = HibernateUtils.getSessionFactory();
            Session session = factory.getCurrentSession();

            String keySearch = searchBar.getText();
            List<Merchandise> merchandiseList = MerchandiseRepository.getLikeNameAndBranch(session, keySearch);
            assert merchandiseList != null;
            TableHelper.setMerchandiseTable(merchandiseList, contentTable, nameCol, branchCol, priceCol);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    // Refresh table
    public void refresh() {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();

        List<Merchandise> merchandiseList = MerchandiseRepository.getAll(session);
        assert merchandiseList != null;
        TableHelper.setMerchandiseTable(merchandiseList, contentTable, nameCol, branchCol, priceCol);
    }
}

package controllers;

import com.jfoenix.controls.JFXButton;
import dataModel.ImportsModel;
import entities.Imports;
import holders.ImportsHolder;
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
import repositories.ImportsDetailRepository;
import repositories.ImportsRepository;
import utils.HibernateUtils;
import utils.NumberHelper;
import utils.StageHelper;
import utils.TableHelper;

import java.net.URL;
import java.util.*;

public class ImportsCategoryController implements Initializable {
    @FXML
    private TableView<ImportsModel> contentTable;
    @FXML
    private TableColumn<ImportsModel, Date> dateCol;
    @FXML
    private TableColumn<ImportsModel, String> nameCol;
    @FXML
    private TableColumn<ImportsModel, String> descriptionCol;
    @FXML
    private TableColumn<ImportsModel, Integer> quantityCol;
    @FXML
    private TableColumn<ImportsModel, String> amountCol;

    @FXML
    private AnchorPane host;
    @FXML
    private TextField searchBar;
    @FXML
    private JFXButton searchButton;
    @FXML
    private JFXButton saveButton;

    // For other class call function from this class
    public static ImportsCategoryController instance;
    public ImportsCategoryController() { instance = this; }
    public static ImportsCategoryController getInstance() { return instance; }
    ///

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
            Session session;

            // Get all imports
            session = sessionFactory.openSession();
            List<Imports> importsList = ImportsRepository.getAll(session);
            // Set imports model
            if (importsList != null) {
                List<ImportsModel> importsModelList = new ArrayList<>();
                for (Imports item : importsList) {
                    ImportsModel importsModel = new ImportsModel();
                    importsModel.setImports(item);
                    importsModel.setCreatedDate(item.getCreatedDate());
                    importsModel.setCustomerName(item.getOrders().getCustomer().getFullName());
                    importsModel.setDescription(item.getDescription());

                    session = sessionFactory.openSession();
                    int sumQuantity = Math.toIntExact(ImportsDetailRepository.getSumQuantityByImportsId(session, item.getId()));
                    session = sessionFactory.openSession();
                    Long sumAmount = ImportsDetailRepository.getSumAmountByImportsId(session, item.getId());

                    importsModel.setSumQuantity(sumQuantity);
                    importsModel.setSumAmount(NumberHelper.addComma(String.valueOf(sumAmount)));
                    importsModelList.add(importsModel);
                }
                TableHelper.setImportsModelTable(importsModelList, contentTable, dateCol, nameCol, descriptionCol, quantityCol, amountCol);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    @FXML
    void save(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/ImportsAdd.fxml")));
            StageHelper.startStage(root);
            //Hide host
            AnchorPane host = MainNavigatorController.instance.getHost();
            host.setDisable(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    @FXML
    void search(ActionEvent event) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session;

        session = sessionFactory.openSession();
        List<Imports> importsList = ImportsRepository.getLikeCustomerName(session, searchBar.getText());
        // Set imports model
        if (importsList != null && importsList.size() > 0) {
            List<ImportsModel> importsModelList = new ArrayList<>();
            for (Imports item : importsList) {
                ImportsModel importsModel = new ImportsModel();
                importsModel.setImports(item);
                importsModel.setCreatedDate(item.getCreatedDate());
                importsModel.setCustomerName(item.getOrders().getCustomer().getFullName());
                importsModel.setDescription(item.getDescription());

                session = sessionFactory.openSession();
                int sumQuantity = Math.toIntExact(ImportsDetailRepository.getSumQuantityByImportsId(session, item.getId()));
                session = sessionFactory.openSession();
                Long sumAmount = ImportsDetailRepository.getSumAmountByImportsId(session, item.getId());

                importsModel.setSumQuantity(sumQuantity);
                importsModel.setSumAmount(NumberHelper.addComma(String.valueOf(sumAmount)));
                importsModelList.add(importsModel);
            }
            TableHelper.setImportsModelTable(importsModelList, contentTable, dateCol, nameCol, descriptionCol, quantityCol, amountCol);
        }
    }

    @FXML
    void select(MouseEvent event) {
        if (event.getClickCount() == 2) {
            try {
                Imports imports = contentTable.getSelectionModel().getSelectedItem().getImports();
                contentTable.getSelectionModel().clearSelection();
                // Store Imports to use in another class
                if (imports != null) {
                    ImportsHolder importsHolder = ImportsHolder.getInstance();
                    importsHolder.setImports(imports);
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/ImportsUpdate.fxml")));
                    StageHelper.startStage(root);
                    // Hide host
                    MainNavigatorController.getInstance().getHost().setDisable(true);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    void requestFocus(MouseEvent event) {
        host.requestFocus();
    }

    // Refresh table
    public void refresh() {
        initialize(null, null);
    }
}

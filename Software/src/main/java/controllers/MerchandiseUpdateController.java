package controllers;

import com.jfoenix.controls.JFXButton;
import entities.Customer;
import entities.Merchandise;
import holders.MerchandiseHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.MerchandiseRepository;
import utils.AlertBoxHelper;
import utils.HibernateUtils;
import utils.NumberHelper;
import utils.StageHelper;
import validation.MerchandiseValidation;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MerchandiseUpdateController implements Initializable {
    @FXML
    private AnchorPane host;
    @FXML
    private TextField nameHolder;
    @FXML
    private TextField priceHolder;
    @FXML
    private TextField importPriceHolder;
    @FXML
    private TextField typeHolder;
    @FXML
    private TextField branchHolder;
    @FXML
    private TextField quantityHolder;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private ImageView close;
    @FXML
    private Label errorMessage;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXButton updateButton;

    // Get Merchandise from MerchandiseCategoryController select(MouseEvent event)
    MerchandiseHolder merchandiseHolder = MerchandiseHolder.getInstance();
    Merchandise merchandise = merchandiseHolder.getMerchandise();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session;
        // Set Merchandise in update window
        if (merchandise != null) {
            nameHolder.setText(merchandise.getName());
            priceHolder.setText(merchandise.getPrice());
            importPriceHolder.setText(merchandise.getImportPrice());
            typeHolder.setText(merchandise.getType());
            branchHolder.setText(merchandise.getBranch());
            quantityHolder.setText(merchandise.getQuantity().toString());
        }
        // Set autocomplete for merchandise type
        session = sessionFactory.openSession();
        List<String> allMerchandiseTypes = MerchandiseRepository.getAllMerchandiseTypes(session);
        if (allMerchandiseTypes != null) {
            AutoCompletionBinding<String> tHolder = TextFields.bindAutoCompletion(typeHolder, allMerchandiseTypes);
        }
    }

    @FXML
    void update(ActionEvent event) {
        try {
            // Create session
            SessionFactory factory = HibernateUtils.getSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            Merchandise merchandise = merchandiseHolder.getMerchandise();
            merchandise.setName(nameHolder.getText());
            merchandise.setType(typeHolder.getText());
            merchandise.setBranch(branchHolder.getText());
            merchandise.setPrice(NumberHelper.removeComma(priceHolder.getText()));
            merchandise.setImportPrice(NumberHelper.removeComma(importPriceHolder.getText()));
            merchandise.setQuantity(!quantityHolder.getText().isEmpty() ? Integer.parseInt(quantityHolder.getText()) : 0);

            List<String> validateUpdate = MerchandiseValidation.validateUpdate(session, merchandise);
            if (validateUpdate.size() == 0) {
                // Close stage
                StageHelper.closeStage(event);

                // Update customer info
                session = factory.openSession();
                session.beginTransaction();
                session.saveOrUpdate(merchandise);
                session.getTransaction().commit();

                // Show alert box
                AlertBoxHelper.showMessageBox("Cập nhật thành công");

                // Refresh content table
                MerchandiseCategoryController.getInstance().refresh();

                // Set merchandise holder
                merchandiseHolder.setMerchandise(merchandise);

                // Unhide host
                AnchorPane host = MainNavigatorController.instance.getHost();
                host.setDisable(false);
            } else {
                errorMessage.setText(validateUpdate.get(0));
                if (session.getTransaction().isActive()) {
                    session.getTransaction().commit();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace().toString());
        }
    }

    @FXML
    void delete(ActionEvent event) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();

        // Close stage
        StageHelper.closeStage(event);

        // Delete customer
        session.beginTransaction();
        Merchandise merchandise = merchandiseHolder.getMerchandise();
        session.delete(merchandise);
        session.getTransaction().commit();

        // Show alert box
        AlertBoxHelper.showMessageBox("Xoá thành công");

        // Refresh content table
        MerchandiseCategoryController.getInstance().refresh();

        // Clear customer holder
        merchandiseHolder.setMerchandise(null);

        // Unhide host
        AnchorPane host = MainNavigatorController.instance.getHost();
        host.setDisable(false);
    }

    @FXML
    void close(MouseEvent event) {
        // Clear merchandise holder
        merchandiseHolder.setMerchandise(null);
        StageHelper.closeStage(event);

        // Unhide host
        AnchorPane host = MainNavigatorController.instance.getHost();
        host.setDisable(false);
    }
}

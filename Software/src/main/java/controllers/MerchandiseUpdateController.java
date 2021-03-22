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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
        // Set Merchandise in update window
        if (merchandise != null) {
            nameHolder.setText(merchandise.getName());
            priceHolder.setText(NumberHelper.addComma(merchandise.getPrice().toString()));
            importPriceHolder.setText(NumberHelper.addComma(merchandise.getImportPrice().toString()));
            typeHolder.setText(merchandise.getType());
            branchHolder.setText(merchandise.getBranch());
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
            merchandise.setPrice(Integer.parseInt(NumberHelper.removeComma(priceHolder.getText())));
            merchandise.setImportPrice(Integer.parseInt(NumberHelper.removeComma(importPriceHolder.getText())));

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

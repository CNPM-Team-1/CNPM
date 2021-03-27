package controllers;

import com.jfoenix.controls.JFXButton;
import entities.Merchandise;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import utils.*;
import validation.MerchandiseValidation;

import java.util.List;

public class MerchandiseAddController {
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
    private JFXButton addButton;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private ImageView close;
    @FXML
    private Label errorMessage;

    @FXML
    void close(MouseEvent event) {
        StageHelper.closeStage(event);
        // Unhide host
        AnchorPane host = MainNavigatorController.instance.getHost();
        host.setDisable(OrderCategoryController.getInstance().ordersAddUpdateIsShow);
    }

    @FXML
    void save(ActionEvent event) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();

        Merchandise merchandise = new Merchandise();
        merchandise.setId(UUIDHelper.generateType4UUID().toString());
        merchandise.setName(nameHolder.getText());
        merchandise.setBranch(branchHolder.getText());
        merchandise.setType(typeHolder.getText());
        merchandise.setImportPrice(importPriceHolder.getText());
        merchandise.setPrice(priceHolder.getText());
        merchandise.setQuantity(!quantityHolder.getText().isEmpty() ? Integer.parseInt(quantityHolder.getText()) : 0);

        List<String> validateInsert = MerchandiseValidation.validateInsert(session, merchandise);
        if (validateInsert.size() == 0) {
            // Save new merchandise
            session = factory.openSession();
            session.beginTransaction();
            session.save(merchandise);
            session.getTransaction().commit();

            // Close stage
            StageHelper.closeStage(event);

            // Show alert box
            AlertBoxHelper.showMessageBox("Thêm thành công");

            // Refresh content table
            MerchandiseCategoryController.getInstance().refresh();

            // Unhide host only when orders add is not show
            AnchorPane host = MainNavigatorController.instance.getHost();
            host.setDisable(OrderCategoryController.getInstance().ordersAddUpdateIsShow);

            // Refresh Orders merchandise list
            if (OrderAddController.getInstance() != null) {
                OrderAddController.getInstance().initialize(null, null);
            }
        } else {
            errorMessage.setText(validateInsert.get(0));
            if (session.getTransaction().getStatus() != TransactionStatus.COMMITTED) {
                session.getTransaction().commit();
            }
        }
    }
}


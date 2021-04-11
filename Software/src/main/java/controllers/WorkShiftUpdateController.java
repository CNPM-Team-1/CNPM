package controllers;

import com.jfoenix.controls.JFXButton;
import entities.WorkShift;
import holders.WorkShiftHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.AlertBoxHelper;
import utils.HibernateUtils;
import utils.StageHelper;
import validation.WorkShiftValidation;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WorkShiftUpdateController implements Initializable {
    @FXML
    private AnchorPane host;
    @FXML
    private TextField nameHolder;
    @FXML
    private ComboBox<String> timeInHolder;
    @FXML
    private ComboBox<String> timeOutHolder;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private ImageView close;
    @FXML
    private Label errorMessage;
    @FXML
    private JFXButton updateButton;
    @FXML
    private JFXButton deleteButton;

    // Get WorkShift from WorkShiftCategoryController select(MouseEvent event)
    WorkShift chosenWorkShift = WorkShiftHolder.getInstance().getWorkShift();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Add hours to ComboBox
        List<String> hours = new ArrayList<>();
        for (int i = 0; i <= 24; i++) {
            hours.add(i + ":00");
            hours.add(i + ":30");
        }
        timeInHolder.getItems().addAll(hours);
        timeOutHolder.getItems().addAll(hours);
        // Set WorkShift data
        nameHolder.setText(chosenWorkShift.getName());
        timeInHolder.setValue(chosenWorkShift.getStartTime());
        timeOutHolder.setValue(chosenWorkShift.getEndTime());
    }

    @FXML
    void close(MouseEvent event) {
        StageHelper.closeStage(event);
        // Unhide host
        MainNavigatorController.instance.getHost().setDisable(false);
    }

    @FXML
    void delete(ActionEvent event) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session;

        List<String> validateDelete = WorkShiftValidation.validateDelete(sessionFactory, chosenWorkShift);
        if (validateDelete.size() == 0) {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(chosenWorkShift);
            session.getTransaction().commit();

            // Clear Holder
            chosenWorkShift = null;
            // Show alert box
            AlertBoxHelper.showMessageBox("Xoá thành công");
            // Refresh content table
            WorkShiftCategoryController.getInstance().initialize(null, null);
            // Unhide host only when orders add is not show
            MainNavigatorController.instance.getHost().setDisable(false);
            // Close stage
            StageHelper.closeStage(event);
        } else {
            errorMessage.setText(validateDelete.get(0));
        }
    }

    @FXML
    void update(ActionEvent event) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session;

        chosenWorkShift.setName(nameHolder.getText());
        chosenWorkShift.setStartTime(timeInHolder.getValue());
        chosenWorkShift.setEndTime(timeOutHolder.getValue());
        List<String> validateUpdate = WorkShiftValidation.validateUpdate(sessionFactory, chosenWorkShift);
        if (validateUpdate.size() == 0) {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(chosenWorkShift);
            session.getTransaction().commit();

            // Clear Holder
            chosenWorkShift = null;
            // Show alert box
            AlertBoxHelper.showMessageBox("Cập nhật thành công");
            // Refresh content table
            WorkShiftCategoryController.getInstance().initialize(null, null);
            // Unhide host only when orders add is not show
            MainNavigatorController.instance.getHost().setDisable(false);
            // Close stage
            StageHelper.closeStage(event);
            // Refresh work table
            WorkTableCategoryController.getInstance().initialize(null, null);
        } else {
            errorMessage.setText(validateUpdate.get(0));
        }

    }
}

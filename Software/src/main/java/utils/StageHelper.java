package utils;

import controllers.EmployeeCategoryController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class StageHelper {

    public static void startStage(Parent root) {
        final Stage stage = new Stage();
        Scene scene = new Scene(root);
        // Hide Stage
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        setDraggable(root, stage);

        stage.setScene(scene);
        stage.show();
    }

    public static void startStage2(String fileName) throws ClassNotFoundException, IOException {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
        Class<?> act = Class.forName(stackTraceElement.getClassName());
        Parent root = FXMLLoader.load(Objects.requireNonNull(act.getClassLoader().getResource(fileName)));

        final Stage stage = new Stage();
        Scene scene = new Scene(root);
        // Hide Stage
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        setDraggable(root, stage);

        stage.setScene(scene);
        stage.show();
    }

    public static void startStage(Parent root, Object a) {
        final Stage stage = new Stage();
        Scene scene = new Scene(root);
        // Hide Stage
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        setDraggable(root, stage);
        stage.setUserData(a);
        stage.setScene(scene);
        stage.show();
    }

    public static void closeStage(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public static void closeStage(MouseEvent mouseEvent) {
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public static void minimizeStage(MouseEvent mouseEvent) {
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setIconified(true);
    }

    public static void setDraggable(Parent root, Stage stage) {
        // Draggable
        final double[] xOffset = {0};
        final double[] yOffset = {0};
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset[0] = event.getSceneX();
                yOffset[0] = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset[0]);
                stage.setY(event.getScreenY() - yOffset[0]);
            }
        });
    }
}

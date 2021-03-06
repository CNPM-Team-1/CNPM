package start;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.SessionFactory;
import utils.HibernateUtils;

import java.util.Arrays;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(final Stage primaryStage) {
        try {
            SessionFactory factory = HibernateUtils.getSessionFactory();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Login.fxml"));
            Scene scene = new Scene(root);

            // Hide border
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);

            // Draggable
            root.setOnMousePressed(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });
            root.setOnMouseDragged(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    primaryStage.setX(event.getScreenX() - xOffset);
                    primaryStage.setY(event.getScreenY() - yOffset);
                }
            });

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));

        }
    }
}

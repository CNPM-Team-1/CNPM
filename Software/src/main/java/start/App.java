package start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import utils.HibernateUtils;
import utils.StageHelper;

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
            StageHelper.startStage(root);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));

        }
    }
}

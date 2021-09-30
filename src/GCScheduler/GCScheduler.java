package GCScheduler;

import GCScheduler.dao.JDBC.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Main class of the application.
 */
public class GCScheduler extends Application {
    private static Stage primaryStage;

    /**
     * Launches the application GUI.
     * @param primaryStage Application window
     * @throws Exception primary stage did not load.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        GCScheduler.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/Login.fxml")));
        GCScheduler.primaryStage.setTitle("GC Scheduler");
        GCScheduler.primaryStage.setScene(new Scene(root));
        GCScheduler.primaryStage.show();
    }

    /**
     * Gets the Primary Stage
     * @return Primary Stage.
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Main method to start application.
     * @param args array of arguments for the application.
     */
    public static void main(String[] args) {
//        Locale.setDefault(new Locale("fr"));
        JDBC.startConnection();
        launch(args);
        JDBC.stopConnection();
    }
}

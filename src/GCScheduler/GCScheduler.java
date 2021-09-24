package GCScheduler;

import GCScheduler.dao.JDBC.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class GCScheduler extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        GCScheduler.primaryStage = primaryStage;
        //TODO set root back to login.
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/Schedule.fxml")));
        GCScheduler.primaryStage.setTitle("GC Scheduler");
        GCScheduler.primaryStage.setScene(new Scene(root));
        GCScheduler.primaryStage.show();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
//        Locale.setDefault(new Locale("fr"));
        JDBC.startConnection();
        launch(args);
        JDBC.stopConnection();
    }
}

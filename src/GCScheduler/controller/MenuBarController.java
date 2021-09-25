package GCScheduler.controller;

import GCScheduler.dao.JDBC.JDBC;
import GCScheduler.model.Scheduler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.sql.SQLException;

public class MenuBarController {

    @FXML private MenuBar menuBar;
    @FXML private Menu fileMenu;
    @FXML private MenuItem refreshMItem;
    @FXML private MenuItem quitMItem;
    @FXML private Menu editMenu;
    @FXML private Menu helpMenu;
    @FXML private MenuItem aboutMitem;

    public void initialize() {
        refreshData();
    }

    @FXML void aboutListener(ActionEvent event) {

    }

    @FXML void refreshListener(ActionEvent event) {
        refreshData();
    }

    @FXML void quitListener(ActionEvent event) {
        JDBC.stopConnection();
        System.exit(0);
    }

    public static void refreshData() {
        try {
            if (JDBC.getConnection().isValid(JDBC.getTimeout())) {
                JDBC.getData();
                System.out.println("MenuBar refreshed data from DB.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Application connection timed-out.");
        }
        Scheduler.setupCountries();
        Scheduler.setupFirstLevelDivs();
        Scheduler.setupAppointments();
        System.out.println("Scheduler data is set.");
    }
}

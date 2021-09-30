package GCScheduler.controller;

import GCScheduler.dao.JDBC.JDBC;
import GCScheduler.model.Scheduler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.sql.SQLException;

/**
 * Controller for MenuBar.fxml initializes data for the application.
 */
public class MenuBarController {

    @FXML private MenuBar menuBar;
    @FXML private Menu fileMenu;
    @FXML private MenuItem refreshMItem;
    @FXML private MenuItem quitMItem;
    @FXML private Menu editMenu;
    @FXML private Menu helpMenu;
    @FXML private MenuItem aboutMitem;

    /**
     * Method called when Controller is loaded. Calls method refreshData.
     */
    public void initialize() {
        refreshData();
    }

    /**
     * Event Listener for the About MenuItem.
     * @param event About menuItem pressed.
     */
    @FXML void aboutListener(ActionEvent event) {

    }

    /**
     * Event Listener for the Refresh MenuItem. Calls refreshData for manual pull.
     * @param event
     */
    @FXML void refreshListener(ActionEvent event) {
        refreshData();
    }

    /**
     * Event Listener for the Quit MenuItem. Closes the application.
     * @param event Quit menu item press.
     */
    @FXML void quitListener(ActionEvent event) {
        JDBC.stopConnection();
        System.exit(0);
    }

    /**
     * Uses Dao to pull data and calls Scheduler methods to make necessary links for local data in the application.
     */
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

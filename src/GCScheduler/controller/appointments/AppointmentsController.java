package GCScheduler.controller.appointments;

import GCScheduler.model.Appointment;
import GCScheduler.model.Scheduler;
import GCScheduler.utilities.DateTimeConv;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * Class to control the Appointments.fxml nested in the Schedule.fxml.
 */
public class AppointmentsController {
    private ZonedDateTime date;
    @FXML private Button prevButton;
    @FXML private Label monthWeekLabel;
    @FXML private Button nextButton;
    @FXML private RadioButton allRadio;
    @FXML private ToggleGroup apptViewGroup;
    @FXML private RadioButton monthRadio;
    @FXML private RadioButton weekRadio;
    @FXML private Label viewLabel;
    @FXML private TableView<Appointment> apptTable;
    @FXML private TableColumn<Appointment, Integer> apptIdCol;
    @FXML private TableColumn<Appointment, String> typeCol;
    @FXML private TableColumn<Appointment, String> titleCol;
    @FXML private TableColumn<Appointment, String> descriptionCol;
    @FXML private TableColumn<Appointment, String> locationCol;
    @FXML private TableColumn<Appointment, String> startCol;
    @FXML private TableColumn<Appointment, String> endCol;
    @FXML private TableColumn<Appointment, Integer> cxIdCol;
    @FXML private TableColumn<Appointment, Integer> usrIdCol;
    @FXML private TableColumn<Appointment, String> contactCol;
    @FXML private Label errorLabel;
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;

    /**
     * Method to enable or disable next and prev buttons for Month and Week selections.
     * @param b true disables the buttons.
     */
    private void disableMWButtons(boolean b){
        prevButton.setDisable(b);
        nextButton.setDisable(b);
    }

    /**
     * Called when Scheduler.fxml is loaded. Initializes the Tableview to all appointments.
     */
    public void initialize() {
        apptTable.setItems(Scheduler.getAllAppointments());
        apptIdCol.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        startCol.setCellValueFactory(p -> new SimpleStringProperty(DateTimeConv.dateToStrLocal(p.getValue().getStart())));
        endCol.setCellValueFactory(p -> new SimpleStringProperty(DateTimeConv.dateToStrLocal(p.getValue().getEnd())));
        cxIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        usrIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        contactCol.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getContact().getContactName()));
    }

    /**
     * Opens the Add appointment form.
     * @param event add button press.
     */
    @FXML void addButtonListener(ActionEvent event) {
        // open add appointment form.
    }

    /**
     * Sets the TableView to all appointments, the default view.
     * @param event all radio selected.
     */
    @FXML void allRadioListener(ActionEvent event) {
        disableMWButtons(true);
        monthWeekLabel.setText("All");
        apptTable.setItems(Scheduler.getAllAppointments());
    }

    /**
     * Deletes selected appointment and shows confirmation message.
     * @param event appointment selected and delete button press.
     */
    @FXML void deleteButtonListener(ActionEvent event) {
        // delete selected item
    }

    /**
     * Sets the Table to view Appointments by Month, defaults to current month. Calls the monthApptFilter and disableMWButtons methods.
     * @param event month radio selected.
     */
    @FXML void monthRadioListener(ActionEvent event) {
        disableMWButtons(false);
        this.date = ZonedDateTime.now();
        monthApptFilter();
    }

    /**
     * Enabled in Month or Week view. Changes the current Month or Week to the next.
     * @param event on &gt; button press.
     */
    @FXML void nextButtonListener(ActionEvent event) {
        if (monthRadio.isSelected()){
            this.date = date.plusMonths(1);
            monthApptFilter();
        }
        if (weekRadio.isSelected()){
            this.date = date.plusWeeks(1);
            weekApptFilter();
        }
    }

    /**
     * Enabled in Month or Week view. Changes the current Month or Week to the prev.
     * @param event on &lt; button press.
     */
    @FXML void prevButtonListener(ActionEvent event) {
        if (monthRadio.isSelected()) {
            this.date = date.minusMonths(1);
            monthApptFilter();
        }
        if (weekRadio.isSelected()) {
            this.date = date.minusWeeks(1);
            weekApptFilter();
        }
    }

    /**
     * Opens the Update Appointment form.
     * @param event on update button press.
     */
    @FXML void updateButtonListener(ActionEvent event) {
        // opens the update appt form
    }

    /**
     * Sets the Table to view Appointments by Week, defaults to current week. Calls the weekApptFilter and disableMWButtons methods.
     * @param event week radio selected.
     */
    @FXML void weekRadioListener(ActionEvent event) {
        disableMWButtons(false);
        this.date = ZonedDateTime.now();
        weekApptFilter();
        monthWeekLabel.setText("Current Week " + DateTimeConv.getWeekOfYear(this.date));
    }

    /**
     * Applies a filtered view of appointments by month and changes the month label to selected month.
     */
    protected void monthApptFilter() {
        ObservableList<Appointment> monthFilter = FXCollections.observableArrayList();
        for (Appointment appt : Scheduler.getAllAppointments()) {
            if (DateTimeConv.localToUTC(this.date).getMonth().equals(appt.getStart().getMonth())) {
                monthFilter.add(appt);
            }
        }
        apptTable.setItems(monthFilter);
        monthWeekLabel.setText(String.valueOf(this.date.getMonth()));
        // System.out.println("Month selected is " + this.date.getMonth());
    }

    /**
     * Applies a filtered view of appointments by week and changes the label to selected week.
     */
    protected void weekApptFilter() {
        ObservableList<Appointment> weekFilter = FXCollections.observableArrayList();
        // Find monday of this week.
        DayOfWeek weekDate = this.date.getDayOfWeek();
        int daysFromMonday = weekDate.compareTo(DayOfWeek.MONDAY);
        // minus hours and minutes required.
        ZonedDateTime monday =  date.minusDays(daysFromMonday).minusHours(date.getHour()).minusMinutes(date.getMinute());
        // System.out.println("Monday of selected Week is " + monday);
        // Find appointments that are from this week's Monday to Sunday.
        for (Appointment appt : Scheduler.getAllAppointments()) {
            ZonedDateTime apptDate = DateTimeConv.utcToLocal(appt.getStart());
            if (apptDate.isAfter(monday) && apptDate.isBefore(monday.plusDays(7))){
                weekFilter.add(appt);
            }
        }
        // Set Table which displays time in system time.
        apptTable.setItems(weekFilter);
        monthWeekLabel.setText("Week " + DateTimeConv.getWeekOfYear(monday));
    }
}

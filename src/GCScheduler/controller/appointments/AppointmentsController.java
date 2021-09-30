package GCScheduler.controller.appointments;

import GCScheduler.controller.ReportsController;
import GCScheduler.dao.AppointmentDao;
import GCScheduler.dao.JDBC.AppointmentImpl;
import GCScheduler.dao.JDBC.JDBC;
import GCScheduler.model.Appointment;
import GCScheduler.model.Contact;
import GCScheduler.model.Scheduler;
import GCScheduler.utilities.DateTimeConv;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.ZonedDateTime;

/**
 * Class to control the Appointments.fxml nested in the Schedule.fxml.
 */
public class AppointmentsController {
    private ZonedDateTime date;
    @FXML private Button prevButton;
    @FXML private Label monthWeekLabel;
    @FXML private Button nextButton;
    @FXML private ComboBox<Contact> contactCombo;
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
    private boolean deleted;
    private ObservableList<Appointment> contactFilter = FXCollections.observableArrayList();

    /**
     * Called when Scheduler.fxml is loaded. Initializes the Tableview to all appointments.
     * LAMBDA EXPRESSION used to set the start and end column cells vs using the Callback method.
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
        apptTable.getSortOrder().add(startCol);
        errorLabel.setVisible(false);
        contactFilter.addAll(Scheduler.getAllAppointments()); //still used in month and week filter.
        setupContactCombo();
        ReportsController.setupApptBarChart();
    }

    /**
     * Method to enable or disable next and prev buttons for Month and Week selections.
     * @param b true disables the buttons.
     */
    private void disableMWButtons(boolean b){
        prevButton.setDisable(b);
        nextButton.setDisable(b);
    }

    /**
     * Sets up the Contact ComboBox with a Clear object and all contacts.
     */
    private void setupContactCombo() {
        //Added clear as it doubled everytime intitialize was called.
        contactCombo.getItems().clear();
        //Created clear to have a selection to clear the combo.
        Contact clear = new Contact(0,"Clear","clear@selection");
        contactCombo.getItems().add(clear);
        //All contacts.
        contactCombo.getItems().addAll(Scheduler.getAllContacts());
        contactCombo.setPromptText("By Contact");
    }

    /**
     * Opens the Add appointment form.
     * @param event add button press.
     */
    @FXML void addButtonListener(ActionEvent event) throws IOException {
        AddAppointmentFormController addController = new AddAppointmentFormController();
        Stage stage = stageFactory(addController);
        stage.setTitle("Add Appointment");
        stage.showAndWait();
        initialize();
    }

    /**
     * Sets the TableView to all appointments when the all radio is selected, the default view.
     */
    @FXML void allRadioListener() {
        disableMWButtons(true);
        monthWeekLabel.setText("All");
        apptTable.setItems(contactFilter);
        allRadio.setSelected(true);
    }

    /**
     * Filters the view based on Contact choice from contact combo box. Clear re-initializes to default.
     * Changing the combo fires the All radio button since the filtered list changes by contact.
     */
    @FXML void contactComboListener() {
        int contactId = contactCombo.getSelectionModel().getSelectedItem().getContactId();
        contactFilter.clear();
        allRadioListener();
        if (contactId != 0) {
            for (Appointment appt : Scheduler.getAllAppointments()) {
                if (appt.getContactId() == contactId) {
                    this.contactFilter.add(appt);
                }
            }
            apptTable.setItems(contactFilter);
        } else {
            apptTable.setItems(Scheduler.getAllAppointments());
            contactFilter.addAll(Scheduler.getAllAppointments());
        }
    }

    /**
     * Deletes selected appointment and shows confirmation message.
     * LAMBDA EXPRESSION used for Alert showAndWait response as it is one of the simpler options to use to get the response type.
     * @param event appointment selected and delete button press.
     */
    @FXML void deleteButtonListener(ActionEvent event) throws SQLException {
        if (apptTable.getSelectionModel().isEmpty()) {
            errorLabel.setText("Error: Please select an appointment to delete.");
            errorLabel.setVisible(true);
        } else if (JDBC.getConnection().isValid(JDBC.getTimeout())) {
            Appointment appt = apptTable.getSelectionModel().getSelectedItem();
            AppointmentDao dao = new AppointmentImpl();
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            String apptName = "Appt ID: "+appt.getApptId()+" of Type: "+appt.getApptType();
            confirm.setContentText("Are you sure you want to delete:\n"+apptName);
            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    appt.getCustomer().getAppointments().remove(appt);
                    Scheduler.getAllAppointments().remove(appt);
                    this.deleted = dao.deleteAppt(appt);
                }
            });
            if (deleted) {
                Alert info = new Alert(Alert.AlertType.INFORMATION, apptName+" was deleted.",ButtonType.OK);
                info.show();
            }
            initialize();
        }
    }

    /**
     * Sets the Table to view Appointments by Month, defaults to current month. Calls the monthApptFilter and disableMWButtons methods.
     * @param event month radio selected.
     */
    @FXML void monthRadioListener(ActionEvent event) {
        disableMWButtons(false);
        this.date = ZonedDateTime.now();
        monthApptFilter();
        monthRadio.setSelected(true);
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
    @FXML void updateButtonListener(ActionEvent event) throws IOException {
        if (!apptTable.getSelectionModel().isEmpty()) {
            UpdateAppointmentFormController updateController = new UpdateAppointmentFormController();
            Stage stage = stageFactory(updateController);
            updateController.getSelectedAppt(apptTable.getSelectionModel().getSelectedItem());
            stage.setTitle("Update Appointment");
            stage.showAndWait();
            initialize();
        } else {
            errorLabel.setText("Error: Please select an appointment to update.");
            errorLabel.setVisible(true);
        }
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
        weekRadio.setSelected(true);
    }

    /**
     * Applies a filtered view of appointments by month and changes the month label to selected month.
     */
    protected void monthApptFilter() {
        ObservableList<Appointment> monthFilter = FXCollections.observableArrayList();
        for (Appointment appt : contactFilter) {
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
        for (Appointment appt : contactFilter) {
            ZonedDateTime apptDate = DateTimeConv.utcToLocal(appt.getStart());
            if (apptDate.isAfter(monday) && apptDate.isBefore(monday.plusDays(7))){
                weekFilter.add(appt);
            }
        }
        // Set Table which displays time in system time.
        apptTable.setItems(weekFilter);
        monthWeekLabel.setText("Week " + DateTimeConv.getWeekOfYear(monday));
    }

    /**
     * Helper method to create windows for new Add or Update Forms using AppointmentForm.fxml
     * @param controller Controller for the Form.
     * @return Stage with new Scene and controller linked.
     * @throws IOException loads AppointmentForm.fxml
     */
    protected Stage stageFactory(AppointmentFormController controller) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GCScheduler/view/appointments/AppointmentForm.fxml"));
        loader.setController(controller);
        stage.setScene(new Scene(loader.load()));
        return stage;
    }
}

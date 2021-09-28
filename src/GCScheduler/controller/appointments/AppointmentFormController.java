package GCScheduler.controller.appointments;

import GCScheduler.model.*;
import GCScheduler.utilities.DateTimeConv;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * Abstract Controller Class for the Add and Delete Appointment Forms. Provides links to AppointmentForm.fxml and methods to be used in both child Classes.
 */
public abstract class AppointmentFormController {
    @FXML protected Button saveButton;
    @FXML protected Button cancelButton;
    @FXML protected TextField apptIdField;
    @FXML protected TextField titleField;
    @FXML protected TextField descField;
    @FXML protected TextField locationField;
    @FXML protected TextField typeField;
    @FXML protected DatePicker dateField;
    @FXML protected TextField startTimeField;
    @FXML protected TextField endTimeField;
    @FXML protected ComboBox<Customer> customerCombo;
    @FXML protected ComboBox<Contact> contactCombo;
    @FXML protected ComboBox<User> userCombo;
    @FXML protected Label startESTLabel;
    @FXML protected Label endESTLabel;
    @FXML protected Label errorLabel;
    private ZonedDateTime startValidate;
    private ZonedDateTime endValidate;


    /**
     * Method closes the window.
     * @param event Cancel button press.
     */
    @FXML void cancelListener(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Listener for the Save Button and to be Overridden in child classes.
     * @param event Save button press.
     */
    @FXML void saveListener(ActionEvent event) throws SQLException {

    }

    /**
     * Method to setup the Combos on the Appointment Form.
     */
    public void setupCombos() {
        customerCombo.setItems(Scheduler.getAllCustomers());
        customerCombo.setPromptText("Select Customer");
        contactCombo.setItems(Scheduler.getAllContacts());
        contactCombo.setPromptText("Select Contact");
        userCombo.setItems(Scheduler.getAllUsers());
        userCombo.setPromptText("Select User");
    }

    /**
     * Helper method to create a new Appointment for Add and Update Forms by collecting input from form fields.
     * @return new Appointment with attributes from Form fields.
     */
    public Appointment apptBuilder() {
        Appointment appt = new Appointment(titleField.getText());
        appt.setDescription(descField.getText());
        appt.setLocation(locationField.getText());
        appt.setApptType(typeField.getText());
        appt.setCustomerId(customerCombo.getSelectionModel().getSelectedItem().getCustomerId());
        appt.setContactId(contactCombo.getSelectionModel().getSelectedItem().getContactId());
        appt.setUserId(userCombo.getSelectionModel().getSelectedItem().getUserId());
        ZonedDateTime start = DateTimeConv.localToUTC(DateTimeConv.strToDateLocal(dateField.getValue()+" "+startTimeField.getText()+":00"));
        ZonedDateTime end = DateTimeConv.localToUTC(DateTimeConv.strToDateLocal(dateField.getValue()+" "+endTimeField.getText()+":00"));
        appt.setStart(start);
        appt.setEnd(end);
        return appt;
    }

    /**
     * Validates all Form fields, produces error messages, and returns true when all fields are valid.
     * @return true if all fields are valid.
     */
    public boolean validateForm() {
        String errors = "";
        errorLabel.setText(errors);
        //Check Title for blank.
        boolean validTitle = (!titleField.getText().isBlank());
        errors += (validTitle) ? "":"Error: Title cannot be blank.";
        //Check Description for blank.
        boolean validDesc = (!descField.getText().isBlank());
        errors += (validDesc) ? "":"\nError: Description cannot be blank.";
        //Check Location for blank.
        boolean validLoc = (!locationField.getText().isBlank());
        errors += (validLoc) ? "":"\nError: Location cannot be blank.";
        //Check Type for blank.
        boolean validType = (!typeField.getText().isBlank());
        errors += (validType) ? "":"\nError: Type cannot be blank.";
        //Check if Customer Combo is empty.
        boolean validCustomer = (!customerCombo.getSelectionModel().isEmpty());
        errors += (validCustomer) ? "":"\nError: Please select a customer.";
        //Check if Contact Combo is empty.
        boolean validContact = (!contactCombo.getSelectionModel().isEmpty());
        errors += (validContact) ? "":"\nError: Please select a contact.";
        //Check if User Combo is empty.
        boolean validUser = (!userCombo.getSelectionModel().isEmpty());
        errors += (validUser) ? "":"\nError: Please select a user.";
        errorLabel.setText(errors);
        errorLabel.setVisible(true);
        boolean validDate = validateDate();
        boolean validStart = validateStart();
        boolean validEnd = validateEnd();
        return validTitle && validDesc && validLoc && validType && validCustomer && validContact && validUser && validDate && validStart && validEnd;
    }

    /**
     * Helper method to validate the DatePicker.
     * Check Date for blank and is a future date.
     * @return true if valid.
     */
    private boolean validateDate() {
        //Check Date for blank and is a future date.
        String errors = errorLabel.getText();
        boolean validDate = (dateField.getValue() != null);
        errors += (validDate) ? "":"\nError: Please select a date.";
        if (validDate) {
            boolean validDate2 = (dateField.getValue().isAfter(LocalDate.now().minusDays(1)));
            errors += (validDate2) ? "":"\nError: Please select a future date.";
            if (validDate2) {
                return true;
            }
        }
        errorLabel.setText(errors);
        return false;
    }

    /**
     * Helper method to validate the Start time field.
     * Checks Start Time for Blank, valid format, within EST business hours and future time.
     * @return true if valid.
     */
    private boolean validateStart() {
        //Check Start Time for Blank, valid format, within EST business hours and future time.
        String errors = errorLabel.getText();
        boolean validStart = (!startTimeField.getText().isBlank());
        errors += (validStart) ? "" : "\nError: Start cannot be blank.";
        if (validStart) {
            boolean validStart2 = (DateTimeConv.matchesTimePattern(startTimeField.getText()));
            errors += (validStart2) ? "" : "\nError: Start Time not valid.";
            if (validStart2) {
                this.startValidate = DateTimeConv.strToDateLocal(dateField.getValue() + " " + startTimeField.getText() + ":00");
                boolean validStart3 = (this.startValidate.isAfter(ZonedDateTime.now()));
                errors += (validStart3) ? "" : "\nError: Please select a future time.";
                startESTLabel.setText("= ("+DateTimeConv.timeToStrEST(this.startValidate)+" EST)");
                startESTLabel.setVisible(true);
                if (validStart3) {
                    boolean validStart4 = (DateTimeConv.inBusinessHrs(this.startValidate));
                    errors += (validStart4) ? "" : "\nError: Start is not within business hours (08-22 EST).";
                    if (validStart4) {
                        return true;
                    }
                }
            }
        }
        errorLabel.setText(errors);
        return false;
    }

    /**
     * Helper method to validate the End time field.
     * Checks End Time for Blank, valid format, within EST business hours and after start dateTime.
     * @return true if valid.
     */
    private boolean validateEnd() {
        //Check End Time for Blank, valid format, within EST business hours and after start dateTime.
        String errors = errorLabel.getText();
        boolean validEnd = (!endTimeField.getText().isBlank());
        errors += (validEnd) ? "":"\nError: End cannot be blank.";
        if (validEnd) {
            boolean validEnd2 = (DateTimeConv.matchesTimePattern(endTimeField.getText()));
            errors += (validEnd2) ? "" : "\nError: End Time not valid.";
            if (validEnd2) {
                this.endValidate = DateTimeConv.strToDateLocal(dateField.getValue() + " " + endTimeField.getText() + ":00");
                boolean validEnd3 = (this.endValidate.isAfter(this.startValidate));
                errors += (validEnd3) ? "" : "\nError: End is not after Start.";
                endESTLabel.setText("= ("+DateTimeConv.timeToStrEST(this.endValidate)+" EST)");
                endESTLabel.setVisible(true);
                if (validEnd3) {
                    boolean validEnd4 = (DateTimeConv.inBusinessHrs(this.endValidate));
                    errors += (validEnd4) ? "" : "\nError: End is not within business hours (08-22 EST).";
                    if (validEnd4) {
                        return true;
                    }
                }
            }
        }
        errorLabel.setText(errors);
        return false;
    }
}

package GCScheduler.controller.appointments;

import GCScheduler.model.Contact;
import GCScheduler.model.Customer;
import GCScheduler.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    @FXML protected Label errorLabel;

    @FXML void cancelListener(ActionEvent event) {

    }

    @FXML void saveListener(ActionEvent event) {

    }
}

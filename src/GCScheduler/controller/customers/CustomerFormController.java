package GCScheduler.controller.customers;

import GCScheduler.model.Country;
import GCScheduler.model.FirstLevelDiv;
import GCScheduler.model.Scheduler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Parent Controller for Add and Update CustomerFormControllers. Holds all connections to CustomerForm.fxml.
 */
public abstract class CustomerFormController {
    @FXML protected Button saveButton;
    @FXML protected Button cancelButton;
    @FXML protected TextField customerIdField;
    @FXML protected TextField nameField;
    @FXML protected ComboBox<Country> countryCombo;
    @FXML protected TextField streetField;
    @FXML protected ComboBox<FirstLevelDiv> stateCombo;
    @FXML protected TextField postalCodeField;
    @FXML protected TextField phoneField;
    @FXML protected Label errorLabel;

    /**
     * Method to cancel the Add or Update Customer Form with cancel button.
     * @param event Cancel button press.
     */
    @FXML void cancelListener(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Method to save the Add or Update Customer Form. To be overridden by child classes.
     * @param event Save button press.
     */
    @FXML void saveListener(ActionEvent event) {

    }

    /**
     * Method to set up the ComboBoxes for Add and Update forms. Disables State/Province Combo.
     */
    public void setCombos() {
        countryCombo.setPromptText("Select Country");
        countryCombo.setItems(Scheduler.getAllCountries());
        stateCombo.setDisable(true);
    }

    /**
     * Method to enable and set the State/Province Combo when a Country is selected.
     * @param event Country Combo has selection.
     */
    @FXML void countryComboListener(ActionEvent event) {
        if (!countryCombo.getSelectionModel().isEmpty()) {
            stateCombo.setPromptText("Select State/Province");
            stateCombo.setDisable(false);
            stateCombo.setItems(countryCombo.getSelectionModel().getSelectedItem().getFirstLevelDivs());
        }
    }
}

package GCScheduler.controller.customers;

import GCScheduler.model.Country;
import GCScheduler.model.Customer;
import GCScheduler.model.FirstLevelDiv;
import GCScheduler.model.Scheduler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;

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
    @FXML void saveListener(ActionEvent event) throws SQLException {

    }

    /**
     * Method to set up the ComboBoxes for Add and Update forms.
     */
    public void setupCombos() {
        countryCombo.setPromptText("Select Country");
        countryCombo.setItems(Scheduler.getAllCountries());
        if (countryCombo.getSelectionModel().isEmpty()) {
            stateCombo.setDisable(true);
        } else {
            stateCombo.setItems(countryCombo.getSelectionModel().getSelectedItem().getFirstLevelDivs());
        }
    }

    /**
     * Method to enable and set the State/Province Combo when a Country is selected.
     * @param event Country Combo has selection.
     */
    @FXML void countryComboListener(ActionEvent event) {
        if (!countryCombo.getSelectionModel().isEmpty()) {
            stateCombo.setDisable(false);
        }
        stateCombo.setItems(countryCombo.getSelectionModel().getSelectedItem().getFirstLevelDivs());
        setupPrompts(countryCombo.getSelectionModel().getSelectedItem());
    }

    /**
     * Method to create a new Customer for Add and Update Forms using the input from the form fields.
     * Builds with Name,Address,Postal,Phone, and Div ID from Form Fields.
     * @return Customer with attributes from Form fields.
     */
    public Customer customerBuilder() {
        Customer customer = new Customer(nameField.getText());
        customer.setAddress(streetField.getText());
        customer.setPostalCode(postalCodeField.getText());
        customer.setPhoneNum(phoneField.getText());
        customer.setDivId(stateCombo.getSelectionModel().getSelectedItem().getDivId());
        return customer;
    }

    /**
     * Method to validate form fields for both Add and Update Forms.
     * @return true if form is valid.
     */
    public boolean validateForm() {
        String errors = "";
        errorLabel.setText(errors);
        //Check Name field.
        boolean validName = (!nameField.getText().isBlank());
        errors += (validName) ? "":"Error: Name cannot be blank.";
        //Check Country
        boolean validCountry = (!countryCombo.getSelectionModel().isEmpty());
        errors += (validCountry) ? "":"\nError: Please select a Country.";
        //Check Address field.
        boolean validAddress = (!streetField.getText().isBlank());
        errors += (validAddress) ? "":"\nError: Address cannot be blank.";
        //Check State / Province.
        boolean validDiv = (!stateCombo.getSelectionModel().isEmpty());
        errors += (validDiv) ? "":"\nError: Please select a State/Province.";
        //Check Postal Code.
        boolean validPostal = (!postalCodeField.getText().isBlank());
        errors += (validPostal) ? "":"\nError: Postal Code cannot be blank.";
        //Check Phone.
        boolean validPhone = (!phoneField.getText().isBlank());
        errors += (validPhone) ? "":"\nError: Phone cannot be blank.";
        errorLabel.setVisible(true);
        errorLabel.setText(errors);
        return validName && validCountry && validAddress && validDiv && validPostal && validPhone;
        //When there are Errors.
    }

    /**
     * Method to set form prompts based on Country.
     * @param country Country to get Country ID.
     */
    private void setupPrompts(Country country) {
        switch (country.getCountryId()) {
            case 1:
                //US
                stateCombo.setPromptText("Select State");
                streetField.setPromptText("123 ABC Street, White Plains");
                postalCodeField.setPromptText("00000");
                phoneField.setPromptText("1-123-456-7890");
                break;
            case 2:
                //UK
                stateCombo.setPromptText("Select Country/Region");
                streetField.setPromptText("123 ABC Street, Greenwich, London");
                postalCodeField.setPromptText("XXXX XXX");
                phoneField.setPromptText("44 7911 123456");
                break;
            case 3:
                //Canada
                stateCombo.setPromptText("Select Province");
                streetField.setPromptText("123 ABC Street, Newmarket");
                postalCodeField.setPromptText("XNX NXN");
                phoneField.setPromptText("1-123-456-7890");
                break;
        }
    }
}

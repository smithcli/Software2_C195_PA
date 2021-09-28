package GCScheduler.controller.customers;

import GCScheduler.dao.CustomerDao;
import GCScheduler.dao.JDBC.CustomerImpl;
import GCScheduler.dao.JDBC.JDBC;
import GCScheduler.model.Customer;
import GCScheduler.model.Scheduler;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * Class to control the Update Customer Form.
 */
public class UpdateCustomerFormController extends CustomerFormController{
    private Customer oldCustomer;

    /**
     * Method uses the CustomerDao to update the Customer record from the fields on the form.
     * @param event Save button press.
     * @throws SQLException uses JDBC and CustomerImpl.
     */
    @Override
    void saveListener(ActionEvent event) throws SQLException {
        if (JDBC.getConnection().isValid(JDBC.getTimeout()) && super.validateForm()) {
            CustomerDao dao = new CustomerImpl();
            //Build new customer from Form fields and necessary customer data.
            Customer customer = super.customerBuilder();
            customer.setCustomerId(oldCustomer.getCustomerId());
            customer.setCreatedBy(oldCustomer.getCreatedBy());
            customer.setCreateDate(oldCustomer.getCreateDate());
            //Update customer in DB
            dao.updateCustomer(customer);
            //Update customer in local machine.
            Scheduler.getAllCustomers().remove(oldCustomer);
            oldCustomer.getDiv().getCustomerList().remove(oldCustomer);
            Scheduler.getAllCustomers().add(dao.getCustomer(customer.getCustomerId()));
            //Relink references to the updated customer.
            Scheduler.setupFirstLevelDivs();
            Scheduler.setupAppointments();
            //Close window
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Method is used in the CustomerController class to pass the selected Customer and set the fields on the form with customer data.
     * @param customer
     */
    public void getSelectedCustomer(Customer customer) {
        oldCustomer = customer;
        customerIdField.setText(String.valueOf(customer.getCustomerId()));
        nameField.setText(customer.getCustomerName());
        countryCombo.getSelectionModel().select(customer.getDiv().getCountry());
        streetField.setText(customer.getAddress());
        stateCombo.getSelectionModel().select(customer.getDiv());
        postalCodeField.setText(customer.getPostalCode());
        phoneField.setText(customer.getPhoneNum());
        super.setupCombos();
    }
}

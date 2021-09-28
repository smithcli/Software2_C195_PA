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
 * Class controller for the Add Customer Form. Uses CustomerDao to add Customer to database.
 */
public class AddCustomerFormController extends CustomerFormController{

    /**
     * Method calls super setupCombos and prepares the form.
     */
    public void initialize(){
        super.setupCombos();
        customerIdField.setText("Auto-Generated");
    }

    /**
     * Method creates a new Customer based on user input after validation.
     * @param event Save button press.
     * @throws SQLException Uses mySql database to save Customer and return complete Customer to application.
     */
    @Override
    void saveListener(ActionEvent event) throws SQLException {
        if (JDBC.getConnection().isValid(JDBC.getTimeout()) && super.validateForm()) {
            CustomerDao dao = new CustomerImpl();
            //Build new customer from Form fields.
            Customer customer = super.customerBuilder();
            //Add customer to DB
            dao.createCustomer(customer);
            //Add customer to local machine.
            Scheduler.getAllCustomers().add(dao.getCustomer(customer.getCustomerName(),customer.getPhoneNum()));
            //Link references to the new customer.
            Scheduler.setupFirstLevelDivs();
            //New customer will not have any appointments to setup yet.
            //Close window
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        }
    }
}

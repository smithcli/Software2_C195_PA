package GCScheduler.controller.customers;

import GCScheduler.dao.CustomerDao;
import GCScheduler.dao.JDBC.CustomerImpl;
import GCScheduler.dao.JDBC.JDBC;
import GCScheduler.model.Customer;
import GCScheduler.model.Scheduler;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Controller Class for Customer Tab.
 */
public class CustomersController {
    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, Integer> cxIdCol;
    @FXML private TableColumn<Customer, String> nameCol;
    @FXML private TableColumn<Customer, String> streetCol;
    @FXML private TableColumn<Customer, String> firstLevelCol;
    @FXML private TableColumn<Customer, String> countryCol;
    @FXML private TableColumn<Customer, String> postalCodeCol;
    @FXML private TableColumn<Customer, String> phoneCol;
    @FXML private Button deleteButton;
    @FXML private Button updateButton;
    @FXML private Button addButton;
    @FXML private Label errorLabel;
    private boolean deleted;

    /**
     * Method called when loaded, populates tableView from Scheduler class model.
     * LAMBDA EXPRESSION was used to reduce amount of code. The &quot;new Callback&quot; way is extremely long.
     */
    public void initialize() {
        // Place customer list on TableView.
        customerTable.setItems(Scheduler.getAllCustomers());
        cxIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        streetCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        firstLevelCol.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getDiv().getDivName()));
        countryCol.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getDiv().getCountry().getCountryName()));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        customerTable.getSortOrder().add(cxIdCol);
        errorLabel.setVisible(false);
    }

    /**
     * Method opens a new window for Add Customer Form.
     * @param event on Add button press.
     * @throws IOException loads CustomerForm.fxml and new Stage.
     */
    @FXML void addButtonListener(ActionEvent event) throws IOException {
        AddCustomerFormController addController = new AddCustomerFormController();
        Stage stage = stageFactory(addController);
        stage.setTitle("Add Customer");
        stage.showAndWait();
        initialize();
    }

    /**
     * Deletes selected customer and associated appointments from database. Also makes confirmation messages before and after deletion.
     * LAMBDA EXPRESSION used for Alert showAndWait response as it is one of the simpler options to use to get the response type.
     * @param event delete button press.
     * @throws SQLException accesses database.
     */
    @FXML void deleteButtonListener(ActionEvent event) throws SQLException {
        if (customerTable.getSelectionModel().isEmpty()) {
            errorLabel.setText("Error: Please select a customer to delete.");
            errorLabel.setVisible(true);
        } else if (JDBC.getConnection().isValid(JDBC.getTimeout())) {
            Customer customer = customerTable.getSelectionModel().getSelectedItem();
            CustomerDao customerDao = new CustomerImpl();
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setContentText("Are you sure you want to delete: \n"+customer.getCustomerName());
            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    customer.getDiv().getCustomerList().remove(customer);
                    Scheduler.getAllCustomers().remove(customer);
                    Scheduler.getAllAppointments().removeAll(customer.getAppointments());
                    this.deleted = customerDao.deleteCustomer(customer);
                }
            });
            if (deleted) {
                Alert info = new Alert(Alert.AlertType.INFORMATION, customer.getCustomerName()+" was deleted.",ButtonType.OK);
                info.show();
            }
            initialize();
        }
    }

    /**
     * Method opens a new window for Update Customer Form.
     * @param event Update button press.
     * @throws IOException loads CustomerForm.fxml and new Stage.
     */
    @FXML void updateButtonListener(ActionEvent event) throws IOException {
        if (!customerTable.getSelectionModel().isEmpty()) {
            UpdateCustomerFormController controller = new UpdateCustomerFormController();
            Stage stage = stageFactory(controller);
            controller.getSelectedCustomer(customerTable.getSelectionModel().getSelectedItem());
            stage.setTitle("Update Customer");
            stage.showAndWait();
            initialize();
        } else {
            errorLabel.setText("Error: Please select a customer to update.");
            errorLabel.setVisible(true);
        }
    }

    /**
     * Helper method creates a new Stage with CustomerForm.fxml and connects with controller argument.
     * @param controller controller to connect to CustomerForm.fxml
     * @return Stage with CustomerForm
     * @throws IOException loads CustomerForm.fxml with new Stage.
     */
    protected Stage stageFactory(CustomerFormController controller) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GCScheduler/view/customers/CustomerForm.fxml"));
        loader.setController(controller);
        stage.setScene(new Scene(loader.load()));
        return stage;
    }
}

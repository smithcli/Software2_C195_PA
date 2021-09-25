package GCScheduler.controller.customers;

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

    /**
     * Method called when loaded, populates tableView from Scheduler class model.
     * Includes lambda expressions to reduce amount of code. Callback factory is extremely long.
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

    @FXML void deleteButtonListener(ActionEvent event) {
        //TODO delete customer button.
    }

    /**
     * Method opens a new window for Update Customer Form.
     * @param event Update button press.
     * @throws IOException loads CustomerForm.fxml and new Stage.
     */
    @FXML void updateButtonListener(ActionEvent event) throws IOException {
        //TODO add error if customer is not selected
        //TODO add UpdateController get item method.
        UpdateCustomerFormController controller = new UpdateCustomerFormController();
        Stage stage = stageFactory(controller);
        stage.setTitle("Update Customer");
        stage.show();
    }

    /**
     * Helper method creates a new Stage with CustomerForm.fxml and connects with controller argument.
     * @param controller controller to connect to CustomerForm.fxml
     * @return Stage with CustomerForm
     * @throws IOException loads CustomerForm.fxml with new Stage.
     */
    private Stage stageFactory(CustomerFormController controller) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GCScheduler/view/customers/CustomerForm.fxml"));
        loader.setController(controller);
        stage.setScene(new Scene(loader.load()));
        return stage;
    }
}

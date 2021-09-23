package GCScheduler.controller.customers;

import GCScheduler.model.Customer;
import GCScheduler.model.Scheduler;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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

    @FXML
    void addButtonListener(ActionEvent event) {

    }

    @FXML
    void deleteButtonListener(ActionEvent event) {

    }

    @FXML
    void updateButtonListener(ActionEvent event) {

    }
}

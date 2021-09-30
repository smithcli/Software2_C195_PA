package GCScheduler.dao;

import GCScheduler.model.Customer;
import javafx.collections.ObservableList;

/**
 * Customer Data Access Interface for CRUD operations on persistence layer.
 */
public interface CustomerDao {

    /**
     * Adds a Customer.
     * @param customer Customer to add.
     */
    public void createCustomer(Customer customer);

    /**
     * Gets a Customer.
     * @param customerId Customer ID.
     * @return Customer object.
     */
    public Customer getCustomer(int customerId);

    /**
     * Gets a Customer.
     * @param customerName Customer Name.
     * @param phoneNum Customer Phone-number.
     * @return Customer Object.
     */
    public Customer getCustomer(String customerName, String phoneNum);

    /**
     * Gets all Customers.
     * @return ObservableList of Customers.
     */
    public ObservableList<Customer> getAllCustomers();

    /**
     * Updates a Customer.
     * @param customer Customer to Update.
     */
    public void updateCustomer(Customer customer);

    /**
     * Deletes a Customer.
     * @param customer Customer to Delete.
     * @return true if deleted.
     */
    public boolean deleteCustomer(Customer customer);
}

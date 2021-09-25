package GCScheduler.dao;

import GCScheduler.model.Customer;
import javafx.collections.ObservableList;

public interface CustomerDao {
    public void createCustomer(Customer customer);
    public Customer getCustomer(int customerId);
    public Customer getCustomer(String customerName, String phoneNum);
    public ObservableList<Customer> getAllCustomers();
    public void updateCustomer(Customer customer);
    public boolean deleteCustomer(Customer customer);
}

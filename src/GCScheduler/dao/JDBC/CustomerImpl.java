package GCScheduler.dao.JDBC;

import GCScheduler.dao.CustomerDao;
import GCScheduler.model.Customer;
import GCScheduler.utilities.DateTimeConv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

public class CustomerImpl implements CustomerDao {
    @Override
    public void createCustomer(Customer customer) {

    }

    @Override
    public Customer getCustomer(int customerId) {
        String query = "SELECT * FROM customers WHERE Customer_ID = " + customerId + ";";
        try {
            ResultSet resultSet = JDBC.conn.createStatement().executeQuery(query);
            resultSet.next();
            int custId = resultSet.getInt("Customer_ID");
            String custName = resultSet.getString("Customer_Name");
            String address = resultSet.getString("Address");
            String postalCode = resultSet.getString("Postal_Code");
            String phone = resultSet.getString("Phone");
            String createDate = resultSet.getString("Create_Date");
            String createdBy = resultSet.getString("Created_By");
            String lastUpdate = resultSet.getString("Last_Update");
            String lastUpdatedBy = resultSet.getString("Last_Updated_By");
            int divId = resultSet.getInt("Division_ID");
            ZonedDateTime cDate = DateTimeConv.strToDateUTC(createDate);
            ZonedDateTime uDate = DateTimeConv.strToDateUTC(lastUpdate);
            return new Customer(custId,custName,address,postalCode,phone,cDate,createdBy,uDate,lastUpdatedBy,divId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
   }

    @Override
    public ObservableList<Customer> getAllCustomers() {
        String query = "SELECT * FROM customers;";
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = JDBC.conn.createStatement().executeQuery(query);
            while (resultSet.next()) {
                int custId = resultSet.getInt("Customer_ID");
                String custName = resultSet.getString("Customer_Name");
                String address = resultSet.getString("Address");
                String postalCode = resultSet.getString("Postal_Code");
                String phone = resultSet.getString("Phone");
                String createDate = resultSet.getString("Create_Date");
                String createdBy = resultSet.getString("Created_By");
                String lastUpdate = resultSet.getString("Last_Update");
                String lastUpdatedBy = resultSet.getString("Last_Updated_By");
                int divId = resultSet.getInt("Division_ID");
                ZonedDateTime cDate = DateTimeConv.strToDateUTC(createDate);
                ZonedDateTime uDate = DateTimeConv.strToDateUTC(lastUpdate);
                Customer customer = new Customer(custId,custName,address,postalCode,phone,cDate,createdBy,uDate,lastUpdatedBy,divId);
                allCustomers.add(customer);
            }
            return allCustomers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateCustomer(Customer customer) {

    }

    @Override
    public boolean deleteCustomer(Customer customer) {
        return false;
    }
}

package GCScheduler.dao.JDBC;

import GCScheduler.dao.CustomerDao;
import GCScheduler.model.Customer;
import GCScheduler.utilities.DateTimeConv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

public class CustomerImpl implements CustomerDao {
    @Override
    public void createCustomer(Customer customer) {
        String query = "INSERT INTO customers (Customer_Name,Address,Postal_Code,Phone,Division_ID,create_date,created_by,last_update,last_updated_by) values (?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement pstmt = JDBC.getConnection().prepareStatement(query);
            customer.setCreateDate(DateTimeConv.localToUTC(ZonedDateTime.now()));
            customer.setCreatedBy(JDBC.getDbUser());
            setCustomer(customer,pstmt);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer getCustomer(int customerId) {
        String query = "SELECT * FROM customers WHERE Customer_ID = " + customerId + ";";
        try {
            ResultSet rset = JDBC.getConnection().createStatement().executeQuery(query);
            rset.next();
            return makeCustomer(rset);
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
            ResultSet rset = JDBC.getConnection().createStatement().executeQuery(query);
            while (rset.next()) {
                allCustomers.add(makeCustomer(rset));
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

    private Customer makeCustomer(ResultSet rset) throws SQLException {
        int custId = rset.getInt("Customer_ID");
        String custName = rset.getString("Customer_Name");
        String address = rset.getString("Address");
        String postalCode = rset.getString("Postal_Code");
        String phone = rset.getString("Phone");
        String createDate = rset.getString("Create_Date");
        String createdBy = rset.getString("Created_By");
        String lastUpdate = rset.getString("Last_Update");
        String lastUpdatedBy = rset.getString("Last_Updated_By");
        int divId = rset.getInt("Division_ID");
        ZonedDateTime cDate = DateTimeConv.strToDateUTC(createDate);
        ZonedDateTime uDate = DateTimeConv.strToDateUTC(lastUpdate);
        return new Customer(custId,custName,address,postalCode,phone,cDate,createdBy,uDate,lastUpdatedBy,divId);
    }

    private PreparedStatement setCustomer(Customer customer, PreparedStatement pstmt) throws SQLException {
        ZonedDateTime now = ZonedDateTime.now();
        pstmt.setString(1,customer.getCustomerName());
        pstmt.setString(2,customer.getAddress());
        pstmt.setString(3,customer.getPostalCode());
        pstmt.setString(4,customer.getPhoneNum());
        pstmt.setInt(5,customer.getDivId());
        pstmt.setString(6,DateTimeConv.dateToStrUTC(customer.getCreateDate()));
        pstmt.setString(7,customer.getCreatedBy());
        pstmt.setString(8,DateTimeConv.dateToStrUTC(now));
        pstmt.setString(9,JDBC.getDbUser());
        return pstmt;
    }
}

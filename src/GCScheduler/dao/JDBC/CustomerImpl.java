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

/**
 * Class is the Customer interface for the mySql Database. Uses sql queries to perform database functions.
 */
public class CustomerImpl implements CustomerDao {

    /**
     * Method inserts a new Customer record into mySql database using a Customer object. Customer ID will be automatically generated.
     * @param customer Customer object to Insert.
     */
    @Override
    public void createCustomer(Customer customer) {
        String query = "INSERT INTO customers (Customer_Name,Address,Postal_Code,Phone,Division_ID,create_date,created_by,last_update,last_updated_by) values (?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement pstmt = JDBC.getConnection().prepareStatement(query);
            //Necessary for customer creation (not completed by user)
            customer.setCreateDate(DateTimeConv.localToUTC(ZonedDateTime.now()));
            customer.setCreatedBy(JDBC.getDbUser());
            //Helper Method to prepare statement
            setCustomer(customer,pstmt);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method uses sql to locate and return a new Customer object by Customer ID.
     * Best to use Customer ID as it's the primary key and will keep application from displaying duplicate objects resulting from having the same Customer name or other field.
     * @param customerId Customer ID
     * @return new Customer object.
     */
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

    /**
     * Method uses sql to locate and return a new Customer object by Name and Phone Number.
     * Reduces the chance to select a record with the same name.
     * @param customerName Customer Name
     * @param phoneNum Customer Phone Number
     * @return new Customer object.
     */
    @Override
    public Customer getCustomer(String customerName, String phoneNum) {
        String query = "SELECT * FROM customers WHERE Customer_Name = '"+customerName+"' AND Phone = '"+phoneNum+"';";
        try {
            ResultSet rset = JDBC.getConnection().createStatement().executeQuery(query);
            rset.next();
            return makeCustomer(rset);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method returns all records from Customer table in a mySql database.
     * @return ObservableList of Customer Objects.
     */
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

    /**
     * Updates a customer record using sql. Identifies customer by Customer_ID.
     * @param customer Customer object to replace the old record.
     */
    @Override
    public void updateCustomer(Customer customer) {
        String query = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ?, create_date = ?, created_by = ?, last_update = ?, last_updated_by = ? WHERE Customer_ID = ?;";
        try {
            PreparedStatement pstmt = JDBC.getConnection().prepareStatement(query);
            setCustomer(customer,pstmt);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a customer record from the database as well as all related customer appointments.
     * @param customer Customer object to be deleted.
     * @return true if deleted.
     */
    @Override
    public boolean deleteCustomer(Customer customer) {
        int customerId = customer.getCustomerId();
        String query = "DELETE FROM customers WHERE Customer_ID = "+customerId+";";
        try {
            JDBC.getConnection().createStatement().execute(query);
            return true;
            //TODO delete all appointments with customer.
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method helps return a ResultSet for a Customer record in the Customer table.
     * @param rset the ResultSet to help.
     * @return Customer Object that contains data from Customer record.
     * @throws SQLException Uses Sql Class ResultSet to access a mySql database.
     */
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

    /**
     * Method helps return a PreparedStatement for a Customer object.
     * Indices used are 1-9: 1-Name, 2-Address, 3-PostalCode, 4-Phone, 5-divId, 6-createDate, 7-createBy, 8-lastUpdate, 9-lastUpdatedBy.
     * @param customer Customer Object.
     * @param pstmt PreparedStatment to help.
     * @return PreparedStatement with Customer Fields.
     * @throws SQLException Uses PreparedStatement.
     */
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
        if (pstmt.getParameterMetaData().getParameterCount() > 9) {
            pstmt.setInt(10, customer.getCustomerId());
        }
        return pstmt;
    }
}

package GCScheduler.dao.JDBC;

import GCScheduler.dao.UserDao;
import GCScheduler.model.User;
import GCScheduler.utilities.DateTimeConv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

/**
 * Queries the mySql database with CRUD operations for the User model object.
 */
public class UserImpl implements UserDao {

    /**
     * Not used.
     * Creates a new User record using the object's attributes.
     * @param user User to add.
     */
    @Override
    public void createUser(User user) {

    }

    /**
     * Returns a User object by using the user Id, the primary key in the table.
     * @param usrId ID to locate the User.
     * @return User object.
     */
    @Override
    public User getUser(int usrId) {
        String query = "SELECT * FROM users WHERE User_ID = " + usrId + ";";
        try {
            ResultSet rset = JDBC.getConnection().createStatement().executeQuery(query);
            rset.next();
            return makeUser(rset);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns all User records.
     * @return ObservableList of Users.
     */
    @Override
    public ObservableList<User> getAllUsers() {
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        String query = "SELECT * FROM users;";
        try {
            ResultSet rset = JDBC.getConnection().createStatement().executeQuery(query);
            while (rset.next()) {
                allUsers.add(makeUser(rset));
            }
            return allUsers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Not used.
     * Updates the User record using passed object's attributes.
     * @param user User to update.
     */
    @Override
    public void updateUser(User user) {

    }

    /**
     * Not used.
     * Deletes a User record using the ID.
     * @param user User to delete.
     * @return true if deleted.
     */
    @Override
    public boolean deleteUser(User user) {
        return false;
    }

    /**
     * Helper method to return a ResultSet for a User record.
     * @param rset The ResultSet to help.
     * @return new User with attributes from the record.
     * @throws SQLException uses ResultSet.
     */
    private User makeUser(ResultSet rset) throws SQLException {
        int id = rset.getInt("User_ID");
        String name = rset.getString("User_Name");
        String password = rset.getString("Password");
        String createDate = rset.getString("Create_Date");
        String createdBy = rset.getString("Created_By");
        String lastUpdate = rset.getString("Last_Update");
        String lastUpdatedBy = rset.getString("Last_Updated_By");
        ZonedDateTime cDate = DateTimeConv.strToDateUTC(createDate);
        ZonedDateTime uDate = DateTimeConv.strToDateUTC(lastUpdate);
        return new User(id,name,password,cDate,createdBy,uDate,lastUpdatedBy);
    }
}

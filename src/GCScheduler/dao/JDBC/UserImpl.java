package GCScheduler.dao.JDBC;

import GCScheduler.dao.UserDao;
import GCScheduler.model.User;
import GCScheduler.utilities.DateTimeConv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

public class UserImpl implements UserDao {
    @Override
    public void createUser(User user) {

    }

    @Override
    public User getUser(int usrId) {
        String query = "SELECT * FROM users WHERE User_ID = " + usrId + ";";
        try {
            ResultSet rset = JDBC.conn.createStatement().executeQuery(query);
            rset.next();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<User> getAllUsers() {
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        String query = "SELECT * FROM users;";
        try {
            ResultSet rset = JDBC.conn.createStatement().executeQuery(query);
            while (rset.next()) {
                int id = rset.getInt("User_ID");
                String name = rset.getString("User_Name");
                String password = rset.getString("Password");
                String createDate = rset.getString("Create_Date");
                String createdBy = rset.getString("Created_By");
                String lastUpdate = rset.getString("Last_Update");
                String lastUpdatedBy = rset.getString("Last_Updated_By");
                ZonedDateTime cDate = DateTimeConv.strToDateUTC(createDate);
                ZonedDateTime uDate = DateTimeConv.strToDateUTC(lastUpdate);
                User user = new User(id,name,password,cDate,createdBy,uDate,lastUpdatedBy);
                allUsers.add(user);
            }
            return allUsers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }
}

package GCScheduler.dao.JDBC;

import GCScheduler.model.Scheduler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class to connect to mySql database using Driver v8.0.25.
 */
public abstract class JDBC {
    private static final String dbName="client_schedule";
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//localhost:3306/";
    private static final String dbURL = protocol + vendorName + ipAddress + dbName + "?connectionTimeZone=SERVER"; //Driver v8.0.25
    //DB User info
    private static final String userName = "sqlUser";
    private static final String password = "Passw0rd!";
    private static int timeout = 10;
    private static Connection conn;

    /**
     * Method to start connections to mySql database.
     * @return Connection
     */
    public static Connection startConnection() {
        try {
            conn = DriverManager.getConnection(dbURL,userName,password);
            System.out.println("Connection Successful");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Method to return the connection if required.
     * @return Connection
     */
    public static Connection getConnection() {
        return conn;
    }

    /**
     * Method to close the connection to mySql database.
     */
    public static void stopConnection() {
        try {
            conn.close();
            System.out.println("Connection closed.");
        } catch (Exception e) {
            //do nothing
        }
    }

    /**
     * Method to return the Username used to login into the database.
     * @return mySql Username.
     */
    public static String getDbUser() {
        return userName;
    }

    public static int getTimeout() {
        return timeout;
    }

    public static void setTimeout(int timeout) {
        JDBC.timeout = timeout;
    }

    /**
     * Method to quickly get data from mySql database and load into Scheduler class containers.
      */
    public static void getData() {
        Scheduler.setAllCustomers(new CustomerImpl().getAllCustomers());
        Scheduler.setAllFirstLevelDivs(new FirstLevelDivImpl().getAllFirstLevelDivs());
        Scheduler.setAllCountries(new CountryImpl().getAllCountries());
        Scheduler.setAllUsers(new UserImpl().getAllUsers());
        Scheduler.setAllContacts(new ContactImpl().getAllContacts());
        Scheduler.setAllAppointments(new AppointmentImpl().getAllAppts());
    }
}

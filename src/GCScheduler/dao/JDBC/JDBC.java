package GCScheduler.dao.JDBC;

import GCScheduler.model.Scheduler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class JDBC {
    private static final String dbName="client_schedule";
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//localhost:3306/";
    private static final String dbURL = protocol + vendorName + ipAddress + dbName + "?connectionTimeZone=SERVER"; //8.0.25

    private static final String userName = "sqlUser";
    private static final String password = "Passw0rd!";

    static Connection conn;

    public static Connection startConnection() {
        try {
            conn = DriverManager.getConnection(dbURL,userName,password);
            System.out.println("Connection Successful");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection getConnection() {
        return conn;
    }

    public static void stopConnection() {
        try {
            conn.close();
            System.out.println("Connection closed.");
        } catch (Exception e) {
            //do nothing
        }
    }

    public static void getData() {
        Scheduler.setAllCustomers(new CustomerImpl().getAllCustomers());
        Scheduler.setAllFirstLevelDivs(new FirstLevelDivImpl().getAllFirstLevelDivs());
        Scheduler.setAllCountries(new CountryImpl().getAllCountries());
        Scheduler.setAllUsers(new UserImpl().getAllUsers());
        Scheduler.setAllContacts(new ContactImpl().getAllContacts());
        Scheduler.setAllAppointments(new AppointmentImpl().getAllAppts());
    }
}

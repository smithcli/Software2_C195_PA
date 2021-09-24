package GCScheduler.dao.JDBC;

import GCScheduler.dao.AppointmentDao;
import GCScheduler.model.Appointment;
import GCScheduler.utilities.DateTimeConv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

public class AppointmentImpl implements AppointmentDao {
    @Override
    public void createAppt(Appointment appointment) {
        String query = "INSERT INTO appointments (title,description,location,type,start,end,create_date,created_by,last_update,last_updated_by,Customer_ID,User_ID,Contact_ID) values (?,?,?,?,?,?,?,?,?,?,?,?,?);";

    }

    @Override
    public Appointment getAppt(int apptId) {
        String query = "SELECT * FROM appointments WHERE Appointment_ID = " + apptId + ";";
        try {
            ResultSet rset = JDBC.getConnection().createStatement().executeQuery(query);
            rset.next();
            return makeAppt(rset);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<Appointment> getAllAppts() {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String query = "SELECT * FROM appointments;";
        try {
            ResultSet rset = JDBC.getConnection().createStatement().executeQuery(query);
            while (rset.next()) {
                allAppointments.add(makeAppt(rset));
            }
            return allAppointments;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateAppt(Appointment appointment) {

    }

    @Override
    public boolean deleteAppt(Appointment appointment) {
        return false;
    }

    @Override
    public boolean deleteAllAppts(ObservableList<Appointment> appointments) {
        return false;
    }

    private Appointment makeAppt(ResultSet rset) throws SQLException {
        int id = rset.getInt("Appointment_ID");
        String name = rset.getString("Title");
        String desc = rset.getString("Description");
        String loc = rset.getString("Location");
        String type = rset.getString("Type");
        String start = rset.getString("Start");
        String end = rset.getString("End");
        String createDate = rset.getString("Create_Date");
        String createdBy = rset.getString("Created_By");
        String lastUpdate = rset.getString("Last_Update");
        String lastUpdatedBy = rset.getString("Last_Updated_By");
        int cId = rset.getInt("Customer_ID");
        int uId = rset.getInt("User_ID");
        int contactId = rset.getInt("Contact_ID");
        ZonedDateTime startUTC = DateTimeConv.strToDateUTC(start);
        ZonedDateTime endUTC = DateTimeConv.strToDateUTC(end);
        ZonedDateTime cDate = DateTimeConv.strToDateUTC(createDate);
        ZonedDateTime uDate = DateTimeConv.strToDateUTC(lastUpdate);
        return new Appointment(id,name,desc,loc,type,startUTC,endUTC,cDate,createdBy,uDate,lastUpdatedBy,cId,uId,contactId);
    }
}

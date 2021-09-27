package GCScheduler.dao.JDBC;

import GCScheduler.dao.AppointmentDao;
import GCScheduler.model.Appointment;
import GCScheduler.utilities.DateTimeConv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

/**
 * Class is the Appointment interface for the mySql database. Uses sql queries to perform database functions on the appointments table.
 */
public class AppointmentImpl implements AppointmentDao {

    /**
     * Creates a new appointment using all appointment attributes.
     * @param appointment Appointment to add.
     */
    @Override
    public void createAppt(Appointment appointment) {
        String query = "INSERT INTO appointments (title,description,location,type,start,end,create_date,created_by,last_update,last_updated_by,Customer_ID,User_ID,Contact_ID) values (?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement pstmt = JDBC.getConnection().prepareStatement(query);
            //Necessary for appt creation (not completed by user)
            appointment.setCreateDate(DateTimeConv.localToUTC(ZonedDateTime.now()));
            appointment.setCreateBy(JDBC.getDbUser());
            //Helper Method to prepare statement
            setAppt(appointment,pstmt);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns an Appointment object by using the appointment id, the primary key in the table.
     * @param apptId Appointment Id to locate appointment record.
     * @return Appointment object.
     */
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

    /**
     * Returns an Appointment object by using the title, start date-time, and associated customer id.
     * @param title Title of the appointment
     * @param start Start date and time of the appointment format yyyy-MM-dd HH:mm:ss.
     * @param customerId Associated Customer ID of the appointment.
     * @return Appointment Object.
     */
    @Override
    public Appointment getAppt(String title, String start, int customerId) {
        String query = "SELECT * FROM appointments WHERE title = "+title+" AND start = "+start+" AND Customer_ID = "+customerId+";";
        try {
            ResultSet rset = JDBC.getConnection().createStatement().executeQuery(query);
            rset.next();
            return makeAppt(rset);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns all Appointments using an ObservableList of Appointment type.
     * @return ObservableList of Appointments.
     */
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

    /**
     * Updates the appointment record.
     * @param appointment Appointment with updates.
     */
    @Override
    public void updateAppt(Appointment appointment) {
        String query = "UPDATE appointments SET title = ?, description = ?, location = ?, type = ?, start = ?, end = ?, create_date = ?, created_by = ?, last_update = ?, last_updated_by = ?, customer_id = ?, user_id = ?, contact_id = ? WHERE appointment_id = ?;";
        try {
            PreparedStatement pstmt = JDBC.getConnection().prepareStatement(query);
            setAppt(appointment, pstmt);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Deletes an appointment record.
     * @param appointment the appointment to be deleted.
     * @return true if deleted.
     */
    @Override
    public boolean deleteAppt(Appointment appointment) {
        int apptId = appointment.getApptId();
        String query = "DELETE FROM appointments WHERE appointment_id = "+apptId+";";
        try {
            JDBC.getConnection().createStatement().execute(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Deletes all appoinments passed by in the argument.
     * @param appointments Appointments to be deleted.
     * @return true if all deleted.
     */
    @Override
    public boolean deleteAllAppts(ObservableList<Appointment> appointments) {
        for (Appointment appt : appointments) {
            try {
                deleteAppt(appt);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /**
     * Method helps return a ResultSet for an Appointment record.
     * @param rset The ResultSet to help.
     * @return Appointment object that contains data from appointment record.
     * @throws SQLException uses sql class ResultSet to access record.
     */
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

    /**
     * Method helps return a PreparedStatement for an Appointment object.
     * @param appointment Appointment to use for data in PreparedStatement.
     * @param pstmt PreparedStatement to help
     * @return PreparedStatement with Appointment Fields.
     * @throws SQLException Uses PreparedStatement.
     */
    private PreparedStatement setAppt(Appointment appointment, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, appointment.getApptTitle());
        pstmt.setString(2,appointment.getDescription());
        pstmt.setString(3,appointment.getLocation());
        pstmt.setString(4,appointment.getApptType());
        pstmt.setString(5,DateTimeConv.dateToStrUTC(appointment.getStart()));
        pstmt.setString(6,DateTimeConv.dateToStrUTC(appointment.getEnd()));
        pstmt.setString(7,DateTimeConv.dateToStrUTC(appointment.getCreateDate()));
        pstmt.setString(8,appointment.getCreateBy());
        pstmt.setString(9,DateTimeConv.dateToStrUTC(ZonedDateTime.now()));
        pstmt.setString(10,JDBC.getDbUser());
        pstmt.setInt(11,appointment.getCustomerId());
        pstmt.setInt(12,appointment.getUserId());
        pstmt.setInt(13,appointment.getContactId());
        if (pstmt.getParameterMetaData().getParameterCount() > 13) {
            pstmt.setInt(14,appointment.getApptId());
        }
        return pstmt;
    }
}

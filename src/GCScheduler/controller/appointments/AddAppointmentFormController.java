package GCScheduler.controller.appointments;

import GCScheduler.dao.AppointmentDao;
import GCScheduler.dao.JDBC.AppointmentImpl;
import GCScheduler.dao.JDBC.JDBC;
import GCScheduler.model.Appointment;
import GCScheduler.model.Scheduler;
import GCScheduler.utilities.DateTimeConv;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * Class for the Add Appointment Form. Uses AppointmentDao to create an appointment.
 */
public class AddAppointmentFormController extends AppointmentFormController{

    /**
     * Method is called when controller is loaded. Uses parent method and prepares the form.
     */
    public void initialize() {
        super.setupCombos();
        apptIdField.setText("Auto-Generated");
    }

    /**
     * Uses AppointmentImpl to create appointment in database from user form input and adds it to local machine.
     * @param event Save button press.
     * @throws SQLException uses AppointmentDao.
     */
    @Override
    void saveListener(ActionEvent event) throws SQLException {
        if (JDBC.getConnection().isValid(JDBC.getTimeout()) && super.validateForm()) {
            AppointmentDao dao = new AppointmentImpl();
            //Build new Appointment from Form Fields.
            Appointment appt = super.apptBuilder();
            //Add Appt to DB.
            dao.createAppt(appt);
            //Add Appt to local machine.
            Scheduler.getAllAppointments().add(dao.getAppt(appt.getApptTitle(), DateTimeConv.dateToStrUTC(appt.getStart()),appt.getCustomerId()));
            //Link references to new appointment.
            Scheduler.setupAppointments();
            //Close window.
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        }
    }
}

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
 * Class to control the Update Appointment Form.
 */
public class UpdateAppointmentFormController extends AppointmentFormController{
    private Appointment oldAppointment;

    /**
     * Saves the Appointment to the database using the form fields on the form and updates local data.
     * @param event Save button press.
     * @throws SQLException uses JDBC and AppointmentImpl.
     */
    @Override
    void saveListener(ActionEvent event) throws SQLException {
        if (JDBC.getConnection().isValid(JDBC.getTimeout()) && super.validateForm()) {
            AppointmentDao dao = new AppointmentImpl();
            //Build new Appointment from Form fields and necessary appointment data.
            Appointment appt = super.apptBuilder();
            appt.setApptId(oldAppointment.getApptId());
            appt.setCreateDate(oldAppointment.getCreateDate());
            appt.setCreateBy(oldAppointment.getCreateBy());
            //Update Appointment in DB.
            dao.updateAppt(appt);
            //Update Appt in local machine.
            Scheduler.getAllAppointments().remove(oldAppointment);
            oldAppointment.getCustomer().getAppointments().remove(oldAppointment);
            Scheduler.getAllAppointments().add(dao.getAppt(appt.getApptId()));
            //Relink references to the updated appointment.
            Scheduler.setupAppointments();
            //Close window
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Method is used in the AppointmentController Class to pass the selected Appointment and sets up the Update Form fields with selected appointment data.
     * @param appt Appointment to pass to the Update Form.
     */
    public void getSelectedAppt(Appointment appt) {
        super.setupCombos();
        oldAppointment = appt;
        apptIdField.setText(String.valueOf(appt.getApptId()));
        titleField.setText(appt.getApptTitle());
        descField.setText(appt.getDescription());
        locationField.setText(appt.getLocation());
        typeField.setText(appt.getApptType());
        dateField.setValue(appt.getStart().toLocalDate());
        startTimeField.setText(DateTimeConv.getTIMEformat(DateTimeConv.utcToLocal(appt.getStart())));
        endTimeField.setText(DateTimeConv.getTIMEformat(DateTimeConv.utcToLocal(appt.getEnd())));
        customerCombo.getSelectionModel().select(appt.getCustomer());
        contactCombo.getSelectionModel().select(appt.getContact());
        userCombo.getSelectionModel().select(appt.getUser());
    }
}

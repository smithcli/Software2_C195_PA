package GCScheduler.controller;

import GCScheduler.model.Appointment;
import GCScheduler.model.Scheduler;
import GCScheduler.utilities.DateTimeConv;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.ZonedDateTime;

/**
 * Controller class for the Schedule.fxml that holds the tab pane for the other views and the MenuBar.
 */
public class ScheduleController {
    @FXML private Tab apptTab;
    @FXML private Tab customerTab;
    @FXML private Tab reportsTab;

    /**
     * Initialized only after login, making best to hold the appointmentAlert method.
     */
    public void initialize() {
        appointmentAlert();
    }

    /**
     * Creates an Alert message to notify user of any upcoming appointments within 15 minutes.
     */
    protected void appointmentAlert() {
        //Convert local time now and 15min to UTC for comparison.
        ZonedDateTime alertTime = DateTimeConv.localToUTC(ZonedDateTime.now());
        ZonedDateTime alertTime16 = DateTimeConv.localToUTC(ZonedDateTime.now().plusMinutes(16));
        String alertMessage = "Upcoming Appointments:";
        int apptCount = 0;
        for (Appointment appt : Scheduler.getAllAppointments()) {
            if (appt.getStart().isAfter(alertTime) && appt.getStart().isBefore(alertTime16)) {
                apptCount++;
                alertMessage += "\nAppointment ID: "+appt.getApptId()+" at "+DateTimeConv.dateToStrLocal(appt.getStart());
            }
        }
        if (apptCount == 0) {
            alertMessage = "There are no upcoming Appointments";
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION,alertMessage,ButtonType.OK);
        alert.show();
    }
}

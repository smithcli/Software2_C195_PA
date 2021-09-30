package GCScheduler.dao;

import GCScheduler.model.Appointment;
import javafx.collections.ObservableList;

/**
 * Appointment Data Access Interface for CRUD operations on persistence layer.
 */
public interface AppointmentDao {

    /**
     * Adds an Appointment.
     * @param appointment Appointment to add.
     */
    public void createAppt(Appointment appointment);

    /**
     * Gets an Appointment
     * @param apptId Appointment ID.
     * @return Appointment Object
     */
    public Appointment getAppt(int apptId);

    /**
     * Gets An Appointment
     * @param title Appointment Title
     * @param start Appointment Start
     * @param customerId Customer ID
     * @return Appointment Object
     */
    public Appointment getAppt(String title, String start, int customerId);

    /**
     * Gets all appointments.
     * @return ObservableList of Appointments.
     */
    public ObservableList<Appointment> getAllAppts();

    /**
     * Updates an Appointment.
     * @param appointment Appointment to update.
     */
    public void updateAppt(Appointment appointment);

    /**
     * Deletes an Appointment.
     * @param appointment Appointment to delete.
     * @return true if deleted.
     */
    public boolean deleteAppt(Appointment appointment);

    /**
     * Deletes all Appointments.
     * @param appointments ObservableList of Appointments to delete.
     * @return true if deleted.
     */
    public boolean deleteAllAppts(ObservableList<Appointment> appointments);
}

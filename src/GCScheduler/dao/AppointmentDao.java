package GCScheduler.dao;

import GCScheduler.model.Appointment;
import javafx.collections.ObservableList;

public interface AppointmentDao {
    public void createAppt(Appointment appointment);
    public Appointment getAppt(int apptId);
    public Appointment getAppt(String title, String start, int customerId);
    public ObservableList<Appointment> getAllAppts();
    public void updateAppt(Appointment appointment);
    public boolean deleteAppt(Appointment appointment);
    public boolean deleteAllAppts(ObservableList<Appointment> appointments);
}

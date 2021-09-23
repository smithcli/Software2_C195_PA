package GCScheduler.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class Scheduler {
    private static ObservableList<Country> allCountries = FXCollections.observableArrayList();
    private static ObservableList<FirstLevelDiv> allFirstLevelDivs = FXCollections.observableArrayList();
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();
    private static ObservableList<Contact> allContacts = FXCollections.observableArrayList();
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    public static ObservableList<Country> getAllCountries() throws SQLException {
        return allCountries;
    }

    public static void setAllCountries(ObservableList<Country> allCountries) {
        Scheduler.allCountries = allCountries;
    }

    public static void setupCountries() {
        for (Country country :
                allCountries) {
            for (FirstLevelDiv div :
                    allFirstLevelDivs) {
                if (div.getCountryId() == country.getCountryId()) {
                    country.addFirstLevelDiv(div);
                    div.setCountry(country);
                }
            }
        }
    }

    public static ObservableList<FirstLevelDiv> getAllFirstLevelDivs() {
        return allFirstLevelDivs;
    }

    public static void setAllFirstLevelDivs(ObservableList<FirstLevelDiv> allFirstLevelDivs) {
        Scheduler.allFirstLevelDivs = allFirstLevelDivs;
    }

    public static void setupFirstLevelDivs() {
        for (FirstLevelDiv div :
                allFirstLevelDivs) {
            for (Customer customer :
                    allCustomers) {
                if (customer.getDivId() == div.getDivId()) {
                    div.addCustomer(customer);
                    customer.setDiv(div);
                }
            }
        }
    }

    public static ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    }

    public static void setAllCustomers(ObservableList<Customer> allCustomers) {
        Scheduler.allCustomers = allCustomers;
    }

    public static ObservableList<User> getAllUsers() {
        return allUsers;
    }

    public static void setAllUsers(ObservableList<User> allUsers) {
        Scheduler.allUsers = allUsers;
    }

    public static ObservableList<Contact> getAllContacts() {
        return allContacts;
    }

    public static void setAllContacts(ObservableList<Contact> allContacts) {
        Scheduler.allContacts = allContacts;
    }

    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }

    public static void setAllAppointments(ObservableList<Appointment> allAppointments) {
        Scheduler.allAppointments = allAppointments;
    }

    public static void setupAppointments() {
        for (Appointment appt : allAppointments) {
            for (User user : allUsers) {
                if (user.getUserId() == appt.getUserId()) {
                    appt.setUser(user);
                }
            }
            for (Contact contact : allContacts) {
                if (contact.getContactId() == appt.getContactId()) {
                    appt.setContact(contact);
                }
            }
            for (Customer customer : allCustomers) {
                if (customer.getCustomerId() == appt.getCustomerId()) {
                    appt.setCustomer(customer);
                    customer.addAppointment(appt);
                }
            }
        }
    }
}

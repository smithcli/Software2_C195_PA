package GCScheduler.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Scheduler class is a container for all model objects and has a few methods to complete required application setup.
 */
public class Scheduler {
    private static ObservableList<Country> allCountries = FXCollections.observableArrayList();
    private static ObservableList<FirstLevelDiv> allFirstLevelDivs = FXCollections.observableArrayList();
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();
    private static ObservableList<Contact> allContacts = FXCollections.observableArrayList();
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private static User activeUser;

    /**
     * Gets a list of all Countries
     * @return List of all Countries
     */
    public static ObservableList<Country> getAllCountries() {
        return allCountries;
    }

    /**
     * Sets a list of all Countries
     * @param allCountries List of all Countries
     */
    public static void setAllCountries(ObservableList<Country> allCountries) {
        Scheduler.allCountries = allCountries;
    }

    /**
     * Connects required reference links between Country and FirstLevelDiv Objects.
     */
    public static void setupCountries() {
        for (Country country :
                allCountries) {
            for (FirstLevelDiv div :
                    allFirstLevelDivs) {
                // If ID's match and Country hasn't already added the div to the list.
                if (div.getCountryId() == country.getCountryId() && !country.getFirstLevelDivs().contains(div)) {
                    country.getFirstLevelDivs().add(div);
                    div.setCountry(country);
                }
            }
        }
    }

    /**
     * Gets a list of all FirstLevelDivs
     * @return list of all FirstLevelDivs
     */
    public static ObservableList<FirstLevelDiv> getAllFirstLevelDivs() {
        return allFirstLevelDivs;
    }

    /**
     * Sets a list of all FirstLevelDivs
     * @param allFirstLevelDivs list of all FirstLevelDivs
     */
    public static void setAllFirstLevelDivs(ObservableList<FirstLevelDiv> allFirstLevelDivs) {
        Scheduler.allFirstLevelDivs = allFirstLevelDivs;
    }

    /**
     * Connects required reference links between FirstLevelDiv and Customer objects.
     */
    public static void setupFirstLevelDivs() {
        for (FirstLevelDiv div :
                allFirstLevelDivs) {
            for (Customer customer :
                    allCustomers) {
                //If ID's match and Div doesn't already have the customer in the list (removes duplicates).
                if (customer.getDivId() == div.getDivId() && !div.getCustomerList().contains(customer)) {
                    div.getCustomerList().add(customer);
                    customer.setDiv(div);
                }
            }
        }
    }

    /**
     * Gets a list of all Customers
     * @return list of all Customers
     */
    public static ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    }

    /**
     * Sets a list of all Customers.
     * @param allCustomers list of all Customers.
     */
    public static void setAllCustomers(ObservableList<Customer> allCustomers) {
        Scheduler.allCustomers = allCustomers;
    }

    /**
     * Gets a list of all Users.
     * @return list of all Users.
     */
    public static ObservableList<User> getAllUsers() {
        return allUsers;
    }

    /**
     * Sets a list of all Users.
     * @param allUsers list of all Users.
     */
    public static void setAllUsers(ObservableList<User> allUsers) {
        Scheduler.allUsers = allUsers;
    }

    /**
     * Gets a list of all Contacts.
     * @return list of all Contacts.
     */
    public static ObservableList<Contact> getAllContacts() {
        return allContacts;
    }

    /**
     * Sets a list of all Contacts.
     * @param allContacts list of all Contacts.
     */
    public static void setAllContacts(ObservableList<Contact> allContacts) {
        Scheduler.allContacts = allContacts;
    }

    /**
     * Gets a list of all Appointments.
     * @return list of all Appointments.
     */
    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }

    /**
     * Sets a list of all Appointments.
     * @param allAppointments list of all Appointments.
     */
    public static void setAllAppointments(ObservableList<Appointment> allAppointments) {
        Scheduler.allAppointments = allAppointments;
    }

    /**
     * Connects required reference links between Appointments, Users, Contacts, and Customers.
     */
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
                //If ID's Match and Customer doesn't already have the appointment on their list.
                if (customer.getCustomerId() == appt.getCustomerId() && !customer.getAppointments().contains(appt)) {
                    appt.setCustomer(customer);
                    customer.getAppointments().add(appt);
                }
            }
        }
    }

    /**
     * Gets the Active User for the session.
     * @return Active User
     */
    public static User getActiveUser() {
        return activeUser;
    }

    /**
     * Sets the Active User for the session.
     * @param activeUser Active User.
     */
    public static void setActiveUser(User activeUser) {
        Scheduler.activeUser = activeUser;
    }
}

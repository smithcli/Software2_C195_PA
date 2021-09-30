package GCScheduler.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.ZonedDateTime;

/**
 * Customer Class represents customers.
 */
public class Customer {
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phoneNum;
    private ZonedDateTime createDate;
    private String createdBy;
    private ZonedDateTime lastUpdate;
    private String lastUpdatedBy;
    private int divId;
    private FirstLevelDiv div;
    private ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    /**
     * Simple Constructor with just name.
     * @param customerName Name
     */
    public Customer(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Constructor with all required attributes.
     * @param customerId ID
     * @param customerName Name
     * @param address Address
     * @param postalCode Postal Code
     * @param phoneNum Phone Number
     * @param createDate Create Date
     * @param createdBy Created By
     * @param lastUpdate Last Update
     * @param lastUpdatedBy Last Updated By
     * @param divId firstLevelDiv ID
     */
    public Customer(int customerId, String customerName, String address, String postalCode, String phoneNum, ZonedDateTime createDate, String createdBy, ZonedDateTime lastUpdate, String lastUpdatedBy, int divId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNum = phoneNum;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divId = divId;
    }

    /**
     * Gets the Customer ID
     * @return ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the Customer ID
     * @param customerId ID
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the Customer Name
     * @return Name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the Customer Name
     * @param customerName Name
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets the Customer's Address
     * @return Address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the Customer's Address
     * @param address Address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the Customer's Postal Code
     * @return Postal Code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the Customer's Postal Code.
     * @param postalCode Postal Code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets the Customer's Phone Number
     * @return Phone Number
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * Sets the Customer's Phone Number
     * @param phoneNum Phone Number
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * Get the creation DateTime of the Customer object
     * @return Create Date
     */
    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Sets the creation DateTime of the Customer object
     * @param createDate Create Date
     */
    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the author of the Customer object.
     * @return Created By
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the author of the Customer object.
     * @param createdBy Created By
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the DateTime of the last update of the Customer object.
     * @return last update
     */
    public ZonedDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the DateTime of the last update of the Customer object.
     * @param lastUpdate last update
     */
    public void setLastUpdate(ZonedDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Gets the author of the last update of the Customer object.
     * @return last updated by
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the author of the last update of the Customer object.
     * @param lastUpdatedBy last updated by
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Get the Customer's firstLevelDiv ID.
     * @return firstLevelDiv ID
     */
    public int getDivId() {
        return divId;
    }

    /**
     * Set the Customer's firstLevelDiv ID.
     * @param divId firstLevelDiv ID
     */
    public void setDivId(int divId) {
        this.divId = divId;
    }

    /**
     * Get the Customer's firstLevelDiv.
     * @return firstLevelDiv
     */
    public FirstLevelDiv getDiv() {
        return div;
    }

    /**
     * Set the Customer's firstLevelDiv
     * @param div firstLevelDiv
     */
    public void setDiv(FirstLevelDiv div) {
        this.div = div;
    }

    /**
     * Get the Customer's List of appointments
     * @return appointments list
     */
    public ObservableList<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Set the Customer's List of appointments
     * @param appointments appointments list
     */
    public void setAppointments(ObservableList<Appointment> appointments) {
        this.appointments = appointments;
    }

    /**
     * Method to convert object to String for use in ComboBoxes.
     * @return Customer Name
     */
    @Override
    public String toString() {
        return this.customerName;
    }
}


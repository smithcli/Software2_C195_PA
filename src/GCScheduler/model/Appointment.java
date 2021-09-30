package GCScheduler.model;

import java.time.ZonedDateTime;

/**
 * Appointment Class represents appointments and its attributes contains all required info about it.
 */
public class Appointment {
    private int apptId;
    private String apptTitle;
    private String description;
    private String location;
    private String apptType;
    private ZonedDateTime start;
    private ZonedDateTime end;
    private ZonedDateTime createDate;
    private String createBy;
    private ZonedDateTime lastUpdate;
    private String lastUpdateBy;
    private int customerId;
    private Customer customer;
    private int userId;
    private User user;
    private int contactId;
    private Contact contact;

    /**
     * Simple Constructor with just the title.
     * @param apptTitle Title
     */
    public Appointment(String apptTitle) {
        this.apptTitle = apptTitle;
    }

    /**
     * Constructor with all required attributes.
     * @param apptId ID
     * @param apptTitle Title
     * @param description Description
     * @param location Location
     * @param apptType Type
     * @param start Start DateTime
     * @param end End DateTime
     * @param createDate Create Date
     * @param createBy Create By
     * @param lastUpdate Last Update
     * @param lastUpdateBy Last Updated By
     * @param customerId Customer ID
     * @param userId User ID
     * @param contactId Contact ID
     */
    public Appointment(int apptId, String apptTitle, String description, String location, String apptType, ZonedDateTime start, ZonedDateTime end, ZonedDateTime createDate, String createBy, ZonedDateTime lastUpdate, String lastUpdateBy, int customerId, int userId, int contactId) {
        this.apptId = apptId;
        this.apptTitle = apptTitle;
        this.description = description;
        this.location = location;
        this.apptType = apptType;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createBy = createBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**
     * Gets the Appointment ID
     * @return Appointment ID
     */
    public int getApptId() {
        return apptId;
    }

    /**
     * Sets the Appointment ID
      * @param apptId ID
     */
    public void setApptId(int apptId) {
        this.apptId = apptId;
    }

    /**
     * Gets the Appointment Title
     * @return Appointment Title
     */
    public String getApptTitle() {
        return apptTitle;
    }

    /**
     * Set the Appointment Title
     * @param apptTitle Appointment Title
     */
    public void setApptTitle(String apptTitle) {
        this.apptTitle = apptTitle;
    }

    /**
     * Gets the Appointment Description
     * @return Appointment Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the Appointment Description
     * @param description Appointment Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the Appointment Location
     * @return Appointment Location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the Appointment Location
     * @param location Appointment Location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the Appointment Type
     * @return Appointment Type
     */
    public String getApptType() {
        return apptType;
    }

    /**
     * Sets the Appointment Type
     * @param apptType Appointment Type
     */
    public void setApptType(String apptType) {
        this.apptType = apptType;
    }

    /**
     * Gets the Appointment Start DateTime
     * @return Start DateTime
     */
    public ZonedDateTime getStart() {
        return start;
    }

    /**
     * Sets the Appointment Start DateTime
     * @param start Appointment Start DateTime
     */
    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    /**
     * Gets the Appointment End DateTime
     * @return End DateTime
     */
    public ZonedDateTime getEnd() {
        return end;
    }

    /**
     * Sets the Appointment End DateTime
     * @param end End DateTime
     */
    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }

    /**
     * Gets the Appointment creation date
     * @return Creation date
     */
    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Sets the Appointment creation date
     * @param createDate Creation Date
     */
    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the author of the Appointment.
     * @return Created By
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * Sets the author of the Appointment
     * @param createBy Created By
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * Gets the last time the Appointment was updated.
     * @return last update
     */
    public ZonedDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the last time the Appointment was updated.
     * @param lastUpdate last update.
     */
    public void setLastUpdate(ZonedDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Gets the author of the Appointment last update.
     * @return last updated by
     */
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    /**
     * Sets the author of the Appointment last update.
     * @param lastUpdateBy last updated by
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * Gets the associated Customer ID
     * @return Customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the associated Customer ID.
     * @param customerId Customer ID
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the associated Customer.
     * @return Customer object.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the associated Customer.
     * @param customer Customer object.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Gets the associated User ID.
     * @return User ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the associated User ID.
     * @param userId User ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the associated User.
     * @return User object.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the associated User.
     * @param user User object.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the associated Contact ID.
     * @return Contact ID.
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Sets the associated Contact ID
     * @param contactId Contact ID
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Gets the associated Contact.
     * @return Contact object.
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Sets the associated Contact.
     * @param contact Contact object.
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }
}

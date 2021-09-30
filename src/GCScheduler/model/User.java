package GCScheduler.model;

import java.time.ZonedDateTime;

/**
 * User class represents users for the application.
 */
public class User {
    private int userId;
    private String userName;
    private String password;
    private ZonedDateTime createDate;
    private String createdBy;
    private ZonedDateTime lastUpdate;
    private String lastUpdatedBy;
    private boolean active;

    /**
     * Constructor with all required attributes.
     * @param userId User ID
     * @param userName Username
     * @param password Password
     * @param createDate Create Date
     * @param createdBy Created By
     * @param lastUpdate Last Update
     * @param lastUpdatedBy Last Updated By
     */
    public User(int userId, String userName, String password, ZonedDateTime createDate, String createdBy, ZonedDateTime lastUpdate, String lastUpdatedBy) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.active = false;
    }

    /**
     * Get the User ID
     * @return ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Set the User ID
     * @param userId ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Get the Username
     * @return Username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the Username
     * @param userName Username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get the User Password
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the User Password
     * @param password Password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the creation date of the User object
     * @return Create date
     */
    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Set the creation date of the User object
     * @param createDate Create date
     */
    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Get the author of the User object
     * @return Created By
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Set the author of the User object
     * @param createdBy Created By
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Get the last update of the User object.
     * @return Last Update
     */
    public ZonedDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Set the last update of the User object.
     * @param lastUpdate Last Update
     */
    public void setLastUpdate(ZonedDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Get the author of the User object's last update.
     * @return Last Updated By
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Set the author of the User object's last update.
     * @param lastUpdatedBy Last Updated By
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Determine if the User is active.
     * @return Boolean Active = true
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Set the active status of the User
     * @param active Boolean Active = true
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Method to convert object to String for use with ComboBoxes.
     * @return Username
     */
    @Override
    public String toString() {
        return this.userName;
    }
}

package GCScheduler.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.ZonedDateTime;

/**
 * FirstLevelDiv class represents the first sub division of a Country.
 */
public class FirstLevelDiv {
    private int divId;
    private String divName;
    private ZonedDateTime createDate;
    private String createdBy;
    private ZonedDateTime lastUpdate;
    private String lastUpdatedBy;
    private int countryId;
    private Country country;
    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

    /**
     * Constructor with all required attributes.
     * @param divId ID
     * @param divName Name
     * @param createDate Create Date
     * @param createdBy Created By
     * @param lastUpdate Last Update
     * @param lastUpdatedBy Last Updated By
     * @param countryId Country ID
     */
    public FirstLevelDiv(int divId, String divName, ZonedDateTime createDate, String createdBy, ZonedDateTime lastUpdate, String lastUpdatedBy, int countryId) {
        this.divId = divId;
        this.divName = divName;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryId = countryId;
    }

    /**
     * Get the FirstLevelDiv ID
     * @return ID
     */
    public int getDivId() {
        return divId;
    }

    /**
     * Set the FirstLevelDiv ID
     * @param divId ID
     */
    public void setDivId(int divId) {
        this.divId = divId;
    }

    /**
     * Get the FirstLevelDiv name
     * @return Name
     */
    public String getDivName() {
        return divName;
    }

    /**
     * Set the FirstLevelDiv name
     * @param divName Name
     */
    public void setDivName(String divName) {
        this.divName = divName;
    }

    /**
     * Get the creation date of the FirstLevelDiv object.
     * @return Create date
     */
    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Set the creation date of the FirstLevelDiv object.
     * @param createDate Create date
     */
    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Get the author of the FirstLevelDiv object.
     * @return Created By
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Set the author of the FirstLevelDiv object.
     * @param createdBy Created By
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Get the date of the FirstLevelDiv object's last update.
     * @return Last Update
     */
    public ZonedDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Set the date of the FirstLevelDiv object's last update.
     * @param lastUpdate Last update.
     */
    public void setLastUpdate(ZonedDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Get the author of the FirstLevelDiv object's last update.
     * @return Last Updated By
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Set the author of the FirstLevelDiv object's last update.
     * @param lastUpdatedBy Last updated by
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Get the associated Country ID
     * @return Country ID
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Set the associated Country ID
     * @param countryId Country ID
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Get the associated Country object
     * @return Country object
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Set the associated Country object
     * @param country Country object
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * Get the List of all associated Customers in the Division.
     * @return Customer List
     */
    public ObservableList<Customer> getCustomerList() {
        return customerList;
    }

    /**
     * Set the List of all associated Customers in the Division.
     * @param customerList Customer List
     */
    public void setCustomerList(ObservableList<Customer> customerList) {
        this.customerList = customerList;
    }

    /**
     * Method to convert object to a String for use with ComboBoxes.
     * @return FirstLevelDiv Name
     */
    @Override
    public String toString() {
        return this.divName;
    }
}

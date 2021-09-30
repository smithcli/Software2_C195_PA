package GCScheduler.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.ZonedDateTime;

/**
 * Country Class represents Countries and holds a container for referencing the country's sub divisions.
 */
public class Country {
    private int countryId;
    private String countryName;
    private ZonedDateTime createDate;
    private String createdBy;
    private ZonedDateTime lastUpdate;
    private String lastUpdatedBy;
    private ObservableList<FirstLevelDiv> firstLevelDivs = FXCollections.observableArrayList();

    /**
     * Constructor with all required attributes.
     * @param countryId ID
     * @param countryName Name
     * @param createDate Date Created
     * @param createdBy Author
     * @param lastUpdate Last Update
     * @param lastUpdatedBy Last Update Author
     */
    public Country(int countryId, String countryName, ZonedDateTime createDate, String createdBy, ZonedDateTime lastUpdate, String lastUpdatedBy) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Gets the Country ID
     * @return ID
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Sets the Country ID
     * @param countryId ID
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Gets the Country Name
     * @return Name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets the Country Name
     * @param countryName Name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Gets the Country Object creation date.
     * @return create date
     */
    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Sets the Country Object creation date.
     * @param createDate create date
     */
    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the Country object's author
     * @return created by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Set the Country object's author
     * @param createdBy created by
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Get the last update to the Country object.
     * @return last update
     */
    public ZonedDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Set the last update to the Country object.
     * @param lastUpdate last update.
     */
    public void setLastUpdate(ZonedDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Get the author of the last update to the Country object.
     * @return last updated by
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Set the author of the last update to the Country object.
     * @param lastUpdatedBy last updated by
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Get all of the Country's first level sub divisions.
     * @return firstLevelDivs
     */
    public ObservableList<FirstLevelDiv> getFirstLevelDivs() {
        return firstLevelDivs;
    }

    /**
     * Set all of the Country's first level sub divisions.
     * @param firstLevelDivs firstLevelDivs
     */
    public void setFirstLevelDivs(ObservableList<FirstLevelDiv> firstLevelDivs) {
        this.firstLevelDivs = firstLevelDivs;
    }

    /**
     * Method to convert Object to String for ComboBoxes.
     * @return Country Name
     */
    @Override
    public String toString() {
        return this.countryName;
    }
}


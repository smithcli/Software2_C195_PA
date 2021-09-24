package GCScheduler.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.ZonedDateTime;

public class Country {
    private int countryId;
    private String countryName;
    private ZonedDateTime createDate;
    private String createdBy;
    private ZonedDateTime lastUpdate;
    private String lastUpdatedBy;
    private ObservableList<FirstLevelDiv> firstLevelDivs = FXCollections.observableArrayList();

    public Country(int countryId, String countryName, ZonedDateTime createDate, String createdBy, ZonedDateTime lastUpdate, String lastUpdatedBy) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(ZonedDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public ObservableList<FirstLevelDiv> getFirstLevelDivs() {
        return firstLevelDivs;
    }

    public void setFirstLevelDivs(ObservableList<FirstLevelDiv> firstLevelDivs) {
        this.firstLevelDivs = firstLevelDivs;
    }

    public void addFirstLevelDiv(FirstLevelDiv firstLevelDiv) {
        firstLevelDivs.add(firstLevelDiv);
    }

    public void removeFirstLevelDiv(FirstLevelDiv firstLevelDiv) {
        firstLevelDivs.remove(firstLevelDiv);
    }

    @Override
    public String toString() {
        return this.countryName;
    }
}


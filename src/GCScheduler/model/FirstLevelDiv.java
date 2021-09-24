package GCScheduler.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.ZonedDateTime;

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

    public FirstLevelDiv(int divId, String divName, ZonedDateTime createDate, String createdBy, ZonedDateTime lastUpdate, String lastUpdatedBy, int countryId) {
        this.divId = divId;
        this.divName = divName;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryId = countryId;
    }

    public int getDivId() {
        return divId;
    }

    public void setDivId(int divId) {
        this.divId = divId;
    }

    public String getDivName() {
        return divName;
    }

    public void setDivName(String divName) {
        this.divName = divName;
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

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public ObservableList<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(ObservableList<Customer> customerList) {
        this.customerList = customerList;
    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customerList.remove(customer);
    }

    @Override
    public String toString() {
        return this.divName;
    }
}

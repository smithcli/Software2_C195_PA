package GCScheduler.dao;

import GCScheduler.model.Country;
import javafx.collections.ObservableList;

/**
 * Country Data Access Interface for CRUD operations on persistence layer.
 */
public interface CountryDao {

    /**
     * Adds a Country.
     * @param country Country to add.
     */
    public void createCountry(Country country);

    /**
     * Gets a Country.
     * @param countryId Country ID.
     * @return Country Object.
     */
    public Country getCountry(int countryId);

    /**
     * Gets all Countries.
     * @return ObservableList of Countries.
     */
    public ObservableList<Country> getAllCountries();

    /**
     * Updates a Country.
     * @param country Country to update.
     */
    public void updateCountry(Country country);

    /**
     * Deletes a Country.
     * @param country Country to delete.
     * @return true if deleted.
     */
    public boolean deleteCountry(Country country);
}

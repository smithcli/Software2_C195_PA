package GCScheduler.dao;

import GCScheduler.model.Country;
import javafx.collections.ObservableList;

public interface CountryDao {
    public void createCountry(Country country);
    public Country getCountry(int countryId);
    public ObservableList<Country> getAllCountries();
    public void updateCountry(Country country);
    public boolean deleteCountry(Country country);
}

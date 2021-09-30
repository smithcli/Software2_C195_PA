package GCScheduler.dao.JDBC;

import GCScheduler.dao.CountryDao;
import GCScheduler.model.Country;
import GCScheduler.utilities.DateTimeConv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

/**
 * Queries the mySql database with CRUD operations for the Country model object.
 */
public class CountryImpl implements CountryDao {

    /**
     * Not used.
     * Creates a new Country record using the Country object attributes.
     * @param country Country to add.
     */
    @Override
    public void createCountry(Country country) {

    }

    /**
     * Returns a Country object by using the Country id, the primary key in the table.
     * @param countryId Country ID to locate the record.
     * @return Country object.
     */
    @Override
    public Country getCountry(int countryId) {
        String query = "SELECT * FROM countries WHERE Country_ID = " + countryId + ";";
        try {
            ResultSet rset = JDBC.getConnection().createStatement().executeQuery(query);
            rset.next();
            return makeCountry(rset);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns all Country records using an ObservableList of Country type.
     * @return ObservableList of Countries.
     */
    @Override
    public ObservableList<Country> getAllCountries() {
        String query = "SELECT * FROM countries;";
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        try {
            ResultSet rset = JDBC.getConnection().createStatement().executeQuery(query);
            while (rset.next()) {
                allCountries.add(makeCountry(rset));
            }
            return allCountries;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Not used.
     * Updates the Country record using the passed Country object's attributes.
     * @param country Country to update.
     */
    @Override
    public void updateCountry(Country country) {

    }

    /**
     * Not used.
     * Deletes a Country record using the Country object's id.
     * @param country Country to delete.
     * @return true if deleted.
     */
    @Override
    public boolean deleteCountry(Country country) {
        return false;
    }

    /**
     * Helper method to return a ResultSet for a Country record.
     * @param rset the ResultSet to help.
     * @return new Country with attributes from the record.
     * @throws SQLException uses ResultSet.
     */
    private Country makeCountry(ResultSet rset) throws SQLException {
        int id = rset.getInt("Country_ID");
        String name = rset.getString("Country");
        String createDate = rset.getString("Create_Date");
        String createdBy = rset.getString("Created_By");
        String lastUpdate = rset.getString("Last_Update");
        String lastUpdatedBy = rset.getString("Last_Updated_By");
        ZonedDateTime cDate = DateTimeConv.strToDateUTC(createDate);
        ZonedDateTime uDate = DateTimeConv.strToDateUTC(lastUpdate);
        return new Country(id,name,cDate,createdBy,uDate,lastUpdatedBy);
    }
}

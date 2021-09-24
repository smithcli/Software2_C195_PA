package GCScheduler.dao.JDBC;

import GCScheduler.dao.CountryDao;
import GCScheduler.model.Country;
import GCScheduler.utilities.DateTimeConv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

public class CountryImpl implements CountryDao {
    @Override
    public void createCountry(Country country) {

    }

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

    @Override
    public void updateCountry(Country country) {

    }

    @Override
    public boolean deleteCountry(Country country) {
        return false;
    }

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

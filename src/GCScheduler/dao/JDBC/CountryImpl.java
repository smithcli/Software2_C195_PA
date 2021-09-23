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
            ResultSet resultSet = JDBC.conn.createStatement().executeQuery(query);
            resultSet.next();
            int id = resultSet.getInt("Country_ID");
            String name = resultSet.getString("Country");
            String createDate = resultSet.getString("Create_Date");
            String createdBy = resultSet.getString("Created_By");
            String lastUpdate = resultSet.getString("Last_Update");
            String lastUpdatedBy = resultSet.getString("Last_Updated_By");
            ZonedDateTime cDate = DateTimeConv.strToDateUTC(createDate);
            ZonedDateTime uDate = DateTimeConv.strToDateUTC(lastUpdate);
            return new Country(id,name,cDate,createdBy,uDate,lastUpdatedBy);
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
            ResultSet resultSet = JDBC.conn.createStatement().executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("Country_ID");
                String name = resultSet.getString("Country");
                String createDate = resultSet.getString("Create_Date");
                String createdBy = resultSet.getString("Created_By");
                String lastUpdate = resultSet.getString("Last_Update");
                String lastUpdatedBy = resultSet.getString("Last_Updated_By");
                ZonedDateTime cDate = DateTimeConv.strToDateUTC(createDate);
                ZonedDateTime uDate = DateTimeConv.strToDateUTC(lastUpdate);
                Country country = new Country(id,name,cDate,createdBy,uDate,lastUpdatedBy);
                allCountries.add(country);
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
}

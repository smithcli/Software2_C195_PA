package GCScheduler.dao.JDBC;

import GCScheduler.dao.FirstLevelDivDao;
import GCScheduler.model.FirstLevelDiv;
import GCScheduler.utilities.DateTimeConv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

public class FirstLevelDivImpl implements FirstLevelDivDao {
    @Override
    public void createFirstLevelDiv(FirstLevelDiv firstLevelDiv) {

    }

    @Override
    public FirstLevelDiv getFirstLevelDiv(int divId) {
        String query = "SELECT * FROM first_level_divisions WHERE Division_ID = " + divId + ";";
        try {
            ResultSet rset = JDBC.getConnection().createStatement().executeQuery(query);
            rset.next();
            return makeDiv(rset);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<FirstLevelDiv> getAllFirstLevelDivs() {
        String query = "SELECT * FROM first_level_divisions;";
        ObservableList<FirstLevelDiv> allFirstLevelDivs = FXCollections.observableArrayList();
        try {
            ResultSet rset = JDBC.getConnection().createStatement().executeQuery(query);
            while (rset.next()) {
                allFirstLevelDivs.add(makeDiv(rset));
            }
            return allFirstLevelDivs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(FirstLevelDiv firstLevelDiv) {

    }

    @Override
    public boolean deleteFirstLevelDiv(FirstLevelDiv firstLevelDiv) {
        return false;
    }

    private FirstLevelDiv makeDiv(ResultSet rset) throws SQLException {
        int id = rset.getInt("Division_ID");
        String div = rset.getString("Division");
        String createDate = rset.getString("Create_Date");
        String createdBy = rset.getString("Created_By");
        String lastUpdate = rset.getString("Last_Update");
        String lastUpdatedBy = rset.getString("Last_Updated_By");
        int countryId = rset.getInt("Country_ID");
        ZonedDateTime cDate = DateTimeConv.strToDateUTC(createDate);
        ZonedDateTime uDate = DateTimeConv.strToDateUTC(lastUpdate);
        return new FirstLevelDiv(id,div,cDate,createdBy,uDate,lastUpdatedBy,countryId);
    }
}

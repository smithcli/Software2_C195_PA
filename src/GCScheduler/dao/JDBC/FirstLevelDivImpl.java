package GCScheduler.dao.JDBC;

import GCScheduler.dao.FirstLevelDivDao;
import GCScheduler.model.FirstLevelDiv;
import GCScheduler.utilities.DateTimeConv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

/**
 * Queries the mySql database with CRUD operations for the FirstLevelDiv model Object.
 */
public class FirstLevelDivImpl implements FirstLevelDivDao {

    /**
     * Not used.
     * Creates a new firstLevelDiv record using the object attributes.
     * @param firstLevelDiv firstLevelDiv to add.
     */
    @Override
    public void createFirstLevelDiv(FirstLevelDiv firstLevelDiv) {

    }

    /**
     * Returns a firstLevelDiv object by using the ID, the primary key in the table.
     * @param divId ID to locate the firstLevelDiv.
     * @return firstLevelDiv object.
     */
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

    /**
     * Returns all firstLevelDiv records using an ObservableList.
     * @return ObservableList of firstLevelDivs.
     */
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

    /**
     * Not used.
     * Updates the record using the passed object.
     * @param firstLevelDiv firstLevelDiv to update.
     */
    @Override
    public void update(FirstLevelDiv firstLevelDiv) {

    }

    /**
     * Not used.
     * Deletes a firstLevelDiv record using the ID.
     * @param firstLevelDiv firstLevelDiv to delete.
     * @return true if deleted.
     */
    @Override
    public boolean deleteFirstLevelDiv(FirstLevelDiv firstLevelDiv) {
        return false;
    }

    /**
     * Helper Method to return a ResultSet for a firstLevelDiv record.
     * @param rset The ResultSet to help.
     * @return new Div with attributes from the record.
     * @throws SQLException uses ResultSet.
     */
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

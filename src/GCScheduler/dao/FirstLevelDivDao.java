package GCScheduler.dao;

import GCScheduler.model.FirstLevelDiv;
import javafx.collections.ObservableList;

/**
 * FirstLevelDiv Data Access Interface for CRUD operations on persistence layer.
 */
public interface FirstLevelDivDao {

    /**
     * Adds a FirstLevelDiv.
     * @param firstLevelDiv FirstLevelDiv to add.
     */
    public void createFirstLevelDiv(FirstLevelDiv firstLevelDiv);

    /**
     * Gets a FirstLevelDiv.
     * @param divId Div ID.
     * @return FirstLevelDiv object.
     */
    public FirstLevelDiv getFirstLevelDiv(int divId);

    /**
     * Gets all FirstLevelDivs.
     * @return ObservableList of FirstLevelDivs.
     */
    public ObservableList<FirstLevelDiv> getAllFirstLevelDivs();

    /**
     * Updates a FirstLevelDiv.
     * @param firstLevelDiv FirstLevelDiv to update.
     */
    public void update(FirstLevelDiv firstLevelDiv);

    /**
     * Deletes a FirstLevelDiv.
     * @param firstLevelDiv FirstLevelDiv to delete.
     * @return true if deleted.
     */
    public boolean deleteFirstLevelDiv(FirstLevelDiv firstLevelDiv);
}

package GCScheduler.dao;

import GCScheduler.model.FirstLevelDiv;
import javafx.collections.ObservableList;

public interface FirstLevelDivDao {
    public void createFirstLevelDiv(FirstLevelDiv firstLevelDiv);
    public FirstLevelDiv getFirstLevelDiv(int divId);
    public ObservableList<FirstLevelDiv> getAllFirstLevelDivs();
    public void update(FirstLevelDiv firstLevelDiv);
    public boolean deleteFirstLevelDiv(FirstLevelDiv firstLevelDiv);
}

package GCScheduler.dao;

import GCScheduler.model.User;
import javafx.collections.ObservableList;

public interface UserDao {
    public void createUser(User user);
    public User getUser(int usrId);
    public ObservableList<User> getAllUsers();
    public void updateUser(User user);
    public boolean deleteUser(User user);
}

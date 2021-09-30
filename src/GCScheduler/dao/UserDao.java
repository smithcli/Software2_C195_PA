package GCScheduler.dao;

import GCScheduler.model.User;
import javafx.collections.ObservableList;

/**
 * User Data Access Interface for CRUD operations on persistence layer.
 */
public interface UserDao {

    /**
     * Adds a User.
     * @param user User to add.
     */
    public void createUser(User user);

    /**
     * Gets a User.
     * @param usrId User ID.
     * @return User object.
     */
    public User getUser(int usrId);

    /**
     * Gets all Users.
     * @return ObservableList of Users.
     */
    public ObservableList<User> getAllUsers();

    /**
     * Updates a User.
     * @param user User to update.
     */
    public void updateUser(User user);

    /**
     * Deletes a User.
     * @param user User to delete.
     * @return true if deleted.
     */
    public boolean deleteUser(User user);
}

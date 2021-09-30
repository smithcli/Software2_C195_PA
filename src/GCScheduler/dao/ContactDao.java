package GCScheduler.dao;

import GCScheduler.model.Contact;
import javafx.collections.ObservableList;

/**
 * Contact Data Access Interface for CRUD operations on persistence layer.
 */
public interface ContactDao {

    /**
     * Adds Contact.
     * @param contact Contact to add.
     */
    public void createContact(Contact contact);

    /**
     * Gets a Contact.
     * @param contactId Contact ID.
     * @return Contact Object.
     */
    public Contact getContact(int contactId);

    /**
     * Gets all Contacts.
     * @return ObservableList of Contacts.
     */
    public ObservableList<Contact> getAllContacts();

    /**
     * Updates a Contact.
     * @param contact Contact to update.
     */
    public void updateContact(Contact contact);

    /**
     * Deletes a Contact.
     * @param contact Contact to delete.
     * @return true if deleted.
     */
    public boolean deleteContact(Contact contact);
}

package GCScheduler.dao;

import GCScheduler.model.Contact;
import javafx.collections.ObservableList;

public interface ContactDao {
    public void createContact(Contact contact);
    public Contact getContact(int contactId);
    public ObservableList<Contact> getAllContacts();
    public void updateContact(Contact contact);
    public boolean deleteContact(Contact contact);
}

package GCScheduler.dao.JDBC;

import GCScheduler.dao.ContactDao;
import GCScheduler.model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Queries the mySql database with CRUD operations for the Contact model object.
 */
public class ContactImpl implements ContactDao {

    /**
     * Not used.
     * Creates a new Contact record using the Contact object attributes.
     * @param contact Contact to add.
     */
    @Override
    public void createContact(Contact contact) {

    }

    /**
     * Returns a Contact object by using the contact id, the primary key in the table.
     * @param contactId Contact ID to locate the Contact.
     * @return Contact object.
     */
    @Override
    public Contact getContact(int contactId) {
        String query = "SELECT * FROM contacts WHERE Contact_ID = " + contactId + ";";
        try {
            ResultSet rset = JDBC.getConnection().createStatement().executeQuery(query);
            rset.next();
            return makeContact(rset);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns all Contact records using an ObservableList of Contact type.
     * @return ObservableList of Contacts.
     */
    @Override
    public ObservableList<Contact> getAllContacts() {
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        String query = "SELECT * FROM contacts";
        try {
            ResultSet rset = JDBC.getConnection().createStatement().executeQuery(query);
            while (rset.next()) {
                allContacts.add(makeContact(rset));
            }
            return allContacts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Not used.
     * Updates the Contact record using the passed Contact objects attributes.
     * @param contact Contact to update.
     */
    @Override
    public void updateContact(Contact contact) {

    }

    /**
     * Not used.
     * Deletes a Contact record using the Contact object's ID.
     * @param contact Contact to delete.
     * @return true if deleted.
     */
    @Override
    public boolean deleteContact(Contact contact) {
        return false;
    }

    /**
     * Helper Method to return a ResultSet for a Contact record.
     * @param rset The ResultSet to help.
     * @return new Contact with attributes from the record.
     * @throws SQLException uses ResultSet.
     */
    private Contact makeContact(ResultSet rset) throws SQLException {
        int id = rset.getInt("Contact_ID");
        String name = rset.getString("Contact_Name");
        String email = rset.getString("Email");
        return new Contact(id,name,email);
    }
}

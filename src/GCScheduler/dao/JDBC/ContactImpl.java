package GCScheduler.dao.JDBC;

import GCScheduler.dao.ContactDao;
import GCScheduler.model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactImpl implements ContactDao {
    @Override
    public void createContact(Contact contact) {

    }

    @Override
    public Contact getContact(int contactId) {
        String query = "SELECT * FROM contacts WHERE Contact_ID = " + contactId + ";";
        try {
            ResultSet rset = JDBC.conn.createStatement().executeQuery(query);
            rset.next();
            int id = rset.getInt("Contact_ID");
            String name = rset.getString("Contact_Name");
            String email = rset.getString("Email");
            return new Contact(id,name,email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<Contact> getAllContacts() {
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        String query = "SELECT * FROM contacts";
        try {
            ResultSet rset = JDBC.conn.createStatement().executeQuery(query);
            while (rset.next()) {
                int id = rset.getInt("Contact_ID");
                String name = rset.getString("Contact_Name");
                String email = rset.getString("Email");
                Contact contact = new Contact(id,name,email);
                allContacts.add(contact);
            }
            return allContacts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateContact(Contact contact) {

    }

    @Override
    public boolean deleteContact(Contact contact) {
        return false;
    }
}

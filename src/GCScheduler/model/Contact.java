package GCScheduler.model;

/**
 * Contact Class represents contacts.
 */
public class Contact {
    private int contactId;
    private String contactName;
    private String email;

    /**
     * Contact Class Constructor with all required attributes.
     * @param contactId ID
     * @param contactName Name
     * @param email Email
     */
    public Contact(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * Gets the Contact ID
     * @return ID
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Sets the Contact ID
     * @param contactId ID
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Gets the Contact Name
     * @return Name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the Contact Name
     * @param contactName Name
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Gets the Contact email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the Contact email
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method to Convert Object to String for use in ComboBoxes.
     * @return Contact Name
     */
    @Override
    public String toString() {
        return this.getContactName();
    }
}

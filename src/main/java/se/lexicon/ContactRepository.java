package se.lexicon;

import java.util.Arrays;

public class ContactRepository {

    private static final int MAX_SIZE = 100;

    private static String[] contacts = new String[0];

    // Methods
    public static boolean add(String contact) { // "Name,Mobile,Email"
        if (contact == null || contact.isEmpty()) {
            //throw new IllegalArgumentException("Contact  Data is null.");
            return false;
        }
        if (contacts.length > MAX_SIZE) {
            //throw new IllegalArgumentException("Contact length should not be > " + MAX_SIZE);
            return false;
        }
        if (isDuplicateMobileNumber(contact)) {
            //throw new IllegalArgumentException("Contact  Data is null.");
            return false;
        }
        String[] newArray = Arrays.copyOf(contacts, contacts.length + 1);
        newArray[newArray.length - 1] = contact;
        contacts = newArray;
        return true;
    }

    private static boolean isDuplicateMobileNumber(String contact) { //"Name,Mobile,Email"
        String paramMobileNumber = contact.split(",")[1];
        for (String contactElement : contacts) { // "name,mobile,email"
            String elementMobileNumber = contactElement.split(",")[1];
            if (elementMobileNumber.equals(paramMobileNumber)) {
                return true;
            }
        }
        return false;
    }


    public static int size() {
        return contacts.length;
    }

    public static String[] getAll() {
        return Arrays.copyOf(contacts, contacts.length);
    }

    public static void clear() {
        contacts = new String[0];
    }


    // Method to find and return contacts by name
    public static String[] findByName(final String name) {
        if (name == null || name.isEmpty()) {
            return new String[0]; // Return an empty array if the name is null or empty
        }

        String[] result = new String[0];
        for (String element : contacts) {
            String contactName = element.split(",")[0]; // Assuming name is the first element separated by comma

            if (contactName.equalsIgnoreCase(name)) {
                String[] tmp = Arrays.copyOf(result, result.length + 1);
                tmp[tmp.length - 1] = element;
                result = tmp;
            }
        }
        return result;
    }

    // Method to remove contacts by mobile number
    public static boolean removeByMobileNumber(final String mobileNumber) {
        int indexToRemove = -1;
        for (int i = 0; i < size(); i++) {
            if (contacts[i] != null && contacts[i].contains(mobileNumber)) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove != -1) {
            String[] newContacts = new String[size() - 1];
            int newIndex = 0;
            for (int i = 0; i < size(); i++) {
                if (i != indexToRemove) {
                    newContacts[newIndex++] = contacts[i];
                }
            }
            contacts = newContacts;
            return true;
        }
        return false;
    }

    // Method to remove contacts by mobile number
    public static boolean removeByMobileNumberV2(final String mobileNumber) {
        if (mobileNumber == null || mobileNumber.isEmpty()) {
            return false;
        }

        int currentSize = size();
        int indexToRemove = -1;

        // Find the index of the contact to remove
        for (int i = 0; i < currentSize; i++) {
            if (contacts[i] != null && contacts[i].contains(mobileNumber)) {
                indexToRemove = i;
                break;
            }
        }

        // If no contact is found, return false
        if (indexToRemove == -1) {
            return false;
        }

        // Create a new array with one less element
        String[] newContacts = new String[currentSize - 1];

        // Copy elements, skipping the one to remove
        System.arraycopy(contacts, 0, newContacts, 0, indexToRemove);
        System.arraycopy(contacts, indexToRemove + 1, newContacts, indexToRemove, currentSize - indexToRemove - 1);

        // Update the contacts array
        contacts = newContacts;

        return true;
    }


    // Method to update contact information by mobile number
    public static boolean updateByMobileNumber(final String mobileNumber, final String newContact) {
        // Check if the provided mobile number is a duplicate
        if (isDuplicateMobileNumber(newContact)) {
            return false; // Mobile number already exists in another contact
        }

        for (int i = 0; i < size(); i++) {
            if (contacts[i] != null && contacts[i].contains(mobileNumber)) {
                // Update the contact information
                contacts[i] = newContact;
                return true; // Contact updated successfully
            }
        }
        return false;
    }


}

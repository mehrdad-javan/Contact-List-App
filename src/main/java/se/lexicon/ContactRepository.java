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


    public static int size(){
        return contacts.length;
    }

    public static String[] getAll(){
        return Arrays.copyOf(contacts, contacts.length);
    }

    public static void clear(){
        contacts = new String[0];
    }

}

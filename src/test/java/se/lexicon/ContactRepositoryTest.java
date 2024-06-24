package se.lexicon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactRepositoryTest {

    @BeforeEach
    public void setup(){
        ContactRepository.clear();
    }

    @Test
    void testAdd() {
        String contactData = "Mehrdad Javan,1234567890,mehrdad.javan@lexicon.se";
        assertTrue(ContactRepository.add(contactData));
    }


    @Test
    void testAdd_DuplicateMobileNumber() {
        String contactData = "Mehrdad Javan,1234567890,mehrdad.javan@lexicon.se";
        assertTrue(ContactRepository.add(contactData));
        assertFalse(ContactRepository.add(contactData));
    }

    @Test
    void testAdd_EmptyContact() {
        String contactData = "";
        assertFalse(ContactRepository.add(contactData));
    }
 @Test
    void testAdd_NullContact() {
        String contactData = null;
        assertFalse(ContactRepository.add(contactData));
    }

    @Test
    void testSize() {
        String contactData = "Mehrdad Javan,1234567890,mehrdad.javan@lexicon.se";
        assertTrue(ContactRepository.add(contactData));

        int expectedSize = 1;
        int actualSize = ContactRepository.size();

        assertEquals(expectedSize, actualSize);
    }


    @Test
    void testGetAll() {
        String contact2 = "Jane Smith,0987654321,jane.smith@example.com";
        ContactRepository.add(contact2);
        String[] contacts = ContactRepository.getAll();
        assertEquals(1, contacts.length);
    }

    @Test
    void testFindByName() {
        String contactData = "Mehrdad Javan,1234567890,mehrdad.javan@lexicon.se";
        assertTrue(ContactRepository.add(contactData));

        String[] result = ContactRepository.findByName("Mehrdad Javan");
        assertEquals(1, result.length);
        assertTrue(result[0].contains("1234567890"));
    }

    @Test
    void testRemoveByMobileNumber() {
        String contactData = "Mehrdad Javan,1234567890,mehrdad.javan@lexicon.se";
        assertTrue(ContactRepository.add(contactData));

        assertTrue(ContactRepository.removeByMobileNumber("1234567890"));
        assertEquals(0, ContactRepository.getAll().length);
    }

    @Test
    void testRemoveByMobileNumber_NotFound() {
        String contactData = "Mehrdad Javan,1234567890,mehrdad.javan@lexicon.se";
        assertTrue(ContactRepository.add(contactData));

        assertFalse(ContactRepository.removeByMobileNumber("0987654321"));
        assertEquals(1, ContactRepository.getAll().length);
    }

    @Test
    void testUpdateByMobileNumber() {
        String contactData = "Mehrdad Javan,1234567890,mehrdad.javan@lexicon.se";
        assertTrue(ContactRepository.add(contactData));


        String updatedContact = "Nickan Javan,2222222222,nickan@test.se";
        assertTrue(ContactRepository.updateByMobileNumber("1234567890", updatedContact));
        assertEquals(updatedContact, ContactRepository.getAll()[0]);
    }


}

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


}

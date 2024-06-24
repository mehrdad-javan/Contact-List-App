package se.lexicon;


import javax.naming.Name;

public class App {
    public static void main(String[] args) {

        System.out.println(ContactRepository.size()); // 0
        boolean isAddedContact1 = ContactRepository.add("Test,1234567890,test@test.se");
        System.out.println(isAddedContact1); // -> true
        System.out.println(ContactRepository.size()); // 1
        System.out.println(ContactRepository.add("Test2,1234567890,test2@test2.se")); // ? false
        System.out.println(ContactRepository.size()); // 1

    }
}

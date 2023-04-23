package eu.epfc.a5900.contactapp;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class ContactApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PrintStream output = System.out;

        ContactDao.initialize();
        String choice;
        do {
            choice = UIContact.getMenuChoice(input, output);
            switch (choice) {
                case "1" -> showContacts(output);
                case "2" -> addContact(input, output);
                case "3" -> updateContact(input, output);
                case "4" -> deleteContact(input, output);
            }
        } while(! choice.equals("Q"));
    }

    private static void deleteContact(Scanner input, PrintStream output) {
        output.println("\nFormulaire de suppression d'un contact");
    }

    private static void updateContact(Scanner input, PrintStream output) {
        output.println("\nFormulaire de modification d'un contact");
    }

    private static void addContact(Scanner input, PrintStream output) {
        Contact contact = UIContact.get(input, output);
        ContactDao.add(contact);
    }

    private static void showContacts(PrintStream output) {
        List<Contact> contacts = ContactDao.fetch();
        UIContact.show(output, contacts);
    }

}

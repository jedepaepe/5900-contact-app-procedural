package eu.epfc.a5900.contactapp;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class UIContact {
    static String getMenuChoice(Scanner input, PrintStream output) {
        output.println("\nChoisissez dans les options suivantes");
        output.println("(1) pour afficher les contacts");
        output.println("(2) pour insérer un nouveau contact");
        output.println("(3) pour modifier un contact existant");
        output.println("(4) pour supprimer un contact");
        output.println("(Q) pour quitter l'application");
        return input.nextLine();
    }

    static Contact get(Scanner input, PrintStream output) {
        Contact contact = new Contact();
        output.println("\nFormulaire d'ajout du contact");
        output.print("Prénom: ");
        contact.firstName = input.nextLine();
        output.print("Nom de famille: ");
        contact.lastName = input.nextLine();
        output.print("Email: ");
        contact.email = input.nextLine();
        output.print("Téléphone: ");
        contact.phone = input.nextLine();
        return contact;
    }

    static void show(PrintStream output, List<Contact> contacts) {
        output.println("\nAffiche les contacts");
        for (Contact c : contacts) {
            output.println(String.join(" - ", "" + c.id, c.firstName, c.lastName, c.email, c.phone));
        }
    }
}

package UI;

import Domain.Kundenverwaltung;
import Domain.Lagerverwaltung;
import ValueObjekt.Artikel;
import ValueObjekt.Mitarbeiter;

import java.util.Scanner;


public class Main {
    //fast fertig
    public static void main(String[] args) {
        //Scanner Klasse für Keyboard-Input
        //Scanner scanner = new Scanner(System.in);


        Lagerverwaltung lagerverwaltung = new Lagerverwaltung();

        // Neue Artikel anlegen
        lagerverwaltung.neuenArtikelAnlegen("Artikel 1", 10);
        lagerverwaltung.neuenArtikelAnlegen("Artikel 2", 5);

        // Bestand erhöhen
        lagerverwaltung.bestandErhoehen("Artikel 1", 20);

        // Neue Mitarbeiter registrieren
        lagerverwaltung.neuenMitarbeiterRegistrieren("Benutzer1", "Passwort123");

        // usereinloggen
        Mitarbeiter eingeloggterMitarbeiter = lagerverwaltung.mitarbeiterEinloggen("Benutzer1", "Passwort123");
        if (eingeloggterMitarbeiter != null) {
            System.out.println("Erfolgreich eingeloggt: " + eingeloggterMitarbeiter.getUserName());
        } else;

    }

}
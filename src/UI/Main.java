package UI;

import Domain.Lagerverwaltung;
import ValueObjekt.Mitarbeiter;

public class Main {
    public static void main(String[] args) {

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
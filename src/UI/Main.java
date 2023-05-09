package UI;

import Domain.Artikelverwaltung;
import Domain.Kundenverwaltung;
import Domain.Lagerverwaltung;
import ValueObjekt.Artikel;
import ValueObjekt.Mitarbeiter;


import java.util.Scanner;

public class Benutzungsschnittstelle{
    private Artikelverwaltung artikelverwaltung;

    public Benutzungsschnittstelle(){
        artikelverwaltung = new Artikelverwaltung();
    }
    public void start(){
        Scanner scanner = new Scanner(System.in);
        int auswahl = 0;

        while (auswahl != 6){
            System.out.println("Artikelverwaltung");
            System.out.println("1. Artikel hinzufügen");
            System.out.println("2. Artikel löschen");
            System.out.println("3. Artikel ausgeben");
            System.out.println("4. Nach Beziehung sortieren");
            System.out.println("5. Nach Artikelnummer sortieren");
            System.out.println("6. Beenden");
            System.out.println("Auswahl: ");
            auswahl =scanner.nextInt();

            switch (auswahl){
                case 1:
                    artikelverwaltung.artikelHinzufuegen();
                    break;
                case 2:
                    artikelLoeschen(scanner);
                    break;
                case 3:

            }


        }
    }
    // private void loeschen behalte ich erstmal so lange bis ich in artikelverwaltung artikelloeschen erstellt habe.
    private void artikelLoeschen(Scanner scanner) {
    }
}
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
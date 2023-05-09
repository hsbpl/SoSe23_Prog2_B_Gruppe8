package UI;

import Domain.Lagerverwaltung;
import ValueObjekt.Artikel;
import ValueObjekt.Kunde;
import ValueObjekt.Mitarbeiter;
import ValueObjekt.Rechnung;

import java.util.HashMap;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;

public class Main {
    private Kunde kunde;
    private Mitarbeiter mitarbeiter;
    private double gesamtePreis;
    private HashMap<Artikel, Integer> gekaufteArtikel;




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

    // anzeigen() gibt die Rechnung auf dem Bildschirm aus.
    public void anzeigen() {
        System.out.println("Rechnung für " + kunde.getUserName());
        System.out.println("Datum: " + date ());
        System.out.println("Gekaufte Artikel:");
        for (Artikel artikel : gekaufteArtikel.keySet()) {
            int stueckzahl = gekaufteArtikel.get(artikel);
            double preis = artikel.getPreis() * stueckzahl;
            System.out.println(artikel.getArtikelNummer() + " (Stückzahl: " + stueckzahl + ", Preis: " + preis + ")");
        }
        System.out.println("Gesamtpreis: " + gesamtePreis());
    }
}
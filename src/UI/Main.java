package UI;

import Domain.Artikelverwaltung;
import Domain.Lagerverwaltung;
import Domain.Mitarbeiterverwaltung;
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

        Mitarbeiterverwaltung mitarbeiterverwaltung = new Mitarbeiterverwaltung();


        // Neue Artikel anlegen
        lagerverwaltung.neuenArtikelAnlegen("Artikel 1", 10);
        lagerverwaltung.neuenArtikelAnlegen("Artikel 2", 5);

        // Bestand erhöhen
       Artikelverwaltung.bestandErhoehen("Artikel 1", 20);

        // Neue Mitarbeiter registrieren
       Mitarbeiterverwaltung.neuenMitarbeiterRegistrieren("Benutzer1", "Passwort123");

        // usereinloggen
        Mitarbeiter eingeloggterMitarbeiter = mitarbeiterverwaltung.("Benutzer1", "Passwort123");
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
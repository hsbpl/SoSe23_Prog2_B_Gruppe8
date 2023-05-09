package Domain;

import ValueObjekt.Artikel;
import ValueObjekt.Mitarbeiter;

import java.util.ArrayList;
import java.util.List;

public class Lagerverwaltung {
    private List<Artikel> artikelListe;

    private List<Mitarbeiter> mitarbeiterListe;


    // addArtikel fügt eine Neue Artikel hizu
    public void addArtikel(String bezeichnung, int artikelNummer, int bestand, double preis, boolean verfuegbar) {
        Artikel neuerArtikel = new Artikel(bezeichnung, artikelNummer, bestand, preis, verfuegbar);
        artikelListe.add(neuerArtikel);
    }

    // ErhöhenBestand erhöht der Bestand von Artiklel
    public void bestandErhoehen(String artikelBezeichnung, int Anzahl) {
        for (Artikel artikel : artikelListe) {
            if (artikel.getBezeichnung().equals(artikelBezeichnung)) {
                int bestandErhoehen = artikel.getBestand() + Anzahl;
                artikel.ArtikelbestandErhoehen(Anzahl);
                break;
            }
        }
    }


}



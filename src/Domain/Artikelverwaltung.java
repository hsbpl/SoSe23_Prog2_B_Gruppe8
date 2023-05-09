package Domain;

import ValueObjekt.Artikel;
import jdk.internal.icu.text.UnicodeSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class Artikelverwaltung {
    private ArrayList<Artikel> ArtikelListe = new ArrayList<>();
    private UnicodeSet artikelListe;

    public Artikelverwaltung(ArrayList<Artikel> ArtikelListe) {
        waren = new ArrayList<>();
        this.ArtikelListe = ArtikelListe;
    }

    public void artikelAnlegen(Artikel artikel) {
        artikelListe.add((CharSequence) artikel);
    }

    public void setArtikelListe(ArrayList<Artikel> artikelListe) {
        ArtikelListe = artikelListe;
    }

    public ArrayList<Artikel> getArtikelListe() {
        return ArtikelListe;
    }

    public void artikelBearbeiten(Artikel artikel) {
        for (Artikel a : artikelListe) {
            if (a.getArtikelnummer() == artikel.getArtikelnummer()) {
                a.setBezeichnung(artikel.getBezeichnung());
                a.setBestand(artikel.getBestand());
                break;
            }
        }
    }

    public void ArtikelLoeschen(Artikel artikel) {
        artikelListe.removeIf(a -> a.getArtikelnummer() == artikel);
    }

    //Die Methoden "artikelAnlegen","artikelBearbeiten" und "artikelLoeschen" ermöglichen uns das Hinzufügen, Bearbeiten und Löschen von Artikeln aus der Datenbank.
    public void artikelSortierenNachBezeichnung() {
        Collections.sort(artikelListe, Comparator.comparing(Artikel::getBezeichnung));

        //public void artikelSortierenNachArtikelnummer () {
            Collections.sort(artikelListe, Comparator.comparing(Artikel::getBezeichnung));
        }
//Die Methoden "sortieren nach Bezeichnung und sortieren nach Artikelnummer" sortieren die Artikel in der Datenbank je nach Bezeichnung und Artikelnummer.

        public void artikelAusgeben() {
            for (Artikel artikel : artikelListe) {
                System.out.println(artikel);
            }
        }
//"artikel Ausgeben" gibt alle artikel in der Datenbank aus.
    }
}



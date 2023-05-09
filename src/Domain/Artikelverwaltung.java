package Domain;

import ValueObjekt.Artikel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;



public class Artikelverwaltung {
    private List<Artikel> artikelListe;

    public Artikelverwaltung() {
        artikelListe = new ArrayList<>();
    }

    public void artikelHinzufuegen(Artikel artikel) {
        artikelListe.add(artikel);
    }

    public void artikelSortierenNachBezeichnung() {
        Collections.sort(artikelListe, Comparator.comparing(Artikel::getBezeichnung));
    }

    public void artikelSortierenNachArtikelnummer() {
        Collections.sort(artikelListe, Comparator.comparing(Artikel::getBezeichnung));
    }

    public void artikelAusgeben() {
        for (Artikel artikel : artikelListe) {
            System.out.println("Bezeichnung: " + artikel.getBezeichnung());
            System.out.println("ArtikelNummer: " + artikel.getArtikelNummer());
            System.out.println("Bestand: " + artikel.getBestand());
            System.out.println("---------------------");
        }
    }


    public void setArtikelListe(ArrayList<Artikel> artikelListe) {
        artikelListe = artikelListe;
    }

    public ArrayList<Artikel> getArtikelListe() {
        return (ArrayList<Artikel>) artikelListe;
    }

    //artikelBearbeiten könnte man theoretisch rausnehmen da es eig. nicht in der Aufgabenstellung genannt wurde.
    public void artikelBearbeiten(Artikel artikel) {
        for (Artikel a : artikelListe) {
            if (a.getArtikelNummer() == artikel.getArtikelNummer()) {
                a.setBezeichnung(artikel.getBezeichnung());
                a.setBestand(artikel.getBestand());
                break;
            }
        }
    }


// Soll ich ArtikelLoeschen drin lassen? wäre an sich logisch es zu behalten für die main.
    //die Methoden "artikelAnlegen","artikelBearbeiten" ermöglichen uns das Hinzufügen, Bearbeiten von Artikeln aus der Datenbank.



//Die Methoden "sortieren nach Bezeichnung und sortieren nach Artikelnummer" sortieren die Artikel in der Datenbank je nach Bezeichnung und Artikelnummer.


//"artikel Ausgeben" gibt alle artikel in der Datenbank aus.
    }




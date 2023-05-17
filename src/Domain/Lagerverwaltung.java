package Domain;

import ValueObjekt.Artikel;
import ValueObjekt.Mitarbeiter;

import java.util.ArrayList;
import java.util.List;

public class Lagerverwaltung {

    //mit Artikelverwaltung zusammenführen
    //Login und Registrierung in Mitarbeiter
    private List<Artikel> artikelListe;
    private List<Mitarbeiter> mitarbeiterListe;

    public Lagerverwaltung() {
        artikelListe = new ArrayList<>();
        mitarbeiterListe = new ArrayList<>();
    }
    public void neuenArtikelAnlegen(String bezeichnung, int bestand) {
        Artikel neuerArtikel = new Artikel(bezeichnung, bestand);
        artikelListe.add(neuerArtikel);
    }

}



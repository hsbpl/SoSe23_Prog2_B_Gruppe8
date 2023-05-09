package Domain;

import ValueObjekt.Artikel;
import ValueObjekt.Mitarbeiter;

import java.util.ArrayList;
import java.util.List;

public class Mitarbeiterverwaltung {
    private List<Mitarbeiter> listMitarbeiter = new ArrayList<>();
    private List<Artikel> artikelList ;

    public List<Mitarbeiter> getListMitarbeiter(){
        return listMitarbeiter;
    }

    public void addArtikel(String bezeichnung, int artikelNummer, int bestand, double preis, boolean verfuegbar) {
        Artikel neuerArtikel = new Artikel(bezeichnung, artikelNummer, bestand, preis, verfuegbar);
        artikelList.add(neuerArtikel);
    }

}

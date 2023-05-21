package Domain;

import ValueObjekt.Artikel;


import java.util.*;


public class Artikelverwaltung { // fertig
    //Lagerwerwaltung zusammenführen artikel liste

    private List<Artikel> artikelListe;

    public Artikelverwaltung() {
        artikelListe = new ArrayList<>(Arrays.asList(cola,kuchen,chips, wasser, mehl));
    }
    Artikel cola = new Artikel("Coca Cola 1L", 17890, 40, 2f, true);
    Artikel kuchen = new Artikel("Käsekuchen", 19002, 12, 4.99f, true);
    Artikel chips = new Artikel("Chips", 39003, 100, 1.79f, true);
    Artikel wasser = new Artikel("Wasser)", 3890, 400, 0.49f, true);
    Artikel mehl = new Artikel("Mehl", 29290, 0, 0.39f, false);

    public void artikelHinzufuegen(Artikel artikel) {
        artikelListe.add(artikel);
    }

    public void artikelSortierenNachBezeichnung() {
        Collections.sort(artikelListe, Comparator.comparing(Artikel::getBezeichnung));
    }

    public void artikelSortierenNachArtikelnummer() {
        Collections.sort(artikelListe, Comparator.comparing(Artikel::getArtikelNummer));
    }

       public ArrayList<Artikel> bestandErhoehen(String artikelBezeichnung, int Anzahl) {
        for (Artikel artikel : artikelListe) {
            if (artikel.getBezeichnung().equals(artikelBezeichnung)) {
                artikel.ArtikelbestandErhoehen(Anzahl);
                break;
            }
        }
        return null;
    }
    public void bestandVerringern(Artikel artikel, int menge){
        if (artikelListe.contains(artikel)) {
            int aktuellerBestand = artikel.getBestand();
            if (menge <= aktuellerBestand) {
                int neuerBestand = aktuellerBestand - menge;
                artikel.setBestand(neuerBestand);
                System.out.println("Bestand verringert für Artikel: " + artikel.getBezeichnung());
            } else {
                System.out.println("Nicht genügend Bestand für Artikel: " + artikel.getBezeichnung());
            }
        }else{
            System.out.println("Artikel nicht vorhanden: " + artikel.getBezeichnung());
            }
        }



    public String artikelAusgeben() {
        String s ="";
        for (Artikel artikel : artikelListe) {

            s +=  artikel.toString() + "\n";
        }
        return s;
    }

    public void setArtikelListe(ArrayList<Artikel> artikelListe) {
        this.artikelListe = artikelListe;
    }


    public ArrayList<Artikel> getArtikelListe() {
        return (ArrayList<Artikel>) artikelListe;
    }

    public void artikelBearbeiten(Artikel artikel) {
        for (Artikel a : artikelListe) {
            if (a.getArtikelNummer() == artikel.getArtikelNummer()) {
                a.setBezeichnung(artikel.getBezeichnung());
                a.setBestand(artikel.getBestand());
                break;
            }
        }
    }

    public void artikelLoeschen(int artikelnummer) {
        Artikel artikelToRemove = null;
        for (Artikel artikel : artikelListe) {
            if (artikel.getArtikelNummer() == artikelnummer) {
                artikelToRemove = artikel;
                break;
            }
        }
        artikelListe.remove(artikelToRemove);
    }
}



/* Soll ich ArtikelLoeschen drin lassen? wäre an sich logisch es zu behalten für die main.
        //die Methoden "artikelAnlegen","artikelBearbeiten" ermöglichen uns das Hinzufügen, Bearbeiten von Artikeln aus der Datenbank.


//Die Methoden "sortieren nach Bezeichnung und sortieren nach Artikelnummer" sortieren die Artikel in der Datenbank je nach Bezeichnung und Artikelnummer.


//"artikel Ausgeben" gibt alle artikel in der Datenbank aus.
*/

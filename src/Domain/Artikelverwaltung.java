package Domain;

import Exceptions.ArtikelExistiertBereitsException;
import Exceptions.ArtikelExistiertNichtException;
import Exceptions.UngueltigeMengeException;
import Persistence.FilePersistenceManager;
import Persistence.PersistenceManager;
import ValueObjekt.*;
import ValueObjekt.Enum;


import java.io.IOException;
import java.util.*;


public class Artikelverwaltung { // fertig
    private PersistenceManager pm = new FilePersistenceManager();


    private List<Ereignis> ereignisse;

    private List<Artikel> artikelListe;

    //liste in den Constructor
    public void liesDaten(String datei) throws IOException {
        //ToDo:
    }
    public void schreibeDaten(String datei) throws IOException{
        //ToDo:
    }
    public Artikelverwaltung() {
        artikelListe = new ArrayList<>(Arrays.asList(cola,kuchen,chips, wasser, mehl, energydrink));
        ereignisse = new  ArrayList<>();
    }

    Artikel cola = new Artikel("Coca Cola 1L", 17890, 40, 2);
    Artikel kuchen = new Artikel("Käsekuchen", 19002, 12, 4.99);
    Artikel chips = new Artikel("Chips", 39003, 100, 1.79);
    Artikel wasser = new Artikel("Wasser", 38900, 400, 0.49);
    Artikel mehl = new Artikel("Mehl", 29290, 20, 0.39);
    Massengutartikel energydrink = new Massengutartikel("Energy", 56899, 300, 1.0, 6);



    public void artikelHinzufuegen(Artikel artikel, Mitarbeiter mitarbeiter) throws ArtikelExistiertBereitsException{

        for(Artikel a : getArtikelListe()){
            if(a.getBezeichnung() == artikel.getBezeichnung() || a.getArtikelNummer() == a.getArtikelNummer()){
                throw new ArtikelExistiertBereitsException();
            } else {
                artikelListe.add(artikel);
                Ereignis e = new Ereignis(artikel.getBestand(), artikel, mitarbeiter, Enum.ANLEGEN);
                ereignisse.add(e);}
            }
        }


/*
        if(getArtikelListe().contains(artikel)){
            throw new ArtikelExistiertBereitsException();
        } else{
        artikelListe.add(artikel);
        Ereignis e = new Ereignis(artikel.getBestand(), artikel, mitarbeiter, Enum.ANLEGEN);
        ereignisse.add(e);}

 */


    public String artikelSortierenNachBezeichnung() {

        Collections.sort(artikelListe, Comparator.comparing(Artikel::getBezeichnung));
        String s ="";
        for (Artikel artikel : artikelListe) {
            s +=  artikel.toString() + "\n";
        }
        return s;

    }

    //TODO in die Main setzen und achsuen ob es klappt
    public  String ereignisseSortiertNachDatum(){
        Collections.sort(ereignisse, Comparator.comparing(Ereignis::getDatum));
        String s="";
        for (Ereignis ereignis: ereignisse){
            s+= ereignis.toString()+"\n";
        }
        return s;
    }

    public String artikelSortierenNachArtikelnummer() {
        Collections.sort(artikelListe, Comparator.comparing(Artikel::getArtikelNummer));
        String s ="";
        for (Artikel artikel : artikelListe) {
            s +=  artikel.toString() + "\n";
        }
        return s;
    }


    public void bestandErhoehen(String artikelBezeichnung, int anzahl, User u)throws ArtikelExistiertNichtException{

        for (Artikel artikel : artikelListe) {

            if (artikel.getBezeichnung().equals(artikelBezeichnung)) {
                int aktuellerBestand = artikel.getBestand();
                int neuerBestand = aktuellerBestand + anzahl;
                artikel.setBestand(neuerBestand);
                Ereignis e = new Ereignis( neuerBestand, artikel, u, Enum.EINLAGERUNG);
                ereignisse.add(e);

            } else {
                throw new ArtikelExistiertNichtException();
            }

        }

    }

    public void bestandVerringern(String artikelname, int menge, User u) throws ArtikelExistiertNichtException, UngueltigeMengeException {   // kopieren oder pushen

        for (Artikel artikel : artikelListe) {

            if (!(artikel.getBezeichnung() ==artikelname)) {
                throw new ArtikelExistiertNichtException();
            } else {
                if (artikel.getBestand() < menge) {
                    throw new UngueltigeMengeException();
                } else {
                    int aktuellerBestand = artikel.getBestand();
                    int neuerBestand = aktuellerBestand - menge;
                    Ereignis e = new Ereignis(neuerBestand, artikel, u, Enum.AUSLAGERUNG);
                    ereignisse.add(e);
                }
            }

        }
    }

    public String artikelAusgeben() {
        String s ="";
        for (Artikel artikel : artikelListe) {
            s +=  artikel.toString() + "\n";
        }
        return s;
    }

    public List<Ereignis> getEreignisse() {
        return ereignisse;
    }

    public String ereignisseAusgeben() {
        String s ="";
        for (Ereignis a : ereignisse) {
            s +=  a.toString() + "\n";
        }
        return s;
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

    public void artikelLoeschen(int artikelnummer) throws ArtikelExistiertNichtException{
        Artikel artikelToRemove = null;
        for (Artikel artikel : artikelListe) {
            if (artikel.getArtikelNummer() == artikelnummer) {
                artikelToRemove = artikel;
                break;
            } else {
                throw new ArtikelExistiertNichtException();
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

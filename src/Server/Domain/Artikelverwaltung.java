package Server.Domain;

import Common.*;
import Common.Exceptions.ArtikelExistiertBereitsException;
import Common.Exceptions.EreignisExistiertBereitsException;
import Common.Exceptions.LeeresTextfieldException;
import Common.Exceptions.UngueltigeMengeException;
import Common.Ereignis;
import Common.Massengutartikel;
import Server.Persistence.FilePersistenceManager;
import Server.Persistence.PersistenceManager;
import Common.Enum;

import java.io.IOException;
import java.util.*;


public class Artikelverwaltung {
    private PersistenceManager pm = new FilePersistenceManager();

    private static List<Ereignis> ereignisse = new ArrayList<>();

    private static List<Artikel> artikelListe = new ArrayList<>();

    //liste in den Constructor


    public Artikelverwaltung() {
    }

    public void liesDaten(String datei) throws IOException {
        try {
            artikelListe = pm.leseArtikelListe(datei);
        } catch (ArtikelExistiertBereitsException e) {
            throw new RuntimeException(e);
        }

    }

    public void liesDatenEreignisse(String datei) throws IOException {
        try {
            ereignisse = pm.leseEreignisList(datei);
        } catch (EreignisExistiertBereitsException e) {
            throw new RuntimeException(e);
        }

    }


    public void schreibeDaten(String datei) throws IOException {
        pm.schreibeArtikelListe(artikelListe, datei);
    }

    public void schreibeDatenEreignisse(String datei) throws IOException {
        pm.schreibeEreignisListe(ereignisse, datei);
    }

    public void artikelHinzufuegen(Artikel artikel, Mitarbeiter mitarbeiter) throws ArtikelExistiertBereitsException, LeeresTextfieldException {
        if (artikel.getBezeichnung().isEmpty()) {
            throw new LeeresTextfieldException();
        } else {
            if (!(istArtikelNichtVorhanden(artikel, getArtikelListe(), artikel.getBezeichnung()))) {
                artikelListe.add(artikel);
                Ereignis e = new Ereignis(artikel.getBestand(), artikel, mitarbeiter, Enum.ANLEGEN, artikel.getBestand());
                ereignisse.add(e);
            } else {
                throw new ArtikelExistiertBereitsException();
            }
        }
    }

    public void massengutArtikelHinzufuegen(Massengutartikel artikel, Mitarbeiter mitarbeiter) throws ArtikelExistiertBereitsException, LeeresTextfieldException {

        if (artikel.getBezeichnung().isEmpty()) {
            throw new LeeresTextfieldException();
        } else {
            if (!(istArtikelNichtVorhanden(artikel, artikelListe, artikel.getBezeichnung()))) {
                artikelListe.add(artikel);
                Ereignis e = new Ereignis(artikel.getBestand(), artikel, mitarbeiter, Enum.ANLEGEN, artikel.getBestand());
                ereignisse.add(e);
            } else {
                throw new ArtikelExistiertBereitsException();
            }
        }
    }


    public static boolean istArtikelNichtVorhanden(Artikel artikel, List<Artikel> liste, String bezeichnung) throws ArtikelExistiertBereitsException {

        return liste.stream().anyMatch(a -> a.getArtikelNummer() == artikel.getArtikelNummer() ||
                a.getBezeichnung().equalsIgnoreCase(artikel.getBezeichnung()));

    }


    public List<Artikel> artikelSortierenNachBezeichnung() {

        Collections.sort(artikelListe, Comparator.comparing(Artikel::getBezeichnung));

        return artikelListe;

    }
    public String artikelSortierenNachBezeichnungString(){
        List<Artikel> geordneteListe = artikelSortierenNachBezeichnung();
        String s ="";
        for (Artikel artikel : geordneteListe) {
            s +=  artikel.toString() + "\n";
        }
        return s;
    }



    public  List<Ereignis> ereignisseSortiertNachDatum(){
        Collections.sort(ereignisse, Comparator.comparing(Ereignis::getDatum).reversed());
        return ereignisse;
    }

    public  String ereignisseSortiertNachDatumString(){

        String s="";
        for (Ereignis ereignis: ereignisseSortiertNachDatum()){
            s+= ereignis.toString()+"\n";
        }
        return s;
    }



    public List<Artikel> artikelSortierenNachArtikelnummer() {
        Collections.sort(artikelListe, Comparator.comparing(Artikel::getArtikelNummer));

        return artikelListe;
    }

    public String artikelSortierenNachArtikelnummerString(){
        String s ="";
        for (Artikel artikel : artikelSortierenNachArtikelnummer()) {
            s +=  artikel.toString() + "\n";
        }
        return s;
    }


    public boolean bestandErhoehen(String artikelBezeichnung, int anzahl, User u)throws LeeresTextfieldException{

        if(artikelBezeichnung.isEmpty()){
            throw new LeeresTextfieldException();
        }else{

            for (Artikel artikel : artikelListe) {
                if (artikel.getBezeichnung().equals(artikelBezeichnung)) {
                    int aktuellerBestand = artikel.getBestand();
                    int neuerBestand = aktuellerBestand + anzahl;
                    artikel.setBestand(neuerBestand);
                    Ereignis e = new Ereignis(anzahl, artikel, u, Enum.EINLAGERUNG, neuerBestand);
                    ereignisse.add(e);
                    return true;
                }
            }

            return  false;}
    }



    public boolean bestandVerringern(String artikelname, int menge, User u) throws UngueltigeMengeException, LeeresTextfieldException{

        if(artikelname.isEmpty()){
            throw new LeeresTextfieldException();
        }else{

            for (Artikel artikel : artikelListe) {
                if(artikel.getBezeichnung().equals(artikelname)){
                    if (artikel.getBestand() < menge) {
                        throw new UngueltigeMengeException();
                    } else {
                        int aktuellerBestand = artikel.getBestand();
                        int neuerBestand = aktuellerBestand - menge;
                        artikel.setBestand(neuerBestand);
                        Ereignis e = new Ereignis(menge, artikel, u, Enum.AUSLAGERUNG, neuerBestand);
                        ereignisse.add(e);
                        return true;
                    }
                }
            }
            return false;}
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

    public ArrayList<Artikel> getArtikelListe()
    {
        return (ArrayList<Artikel>) artikelListe;
    }

    public ArrayList<Ereignis> getEreignisListe() {
        return (ArrayList<Ereignis>) ereignisse;
    }

    public void setEreignisListe(Ereignis ereignis) {
        ereignisse.add(ereignis);
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

    public boolean artikelLoeschen(int artikelnummer){
        Artikel artikelToRemove;
        for (Artikel artikel : artikelListe) {
            if (artikel.getArtikelNummer() == artikelnummer) {
                artikelToRemove = artikel;
                artikelListe.remove(artikelToRemove);
                return true;
            }
        }
        return false;
    }

    public Artikel getArtikelByNumber(int artikelnummer){
        for (Artikel artikel : artikelListe){
            if (artikel.getArtikelNummer() == artikelnummer) {
                return artikel;
            }
        }

        return null;
    }

}

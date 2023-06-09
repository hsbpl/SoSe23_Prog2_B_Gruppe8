package Persistence;

import Exceptions.ArtikelExistiertBereitsException;
import Exceptions.UserExistiertBereitsException;
import ValueObjekt.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FilePersistenceManager implements PersistenceManager{

   private BufferedReader reader = null;
   private PrintWriter writer = null;


    public List<Artikel> leseArtikelListe(String datei) throws IOException, ArtikelExistiertBereitsException{
        reader = new BufferedReader(new FileReader(datei));

        List<Artikel> artikelBestand = new ArrayList<>();
        Artikel einArtikel;

        do {
            einArtikel = ladeArtikel();
            if (einArtikel !=null){
                if (artikelBestand.contains(einArtikel)){
                    throw new ArtikelExistiertBereitsException();
                }

                artikelBestand.add(einArtikel);
            }
        }while (einArtikel !=null);
        return artikelBestand;
    }

    public HashMap<String, Kunde> leseKundenListe(String datei) throws IOException, UserExistiertBereitsException{
        reader = new BufferedReader(new FileReader(datei));

        HashMap<String, Kunde> kundenBestand = new HashMap<>();
        Kunde einKunde;
        int count = 0;

        do {
            einKunde = ladeKunde();
            if (einKunde !=null){
                if (kundenBestand.containsValue(einKunde)){
                    throw new UserExistiertBereitsException();
                }
                kundenBestand.put(String.valueOf(count), einKunde);
                count += 1;
            }
        }while (einKunde !=null);
        return kundenBestand;
    }

    public List<Mitarbeiter> leseMitarbeiterList(String datei) throws IOException, UserExistiertBereitsException{
        reader = new BufferedReader(new FileReader(datei));

        List<Mitarbeiter> mitarbeiterBestand = new ArrayList<>();
        Mitarbeiter einMitarbeiter;

        do {
            einMitarbeiter = ladeMitarbeiter();
            if (einMitarbeiter !=null){
                if (mitarbeiterBestand.contains(einMitarbeiter)){
                    throw new UserExistiertBereitsException();
                }
                mitarbeiterBestand.add(einMitarbeiter);
            }
        }while (einMitarbeiter !=null);
        return mitarbeiterBestand;
    }
    public List<Ereignis> leseEreignisList(String datei) throws IOException, UserExistiertBereitsException{
        reader = new BufferedReader(new FileReader(datei));

        List<Ereignis> ereignisBestand = new ArrayList<>();
        Ereignis einEreignis;
        do {
            einEreignis = ladeEreignis();
            if (einEreignis !=null){
                if (ereignisBestand.contains(einEreignis)){
                    throw new UserExistiertBereitsException();
                }
                ereignisBestand.add(einEreignis);
            }
        }while (einEreignis !=null);
        return ereignisBestand;
    }

    public void schreibeArtikelListe(List<Artikel> liste, String datei) throws IOException {
        writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));

        for(Artikel a : liste)
            speichereArtikel(a);

        writer.close();
    }

    public void schreibeKundeListe(List<Kunde> liste, String datei) throws IOException {
        writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));

        for(Kunde k : liste)
            speichereKunde(k);

        writer.close();
    }

    public void schreibeMitarbeiterListe(List<Mitarbeiter> liste, String datei) throws IOException {
        writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));

        for(Mitarbeiter m : liste)
            speichereMitarbeiter(m);

        writer.close();
    }

    @Override
    public List<Ereignis> leseEreignislist(String datei) throws IOException, ArtikelExistiertBereitsException {
        return null;
    }

    public void schreibeEreignisListe(List<Ereignis> liste, String datei) throws IOException {
        writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));

        for (Ereignis e : liste)
            speichereEreignis(e);
        writer.close();
    }




    //private Method
    private Artikel ladeArtikel() throws IOException{
        //Bezeichnung einlesen
        String bezeichnung = liesZeile();
        if (bezeichnung == null){
            //es sind keine Daten mehr vorhanden
            return null;
        }

        String artnr_string = liesZeile();
        int artikelnummer = Integer.parseInt(artnr_string);

        String bestand_string = liesZeile();
        int bestand = Integer.parseInt(bestand_string);

        String epreis_string = liesZeile();
        double epreis = Double.parseDouble(epreis_string);

        return new Artikel(bezeichnung, artikelnummer, bestand, epreis);
    }

    private boolean speichereArtikel(Artikel a) throws IOException{
        schreibeZeile(a.getBezeichnung());
        schreibeZeile(String.valueOf(a.getArtikelNummer()));
        schreibeZeile(String.valueOf(a.getBestand()));
        schreibeZeile(String.valueOf(a.getEinzelpreis()));

        return true;
    }

    private Kunde ladeKunde() throws IOException{
        //Bezeichnung einlesen
        String username = liesZeile();
        if (username == null){
            //keine Daten mehr vorhanden
            return null;
        }
        String passwort = liesZeile();
        String nachname = liesZeile() ;
        String vorname = liesZeile();
        String adresse = liesZeile();

       return new Kunde(username, passwort, nachname, vorname, adresse);
    }

    private boolean speichereKunde(Kunde k) throws IOException{
        schreibeZeile(k.getUserName());
        schreibeZeile(k.getPasswort());
        schreibeZeile(k.getNachname());
        schreibeZeile(k.getVorname());
        schreibeZeile(k.getKundenAdresse());

        return true;
    }

    private Mitarbeiter ladeMitarbeiter() throws IOException{
        String username = liesZeile();
        if (username == null){
            //keine Daten mehr vorhanden
            return null;
        }
        String passwort = liesZeile();
        String nachname = liesZeile() ;
        String vorname = liesZeile();
        return new Mitarbeiter(username, passwort, nachname, vorname);
    }
    private boolean speichereMitarbeiter(Mitarbeiter m) throws IOException{
        schreibeZeile(m.getUserName());
        schreibeZeile(m.getPasswort());
        schreibeZeile(m.getNachname());
        schreibeZeile(m.getVorname());
        return true;
    }
    private Ereignis ladeEreignis() throws IOException{
        String anzahl = liesZeile();
        if (anzahl == null){
            //keine Daten mehr vorhanden
            return null;
        }
        String artikel = liesZeile();
        String user = liesZeile();
        String ereignistyp = liesZeile();
        String aktualisierterBestand = liesZeile();
        return new Ereignis(anzahl, artikel, user, ereignistyp, aktualisierterBestand);
    }
    private boolean speichereEreignis(Ereignis e) throws IOException{
        schreibeZeile(e.getAnzahl());
        schreibeZeile(e.getArtikel());
        schreibeZeile(e.getUser());
        schreibeZeile(e.getEreignistyp());
        schreibeZeile(e.getAktualisierterBestand());
        return true;
    }




    private String liesZeile() throws IOException{
        if (reader != null)
            return reader.readLine();
        else
            return "";
    }

    private void schreibeZeile(String daten) {
        if (writer != null)
            writer.println(daten);
    }
}

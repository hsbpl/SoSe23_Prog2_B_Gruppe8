package Persistence;

import Exceptions.ArtikelExistiertBereitsException;
import Exceptions.EreignisExistiertBereitsException;
import Exceptions.UserExistiertBereitsException;
import ValueObjekt.*;
import ValueObjekt.Enum;

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

    private static List<Artikel> artikelBestand = new ArrayList<>();
    private static List<Mitarbeiter> mitarbeiterBestand = new ArrayList<>();
    private static HashMap<String, Kunde> kundenBestand = new HashMap<>();
    private static List<Ereignis> ereignisliste = new ArrayList<>();
    private static List<Artikel>massengutBestand = new ArrayList<>();

    public FilePersistenceManager() {

    }



    public List<Artikel> leseArtikelListe(String datei) throws IOException, ArtikelExistiertBereitsException{
        reader = new BufferedReader(new FileReader(datei));

        //List<Artikel> artikelBestand = new ArrayList<>();
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

        //HashMap<String, Kunde> kundenBestand = new HashMap<>();
        Kunde einKunde;
        int count = 0;
        System.out.println("HI");
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
        System.out.println("gzug");
        return kundenBestand;
    }

    public List<Mitarbeiter> leseMitarbeiterList(String datei) throws IOException, UserExistiertBereitsException{
        reader = new BufferedReader(new FileReader(datei));

        //List<Mitarbeiter> mitarbeiterBestand = null;
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
    public List<Ereignis> leseEreignisList(String datei) throws IOException, EreignisExistiertBereitsException {
        reader = new BufferedReader(new FileReader(datei));

        Ereignis einEreignis;

        do {
            einEreignis = ladeEreignis();
            if (einEreignis !=null){
                if (ereignisliste.contains(einEreignis)){
                    throw new EreignisExistiertBereitsException();
                }
                ereignisliste.add(einEreignis);
            }
        }while (einEreignis !=null);
        return ereignisliste;
    }
    public List<Artikel> leseMassengutListe(String datei) throws IOException, EreignisExistiertBereitsException{
        reader = new BufferedReader(new FileReader(datei));

        Artikel  einArtikel;
        do {
            einArtikel = ladeArtikel();
            if (einArtikel !=null){
                if (massengutBestand.contains(einArtikel)) {
                    throw new EreignisExistiertBereitsException();
                }
                massengutBestand.add(einArtikel);
            }
        }while(einArtikel !=null);
        return massengutBestand;
    }

    public void schreibeArtikelListe(List<Artikel> liste, String datei) throws IOException {
        writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));

        for(Artikel a : liste)
            speichereArtikel(a);

        writer.close();
    }

    public void schreibeMassengutListe(List<Artikel> liste, String datei) throws IOException{
        writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));

        for (Artikel mg : liste)
            speichereArtikel(mg);
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
    private Massengutartikel ladeMassengutArtikel() throws IOException{
        String bezeichnung = liesZeile();
        if (bezeichnung == null){
            return null;
        }
        String artikelnummer_string = liesZeile();
        int artikelnummer = Integer.parseInt(artikelnummer_string);

        String bestand_string = liesZeile();
        int bestand = Integer.parseInt(bestand_string);

        String einzelpreis_string = liesZeile();
        double einzelpreis = Double.parseDouble(einzelpreis_string);

        String erwerbwaremenge_string = liesZeile();
        int erwerbwaremenge = Integer.parseInt(erwerbwaremenge_string);

        return new Massengutartikel(bezeichnung, artikelnummer, bestand, einzelpreis, erwerbwaremenge);
    }

    private boolean speichereMassengutartikel(Massengutartikel mg) throws IOException{
        schreibeZeile(mg.getBezeichnung());
        schreibeZeile(String.valueOf(mg.getArtikelNummer()));
        schreibeZeile(String.valueOf(mg.getBestand()));
        schreibeZeile(String.valueOf(mg.getEinzelpreis()));
        schreibeZeile(String.valueOf(mg.getErwerbwareMenge()));

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
    private Ereignis ladeEreignis() throws IOException {
        String anzahl_string = liesZeile();
        System.out.println("Anzahl "+anzahl_string);
        if (anzahl_string == null){
            //keine Daten mehr vorhanden
            return null;
        }
        int anzahl = Integer.parseInt(anzahl_string);

        String artikelnr = liesZeile();
        System.out.println("Artikelnr "+artikelnr);
        Artikel artikel = getArtikelByNumber(Integer.parseInt(artikelnr));
        System.out.println("Artikel "+artikel);
        String username = liesZeile();
        User user = null;
        String ereignistyp_string = null;
        System.out.println(gitMitarbeiterbestand());

        if (username.startsWith("m")) {
            user = getMitarbeiterByUsername(username);
        } else if (username.startsWith("k")) {
            user = getKundeByUsername(username);
        } else {
            return null;
        }

        ereignistyp_string = liesZeile();
        Enum ereignistyp = Enum.valueOf(ereignistyp_string);
        System.out.println("Ereignistyp " +ereignistyp);

        String aktualisierterBestand_string = liesZeile();
        System.out.println("Aktualisierter Bestand "+aktualisierterBestand_string);
        int aktualisierterBestand = Integer.parseInt(aktualisierterBestand_string);
        System.out.println("DONE!");

        return new Ereignis(anzahl, artikel, user, ereignistyp, aktualisierterBestand);
    }
    private boolean speichereEreignis(Ereignis e) throws IOException{
        schreibeZeile(String.valueOf(e.getAnzahl()));
        schreibeZeile(String.valueOf(e.getArtikel().getArtikelNummer()));
        schreibeZeile(String.valueOf(e.getUser().getUserName()));
        schreibeZeile(String.valueOf(e.getEreignistyp()));
        schreibeZeile(String.valueOf(e.getAktualisierterBestand()));
        return true;
    }

    public Artikel getArtikelByNumber(int artikelnummer){
        System.out.println(artikelBestand);
        for (Artikel artikel : artikelBestand){
            if (artikel.getArtikelNummer() == artikelnummer) {
                return artikel;
            }
        }

        return null;
    }
    public Massengutartikel getMassengutByNumber(int artikelnummer){
        System.out.println(massengutBestand);
        for (Artikel artikel : massengutBestand){
            if (artikel.getArtikelNummer() == artikelnummer){
                return (Massengutartikel) artikel;
            }
        }
        return null;
    }

    public Kunde getKundeByUsername(String username){
        List<Kunde> kList = new ArrayList<>(kundenBestand.values());
        for (Kunde kunde : kList){
            if (kunde.getUserName().equals(username)) {
                return kunde;
            }
        }

        return null;
    }

    public Mitarbeiter getMitarbeiterByUsername(String username){
        for (Mitarbeiter mitarbeiter : mitarbeiterBestand){
            if (mitarbeiter.getUserName().equals(username)) {
                return mitarbeiter;
            }
        }

        return null;
    }

    public List<Mitarbeiter> gitMitarbeiterbestand(){
        return mitarbeiterBestand;
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

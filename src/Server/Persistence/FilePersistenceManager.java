package Server.Persistence;

import Common.Enum;
import Common.*;
import Common.Exceptions.ArtikelExistiertBereitsException;
import Common.Exceptions.EreignisExistiertBereitsException;
import Common.Exceptions.UserExistiertBereitsException;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private static List<Massengutartikel>massengutBestand = new ArrayList<>();

    public FilePersistenceManager() {

    }



    public List<Artikel> leseArtikelListe(String datei) throws IOException, ArtikelExistiertBereitsException{
        reader = new BufferedReader(new FileReader(datei));

        Artikel einArtikel;

        do {
            einArtikel = ladeArtikel();
            if (einArtikel !=null){
                if (artikelBestand.contains(einArtikel)){
                    throw new ArtikelExistiertBereitsException();
                }

                if(einArtikel instanceof Massengutartikel){
                    artikelBestand.add((Massengutartikel) einArtikel);
                } else {
                    artikelBestand.add(einArtikel);
                }
            }
        }while (einArtikel !=null);
        return artikelBestand;
    }

    public HashMap<String, Kunde> leseKundenListe(String datei) throws IOException, UserExistiertBereitsException{
        reader = new BufferedReader(new FileReader(datei));

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

        Artikel einArtikel;
        do {
            einArtikel = ladeArtikel();
            if (einArtikel !=null){
                if (artikelBestand.contains(einArtikel)) {
                    throw new EreignisExistiertBereitsException();
                }
                artikelBestand.add(einArtikel);
            }
        }while(einArtikel !=null);
        return artikelBestand;
    }

    public void schreibeArtikelListe(List<Artikel> liste, String datei) throws IOException {
        writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));

        for(Artikel a : liste){
            if(a instanceof Massengutartikel) {
                Massengutartikel mg = (Massengutartikel) a;
                speichereArtikel(mg, "Massengutartikel");
            } else {
                speichereArtikel(a,"Einzelartikel");
            }
        }

        writer.close();
    }

    public void schreibeMassengutListe(List<Artikel> liste, String datei) throws IOException{
        writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));

        for (Artikel mg : liste)
            speichereMassengutartikel((Massengutartikel) mg);
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

        String mindestErwerbMenge_S = liesZeile();
        int mindestErwerbMenge = Integer.parseInt(mindestErwerbMenge_S);

        String artikeltyp = liesZeile();

        if(artikeltyp.equalsIgnoreCase("Massengutartikel")) {
            return new Massengutartikel(bezeichnung, artikelnummer, bestand, epreis, mindestErwerbMenge);
        } else {
            return new Artikel(bezeichnung, artikelnummer, bestand, epreis);
        }
    }

    private boolean speichereArtikel(Artikel a, String artikeltyp) throws IOException{
        schreibeZeile(a.getBezeichnung());
        schreibeZeile(String.valueOf(a.getArtikelNummer()));
        schreibeZeile(String.valueOf(a.getBestand()));
        schreibeZeile(String.valueOf(a.getEinzelpreis()));
        if (artikeltyp.equals("Massengutartikel")) {
            schreibeZeile(String.valueOf(((Massengutartikel) a).getErwerbwareMenge()));
            schreibeZeile(String.valueOf(artikeltyp));
        } else {
            schreibeZeile(String.valueOf(1));
            schreibeZeile(String.valueOf(artikeltyp));
        }
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
        if (anzahl_string == null){
            //keine Daten mehr vorhanden
            return null;
        }
        int anzahl = Integer.parseInt(anzahl_string);

        String artikelnr = liesZeile();
        Artikel artikel = getArtikelByNumber(Integer.parseInt(artikelnr));
        String username = liesZeile();
        User user = null;
        String ereignistyp_string = null;

        if (username.startsWith("m")) {
            user = getMitarbeiterByUsername(username);
        } else if (username.startsWith("k")) {
            user = getKundeByUsername(username);
        } else {
            return null;
        }

        ereignistyp_string = liesZeile();
        Enum ereignistyp = Enum.valueOf(ereignistyp_string);

        String aktualisierterBestand_string = liesZeile();
        int aktualisierterBestand = Integer.parseInt(aktualisierterBestand_string);

        String zeitstempel_string = liesZeile();
        DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime zeitstempel = LocalDateTime.parse(zeitstempel_string, ISO_FORMATTER);

        return new Ereignis(anzahl, artikel, user, ereignistyp, aktualisierterBestand, zeitstempel);
    }
    private boolean speichereEreignis(Ereignis e) throws IOException{
        schreibeZeile(String.valueOf(e.getAnzahl()));
        schreibeZeile(String.valueOf(e.getArtikel().getArtikelNummer()));
        schreibeZeile(String.valueOf(e.getUser().getUserName()));
        schreibeZeile(String.valueOf(e.getEreignistyp()));
        schreibeZeile(String.valueOf(e.getAktualisierterBestand()));
        schreibeZeile(String.valueOf(e.getDatum()));
        return true;
    }

    public Artikel getArtikelByNumber(int artikelnummer){
        for (Artikel artikel : artikelBestand){
            if (artikel.getArtikelNummer() == artikelnummer) {
                return artikel;
            }
        }

        return null;
    }
    public Massengutartikel getMassengutByNumber(int artikelnummer){
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

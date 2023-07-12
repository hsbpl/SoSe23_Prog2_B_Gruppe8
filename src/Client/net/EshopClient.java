package Client.net;

import Common.*;
import Common.Enum;
import Common.Exceptions.*;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EshopClient implements EShopInterface {

    //todo später Exceptionhandling
    //todo warenkorb reinlegen/rauslegen... auf beiden seiten
    //todo dopplung mininmieren
    final String separator = ";";

    private Socket socket;
    private BufferedReader socketIn;
    private PrintStream socketOut;

    private Warenkorb warenkorb;

    public EshopClient(String host, int port) throws IOException {

       try {
            this.socket = new Socket(host, port);
            InputStream is = this.socket.getInputStream();
            this.socketIn = new BufferedReader(new InputStreamReader(is));
            this.socketOut = new PrintStream(this.socket.getOutputStream());
        } catch (IOException var5) {
            var5.printStackTrace();
            throw var5;
        }

        System.out.println("Verbindung hergestellt");
        String nachricht = this.socketIn.readLine();
        System.out.println(nachricht);

        socketOut.println("HALLO_SERVER");

    }




    public void handleGibHalloServer() throws IOException {
        String response = socketIn.readLine(); // Antwort des Servers lesen
        System.out.println("Antwort vom Server: " + response);
    }



    @Override
    public List<Artikel> getAlleArtikel() {
        String cmd = Commands.CMD_GIB_ALLE_ARTIKEL.name();
        socketOut.println(cmd);

        String[] data = readResponse();

        if(Commands.valueOf(data[0]) != Commands.CMD_GIB_ALLE_ARTIKEL_RSP) {
            throw new RuntimeException("Ungueltige Antwort auf Anfrage erhalten!");
        }

        return createArtikellisteFromData(data);
    }


    private List<Artikel> createArtikellisteFromData(String[] data) {
        List<Artikel> artikelliste = new ArrayList<>();

        for(int i=1; i<data.length; i+=4) {

            String bezeicnung = data[i];
            int artikelnummer = Integer.parseInt(data[i+1]);
            int bestand = Integer.parseInt(data[i+2]);
            double einzelpreis = Double.parseDouble(data[i+3]);
            int kaufszahl = Integer.parseInt(data[i+4]);

            //gegenchecken ob Artikel ein Massengut oder EinzelArtikel ist
            if(kaufszahl != 1){
                Massengutartikel massengutartikel = new Massengutartikel(bezeicnung,artikelnummer,bestand, einzelpreis, kaufszahl);
                artikelliste.add(massengutartikel);
            } else {
            Artikel artikel = new Artikel(bezeicnung,artikelnummer,bestand,einzelpreis);
            artikelliste.add(artikel);}

        }
        return artikelliste;
    }

    @Override
    public List<Mitarbeiter> getAlleMitarbeiter() {
        String cmd = Commands.CMD_GIB_ALLE_MITARBEITER.name();
        socketOut.println(cmd);
        String[] data = readResponse();

        if(Commands.valueOf(data[0]) != Commands.CMD_GIB_ALLE_MITARBEITER_RSP) {
            throw new RuntimeException("Ungueltige Antwort auf Anfrage erhalten!");
        }

        return createMitarbeiterlisteFromData(data);
    }

    private List<Mitarbeiter> createMitarbeiterlisteFromData(String[] data) {
        List<Mitarbeiter> mitarbeiterListe = new ArrayList<>();

        for(int i=1; i<data.length; i+=4) {

            String username = data[i];
            String passwort = data[i+1];
            String nachname = data[i+2];
            String vorname  = data[i+3];
            String id = data[i+4];

            Mitarbeiter mitarbeiter = new Mitarbeiter(username, passwort, nachname, vorname);
            mitarbeiter.setID(id);
            mitarbeiterListe.add(mitarbeiter);

        }
        return mitarbeiterListe;
    }

    @Override
    public List<Kunde> getAlleKunden() {
        String cmd = Commands.CMD_GIB_ALLE_KUNDEN.name();
        socketOut.println(cmd);
        String[] data = readResponse();

        if(Commands.valueOf(data[0]) != Commands.CMD_GIB_ALLE_KUNDEN_RSP) {
            throw new RuntimeException("Ungueltige Antwort auf Anfrage erhalten!");
        }

        return createKundenListeFromData(data);
    }

    private List<Kunde> createKundenListeFromData(String[] data) {
        List<Kunde> kundenListe = new ArrayList<>();

        for(int i=1; i<data.length; i+=5) {

            String username = data[i];
            String passwort = data[i+1];
            String nachname = data[i+2];
            String vorname  = data[i+3];
            String adresse =  data[i+4];
            String id = data[i+5];

            Kunde kunde = new Kunde(username, passwort, nachname, vorname, adresse);
            kunde.setID(id);
            kundenListe.add(kunde);

        }
        return kundenListe;
    }

    @Override
    public List<Ereignis> getAlleEreignisse() {
        String cmd = Commands.CMD_GIB_ALLE_EREIGNISSE.name();
        socketOut.println(cmd);
        String[] data = readResponse();

        if(Commands.valueOf(data[0]) != Commands.CMD_GIB_ALLE_EREIGNISSE_RSP) {
            throw new RuntimeException("Ungueltige Antwort auf Anfrage erhalten!");
        }

        return createEreignisListeFromData(data);
    }


    private List<Ereignis> createEreignisListeFromData(String[] data) {
        List<Ereignis> ereignisListe = new ArrayList<>();

        for(int i=1; i<data.length; i+=13) { //todo habe ich die anzahl richtig gesetzt?

            LocalDateTime date = LocalDateTime.parse(data[i]);
            Enum ereignistyp = Enum.valueOf(data[i+2]); // todo ob das richtig funktioniert
            int menge  = Integer.parseInt(data[i+2]);
            int bestandAktualisierung = Integer.parseInt(data[i+3]);

            String username = data[i+4];
            String passwort = data[i+5];
            String vorname  = data[i+6];
            String nachname = data[i+7];
            String id = data[i+8];
            User user = new User(username, passwort, nachname, vorname, id);

            String bezeichnung = data[i+9];
            int artikelnummer = Integer.parseInt(data[i+10]);
            int bestand = Integer.parseInt(data[i+11]);
            double einzelpreis = Double.parseDouble(data[i+12]);
            int kaufszahl = Integer.parseInt(data[i+13]);

            if(kaufszahl != 1){
                Massengutartikel massengutartikel = new Massengutartikel(bezeichnung,artikelnummer,bestand, einzelpreis, kaufszahl);
                Ereignis ereignis = new Ereignis(menge,massengutartikel,user,ereignistyp,bestandAktualisierung);
                ereignis.setDatum(date);
                ereignisListe.add(ereignis);

            } else {
                Artikel artikel = new Artikel(bezeichnung, artikelnummer, bestand, einzelpreis);
                Ereignis ereignis = new Ereignis(menge,artikel,user,ereignistyp,bestandAktualisierung);
                ereignis.setDatum(date);
                ereignisListe.add(ereignis);
            }

        }
        return ereignisListe;
    }

    @Override
    public void schreibeArtikel() throws IOException {
        String cmd = Commands.CMD_SPEICHER_ARTIKEL.name();
        socketOut.println(cmd);
    }


    @Override
    public void schreibeMitarbeiter() throws IOException {
        String cmd = Commands.CMD_SPEICHER_MITAREBITER.name();
        socketOut.println(cmd);
    }

    @Override
    public void schreibeKunde() throws IOException {
        String cmd = Commands.CMD_SPEICHER_KUNDEN.name();
        socketOut.println(cmd);
    }

    @Override
    public void schreibeEreignis() throws IOException {
        String cmd = Commands.CMD_SPEICHER_EREIGNISSE.name();
        socketOut.println(cmd);
    }

    @Override
    public Warenkorb neuenWarenkorbErstellen(Kunde k) {

        String cmd = Commands.CMD_NEUEN_WARENKORB_ERSTELLEN.name();

        String username = k.getUserName();
        String passwort = k.getPasswort();
        String nachname = k.getNachname();
        String vorname  = k.getVorname();
        String adresse =  k.getKundenAdresse();
        String id = k.getidNummer();

        cmd += separator + username;
        cmd += separator + passwort;
        cmd += separator + nachname;
        cmd += separator + vorname;
        cmd += separator + adresse;
        cmd += separator + id;

        socketOut.println(cmd);

        String[] data = readResponse();

        if(Commands.valueOf(data[0]) != Commands.CMD_NEUEN_WARENKORB_ERSTELLEN_RSP) {
            throw new RuntimeException("Ungueltige Antwort auf Anfrage erhalten!");
        }
        //Der Warenkorb der hier erstellt wurde ist sowieso leer, es geht hier eher um den Eintrag in die Kundenhasmap
       warenkorb = new Warenkorb();
        return warenkorb;
    }

    @Override
    public Mitarbeiter mitarbeiterRegistrieren(Mitarbeiter neu) throws UserExistiertBereitsException, LeeresTextfieldException {
        String cmd = Commands.CMD_MITARBEITER_REGISTRIEREN.name();

        String username = neu.getUserName();
        String passwort = neu.getPasswort();
        String nachname = neu.getNachname();
        String vorname  = neu.getVorname();
        String id = neu.getidNummer();

        cmd += separator + username;
        cmd += separator + passwort;
        cmd += separator + nachname;
        cmd += separator + vorname;
        cmd += separator + id;

        socketOut.println(cmd);

        String[] data = readResponse();
/*
        if(CMD_SUCCESS)
            else if(CMD_REGIS_EXP)*/
        if(Commands.valueOf(data[0]) != Commands.CMD_MITARBEITER_REGISTRIEREN_RSP) {
            throw new RuntimeException("Ungueltige Antwort auf Anfrage erhalten!");
        }
        String registrieterUsername = data[1];
        String registrietesPasswort = data[2];
        String registrieterNachname = data[3];
        String registrieterVorname = data[4];
        String registrieteId = data[5];

        Mitarbeiter registrierterMitarbeiter = new Mitarbeiter(registrieterUsername, registrietesPasswort, registrieterNachname, registrieterVorname);
        registrierterMitarbeiter.setID(registrieteId);

        return registrierterMitarbeiter;
    }

    @Override
    public Mitarbeiter mitarbeiterLogin(String username, String passwort) throws LoginFehlgeschlagenException {
        String cmd = Commands.CMD_MITARBEITER_EINLOGGEN.name();

        cmd += separator + username;
        cmd += separator + passwort;

        socketOut.println(cmd);

        String[] data = readResponse();

        if(Commands.valueOf(data[0]) != Commands.CMD_MITARBEITER_EINLOGGEN_RSP) {
            throw new RuntimeException("Ungueltige Antwort auf Anfrage erhalten!");
        }

        String eingeloggterUsername = data[1];
        String eingeloggtesPasswort = data[2];
        String eingeloggterNachname = data[3];
        String eingeloggterVorname = data[4];
        String eingeloggteId = data[5];

        Mitarbeiter eingeloggterMitarbeiter = new Mitarbeiter(eingeloggterUsername, eingeloggtesPasswort, eingeloggterNachname, eingeloggterVorname);
        eingeloggterMitarbeiter.setID(eingeloggteId);

        return eingeloggterMitarbeiter;
    }

    @Override
    public void artHinzufügen(Artikel a, Mitarbeiter mitarbeiter) throws ArtikelExistiertBereitsException, LeeresTextfieldException {
        String cmd = Commands.CMD_EINZELARTIKEL_HINZUFÜGEN.name();

        cmd += separator + a.getBezeichnung();
        cmd += separator + a.getArtikelNummer();
        cmd += separator + a.getBestand();
        cmd += separator + a.getEinzelpreis();

        cmd += separator + mitarbeiter.getUserName();
        cmd += separator + mitarbeiter.getPasswort();
        cmd += separator + mitarbeiter.getVorname();
        cmd += separator + mitarbeiter.getNachname();
        cmd += separator + mitarbeiter.getidNummer();

        socketOut.println(cmd);

        String[] data = readResponse();

        if(Commands.valueOf(data[0]) != Commands.CMD_EINZELARTIKEL_HINZUFÜGEN_RSP) {
            throw new RuntimeException("Ungueltige Antwort auf Anfrage erhalten!");
        }

        System.out.println(data);

    }

    @Override
    public void massengutArtikelHinzufügen(Massengutartikel a, Mitarbeiter mitarbeiter) throws ArtikelExistiertBereitsException, LeeresTextfieldException {
        String cmd = Commands.CMD_MASSENGUTARTIKEL_HINZUFÜGEN.name();
        socketOut.println(cmd);


        cmd += separator + a.getBezeichnung();
        cmd += separator + a.getArtikelNummer();
        cmd += separator + a.getBestand();
        cmd += separator + a.getEinzelpreis();
        cmd += separator + a.getErwerbwareMenge();

        cmd += separator + mitarbeiter.getUserName();
        cmd += separator + mitarbeiter.getPasswort();
        cmd += separator + mitarbeiter.getVorname();
        cmd += separator + mitarbeiter.getNachname();
        cmd += separator + mitarbeiter.getidNummer();

        socketOut.println(cmd);

        String[] data = readResponse();

        if(Commands.valueOf(data[0]) != Commands.CMD_MASSENGUTARTIKEL_HINZUFÜGEN_RSP) {
            throw new RuntimeException("Ungueltige Antwort auf Anfrage erhalten!");
        }

        System.out.println(data);
    }

    @Override
    public void bestandErhöhen(String artikelname, int menge, User u) throws ArtikelExistiertNichtException, LeeresTextfieldException {
        String cmd = Commands.CMD_BESTAND_ERHÖHEN.name();

        cmd += separator + artikelname;
        cmd += separator + menge;

        cmd += separator + u.getUserName();
        cmd += separator + u.getPasswort();
        cmd += separator + u.getNachname();
        cmd += separator + u.getVorname();
        cmd += separator + u.getidNummer();


        socketOut.println(cmd);

        String[] data = readResponse();

        if(Commands.valueOf(data[0]) != Commands.CMD_BESTAND_ERHÖHEN_RSP) {
            throw new RuntimeException("Ungueltige Antwort auf Anfrage erhalten!");
        }

        System.out.println(data);
    }

    @Override
    public void bestanNiedriger(String artikelname, int menge, User u) throws ArtikelExistiertNichtException, UngueltigeMengeException, LeeresTextfieldException {
        String cmd = Commands.CMD_BESTAND_VERRINGERN.name();

        cmd += separator + artikelname;
        cmd += separator + menge;

        cmd += separator + u.getUserName();
        cmd += separator + u.getPasswort();
        cmd += separator + u.getNachname();
        cmd += separator + u.getVorname();
        cmd += separator + u.getidNummer();


        socketOut.println(cmd);

        String[] data = readResponse();

        if(Commands.valueOf(data[0]) != Commands.CMD_BESTAND_VERRINGERN_RSP) {
            throw new RuntimeException("Ungueltige Antwort auf Anfrage erhalten!");
        }

        System.out.println(data);
    }

    @Override
    public List<Artikel> artikelSortierenNachBezeichnung() {

        String cmd = Commands.CMD_ARTIKEL_NACH_ALPHABET_SORTIEREN.name();
        socketOut.println(cmd);

        String[] data = readResponse();

        if(Commands.valueOf(data[0]) != Commands.CMD_ARTIKEL_NACH_ALPHABET_SORTIEREN_RSP) {
            throw new RuntimeException("Ungueltige Antwort auf Anfrage erhalten!");
        }

        return createArtikellisteFromData(data);
    }

    @Override
    public List<Artikel> artikelNachArtikelnummerGeordnetAusgeben() {
        String cmd = Commands.CMD_ARTIKEL_NACH_ARTIKELNUMMER_SORTIEREN.name();
        socketOut.println(cmd);

        String[] data = readResponse();

        if(Commands.valueOf(data[0]) != Commands.CMD_ARTIKEL_NACH_ARTIKELNUMMER_SORTIEREN_RSP) {
            throw new RuntimeException("Ungueltige Antwort auf Anfrage erhalten!");
        }

        return createArtikellisteFromData(data);

    }

    @Override
    public List<Ereignis> ereignisseNachDatum() {
        String cmd = Commands.CMD_EREIGNISSE_NACH_DATUM_SORTIEREN.name();
        socketOut.println(cmd);

        String[] data = readResponse();

        if(Commands.valueOf(data[0]) != Commands.CMD_EREIGNISSE_NACH_DATUM_SORTIEREN_RSP) {
            throw new RuntimeException("Ungueltige Antwort auf Anfrage erhalten!");
        }

        return createEreignisListeFromData(data);
    }

    @Override
    public void inDenWarenkorbLegen(String artikel, int menge, Warenkorb warenkorb) throws ArtikelExistiertNichtException, UngueltigeMengeException {
        String cmd = Commands.CMD_IN_DEN_WARENKORB_LEGEN.name();

        cmd += separator + artikel;
        cmd += separator + menge;

        socketOut.println(cmd);

        String[] data = readResponse();

        if(Commands.valueOf(data[0]) != Commands.CMD_IN_DEN_WARENKORB_LEGEN_RSP) {
            throw new RuntimeException("Ungueltige Antwort auf Anfrage erhalten!");
        }

    }

    @Override
    public void artikelAusWarenkorbEntfernen(String artikel, Warenkorb warenkorb) {
        String cmd = Commands.CMD_AUS_DEM_WARENKORB_LEGEN.name();

        cmd += separator + artikel;

        socketOut.println(cmd);

        String[] data = readResponse();

        if(Commands.valueOf(data[0]) != Commands.CMD_AUS_DEM_WARENKORB_LEGEN_RSP) {
            throw new RuntimeException("Ungueltige Antwort auf Anfrage erhalten!");
        }

    }

    //todo checken ob man das schöner lösen kann
    @Override
    public void warenkorbLeeren(Warenkorb warenkorb) {
        String cmd = Commands.CMD_WARENKORB_LEEREN.name();
        socketOut.println(cmd);

        String [] data = readResponse();
        if(Commands.valueOf(data[0]) != Commands.CMD_WARENKORB_LEEREN_RSP) {
            throw new RuntimeException("Ungueltige Antwort auf Anfrage erhalten!");
        }

        warenkorb.getWarenkorb().clear();

    }

    @Override
    public Kunde kundenLogin(String username, String password) throws LoginFehlgeschlagenException {
        String cmd = Commands.CMD_KUNDEN_EINLOGGEN.name();
        socketOut.println(cmd);

        cmd += separator + username;
        cmd += separator + password;

        socketOut.println(cmd);

        String[] data = readResponse();

        if(Commands.valueOf(data[0]) != Commands.CMD_KUNDEN_EINLOGGEN_RSP) {
            throw new RuntimeException("Ungueltige Antwort auf Anfrage erhalten!");
        }

        String eingeloggterUsername = data[1];
        String eingeloggtesPasswort = data[2];
        String eingeloggterNachname = data[3];
        String eingeloggterVorname = data[4];
        String eingeloggteAdresse = data[5];
        String eingeloggteId = data[6];

        Kunde eingeloggterKunde = new Kunde(eingeloggterUsername, eingeloggtesPasswort, eingeloggterNachname, eingeloggterVorname, eingeloggteAdresse);
        eingeloggterKunde.setID(eingeloggteId);

        return eingeloggterKunde;
    }

    @Override
    public Kunde kundenRegistrieren(Kunde neu) throws UserExistiertBereitsException, LeeresTextfieldException {
        String cmd = Commands.CMD_KUNDEN_REGISTRIEREN.name();

        String username = neu.getUserName();
        String passwort = neu.getPasswort();
        String nachname = neu.getNachname();
        String vorname  = neu.getVorname();
        String adresse = neu.getKundenAdresse();
        String id = neu.getidNummer();

        cmd += separator + username;
        cmd += separator + passwort;
        cmd += separator + nachname;
        cmd += separator + vorname;
        cmd += separator + adresse;
        cmd += separator + id;

        socketOut.println(cmd);
        //todo frage hier senden wir den Kunden der zu registrieren ist rüber und bekommen ihn dann registriert zurück
        //todo reicht es dann denn gesendeten kunden einfach zurückzugeben bei erfolgreichen response oder soll man ihne aus dem response zusammenbauen?
        String[] data = readResponse();

        if(Commands.valueOf(data[0]) != Commands.CMD_KUNDEN_REGISTRIEREN_RSP) {
            throw new RuntimeException("Ungueltige Antwort auf Anfrage erhalten!");
        }
        String registrieterUsername = data[1];
        String registrietesPasswort = data[2];
        String registrieterNachname = data[3];
        String registrieterVorname = data[4];
        String registrierteAdresse = data[5];
        String registrieteId = data[6];

        Kunde registrierterKunde = new Kunde(registrieterUsername, registrietesPasswort, registrieterNachname, registrieterVorname,registrierteAdresse);
        registrierterKunde.setID(registrieteId);

        return registrierterKunde;
    }

    //TODO HEREEEEEE IST NOCH NICHT FERTIGG
    @Override
    public String kaufenUndRechnungEhalten(Kunde kunde, Warenkorb warenkorb) throws WarenkorbIstLeerException, IOException {
        String cmd = Commands.CMD_KAUF_ABSCHLIESSEN.name();

        cmd += separator + kunde.getUserName();
        cmd += separator + kunde.getPasswort();
        cmd += separator + kunde.getKundenAdresse();
        cmd += separator + kunde.getNachname();
        cmd += separator + kunde.getVorname();
        cmd += separator + kunde.getKundenAdresse();
        cmd += separator + kunde.getidNummer();


         warenkorb.getWarenkorb().keySet().forEach(artikel -> {
          String  waren = separator+ artikel.getBezeichnung();
            waren += separator + artikel.getArtikelNummer();
            waren += separator +artikel.getBestand();
            waren+= separator +artikel.getEinzelpreis();});

        socketOut.println(cmd);
        return null;
    }


    private String[] readResponse() {
        String[] parts = null;
        try {
            // Auf Antwort warten. Es wird maximal 1000ms gewartet
            String receivedData = socketIn.readLine();
            parts = receivedData.split(separator);

            System.err.println("Empfangene Antwort: " + receivedData);
        } catch(SocketTimeoutException e) {
            System.out.println("Server hat nicht geantwortet.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parts;
    }
}

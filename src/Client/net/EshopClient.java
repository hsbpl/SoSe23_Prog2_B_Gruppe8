package Client.net;

import Common.*;
import Common.Exceptions.*;

import java.awt.*;
import java.io.*;
import java.lang.Enum;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EshopClient implements EShopInterface {

    final String separator = ";";

    private Socket socket;
    private BufferedReader socketIn;
    private PrintStream socketOut;


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

        //todo die länge wird problematisch beim massengut, da es ein Attribut mehr hat statt 4 = 5...
        for(int i=1; i<data.length; i+=4) {

            String bezeicnung = data[i];
            int artikelnummer = Integer.parseInt(data[i+1]);
            int bestand = Integer.parseInt(data[i+2]);
            double einzelpreis = Double.parseDouble(data[i+3]);
            int massengut = Integer.parseInt(data[i+4]);

            Artikel artikel = new Artikel(bezeicnung,artikelnummer,bestand,einzelpreis);
            artikelliste.add(artikel);

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

    //todo hier noch nicht fertig
    private List<Ereignis> createEreignisListeFromData(String[] data) {
        List<Ereignis> ereignisListe = new ArrayList<>();

        for(int i=1; i<data.length; i+=13) {

            LocalDateTime date = LocalDateTime.parse(data[i]);
            //TODO wie man hier mit enums umgeht Enum ereignistyp = Enum.(data[i+1]);
            int menge  = Integer.parseInt(data[i+2]);
            int bestandAktualisierung = Integer.parseInt(data[i+3]);

            String username = data[i+4];
            String passwort = data[i+5];
            String vorname  = data[i+6];
            String nachname = data[i+7];
            String id = data[i+8];

            String bezeichnung = data[i+9];
            int artikelnummer = Integer.parseInt(data[i+10]);
            int bestand = Integer.parseInt(data[i+11]);
            double einzelpreis = Double.parseDouble(data[i+12]);
            int massengut = Integer.parseInt(data[i+13]);

            User user = new User(username,passwort, nachname, vorname, id);
            Artikel artikel = new Artikel(bezeichnung,artikelnummer,bestand,einzelpreis);
           /* Ereignis ereignis = new Ereignis(menge,artikel,user,ereignistyp,bestandAktualisierung);
            ereignis.setDatum(date);
            ereignisListe.add(ereignis);

            */

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
        Warenkorb warenkorb = new Warenkorb();
        return warenkorb;
    }

    @Override
    public Mitarbeiter mitarbeiterRegistrieren(Mitarbeiter neu) throws UserExistiertBereitsException, LeeresTextfieldException {
        String cmd = Commands.CMD_MITARBEITER_REGISTRIEREN.name();
        socketOut.println(cmd);
        return neu;
    }

    @Override
    public Mitarbeiter mitarbeiterLogin(String username, String passwort) throws LoginFehlgeschlagenException {
        String cmd = Commands.CMD_MITARBEITER_EINLOGGEN.name();
        socketOut.println(cmd);
        return null;
    }

    @Override
    public void artHinzufügen(Artikel a, Mitarbeiter mitarbeiter) throws ArtikelExistiertBereitsException, LeeresTextfieldException {
        String cmd = Commands.CMD_EINZELARTIKEL_HINZUFÜGEN.name();
        socketOut.println(cmd);
    }

    @Override
    public void massengutArtikelHinzufügen(Massengutartikel a, Mitarbeiter mitarbeiter) throws ArtikelExistiertBereitsException, LeeresTextfieldException {
        String cmd = Commands.CMD_MASSENGUTARTIKEL_HINZUFÜGEN.name();
        socketOut.println(cmd);
    }

    @Override
    public void bestandErhöhen(String artikelname, int menge, User u) throws ArtikelExistiertNichtException, LeeresTextfieldException {
        String cmd = Commands.CMD_BESTAND_VERRINGERN.name();
        socketOut.println(cmd);
    }

    @Override
    public void bestanNiedriger(String artikelname, int menge, User u) throws ArtikelExistiertNichtException, UngueltigeMengeException, LeeresTextfieldException {
        String cmd = Commands.CMD_BESTAND_VERRINGERN.name();
        socketOut.println(cmd);
    }

    @Override
    public List<Artikel> artikelSortierenNachBezeichnung() {
        return null;
    }

    @Override
    public List<Artikel> artikelNachArtikelnummerGeordnetAusgeben() {
        String cmd = Commands.CMD_ARTIKEL_NACH_ARTIKELNUMMER_SORTIEREN.name();
        socketOut.println(cmd);
        return null;
    }

    @Override
    public List<Ereignis> ereignisseNachDatum() {
        String cmd = Commands.CMD_EREIGNISSE_NACH_DATUM_SORTIEREN_RSP.name();
        socketOut.println(cmd);
        return null;
    }

    @Override
    public void inDenWarenkorbLegen(String artikel, int menge, Warenkorb warenkorb) throws ArtikelExistiertNichtException, UngueltigeMengeException {
        String cmd = Commands.CMD_IN_DEN_WARENKORB_LEGEN.name();
        socketOut.println(cmd);
    }

    @Override
    public void artikelAusWarenkorbEntfernen(String artikel, Warenkorb warenkorb) {
        String cmd = Commands.CMD_AUS_DEM_WARENKORB_LEGEN.name();
        socketOut.println(cmd);
    }

    @Override
    public void warenkorbLeeren(Warenkorb warenkorb) {
        String cmd = Commands.CMD_WARENKORB_LEEREN.name();
        socketOut.println(cmd);
    }

    @Override
    public Kunde kundenLogin(String username, String password) throws LoginFehlgeschlagenException {
        String cmd = Commands.CMD_KUNDEN_EINLOGGEN.name();
        socketOut.println(cmd);
        return null;
    }

    @Override
    public Kunde kundenRegistrieren(Kunde neu) throws UserExistiertBereitsException, LeeresTextfieldException {
        String cmd = Commands.CMD_KUNDEN_REGISTRIEREN.name();
        socketOut.println(cmd);
        return null;
    }

    @Override
    public String kaufenUndRechnungEhalten(Kunde kunde, Warenkorb warenkorb) throws WarenkorbIstLeerException, IOException {
        String cmd = Commands.CMD_KAUF_ABSCHLIESSEN.name();
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

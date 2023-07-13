package Server.net;

import Common.*;
import Common.Exceptions.*;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

public class ClientRequestProcessor implements Runnable {

    private BufferedReader socketIn;
    private PrintStream socketOut;
    final String separator = ";";
    private Socket clientSocket;
    private Connection connection;
    EShopInterface eshop;

    Warenkorb warenkorb;

    public ClientRequestProcessor(Socket socket, EShopInterface eshop) throws IOException {
        this.eshop = eshop;
        this.clientSocket = socket;
        this.socketOut = new PrintStream(this.clientSocket.getOutputStream());
        this.connection = connection;

        try {
            this.socketIn = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            this.socketOut = new PrintStream(this.clientSocket.getOutputStream());
        } catch (IOException var6) {
            try {
                this.clientSocket.close();
            } catch (IOException var5) {
            }

            System.err.println("Ausnahme bei Bereitstellung des Streams: " + var6);
            return;
        }

        System.out.println("Verbunden mit " + this.clientSocket.getInetAddress() + ":" + this.clientSocket.getPort());
    }


    @Override
    public void run() {
        while (true) {
            try {
                String receivedData = socketIn.readLine();
                if (receivedData == null) {
                    break;
                }
                handleCommandRequest(receivedData);
                System.out.println(receivedData);
            } catch (SocketException e) {
                System.err.println("Client hat Verbindung geschlossen");
                break;
            } catch (IOException | UserExistiertBereitsException | LeeresTextfieldException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleCommandRequest(String receivedData) throws UserExistiertBereitsException, LeeresTextfieldException {

        System.err.println("Vom Client empfangende Daten: " + receivedData);
        String[] parts = receivedData.split(separator);

        if (parts.length == 0) {
            System.err.println("Kein Befehl in den empfangenen Daten gefunden");
            return;
        }

        switch (Commands.valueOf(parts[0])) {
            case HALLO_SERVER:
                handleGibHalloServer();
                break;
            case HALLO_CLIENT:
                handleGibHalloClient();
            case CMD_GIB_ALLE_ARTIKEL:
                handleGibAlleArtikel();
                break;
            case CMD_GIB_ALLE_KUNDEN:
                handleGibAlleKunden();
                break;
            case CMD_GIB_ALLE_EREIGNISSE:
                handleGibAlleEreignisse();
                break;
            case CMD_GIB_ALLE_MITARBEITER:
                handleGibAlleMitarbeiter();
                break;
            case CMD_SPEICHER_HALLO_SERVER:
                handleHalloServerSpeichern();
                break;
            case CMD_SPEICHER_HALLO_CLIENT:
                handleHalloClientSpeichern();
            case CMD_SPEICHER_ARTIKEL:
                handleArtikelSpeichern();
                break;
            case CMD_SPEICHER_MITAREBITER:
                handleMitarbeiterSpeichern();
                break;
            case CMD_SPEICHER_KUNDEN:
                handleKundeSpeichern();
                break;
            case CMD_SPEICHER_EREIGNISSE:
                handleEreignisSpeichern();
                break;
            case CMD_NEUEN_WARENKORB_ERSTELLEN:
                handleNeuenWarenkornErstellen(parts);
                break;
            case CMD_MITARBEITER_REGISTRIEREN:
                handleNuenMitarbeiterRegistrieren(parts);
                break;
            case CMD_MITARBEITER_EINLOGGEN:
                handleMitarbeiterEinloggen(parts);
                break;
            case CMD_EINZELARTIKEL_HINZUFÜGEN:
                handleEinzelartikelHinzufügen(parts);
                break;
            case CMD_MASSENGUTARTIKEL_HINZUFÜGEN:
                handleMassengutartikelHinzufügen(parts);
                break;
            case CMD_BESTAND_ERHÖHEN:
                handleBestandErhöhen(parts);
                break;
            case CMD_BESTAND_VERRINGERN:
                handleBestandVerringern(parts);
                break;
            case CMD_ARTIKEL_NACH_ALPHABET_SORTIEREN:
                handleArtikelNachAlphabetSortieren();
                break;
            case CMD_ARTIKEL_NACH_ARTIKELNUMMER_SORTIEREN:
                handleArtikelNachArtikelnummerSortieren();
                break;
            case CMD_EREIGNISSE_NACH_DATUM_SORTIEREN:
                handleEreignisseNachDatumSortieren();
                break;
            case CMD_IN_DEN_WARENKORB_LEGEN:
                handleInDenWarenkorbLegen(parts);
                break;
            case CMD_AUS_DEM_WARENKORB_LEGEN:
                handleAusDemWarenkorbLegen(parts);
                break;
            case CMD_WARENKORB_LEEREN:
                handleWarenkorbLeeren();
                break;
            case CMD_KUNDEN_REGISTRIEREN:
                handleKundenRegistrieren(parts);
                break;
            case CMD_KUNDEN_EINLOGGEN:
                handleKundenEinloggen(parts);
                break;
            case CMD_KAUF_ABSCHLIESSEN:
                handleKaufAbschliessen();
                break;
            default:
                System.err.println("Ungueltige Anfrage empfangen!");
                break;
        }
    }


    private void handleBestandErhöhen(String[] data) {
        String cmd = Commands.CMD_BESTAND_ERHÖHEN_RSP.name();

        String artikelname = data[1];
        int menge = Integer.parseInt(data[2]);

        String username = data[3];
        String passwort = data[4];
        String nachname = data[5];
        String vorname = data[6];
        String id = data[7];
        User user = new User(username, passwort, nachname, vorname, id);

        //todo Exceptions

        try {
            eshop.bestandErhöhen(artikelname, menge, user);
            cmd += separator + "Erfolgreich";
            socketOut.println(cmd);
        } catch (ArtikelExistiertNichtException e) {
            throw new RuntimeException(e);
        } catch (LeeresTextfieldException e) {
            throw new RuntimeException(e);
        }

        socketOut.println(cmd);
    }

    private void handleHalloClientSpeichern() {
    }

    private void handleGibHalloClient() {
        String response = "Hallo, Client!";
        try {
            socketOut.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleGibHalloServer() {
        String response = "Hallo, Server!";
        try {
            socketOut.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleHalloServerSpeichern() {
        socketOut.println("Befehl 'Hallo Server speichern' erhalten!");

    }

    private void handleGibAlleMitarbeiter() {
        List<Mitarbeiter> result = eshop.getAlleMitarbeiter();

        String cmd = Commands.CMD_GIB_ALLE_ARTIKEL_RSP.name();

        for (Mitarbeiter mitarbeiter : result) {
            cmd += separator + mitarbeiter.getUserName();
            cmd += separator + mitarbeiter.getPasswort();
            cmd += separator + mitarbeiter.getNachname();
            cmd += separator + mitarbeiter.getVorname();
            cmd += separator + mitarbeiter.getidNummer();
        }
        socketOut.println(cmd);
    }

    private void handleGibAlleArtikel() {
        List<Artikel> result = eshop.getAlleArtikel();

        String cmd = Commands.CMD_GIB_ALLE_ARTIKEL_RSP.name();

        for (Artikel artikel : result) {
            cmd += separator + artikel.getBezeichnung();
            cmd += separator + artikel.getArtikelNummer();
            cmd += separator + artikel.getBestand();
            cmd += separator + artikel.getEinzelpreis();

            if (artikel instanceof Massengutartikel) {
                cmd += separator + ((Massengutartikel) artikel).getErwerbwareMenge();
            } else{
                cmd += separator + 1;
            }
        }

        // Artikel an den Client senden
        socketOut.println(cmd);

        // Artikel im Server-Terminal ausgeben
        System.out.println("Alle Artikel:");
        for (Artikel artikel : result) {
            System.out.println("Bezeichnung: " + artikel.getBezeichnung());
            System.out.println("Artikelnummer: " + artikel.getArtikelNummer());
            System.out.println("Bestand: " + artikel.getBestand());
            System.out.println("Einzelpreis: " + artikel.getEinzelpreis());

            if (artikel instanceof Massengutartikel) {
                System.out.println("ErwerbwareMenge: " + ((Massengutartikel) artikel).getErwerbwareMenge());
            }

            System.out.println("----------------------");
        }
    }




    private void handleGibAlleKunden() {
        List<Kunde> result = eshop.getAlleKunden();

        String cmd = Commands.CMD_GIB_ALLE_KUNDEN_RSP.name();

        for (Kunde kunde : result) {
            cmd += separator + kunde.getUserName();
            cmd += separator + kunde.getPasswort();
            cmd += separator + kunde.getNachname();
            cmd += separator + kunde.getVorname();
            cmd += separator + kunde.getKundenAdresse();
            cmd += separator + kunde.getidNummer();
        }
        socketOut.println(cmd);
    }

    private void handleGibAlleEreignisse() {
        List<Ereignis> result = eshop.getAlleEreignisse();

        String cmd = Commands.CMD_GIB_ALLE_EREIGNISSE_RSP.name();

        for (Ereignis ereignis : result) {
            cmd += separator + ereignis.getDatum();
            cmd += separator + ereignis.getEreignistyp();
            cmd += separator + ereignis.getAnzahl();
            cmd += separator + ereignis.getAktualisierterBestand();


            cmd += separator + ereignis.getUser().getUserName();
            cmd += separator + ereignis.getUser().getPasswort();
            cmd += separator + ereignis.getUser().getVorname();
            cmd += separator + ereignis.getUser().getNachname();
            cmd += separator + ereignis.getUser().getidNummer();


            cmd += separator + ereignis.getArtikel().getBezeichnung();
            cmd += separator + ereignis.getArtikel().getArtikelNummer();
            cmd += separator + ereignis.getArtikel().getBestand();
            cmd += separator + ereignis.getArtikel().getEinzelpreis();

            //Um Massengut verkaufsmenge zu checken
            if (ereignis.getArtikel() instanceof Massengutartikel) {
                cmd += separator + ((Massengutartikel) ereignis.getArtikel()).getErwerbwareMenge();
            }else{
                cmd += separator + 1;
            }
        }
        socketOut.println(cmd);
    }

    private void handleArtikelSpeichern() {
        try {
            eshop.schreibeArtikel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleMitarbeiterSpeichern() {
        try {
            eshop.schreibeMitarbeiter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleKundeSpeichern() {
        try {
            eshop.schreibeKunde();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleEreignisSpeichern() {
        try {
            eshop.schreibeEreignis();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleNeuenWarenkornErstellen(String[] data) {

        String username = data[1];
        String passwort = data[2];
        String nachname = data[3];
        String vorname  = data[4];
        String adresse =  data[5];
        String id = data[6];

        Kunde kunde = new Kunde(username,passwort,nachname,vorname,adresse);
        kunde.setID(id);

        String cmd = Commands.CMD_NEUEN_WARENKORB_ERSTELLEN_RSP.name();

        //todo könnte das prolematisch sein ?
        //Der Warenkorb der hier erstellt wurde ist sowieso leer, es geht hier eher um den Eintrag in die Kundenhasmap
        warenkorb =  eshop.neuenWarenkorbErstellen(kunde);
        socketOut.println(cmd);
    }

    private void handleNuenMitarbeiterRegistrieren(String[] data) throws UserExistiertBereitsException, LeeresTextfieldException{
        String username = data[1];
        String passwort = data[2];
        String nachname = data[3];
        String vorname  = data[4];
        String id = data[5];

        Mitarbeiter mitarbeiter = new Mitarbeiter(username, passwort, nachname, vorname);
        mitarbeiter.setID(id);

        //todo fragen wie man mit exceptions umgeht
        try {
            eshop.mitarbeiterRegistrieren(mitarbeiter);
            String cmd = Commands.CMD_GIB_ALLE_MITARBEITER_RSP.name() + separator;

            cmd += mitarbeiter.getUserName() + separator;
            cmd += mitarbeiter.getPasswort() + separator;
            cmd += mitarbeiter.getNachname() + separator;
            cmd += mitarbeiter.getVorname() + separator;
            cmd += mitarbeiter.getidNummer();

            socketOut.println(cmd);

        } catch (UserExistiertBereitsException e) {
            // out.write(CMD_REGISTRIEREN_EXCEP...);
            throw e;
        } catch (LeeresTextfieldException e) {
            throw e;
        }

    }

    private void handleMitarbeiterEinloggen(String[] data) {
        //todo Exceptionhandling
        String username = data[1];
        String passwort = data[2];

        try {
            Mitarbeiter mitarbeiter = eshop.mitarbeiterLogin(username,passwort);

            String cmd = Commands.CMD_MITARBEITER_EINLOGGEN_RSP.name() + separator;

            cmd += mitarbeiter.getUserName() + separator;
            cmd += mitarbeiter.getPasswort() + separator;
            cmd += mitarbeiter.getNachname() + separator;
            cmd += mitarbeiter.getVorname() + separator;
            cmd += mitarbeiter.getidNummer();

            socketOut.println(cmd);
        } catch (LoginFehlgeschlagenException e) {
            throw new RuntimeException(e);
        }
    }
    private void handleEinzelartikelHinzufügen(String[] data) {
        String cmd = Commands.CMD_EINZELARTIKEL_HINZUFÜGEN_RSP.name() + separator;

        String bezeichnung = data[1];
        int artikelnummer = Integer.parseInt(data[2]);
        int bestand = Integer.parseInt(data[3]);
        double einzelpreis  = Double.parseDouble(data[4]);
        Artikel artikel = new Artikel(bezeichnung, artikelnummer, bestand, einzelpreis);

        //miarbeiterdaten
        String username = data[5];
        String passwort = data[6];
        String nachname = data[7];
        String vorname  = data[8];
        String id = data[9];
        Mitarbeiter mitarbeiter = new Mitarbeiter(username, passwort, nachname, vorname);
        mitarbeiter.setID(id);

        //todo exceptions
        try {
            eshop.artHinzufügen(artikel,mitarbeiter);
            cmd += "Erfolgreich";
            socketOut.println(cmd);
        } catch (ArtikelExistiertBereitsException e) {
            throw new RuntimeException(e);
        } catch (LeeresTextfieldException e) {
            throw new RuntimeException(e);
        }


    }
    private void handleMassengutartikelHinzufügen(String[] data) {
        String cmd = Commands.CMD_MASSENGUTARTIKEL_HINZUFÜGEN_RSP.name() + separator;

        String bezeichnung = data[1];
        int artikelnummer = Integer.parseInt(data[2]);
        int bestand = Integer.parseInt(data[3]);
        double einzelpreis  = Double.parseDouble(data[4]);
        int kaufmenge = Integer.parseInt(data[5]);
        Massengutartikel artikel = new Massengutartikel(bezeichnung, artikelnummer, bestand, einzelpreis,kaufmenge);

        //miarbeiterdaten
        String username = data[6];
        String passwort = data[7];
        String nachname = data[8];
        String vorname  = data[9];
        String id = data[10];
        Mitarbeiter mitarbeiter = new Mitarbeiter(username, passwort, nachname, vorname);
        mitarbeiter.setID(id);

        //todo exceptions
        try {
            eshop.massengutArtikelHinzufügen(artikel,mitarbeiter);
            cmd += "Erfolgreich";
            socketOut.println(cmd);
        } catch (ArtikelExistiertBereitsException e) {
            throw new RuntimeException(e);
        } catch (LeeresTextfieldException e) {
            throw new RuntimeException(e);
        }
    }
    private void handleBestandVerringern(String[] data) {
        String cmd = Commands.CMD_BESTAND_VERRINGERN_RSP.name();

        String artikelname = data[1];
        int menge = Integer.parseInt(data[2]);

        String username = data[3];
        String passwort = data[4];
        String nachname = data[5];
        String vorname  = data[6];
        String id = data[7];
        User user = new User(username, passwort, nachname,vorname, id);

        //todo Exceptions

        try {
            eshop.bestanNiedriger(artikelname,menge,user);

            cmd +=separator + "Erfolgreich";
            socketOut.println(cmd);
        } catch (ArtikelExistiertNichtException e) {
            throw new RuntimeException(e);
        } catch (UngueltigeMengeException e) {
            throw new RuntimeException(e);
        } catch (LeeresTextfieldException e) {
            throw new RuntimeException(e);
        }

        socketOut.println(cmd);


    }
    private void handleArtikelNachAlphabetSortieren() {
        List<Artikel> result = eshop.artikelSortierenNachBezeichnung();

        String cmd = Commands.CMD_ARTIKEL_NACH_ALPHABET_SORTIEREN_RSP.name();

        for (Artikel artikel : result) {
            cmd += separator + artikel.getBezeichnung();
            cmd += separator + artikel.getArtikelNummer();
            cmd += separator + artikel.getBestand();
            cmd += separator + artikel.getEinzelpreis();
            if (artikel instanceof Massengutartikel) {
                cmd += separator + ((Massengutartikel) artikel).getErwerbwareMenge();
            }

        }
        socketOut.println(cmd);
    }
    private void handleArtikelNachArtikelnummerSortieren() {

        List<Artikel> result = eshop.artikelNachArtikelnummerGeordnetAusgeben();

        String cmd = Commands.CMD_ARTIKEL_NACH_ARTIKELNUMMER_SORTIEREN_RSP.name();

        for (Artikel artikel : result) {
            cmd += separator + artikel.getBezeichnung();
            cmd += separator + artikel.getArtikelNummer();
            cmd += separator + artikel.getBestand();
            cmd += separator + artikel.getEinzelpreis();
            if (artikel instanceof Massengutartikel) {
                cmd += separator + ((Massengutartikel) artikel).getErwerbwareMenge();
            }

        }
        socketOut.println(cmd);

    }
    private void handleEreignisseNachDatumSortieren() {
        List<Ereignis> result = eshop.ereignisseNachDatum();

        String cmd = Commands.CMD_EREIGNISSE_NACH_DATUM_SORTIEREN_RSP.name();

        for (Ereignis ereignis : result) {
            cmd += separator + ereignis.getDatum();
            cmd += separator + ereignis.getEreignistyp();
            cmd += separator + ereignis.getAnzahl();
            cmd += separator + ereignis.getAktualisierterBestand();


            cmd += separator + ereignis.getUser().getUserName();
            cmd += separator + ereignis.getUser().getPasswort();
            cmd += separator + ereignis.getUser().getVorname();
            cmd += separator + ereignis.getUser().getNachname();
            cmd += separator + ereignis.getUser().getidNummer();


            cmd += separator + ereignis.getArtikel().getBezeichnung();
            cmd += separator + ereignis.getArtikel().getArtikelNummer();
            cmd += separator + ereignis.getArtikel().getBestand();
            cmd += separator + ereignis.getArtikel().getEinzelpreis();

            //Um Massengut verkaufsmenge zu checken
            if (ereignis.getArtikel() instanceof Massengutartikel) {
                cmd += separator + ((Massengutartikel) ereignis.getArtikel()).getErwerbwareMenge();
            }else{
                cmd += separator + 1;
            }
        }
        socketOut.println(cmd);
    }
    private void handleInDenWarenkorbLegen(String[] data) {
        //String cmd = Commands.CMD_IN_DEN_WARENKORB_LEGEN_RSP.name();

        String artikel = data[1];
        int menge = Integer.parseInt(data[2]);

        //todo exceptions
        try {
            System.out.println(artikel);
            System.out.println(menge);
            System.out.println(warenkorb);
            eshop.inDenWarenkorbLegen(artikel, menge, warenkorb);

        } catch (UngueltigeMengeException e) {
            throw new RuntimeException(e);
        } catch (ArtikelExistiertNichtException e) {
            throw new RuntimeException(e);
        }


    }
    private void handleAusDemWarenkorbLegen(String[] data) {
        String cmd= Commands.CMD_AUS_DEM_WARENKORB_LEGEN_RSP.name();

        String artikel = data[1];

        eshop.artikelAusWarenkorbEntfernen(artikel, warenkorb);

        socketOut.println(cmd);

    }
    private void handleWarenkorbLeeren() {
        String cmd = Commands.CMD_WARENKORB_LEEREN_RSP.name();
        eshop.warenkorbLeeren(warenkorb);

        socketOut.println(cmd);
    }
    private void handleKundenRegistrieren(String[] data) throws UserExistiertBereitsException, LeeresTextfieldException {

        String username = data[1];
        String passwort = data[2];
        String nachname = data[3];
        String vorname  = data[4];
        String adresse  = data[5];
        String id = data[6];

        Kunde kunde = new Kunde(username, passwort, nachname, vorname,adresse);
        kunde.setID(id);

        //todo fragen wie man mit exceptions umgeht
        try {
            eshop.kundenRegistrieren(kunde);
            String cmd = Commands.CMD_KUNDEN_REGISTRIEREN_RSP.name() + separator;

            cmd += kunde.getUserName() + separator;
            cmd += kunde.getPasswort() + separator;
            cmd += kunde.getNachname() + separator;
            cmd += kunde.getVorname() + separator;
            cmd += kunde.getKundenAdresse() + separator;
            cmd += kunde.getidNummer();

            socketOut.println(cmd);

        } catch (UserExistiertBereitsException e) {
            throw e;
        } catch (LeeresTextfieldException e) {
            throw e;
        }

    }
    private void handleKundenEinloggen(String data[]) {

        //todo Exceptionhandling
        System.out.println(Arrays.toString(data));
        String username = data[1];
        System.out.println(username);
        String passwort = data[2];
        System.out.println(passwort);

        try {
            Kunde kunde = eshop.kundenLogin(username, passwort);

            String cmd = Commands.CMD_KUNDEN_EINLOGGEN_RSP.name() + separator;

            cmd += kunde.getUserName() + separator;
            cmd += kunde.getPasswort() + separator;
            cmd += kunde.getNachname() + separator;
            cmd += kunde.getVorname() + separator;
            cmd += kunde.getKundenAdresse() + separator;
            cmd += kunde.getidNummer();

            socketOut.println(cmd);
        } catch (LoginFehlgeschlagenException e) {
            throw new RuntimeException(e);
        }



    }
    private void handleKaufAbschliessen() {
    }
}
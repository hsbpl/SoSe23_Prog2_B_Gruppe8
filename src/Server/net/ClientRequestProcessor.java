package Server.net;

import Common.*;
import Common.Exceptions.LeeresTextfieldException;
import Common.Exceptions.UserExistiertBereitsException;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.util.List;

public class ClientRequestProcessor implements Runnable {

    private BufferedReader socketIn;
    private PrintStream socketOut;
    final String separator = ";";
    private Socket clientSocket;
    private Connection connection;
    EShopInterface eshop;

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
                handleNeuenMitarbeiterEinloggen();
                break;
            case CMD_EINZELARTIKEL_HINZUFÜGEN:
                handleEinzelartikelHinzufügen();
                break;
            case CMD_MASSENGUTARTIKEL_HINZUFÜGEN:
                handleMassengutartikelHinzufügen();
                break;
            case CMD_BESTAND_ERHÖHEN:
                handleBestandErhöhen();
                break;
            case CMD_BESTAND_VERRINGERN:
                handleBestandVerringern();
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
                handleInDenWarenkorbLegen();
                break;
            case CMD_AUS_DEM_WARENKORB_LEGEN:
                handleAusDemWarenkorbLegen();
                break;
            case CMD_WARENKORB_LEEREN:
                handleWarenkorbLeeren();
                break;
            case CMD_KUNDEN_REGISTRIEREN:
                handleKundenRegistrieren(parts);
                break;
            case CMD_KUNDEN_EINLOGGEN:
                handleKundenEinloggen();
                break;
            case CMD_KAUF_ABSCHLIESSEN:
                handleKaufAbschliessen();
                break;
            default:
                System.err.println("Ungueltige Anfrage empfangen!");
                break;
        }
    }





    private void handleBestandErhöhen() {
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
            }

        }
        socketOut.println(cmd);
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
        Warenkorb warenkorb =  eshop.neuenWarenkorbErstellen(kunde);
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
            throw e;
        } catch (LeeresTextfieldException e) {
            throw e;
        }

    }

    private void handleNeuenMitarbeiterEinloggen() {
    }
    private void handleEinzelartikelHinzufügen() {
    }
    private void handleMassengutartikelHinzufügen() {
    }
    private void handleBestandVerringern() {
    }
    private void handleArtikelNachAlphabetSortieren() {
    }
    private void handleArtikelNachArtikelnummerSortieren() {
    }
    private void handleEreignisseNachDatumSortieren() {
    }
    private void handleInDenWarenkorbLegen() {
    }
    private void handleAusDemWarenkorbLegen() {
    }
    private void handleWarenkorbLeeren() {
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
    private void handleKundenEinloggen() {
    }
    private void handleKaufAbschliessen() {
    }
}




package Server.net;

import Common.*;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class ClientRequestProcessor implements Runnable {

    private BufferedReader socketIn;
    private PrintStream socketOut;
    final String separator = ";";
    private Socket clientSocket;

    EShopInterface eshop;

    public ClientRequestProcessor(Socket socket, EShopInterface eshop) throws IOException {
        this.eshop = eshop;
        this.clientSocket = socket;

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
    }     //Hier wird eine Erfolgsmeldung ausgegeben, die die Verbindungsinformationen des Sockets enthält (IP-Adresse und Portnummer).


    @Override
    public void run() {
        while (true) {
            try {
                String receivedData = socketIn.readLine(); // BufferedReader bietet readLine()
                handleCommandRequest(receivedData);
            } catch (SocketException e) {
                System.err.println("Client hat Verbindung geschlossen");
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleCommandRequest(String receivedData) {
        System.err.println("Vom Client empfangende Daten: " + receivedData);
        String[] parts = receivedData.split(separator);

        switch (Commands.valueOf(parts[0])) {
            case CMD_GIB_ALLE_ARTIKEL -> handleGibAlleArtikel();
            case CMD_GIB_ALLE_KUNDEN -> handleGibAlleKunden();
            case CMD_GIB_ALLE_EREIGNISSE -> handleGibAlleEreignisse();
            case CMD_GIB_ALLE_MITARBEITER -> handleGibAlleMitarbeiter();
            case CMD_SPEICHER_ARTIKEL -> handleArtikelSpeichern();
            case CMD_SPEICHER_MITAREBITER -> handleMitarbeiterSpeichern();
            case CMD_SPEICHER_KUNDEN -> handleKundeSpeichern();
            case CMD_SPEICHER_EREIGNISSE -> handleEreignisSpeichern();
          /*  case CMD_NEUEN_WARENKORB_ERSTELLEN
            case CMD_MITARBEITER_REGISTRIEREN
            case CMD_MITARBEITER_EINLOGGEN
            case CMD_EINZELARTIKEL_HINZUFÜGEN
            case CMD_MASSENGUTARTIKEL_HINZUFÜGEN
            case CMD_BESTAND_ERHÖHEN
            case CMD_BESTAND_VERRINGERN
            case CMD_ARTIKEL_NACH_ALPHABET_SORTIEREN
            case CMD_ARTIKEL_NACH_ARTIKELNUMMER_SORTIEREN
            case CMD_EREIGNISSE_NACH_DATUM_SORTIEREN
            case CMD_IN_DEN_WARENKORB_LEGEN
            case CMD_AUS_DEM_WARENKORB_LEGEN
            case CMD_WARENKORB_LEEREN
            case CMD_KUNDEN_REGISTRIEREN
            case CMD_KUNDEN_EINLOGGEN
            case CMD_KAUF_ABSCHLIESSEN

           */

            /*

            case CMD_BUCH_SUCHEN -> handleSuchen(parts);
            case CMD_BUCH_EINFUEGEN -> handleEinfuegen(parts);
            case CMD_BUCH_LOESCHEN -> handleLoeschen(parts);

            */

            default -> System.err.println("Ungueltige Anfrage empfangen!");
        }
    }

    private void handleGibAlleMitarbeiter() {

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
                cmd += separator + "MG" + ((Massengutartikel) artikel).getErwerbwareMenge();
            }

        }
        socketOut.println(cmd);
    }

    private void handleGibAlleKunden() {
        List<Kunde> result = eshop.getAlleKunden();

        String cmd = Commands.CMD_GIB_ALLE_KUNDEN_RSP.name();

        for (Kunde kunde : result) {
            cmd += separator + kunde.getUserName();
            cmd += separator + kunde.getNachname();
            cmd += separator + kunde.getVorname();
            cmd += separator + kunde.getidNummer();
            cmd += separator + kunde.getKundenAdresse();
        }
        socketOut.println(cmd);
    }

    private void handleGibAlleEreignisse() {
        List<Ereignis> result = eshop.getAlleEreignisse();

        String cmd = Commands.CMD_GIB_ALLE_EREIGNISSE_RSP.name();

        for (Ereignis ereignis : result) {
            cmd += separator + ereignis.getDatum();
            cmd += separator + ereignis.getEreignistyp();
            cmd += separator + ereignis.getArtikel();
            cmd += separator + ereignis.getAnzahl();
            cmd += separator + ereignis.getAktualisierterBestand();
            cmd += separator + ereignis.getUser();
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

    //todo geht das einfach so ? wirkt auf mich gerade ein wenig faslch
    private void handleWaremkorbErstellen(Kunde kunde) {
        eshop.neuenWarenkorbErstellen(kunde);
    }
}




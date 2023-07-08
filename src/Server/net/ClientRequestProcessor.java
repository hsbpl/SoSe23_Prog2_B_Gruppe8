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
        this.socketOut = new PrintStream(this.clientSocket.getOutputStream());

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
                if (receivedData == null) {
                    break;
                }
                handleCommandRequest(receivedData);
                System.out.println(receivedData);
            } catch (SocketException e) {
                System.err.println("Client hat Verbindung geschlossen");
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleCommandRequest(String receivedData) {
        if (receivedData == null) {
            System.err.println("Empfangene Daten sind null");
            return;
        }

        System.err.println("Vom Client empfangende Daten: " + receivedData);

        String[] parts = receivedData.split("separator");
        if (parts.length == 0) {
            System.err.println("Kein Befehl in den empfangenen Daten gefunden");
            return;
        }

        String command = parts[0];

        switch (command) {
            case "HALLO_SERVER":
                handleGibHalloServer();
                break;
            case "HALLO_CLIENT":
                handleGibHalloClient();
            case "CMD_GIB_ALLE_ARTIKEL":
                handleGibAlleArtikel();
                break;
            case "CMD_GIB_ALLE_KUNDEN":
                handleGibAlleKunden();
                break;
            case "CMD_GIB_ALLE_EREIGNISSE":
                handleGibAlleEreignisse();
                break;
            case "CMD_GIB_ALLE_MITARBEITER":
                handleGibAlleMitarbeiter();
                break;
            case "CMD_SPEICHER_HALLO_SERVER":
                handleHalloServerSpeichern();
                break;
            case "CMD_SPEICHER_HALLO_CLIENT":
                handleHalloClientSpeichern();
            case "CMD_SPEICHER_ARTIKEL":
                handleArtikelSpeichern();
                break;
            case "CMD_SPEICHER_MITARBEITER":
                handleMitarbeiterSpeichern();
                break;
            case "CMD_SPEICHER_KUNDEN":
                handleKundeSpeichern();
                break;
            case "CMD_SPEICHER_EREIGNISSE":
                handleEreignisSpeichern();
                break;
            default:
                System.err.println("Ungueltige Anfrage empfangen!");
                break;

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

        }
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
        String response = "Hallo, Client!";
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

        for (Mitarbeiter mitarbeiter : result){
            cmd += separator + mitarbeiter.getUserName();
            cmd += separator + mitarbeiter.getPasswort();
            cmd += separator + mitarbeiter.getNachname();
            cmd += separator + mitarbeiter.getVorname();
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
    /*private void handleWaremkorbErstellen(Kunde kunde) {
        eshop.neuenWarenkorbErstellen(kunde);
    }

     */
}




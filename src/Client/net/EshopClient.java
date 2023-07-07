package Client.net;

import Client.UI.KundenbereichGUI;
import Common.*;
import Common.Exceptions.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

public class EshopClient implements EShopInterface {
    
    private Socket socket = null;
    private BufferedReader in;
    private PrintStream out;
    private Component popup;

    public EshopClient(String host, int port) throws IOException {
        try {
            this.socket = new Socket(host, port); //Stelle Verbindung zum Server her
            InputStream is = this.socket.getInputStream();
            this.in = new BufferedReader(new InputStreamReader(is));
            this.out = new PrintStream(this.socket.getOutputStream());
        }catch (IOException var5) {
            JOptionPane successMessage = new JOptionPane();
            successMessage.getSize();
            JOptionPane.showMessageDialog(this.popup, "Verbindung zum Server ist fehlgeschlagen!", "Error", 0);
            if (this.socket != null) {
                this.socket.close();
            }
                    System.exit(0);
        }
        System.out.println("Verbindung hergestellt");
        KundenbereichGUI.status = 1;
        String nachricht = this.in.readLine();//Liest eine Nachricht vom Server
        System.out.println(nachricht);
    }

    public void disconnect() throws IOException {
        this.out.println("q");//Sende "q" zum Server, um die Verbindung  zu trennen
        String antwort = "Fehler";

        try {
            antwort = this.in.readLine();// antwort vom server lesen
        }catch (Exception var3) {
            System.err.println(var3.getMessage());
            return;
        }
        System.out.println(antwort);
    }

    @Override
    public List<Artikel> getAlleArtikel() {
        String cmd = Commands.CMD_GIB_ALLE_ARTIKEL.name();
        out.println(cmd);
        return null;
    }

    @Override
    public List<Mitarbeiter> getAlleMitarbeiter() {
        String cmd = Commands.CMD_GIB_ALLE_MITARBEITER.name();
        out.println(cmd);
        return null;
    }

    @Override
    public List<Kunde> getAlleKunden() {
        String cmd = Commands.CMD_GIB_ALLE_KUNDEN.name();
        out.println(cmd);
        return null;
    }

    @Override
    public List<Ereignis> getAlleEreignisse() {
        String cmd = Commands.CMD_GIB_ALLE_EREIGNISSE.name();
        out.println(cmd);
        return null;
    }

    @Override
    public void schreibeArtikel() throws IOException {
        String cmd = Commands.CMD_SPEICHER_ARTIKEL.name();
        out.println(cmd);
    }

    @Override
    public void schreibeMitarbeiter() throws IOException {
        String cmd = Commands.CMD_SPEICHER_MITAREBITER.name();
        out.println(cmd);
    }

    @Override
    public void schreibeKunde() throws IOException {
        String cmd = Commands.CMD_SPEICHER_KUNDEN.name();
        out.println(cmd);
    }

    @Override
    public void schreibeEreignis() throws IOException {
        String cmd = Commands.CMD_SPEICHER_EREIGNISSE.name();
        out.println(cmd);
    }

    @Override
    public Warenkorb neuenWarenkorbErstellen(Kunde k) {
        Warenkorb warenkorb = new Warenkorb();
        return warenkorb;
    }

    @Override
    public Mitarbeiter mitarbeiterRegistrieren(Mitarbeiter neu) throws UserExistiertBereitsException, LeeresTextfieldException {
        return null;
    }

    @Override
    public Mitarbeiter mitarbeiterLogin(String username, String passwort) throws LoginFehlgeschlagenException {
        return null;
    }

    @Override
    public void artHinzufügen(Artikel a, Mitarbeiter mitarbeiter) throws ArtikelExistiertBereitsException, LeeresTextfieldException {

    }

    @Override
    public void massengutArtikelHinzufügen(Massengutartikel a, Mitarbeiter mitarbeiter) throws ArtikelExistiertBereitsException, LeeresTextfieldException {

    }

    @Override
    public void bestandErhöhen(String artikelname, int menge, User u) throws ArtikelExistiertNichtException, LeeresTextfieldException {

    }

    @Override
    public void bestanNiedriger(String artikelname, int menge, User u) throws ArtikelExistiertNichtException, UngueltigeMengeException, LeeresTextfieldException {

    }

    @Override
    public List<Artikel> artikelSortierenNachBezeichnung() {
        return null;
    }

    @Override
    public List<Artikel> artikelNachArtikelnummerGeordnetAusgeben() {
        return null;
    }

    @Override
    public List<Ereignis> ereignisseNachDatum() {
        return null;
    }

    @Override
    public void inDenWarenkorbLegen(String artikel, int menge, Warenkorb warenkorb) throws ArtikelExistiertNichtException, UngueltigeMengeException {

    }

    @Override
    public void artikelAusWarenkorbEntfernen(String artikel, Warenkorb warenkorb) {

    }

    @Override
    public void warenkorbLeeren(Warenkorb warenkorb) {

    }

    @Override
    public Kunde kundenLogin(String username, String password) throws LoginFehlgeschlagenException {
        return null;
    }

    @Override
    public Kunde kundenRegistrieren(Kunde neu) throws UserExistiertBereitsException, LeeresTextfieldException {
        return null;
    }

    @Override
    public String kaufenUndRechnungEhalten(Kunde kunde, Warenkorb warenkorb) throws WarenkorbIstLeerException, IOException {
        return null;
    }
}

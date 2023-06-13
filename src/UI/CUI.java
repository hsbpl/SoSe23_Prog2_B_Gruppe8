
package UI;

import Domain.*;
import Exceptions.*;
import ValueObjekt.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class CUI {
    static Scanner scan = new Scanner(System.in);

    private EShop eshop;



    public CUI(String datei) throws IOException {
        eshop = new EShop(datei);
    }
    private void startMenue() {


        System.out.println("Was möchten Sie tun: ");
        System.out.println("Zum Kundenlogin        `1`");
        System.out.println("Zur Kundenregistrierung        `2`");
        System.out.println("Zum Mitarbeiterlogin        `3`");


        try{
            int input = scan.nextInt();
            startEingabenVerarbeiten(input);
        }
         catch (InputMismatchException e) {

            System.out.println(
                    "*********************************************************************************\n" +
                            "Ungültige Eingabe! Bitte geben Sie eine Zahl für Option '1', '2' oder '3' ein.\n" +
                            "*********************************************************************************\n");
            scan.nextLine();
            startMenue();
        }

    }

    private void startEingabenVerarbeiten(int choice) {

        switch (choice) {
            case 1:
                try {
                    System.out.println("Unsername:");
                    String username = scan.next();
                    System.out.println("Passwort:");
                    String pw = scan.next();
                    Kunde aktuellerKunde = eshop.kundenLogin(username, pw);
                    System.out.println(aktuellerKunde);
                    Warenkorb warenkorb = eshop.neuenWarenkorbErstellen(aktuellerKunde);
                    eshopMenue(aktuellerKunde, warenkorb);
                } catch (LoginFehlgeschlagenException e) {
                    System.out.println(
                            "*********************************************************************************\n" +
                                    "Username oder Passwort falsch. Bitte versuchen Sie es nochmal\n" +
                                    "*********************************************************************************\n");
                    startMenue();

                }

                break;

            case 2:
                try {
                    System.out.println("Unsername:");
                    String uname = scan.next();
                    System.out.println("Passwort:");
                    String pasw = scan.next();
                    System.out.println("Nachname:");
                    String nachname = scan.next();
                    System.out.println("Vorname:");
                    String vorname = scan.next();
                    System.out.println("Adressse:");
                    String adr = scan.next();
                    Kunde kunde = new Kunde(uname, pasw, nachname, vorname,adr);
                    System.out.println(eshop.kundenRegistrieren(kunde));
                    Warenkorb w = eshop.neuenWarenkorbErstellen(kunde);
                    eshopMenue(kunde, w);
                } catch (UserExistiertBereitsException e) {
                    System.out.println(
                            "*********************************************************************************\n" +
                                    "Dieses Konto Existiert bereits. Bitte versuchen Sie es nochmal.\n" +
                                    "*********************************************************************************\n");
                    startMenue();

                }
                break;

            case 3:
                try {
                    System.out.println("Unsername:");
                    String usname = scan.next();
                    System.out.println("Passwort:");
                    String paw = scan.next();
                    Mitarbeiter aktuellerMitarbeiter = eshop.mitarbeiterLogin(usname, paw);
                    System.out.println(aktuellerMitarbeiter);

                    artbeitsMenue(aktuellerMitarbeiter);
                } catch (LoginFehlgeschlagenException e) {
                    System.out.println(
                            "*********************************************************************************\n" +
                                    "Username oder Passwort falsch. Bitte versuchen Sie es nochmal\n" +
                                    "*********************************************************************************\n");
                    startMenue();

                }

                break;
            default:
                System.out.println(
                        "*********************************************************************************\n" +
                                "Ungültige Eingabe! Bitte wählen Sie zwischen Optionen '1', '2' oder '3'. \n" +
                                "*********************************************************************************\n");
                startMenue();
                break;
        }

    }


    private void eshopMenue(Kunde k, Warenkorb w) {
        System.out.println(eshop.artikelListen());
        System.out.println("---------------------");
        System.out.println("Was möchten Sie tun?" + "\n");
        System.out.println("\nArtikel in den Warenkorb legen oder" +
                "         \nMenge eines bereits im Warenkorb vorhandenen Artikels ändern:       '1'");
        System.out.println("\nWarenkorb leeren:       '2'");
        System.out.println("\nZum Warenkorb:       '3'");
        System.out.println("\nKauf abschließen:       '4'");
        System.out.println("---------------------");
        System.out.println("\nBeenden:       '0'");


        try {

               int input= scan.nextInt();
                eshopEingabenVerarbeiten(input, k, w);
        }
        catch (InputMismatchException e) {
            System.out.println(
                    "*********************************************************************************\n" +
                            "Ungültige Eingabe! Bitte geben Sie eine Zahl für Option '1', '2', '3','4' oder '0' ein.\n" +
                            "*********************************************************************************\n");
            scan.nextLine();
            eshopMenue(k,w);

        }

    }

    private void eshopEingabenVerarbeiten(int eingabe, Kunde k, Warenkorb w) {

        switch (eingabe) {
            case 1:
                try {
                    System.out.println("Tippen Sie den Namen des gewählten Artikels ein: ");
                    String gewaehlterArtikel = scan.next();
                    System.out.println("Menge des gewählten Artikels");
                    int menge = scan.nextInt();
                    eshop.inDenWarenkorbLegen(gewaehlterArtikel, menge, w);
                    System.out.println("Ihr Warenkorb: ");
                    System.out.println(eshop.artikelImWarenkorb(w));
                    System.out.println("---------------------");
                    eshopMenue(k, w);
                } catch (ArtikelExistiertNichtException e) {
                    System.out.println(
                            "*********************************************************************************\n" +
                                    "Der von Ihnen gewählte Artikel existiert nicht. Bitte versuchen Sie es nochmal.\n" +
                                    "*********************************************************************************\n");
                    eshopMenue(k, w);
                } catch (UngueltigeMengeException e) {
                    System.out.println(
                            "*********************************************************************************\n" +
                                    "Leider haben wir nicht genügend Artikel im Bestand. Bitte versuchen Sie es nochmal.\n" +
                                    "*********************************************************************************\n");
                    eshopMenue(k, w);
                }

                break;

            case 2:
                eshop.warenkorbLeeren(w);
                System.out.println("Warenkorb wurde geleert.");
                System.out.println("---------------------");
                eshopMenue(k, w);
                break;

            case 3:
                System.out.println("Ihr Warenkorb: ");
                System.out.println(eshop.artikelImWarenkorb(w));
                System.out.println("---------------------");
                eshopMenue(k, w);
                break;

            case 4:
                try
                {
                System.out.println("---------------------");
                System.out.println(eshop.kaufenUndRechnungEhalten(k, w));
                //System.out.println(eshop.ereignisListeAusgeben());
                System.out.println("---------------------");
                startMenue();}
                catch (WarenkorbIstLeerException e){
                    System.out.println("*********************************************************************************\n" +
                            "Ihr Warenkorb ist leer.\n" +
                            "*********************************************************************************\n");
                    eshopMenue(k, w);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;

            case 0:
                startMenue();
                break;
            default:
               System.out.println(
                        "*********************************************************************************\n" +
                                "Ungültige Eingabe! Bitte wählen Sie zwischen Option '1', '2', '3','4' oder '0'. \n" +
                                "*********************************************************************************\n");

                eshopMenue(k, w);


                break;
        }
    }


    public void artbeitsMenue(Mitarbeiter m) {

        System.out.println("         \n  Artikel ausgeben:       '1'");
        System.out.println("         \n  Artikel alphabetisch geordnet ausgeben:       '2'");
        System.out.println("         \n  Artikel nach Artikelnummer geordnet ausgeben:       '3'");
        System.out.println("         \n  Neuen Einzelartikel anlegen: '4'");
        System.out.println("         \n  Neuen Massengutartikel anlegen: '5'");

        System.out.println("         \n  Bestand erhöhen: '6'");
        System.out.println("         \n  Bestand verringern:   '7'");
        System.out.println("         \n  Ereignisliste ausgeben:   '8'");
        System.out.println("         \n  Neuen Mitarbeiter anlegen:   '9'");
        System.out.println("         \n  Daten sichern:   '10'");

        System.out.println("         \n  ---------------------------");
        System.out.println("       \n  Beenden:                '0'");

        try {
            int input = scan.nextInt();
            arbeitsEingabenVerarbeiten(input, m);

        } catch (InputMismatchException e) {

            System.out.println(
                    "*********************************************************************************\n" +
                            "Ungültige Eingabe! Bitte geben Sie eine Zahl für Option 1', '2', '3','4', '5', '6', '7' oder '0' ein.\n" +
                            "*********************************************************************************\n");
            scan.nextLine();
            artbeitsMenue(m);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    private void arbeitsEingabenVerarbeiten(int eingabe, Mitarbeiter m) throws IOException {


        switch (eingabe) {
            case 1:
                System.out.println(eshop.artikelListen());
                artbeitsMenue(m);
                break;

            case 2:
                eshop.artikelAlphabetischAusgeben();
                System.out.println(eshop.artikelListen());
                artbeitsMenue(m);
                break;

            case 3:
                System.out.println(eshop.artikelNachArtikelnummerGeordnetAusgeben());
                artbeitsMenue(m);
                break;

            case 4:
                try {
                    System.out.println("Artikelname: ");
                    String bezeichnung = scan.next();
                    System.out.println("Artikelnummer: ");
                    int artikelnummer = scan.nextInt();
                    System.out.println("Bestand: ");
                    int bestand = scan.nextInt();
                    System.out.println("Preis: ");
                    double preis = scan.nextDouble();
                    eshop.artHinzufügen(new Artikel(bezeichnung, artikelnummer, bestand, preis), m);
                    artbeitsMenue(m);
                } catch (ArtikelExistiertBereitsException e) {
                    System.out.println("*********************************************************************************\n" +
                            "Der von Ihnen gewählte Artikel existiert bereits. Bitte versuchen Sie es nochmal.\n" +
                            "*********************************************************************************\n");
                    artbeitsMenue(m);
                } catch (InputMismatchException e){
                    System.out.println(
                            "*********************************************************************************\n" +
                                    "Ungültige Eingabe!\n" +
                                    "Bei Eingabe der Artikelnummer bitte nur Zahlen verwenden.\n" +
                                    "Ungültige Eingabe! Bei Eingabe des Preises achten Sie darauf ein Komma zu verwenden.\n"+
                                    "Bitte versuchen Sie es nochmal.\n" +

                                    "*********************************************************************************\n");
                    scan.nextLine();
                    artbeitsMenue(m);
                }

                break;

            case 5:
                try {
                    System.out.println("Artikelname: ");
                    String bezeichnung = scan.next();
                    System.out.println("Artikelnummer: ");
                    int artikelnummer = scan.nextInt();
                    System.out.println("Bestand: ");
                    int bestand = scan.nextInt();
                    System.out.println("Preis: ");
                    double preis = scan.nextDouble();
                    System.out.println("Minimale Einkaufsmenge: ");
                    int zumKaufVerfügbar = scan.nextInt();
                    eshop.artHinzufügen(new Massengutartikel(bezeichnung, artikelnummer, bestand, preis, zumKaufVerfügbar), m);
                    artbeitsMenue(m);
                } catch (ArtikelExistiertBereitsException e) {
                    System.out.println("*********************************************************************************\n" +
                            "Der von Ihnen gewählte Artikel existiert bereits. Bitte versuchen Sie es nochmal.\n" +
                            "*********************************************************************************\n");
                    artbeitsMenue(m);
                } catch (InputMismatchException e){
                    System.out.println(
                            "*********************************************************************************\n" +
                                    "Ungültige Eingabe!\n" +
                                    "Bei Eingabe der Artikelnummer bitte nur Zahlen verwenden.\n" +
                                    "Ungültige Eingabe! Bei Eingabe des Preises achten Sie darauf ein Komma zu verwenden.\n"+
                                    "Bitte versuchen Sie es nochmal.\n" +

                                    "*********************************************************************************\n");
                    scan.nextLine();
                    artbeitsMenue(m);
                }

                break;
            case 6:
              try {
                    System.out.println("Artikelname:");
                    String artikelname = scan.next();
                    System.out.println("Zu erhöhende Menge: ");
                    int menge = scan.nextInt();
                    eshop.bestandErhöhen(artikelname, menge, m);
                    System.out.println(eshop.ereignisListeAusgeben());
                    artbeitsMenue(m);
                } catch (ArtikelExistiertNichtException e) {
                    System.out.println("*********************************************************************************\n" +
                            "Der von Ihnen gewählte Artikel existiert nicht. Bitte versuchen Sie es nochmal.\n" +
                            "*********************************************************************************\n");


                    artbeitsMenue(m);
                }

                break;

            case 7:
                try {
                    System.out.println("Artikelname:");
                    String artikelbez = scan.next();
                    System.out.println("Zu verringernde Menge: ");
                    int me = scan.nextInt();
                    eshop.bestanNiedriger(artikelbez, me, m);
                    System.out.println(eshop.ereignisListeAusgeben());
                    artbeitsMenue(m);
                } catch (ArtikelExistiertNichtException e) {
                    System.out.println("*********************************************************************************\n" +
                            "Der von Ihnen gewählte Artikel existiert nicht. Bitte versuchen Sie es nochmal.\n" +
                            "*********************************************************************************\n");
                    artbeitsMenue(m);
                } catch (UngueltigeMengeException u) {
                    System.out.println("*********************************************************************************\n" +
                            "Die von Ihnen gewählte Menge ist zu höher als die Bestandsmenge. Bitte versuchen Sie es nochmal.\n" +
                            "*********************************************************************************\n");
                    artbeitsMenue(m);
                }
                break;

            case 8:
                System.out.println(eshop.ereignisseNachDatum());
                break;
            case 9:
                try {
                    System.out.println("Unsername:");
                    String username = scan.next();
                    System.out.println("Passwort:");
                    String pw = scan.next();
                    System.out.println("Nachname:");
                    String nachname = scan.next();
                    System.out.println("Vorname:");
                    String vorname = scan.next();
                    System.out.println("Mitarbeiternummer:");
                    int id = scan.nextInt();
                    Mitarbeiter neuerMitarbeiter = new Mitarbeiter(username, pw, nachname, vorname);
                    System.out.println(eshop.mitarbeiterRegistrieren(neuerMitarbeiter));
                    artbeitsMenue(m);
                } catch (UserExistiertBereitsException e) {
                    System.out.println(
                            "*********************************************************************************\n" +
                                    "Dieses Konto Existiert bereits. Bitte versuchen Sie es nochmal.\n" +
                                    "*********************************************************************************\n");
                    artbeitsMenue(m);

                }
                break;
            case 10:
                eshop.schreibeArtikel();
                eshop.schreibeEreignis();
                eshop.schreibeKunde();
                eshop.schreibeMitarbeiter();
                break;

            case 0:
                startMenue();
                break;

            default:
                System.out.println(
                        "*********************************************************************************\n" +
                                "Ungültige Eingabe! Bitte wählen Sie zwischen Option '1', '2', '3','4', '5', '6', '7' oder '0'. \n" +
                                "*********************************************************************************\n");
                artbeitsMenue(m);


            break;
        }
    }

    public static void main(String[] args) {

        CUI cui;

        try {
            cui = new CUI("ESHOP");
            cui.startMenue();


        }catch(IOException e){
            e.printStackTrace();
        }

        //Kunde k1 = new Kunde("k1", "abc", "Mann", "Thomas", 001, "Am Berg");
        //eshop.neuenWarenkorbErstellen(k1);

        //Vor dem Start des Menüs die Daten laden
      //  eshop.loadData();


        //Nach dem Beenden des Menüs werden die Daten gespeichert
      //  eshop.saveData();


    }
}


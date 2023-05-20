
        package CUI;
import Domain.*;
import ValueObjekt.Artikel;
import ValueObjekt.Kunde;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import static sun.security.util.Debug.args;


        public class Main {
            Scanner scan = new Scanner(System.in);
            EShop shop = new EShop();

            List<Artikel> bestandaliste = shop.getAlleArtikel();
            List<Kunde> registrierteKunden = shop.getAlleKundenkonten();

            private void start() {

                //je nachdem ein anderes Menü
                int choice;
                System.out.println("Wählen Sie eine Option");
                System.out.println("1. Ich bin ein Kunde.");
                System.out.println("2. Ich bin ein Mitarbeiter.");


                if (scan.nextInt() == 1) {
                    System.out.println("1. Einloggen");
                    System.out.println("2. Registrieren");
                    if (scan.nextInt() == 1) {
                        System.out.println("Unsername:");
                        String username = scan.next();
                        System.out.println("Passwort:");
                        String pw = scan.next();
                        Kunde aktuellerKunde = shop.kundenlogin(username, pw);
                        System.out.println(aktuellerKunde);
                        kaufen(aktuellerKunde);

                    } else if (scan.nextInt() == 2) {
                        System.out.println("Unsername:");
                        String username = scan.next();
                        System.out.println("Passwort:");
                        String pw = scan.next();
                        System.out.println("Nachname:");
                        String nachname = scan.next();
                        System.out.println("Vorname:");
                        String vorname = scan.next();
                        System.out.println("Adressse:");
                        String adr = scan.next();
                        Kunde kunde = new Kunde(username, pw, nachname, vorname, adr);
                        System.out.println(shop.kundenregister(kunde));
                        System.out.println(kunde);

                        kaufen(kunde);
                    }


                } else if (scan.nextInt() == 2) {
                    System.out.println("Unsername:");
                    String username = scan.next();
                    System.out.println("Passwort:");
                    String pw = scan.next();
                    System.out.println(shop.mitarbeiterEinloggen(username, pw)); // möglich sobald Mitarbeiter im Eshop ist

                    arbeiten();
                }
            }

                    private void kaufen(Kunde k){
                        shop.artikelListen();

                        System.out.println("---------------------");
                        System.out.println("Was möchten Sie tun!"+"\n");
                        System.out.println("Artikel in den Warenkorb legen: 1");
                        System.out.println("Artikel aus dem Warenkorb entfernen: 2");
                        System.out.println("Warenkorb leeren: 3");
                        System.out.println("Zum Warenkorb: 4");
                        System.out.println("Kauf abschließen: 5");
                        System.out.println("---------------------");
                        System.out.println("Beenden: '6'");

                        kundenEingabe(scan.nextInt(), k);
                        }
                    private void kundenEingabe(int eingabe, Kunde k) {
                            List<Artikel> warenkorb = shop.meinWarenkorb();

                        switch (eingabe){
                            case 1:
                                System.out.println("Tippen Sie den Namen des gewählten Artikels ein: ");
                                Artikel gewaehlterArtikel = shop.artikelAuswaehlen(scan.next());
                                System.out.println("Menge des gewählten Artikels");
                                int menge = scan.nextInt();
                                shop.reinWarenkorb(bestandaliste, gewaehlterArtikel, menge);
                                System.out.println("Ihr Warenkorb: ");
                                System.out.println(shop.einkaufsliste());
                                kaufen(k);

                            case 2:
                                System.out.println("Ihr Warenkorb: ");
                                System.out.println(shop.einkaufsliste());
                                System.out.println("Tippen Sie den Namen des gewählten Artikels ein: ");
                                Artikel gewArtikel = shop.artikelAuswaehlen(scan.next());
                                System.out.println("Menge des gewählten Artikels");
                                int m = scan.nextInt();
                                shop.rausWarenkorb(gewArtikel, m);
                                System.out.println(shop.einkaufsliste());
                                kaufen(k);

                            case 3:
                                shop.warenkorbLeeren();
                                System.out.println("Warenkorb wurde geleert.");
                                kaufen(k);

                            case 4:
                                System.out.println("Ihr Warenkorb: ");
                                System.out.println(shop.einkaufsliste());
                                kaufen(k);

                            case 5:
                               System.out.println(shop.kaufen(warenkorb,k));
                               start();

                            case 6:
                                start();

                    }

                        private void arbeiten(){
                            System.out.println("\n Befehle:");
                            System.out.println("Artikel ausgeben: '1'");
                            System.out.println("Artikel löschen: '2'");
                            System.out.println("Artikel einfügen: '3'");
                            System.out.println("Artikel suchen: '4'");
                            System.out.println("Warenkorb anzeigen: '5'");
                            System.out.println("Warenkorb leeren: '6'");
                            System.out.println("---------------------");
                            System.out.println("Beenden: 'x'");
                            System.out.print("> "); // Prompt
                            System.out.flush(); // ohne new line ausgeben*/
                        }


                        private String liesEingabe() throws IOException {
                            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                            return br.readLine();
                        }
                        private void verarbeiteEingabe(String input) {
                            switch (input) {
                                case "a":
                                    System.out.println(this.EShop.getArtikelListe());
                                    break;

                                case "b":
                                    System.out.print("Artikelnummer: ");
                                    try {
                                        int artikelnummer = Integer.parseInt(liesEingabe());
                                        this.EShop.removeArtikel(artikelnummer);
                                        System.out.println("Artikel gelöscht.");
                                    } catch (NumberFormatException e) {
                                        System.out.println("Ungültige Eingabe!");
                                    }
                                    break;
                                case "c":
                                    System.out.print("Artikelname: ");
                                    String name = "";
                                    try {
                                        name = liesEingabe();
                                    } catch (IOException e1) {
                                        System.out.println("Fehler beim Einlesen des Namens!");
                                        break;
                                    }
                                    System.out.print("Artikelnummer: ");
                                    int artikelnummer = 0;
                                    try {
                                        artikelnummer = Integer.parseInt(liesEingabe());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Ungültige Eingabe!");
                                        break;
                                    }
                                    System.out.print("Preis: ");
                                    double preis = 0;
                                    try {
                                        preis = Double.parseDouble(liesEingabe());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Ungültige Eingabe!");
                                        break;
                                    }
                                    Artikel artikel = new Artikel(, artikelnummer, preis);
                                    this.EShop.addArtikel(artikel);
                                    System.out.println("Artikel hinzugefügt.");
                                    break;
                                case "d":
                                    System.out.print("Suchbegriff: ");
                                    String suchbegriff = "";
                                    try {
                                        suchbegriff = liesEingabe();
                                    } catch (IOException e1) {
                                        System.out.println("Fehler beim Einlesen des Suchbegriffs!");
                                        break;
                                    }
                                    System.out.println(this.EShop.getWarenkorb());
                                    break;
                                case "e":
                                    System.out.println(this.EShop.getWarenkorb());
                                    break;
                                case "f":
                                    this.EShop.leereWarenkorb();
                                    System.out.println("Warenkorb geleert.");
                                    break;
                                case "x":
                            }
                        }


                        public static void main(String[]args){
                        start();
                        }
                    }




}


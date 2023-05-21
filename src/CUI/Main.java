
        package CUI;
import Domain.*;
import ValueObjekt.Artikel;
import ValueObjekt.Kunde;
import ValueObjekt.Mitarbeiter;
import ValueObjekt.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import static sun.security.util.Debug.args;


        public class Main {
            Scanner scan = new Scanner(System.in);
            EShop shop = new EShop();
            private Mitarbeiterverwaltung mv;
            private BufferedReader input;
            private String eingabe = "";
            boolean ersteLogin = true;

            Main(String datei) throws IOException {
                mv = new EShop(datei);
                input = new BufferedReader(new InputStreamReader(System.in));
            }

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

            private void kaufen(Kunde k) {
                shop.artikelListen();

                System.out.println("---------------------");
                System.out.println("Was möchten Sie tun!" + "\n");
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

                switch (eingabe) {
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
                        System.out.println(shop.kaufen(warenkorb, k));
                        start();

                    case 6:
                        start();


                        // Einloggen Methode
                        public void einloggen(String benutzername, String passwort, Mitarbeiter Mitarbeiter) throws IOException {
                        User user = mv.einloggen(benutzername, passwort);
                        if (mv.istEinloggen()) {
                            if (mv.istMitarbeiter) {
                                if (ersteLogin) {
                                    System.out.println("Login als Mitarbeiter erfolgreich");
                                }
                                mitarbeiterMenuAnzeingen();
                                Mitarbeiter mitarbeiter;
                                eingabe = liesEingabe();
                                startMitarbeiterOperation(benutzername, eingabe);
                            } else {
                            System.out.println("Benutzername oder Passwort falsch !!!");
                        }
                    }

                        // Mitarbeiter auswahloptionen
                        public void mitarbeiterMenuAnzeingen() {
                        System.out.print("Befehle: \n  Artikel ausgeben:       'a'");
                        System.out.print("         \n  Neuer Artikel anlegen: 'b'");
                        System.out.print("         \n  Artikel bestand erhöhen: 'f'");
                        System.out.print("         \n  Neuer Mitarbeiter einlegen:   'e'");
                        System.out.print("         \n  ---------------------------");
                        System.out.println("       \n  Beenden:                'q'");
                        System.out.print("> ");
                        System.out.flush();
                    }   System.out.print("> "); // Prompt
                        System.out.flush(); // ohne new line ausgeben*/
                    }


                    private String liesEingabe() throws IOException {
                        return input.readLine();
                    }
                    public Mitarbeiter neuerMitarbeiter() {
                        String benutzername = null;
                        String passwort = null;
                        int nummer = 0;
                        try {
                            System.out.print("Benutzername des Mitarbeiters eingeben: ");
                            benutzername = liesEingabe();
                            System.out.print("Passwort des Mitarbeiters eingeben: ");
                            passwort = liesEingabe();
                            System.out.print("Nummer des Mitarbeiters eingeben: ");
                            nummer = (int) scan.nextDouble();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Mitarbeiter mitarbeiter = new Mitarbeiter(nummer, benutzername, passwort);
                        return mitarbeiter;
                    }

                    // Mitarbeiter Methoden auslisten
                    public void startMitarbeiterOperation(Benutzer benutzer, String operation) throws IOException {
                        switch (operation) {
                            // Artikel ausgeben
                            case "a":
                                artikelListAnzeigen();
                                break;
                            // neuer Artikel anlegen
                            case "b":
                                Artikel artikel = neueArtikel();
                                if (artikel != null) {
                                    vw.getStock().artikelHinzufuegen(artikel, 1);
                                }
                                break;
                            // Artikel Bestand erhöhen
                            case "f":
                                vw.artikelBestandErhoehen(artikelAusgeben(), 1);
                                break;
                            // neue Mitarbeiter einlegen
                            case "e":
                                Mitarbeiter mitarbeiter = neuerMitarbeiter();
                                if (mitarbeiter != null) {
                                    vw.mitarbeiterRegistrieren(mitarbeiter);
                                }
                                break;
                            default:
                                vw.schreibeDatei();
                                eingabe = "q";
                        }
                    }

                    private String liesEingabe () throws IOException {
                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                        return br.readLine();
                    }
                    private void verarbeiteEingabe (String input){
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


                    public static void Main (String[]args) throws IOException {
                        Main mainShop;
                        mainShop = new Main();

                        try {
                            mainShop.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }
            }
        }


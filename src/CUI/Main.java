
        package CUI;
import Domain.*;
import ValueObjekt.Artikel;
import ValueObjekt.Kunde;
import ValueObjekt.Mitarbeiter;
import ValueObjekt.Warenkorb;

import java.util.Scanner;




        public class Main {
            static Scanner scan = new Scanner(System.in);
            static EShop eshop = new EShop();


            private static void start() {

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
                        Kunde aktuellerKunde = eshop.kundenLogin(username, pw);
                        Warenkorb warenkorb = eshop.neuenWarenkorbErstellen(aktuellerKunde);
                        System.out.println(aktuellerKunde);


                        kaufen(aktuellerKunde, warenkorb);

                    } else if (scan.nextInt() == 2) {
                        System.out.println("Unsername:");
                        String username = scan.next();

                        System.out.println("Passwort:");
                        String pw = scan.next();

                        System.out.println("Nachname:");
                        String nachname = scan.next();

                        System.out.println("Vorname:");
                        String vorname = scan.next();

                        System.out.println("id: ");
                        int id = scan.nextInt();

                        System.out.println("Adressse:");
                        String adr = scan.next();

                        Kunde kunde = new Kunde(username, pw, nachname, vorname,id, adr);
                        Warenkorb warenkorb = eshop.neuenWarenkorbErstellen(kunde);
                        System.out.println(eshop.kundenRegistrieren(kunde));
                        System.out.println(kunde);

                        kaufen(kunde, warenkorb);
                    }


                } else if (scan.nextInt() == 2) {
                    System.out.println("Unsername:");
                    String username = scan.next();
                    System.out.println("Passwort:");
                    String pw = scan.next();
                    Mitarbeiter aktuellerMitarbeiter = eshop.mitarbeiterLogin(username, pw);
                    System.out.println(aktuellerMitarbeiter);

                    artbeitsMenue(aktuellerMitarbeiter);
                }
            }

            private static void kaufen(Kunde k, Warenkorb w) {
                System.out.println(eshop.artikelListen());

                System.out.println("---------------------");
                System.out.println("Was möchten Sie tun?" + "\n");
                System.out.println("Artikel in den Warenkorb legen: 1");
                System.out.println("Artikel aus dem Warenkorb entfernen: 2");
                System.out.println("Warenkorb leeren: 3");
                System.out.println("Zum Warenkorb: 4");
                System.out.println("Kauf abschließen: 5");
                System.out.println("---------------------");
                System.out.println("Beenden: '6'");

                kundenEingabe(scan.nextInt(), k, w);
            }

            private static void kundenEingabe(int eingabe, Kunde k, Warenkorb w) {

                switch (eingabe) {
                    case 1:
                        System.out.println("Tippen Sie den Namen des gewählten Artikels ein: ");
                        String gewaehlterArtikel = scan.next();
                        System.out.println("Menge des gewählten Artikels");
                        int menge = scan.nextInt();
                        eshop.inDenWarenkorbLegen(gewaehlterArtikel, menge, w);
                        System.out.println("Ihr Warenkorb: ");
                        System.out.println(eshop.einkaufsliste(w));
                        System.out.println("---------------------");
                        kaufen(k,w);
                        break;

                    case 2:
                        warenkorbAusgeben(k,w);
                        break;

                    case 3:
                        eshop.warenkorbLeeren(w);
                        System.out.println("Warenkorb wurde geleert.");
                        System.out.println("---------------------");
                        kaufen(k,w);
                        break;

                    case 4:
                        System.out.println("Ihr Warenkorb: ");
                        System.out.println(eshop.einkaufsliste(w));
                        System.out.println("---------------------");
                        kaufen(k,w);
                        break;

                    case 5:
                        System.out.println(eshop.kaufen(k,w));
                        eshop.kaufenUndWarenkorbLeeren(w);
                        System.out.println(eshop.ereignisListeAusgeben());
                        System.out.println("---------------------");
                        start();
                        break;

                    case 6:
                        start();
                        break;

                }
            }

            private static void warenkorbAusgeben(Kunde k, Warenkorb w) {
                System.out.println("Ihr Warenkorb: ");
                System.out.println(eshop.einkaufsliste(w));
                System.out.println("Tippen Sie den Namen des gewählten Artikels ein: ");
                String  gewArtikel = scan.next();
                System.out.println("Menge des gewählten Artikels");
                int m = scan.nextInt();
                eshop.ArtikelAusdemWarenkorbNehmen(gewArtikel, m, w);
                System.out.println("Ihr Warenkorb: ");
                System.out.println(eshop.einkaufsliste(w));
                System.out.println("---------------------");
                kaufen(k,w);
            }

            public static void artbeitsMenue(Mitarbeiter m) {

                System.out.println("         \n  Artikel ausgeben:       '1'");
                System.out.println("         \n  Artikel alphabetisch geordnet ausgeben:       '2 '");
                System.out.println("         \n  Artikel nach Artikelnummer geordnet ausgeben:       '3'");
                System.out.println("         \n  Neuen Artikel anlegen: '4'");
                System.out.println("         \n  Bestand erhöhen: '5'");
                System.out.println("         \n  Bestand verringern:   '6'");
                System.out.println("         \n  Neuen Mitarbeiter anlegen:   '7'");
                System.out.println("         \n  ---------------------------");
                System.out.println("       \n  Beenden:                '8'");

                arbeiten(scan.nextInt(),m);
            }



            private static void arbeiten(int eingabe, Mitarbeiter m) {


                switch (eingabe) {
                    case 1:
                        System.out.println(eshop.artikelListen());
                        artbeitsMenue(m);
                        break;

                    case 2:
                        eshop.alphaArtikel();
                        System.out.println(eshop.artikelListen());
                        artbeitsMenue(m);
                        break;

                    case 3:
                        System.out.println(eshop.nummerArtikel());
                        artbeitsMenue(m);
                        break;

                    case 4:
                        System.out.println("Artikelname: ");
                        String bezeichnung = scan.next();
                        System.out.println("Artikelnummer: ");
                        int artikelnummer = scan.nextInt();
                        System.out.println("Bestand: ");
                        int bestand = scan.nextInt();
                        System.out.println("Preis: ");
                        double preis = scan.nextDouble();
                        System.out.println("Verfügbar: ");
                        boolean verfügbarkeit = true;
                        eshop.artHinzufügen(new Artikel(bezeichnung,artikelnummer,bestand,preis,verfügbarkeit));
                        artbeitsMenue(m);
                        break;

                    case 5:

                        System.out.println("Artikelname:");
                        String artikelname =scan.next();
                        System.out.println("Zu erhöhende Menge: ");
                        int menge = scan.nextInt();
                        eshop.bestandHöher(artikelname, menge, m);
                        System.out.println(eshop.ereignisListeAusgeben());
                        artbeitsMenue(m);
                        break;

                    case 6:
                        System.out.println("Artikelname:");
                        String artikelbez =scan.next();
                        System.out.println("Zu verringernde Menge: ");
                        int me = scan.nextInt();
                        eshop.bestanNiedriger(artikelbez, me, m);
                        System.out.println(eshop.ereignisListeAusgeben());
                        artbeitsMenue(m);
                        break;

                    case 7:
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
                        Mitarbeiter neuerMitarbeiter = new Mitarbeiter(username, pw, nachname, vorname, id);
                        System.out.println(eshop.mitarbeiterRegistrieren(neuerMitarbeiter));
                        artbeitsMenue(m);
                        break;

                    case 8:
                        start();
                        break;

                }
            }

            public static void main(String[] args){
                start();



                /* System.out.println(eshop.artikelListen());
        eshop.bestandHöher("Chips", 200, eshop.mitarbeiterLogin("m1", "123"));
        System.out.println(eshop.artikelListen());
        System.out.println(eshop.ereignisListeAusgeben());

        */
                //System.out.println(eshop.nummerArtikel());

                //System.out.println(eshop.mitarbeiterLogin("m1", "123"));

                //System.out.println(eshop.kundenlogin("k1", "abc"));

            }
        }



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


            private static void startMenue() {


                System.out.println("Was möchten Sie tun: ");
                System.out.println("Zum Kundenlogin        `1`");
                System.out.println("Zur Kundenregistrierung        `2`");
                System.out.println("Zum Mitarbeiterlogin        `3`");

                startEingabenVerarbeiten(scan.nextInt());
            }

            private static void startEingabenVerarbeiten(int choice){

            switch (choice){
                    case 1:
                        System.out.println("Unsername:");
                        String username = scan.next();
                        System.out.println("Passwort:");
                        String pw = scan.next();
                        Kunde aktuellerKunde = eshop.kundenLogin(username, pw);
                        Warenkorb warenkorb = eshop.neuenWarenkorbErstellen(aktuellerKunde);
                        System.out.println(aktuellerKunde);
                        eshopMenue(aktuellerKunde, warenkorb);

                        break;

                    case 2:
                        System.out.println("Unsername:");
                        String uname = scan.next();
                        System.out.println("Passwort:");
                        String pasw = scan.next();
                        System.out.println("Nachname:");
                        String nachname = scan.next();
                        System.out.println("Vorname:");
                        String vorname = scan.next();
                        System.out.println("id: ");
                        int id = scan.nextInt();
                        System.out.println("Adressse:");
                        String adr = scan.next();
                        Kunde kunde = new Kunde(uname, pasw, nachname, vorname,id, adr);
                       // Warenkorb w = eshop.neuenWarenkorbErstellen(kunde);
                        System.out.println(eshop.kundenRegistrieren(kunde));
                        Warenkorb w = eshop.neuenWarenkorbErstellen(kunde);
                        eshopMenue(kunde, w);
                        break;

                    case 3:
                        System.out.println("Unsername:");
                        String usname = scan.next();
                        System.out.println("Passwort:");
                        String paw = scan.next();
                        Mitarbeiter aktuellerMitarbeiter = eshop.mitarbeiterLogin(usname, paw);
                        System.out.println(aktuellerMitarbeiter);

                        artbeitsMenue(aktuellerMitarbeiter);

                        break;

                }

            }


            private static void eshopMenue(Kunde k, Warenkorb w) {
                System.out.println(eshop.artikelListen());

                System.out.println("---------------------");
                System.out.println("Was möchten Sie tun?" + "\n");
                System.out.println("Artikel in den Warenkorb legen oder" +
                        "         \nMenge eines bereits vorhandenen Artikels ändern:       '1'");
                System.out.println("Warenkorb leeren:       '2'");
                System.out.println("Zum Warenkorb:       '3'");
                System.out.println("Kauf abschließen:       '4'");
                System.out.println("---------------------");
                System.out.println("Beenden:       '0'");

                eshopEingabenVerarbeiten(scan.nextInt(), k, w);
            }

            private static void eshopEingabenVerarbeiten(int eingabe, Kunde k, Warenkorb w) {

                switch (eingabe) {
                    case 1: //funktioniert
                        System.out.println("Tippen Sie den Namen des gewählten Artikels ein: ");
                        String gewaehlterArtikel = scan.next();
                        System.out.println("Menge des gewählten Artikels");
                        int menge = scan.nextInt();
                        eshop.inDenWarenkorbLegen(gewaehlterArtikel, menge, w);
                        System.out.println("Ihr Warenkorb: ");
                        System.out.println(eshop.artikelImWarenkorb(w));
                        System.out.println("---------------------");
                        eshopMenue(k,w);
                        break;

                    case 2:
                        eshop.warenkorbLeeren(w); //funktioniert
                        System.out.println("Warenkorb wurde geleert.");
                        System.out.println("---------------------");
                        eshopMenue(k,w);
                        break;

                    case 3: //funktioniert
                        System.out.println("Ihr Warenkorb: ");
                        System.out.println(eshop.artikelImWarenkorb(w));
                        System.out.println("---------------------");
                        eshopMenue(k,w);
                        break;

                    case 4: // Dadurch das die toString Methode nicht funktioniert unklar ob es funktioniert
                        System.out.println(eshop.kaufenUndRechnungEhalten(k,w));
                        //eshop.kaufenUndWarenkorbLeeren(w);
                        System.out.println(eshop.ereignisListeAusgeben());
                        System.out.println("---------------------");
                        startMenue();
                        break;

                    case 0:
                        startMenue();
                        break;

                }
            }


            public static void artbeitsMenue(Mitarbeiter m) {

                System.out.println("         \n  Artikel ausgeben:       '1'");
                System.out.println("         \n  Artikel alphabetisch geordnet ausgeben:       '2'");
                System.out.println("         \n  Artikel nach Artikelnummer geordnet ausgeben:       '3'");
                System.out.println("         \n  Neuen Artikel anlegen: '4'");
                System.out.println("         \n  Bestand erhöhen: '5'");
                System.out.println("         \n  Bestand verringern:   '6'");
                System.out.println("         \n  Neuen Mitarbeiter anlegen:   '7'");
                System.out.println("         \n  ---------------------------");
                System.out.println("       \n  Beenden:                '0'");

                arbeitsEingabenVerarbeiten(scan.nextInt(),m);
            }



            private static void arbeitsEingabenVerarbeiten(int eingabe, Mitarbeiter m) {


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

                    case 0:
                        startMenue();
                        break;

                }
            }

            public static void main(String[] args){
                Kunde k1 = new Kunde("k1", "abc", "Mann", "Thomas",001, "Am Berg");
                eshop.neuenWarenkorbErstellen(k1);
                startMenue();


            }
        }


package UI;

import Domain.Artikelverwaltung;
import Domain.Kundenverwaltung;
import Domain.Mitarbeiterverwaltung;
import ValueObjekt.Artikel;
import ValueObjekt.Kunde;
import ValueObjekt.Mitarbeiter;
import ValueObjekt.User;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class artikelverwaltung {

    private Artikelverwaltung artikelverwaltung;


    public void start() {
        // ToDo: gehört nicht in ui diese klasse (sondern domain)
        artikelverwaltung = new Artikelverwaltung();
//hier musste die Variable 'Artikelverwaltung artikelverwaltung;' noch initialisiert werden das musste ich hier im start hinzufügen.

        Artikel cola = new Artikel("Coca Cola 1L", 17890, 40, 2, true);
        Artikel kuchen = new Artikel("Käsekuchen", 19002, 12, 4.99, true);
        Artikel chips = new Artikel("Chips", 39003, 100, 1.79, true);
        Artikel wasser = new Artikel("Wasser)", 3890, 400, 0.49, true);
        Artikel mehl = new Artikel("Mehl", 29290, 0, 0.39, false);

        Kunde k1 = new Kunde("k1", "123", "Mann", "Thomas", "Am Berg");
        Mitarbeiter m1 = new Mitarbeiter("mit1", "345", "Mitarbeiterin", "Dieerste", 1829);

        List<Artikel> waren = new ArrayList<>(Arrays.asList(cola, kuchen, chips, wasser, mehl));
        List<Artikel> warenkorb = new ArrayList<>();

        Artikelverwaltung av = new Artikelverwaltung();
        Kundenverwaltung kv = new Kundenverwaltung(warenkorb);
        Mitarbeiterverwaltung mv = new Mitarbeiterverwaltung();

        kv.getKRegistrierung().add(k1);

        Scanner scanner = new Scanner(System.in);
        int m = 2;
        int k = 1;
        System.out.println("Drücke 1 wenn du ein Kunde bist oder 2 Wenn du ein Mitabeiter bist:");

        if (scanner.nextInt() != 1 || scanner.nextInt() != 2) {
            System.out.println("Falsche Eingabe");
        } else if (scanner.nextInt() == k) {
            int einloggen = 1;
            int registrieren = 2;
            System.out.println("Drücke 1 wenn dich einloggen willst oder 2 Wenn dich registrieren möchtest: ");
            if (scanner.nextInt() == einloggen) {
                String username;
                String passwort;

                System.out.println("Bitte loggen Sie sich ein:" + "\n" + "Username: ");
                username = scanner.nextLine();

                System.out.println("Passwort: ");
                passwort = scanner.nextLine();

                kv.login(username, passwort);
            } else if (scanner.nextInt() == registrieren) {

                String username;
                String passwort;
                String nachname;
                String vorname;
                System.out.println("Bitte registrieren Sie sich " + "\n");

                System.out.println("Passwort: ");
                username = scanner.nextLine();

                System.out.println("Username: ");
                passwort = scanner.nextLine();

                System.out.println("Nachnahme: ");
                nachname = scanner.nextLine();

                System.out.println("Vorname: ");
                vorname = scanner.nextLine();

                kv.register(new User(username, passwort, nachname, vorname));

                for (User u : kv.getKRegistrierung()) {
                    System.out.println(u.getUserName() + " " + u.getVorname() + " " + u.getNachname());
                }
            }

        } else if (scanner.nextInt() == m) {
            System.out.println("Logge dich ein: ");

            String username;
            String passwort;

            System.out.println("Bitte loggen Sie sich ein:" + "\n" + "Username: ");
            username = scanner.nextLine();

            System.out.println("Passwort: ");
            passwort = scanner.nextLine();
            //noch nicht definiert
            //mv.login(username, passwort);

            // noch nicht fertig, wird noch weitergeführt
        }


// start würde nach der mitarbeiter Reg packen

        int auswahl = 0;

        while (auswahl != 6) {
            System.out.println("Artikelverwaltung");
            System.out.println("1. Artikel hinzufügen");
            System.out.println("2. Artikel löschen");
            System.out.println("3. Artikel ausgeben");
            System.out.println("4. Nach Beziehung sortieren");
            System.out.println("5. Nach Artikelnummer sortieren");
            System.out.println("6. Beenden");
            System.out.println("Auswahl: ");
            auswahl = scanner.nextInt();

            switch (auswahl) {
                case 1:
                    //z.B. chips werden hinzugefügt
                    av.artikelHinzufuegen(chips);
                    break;
                case 2:
                    artikelLoeschen(scanner);
                    break;
                case 3:
                    av.artikelAusgeben();
                    break;
                case 4:
                    av.artikelSortierenNachBezeichnung();
                    break;
                case 5:
                    av.artikelSortierenNachArtikelnummer();
                    break;
                case 6:
                    System.out.println("Programm wird beendet.");
                    break;
                default:
                    System.out.println("Ungültige Auswahl.");
                    break;
            }


        }
    }
//fertig
    private void artikelHinzufuegen(Scanner scanner){
        System.out.println("Bezeichnung: ");
        String bezeichnung = scanner.next();
        System.out.println("Artikelnummer: ");
        int artikelnummer = scanner.nextInt();
        System.out.println("Bestand: ");
        int bestand = scanner.nextInt();

        Artikel artikel = new Artikel(bezeichnung, artikelnummer);
        artikelverwaltung.artikelHinzufuegen(artikel);
        System.out.println("Artikel wurde hinzugefügt.");
    }

    // private void loeschen behalte ich erstmal so lange bis ich in artikelverwaltung artikelloeschen erstellt habe.
    private void artikelLoeschen(Scanner scanner) {
        System.out.println("Artikelnummer: ");
        int artikelnummer = scanner.nextInt();
        artikelverwaltung.artikelLoeschen(artikelnummer);
    }
}

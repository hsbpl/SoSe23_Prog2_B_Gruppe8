
        package CUI;
import Domain.*;
import ValueObjekt.Artikel;
import ValueObjekt.RechnungObjekt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



        public class Main {
            //fast fertig
                public static void main(String[]args){
                    //gekaufte Artikel erstellen
                    Artikel artikel1 = new Artikel("Artikel 1", 2, 10.0);
                    Artikel artikel2 = new Artikel("Artikel 2", 3, 15.0);

                    // eine Liste für gekaufte Artikel Erstellen
                    List<Artikel> gekaufteArtikel = new ArrayList<>();
                    gekaufteArtikel.add(artikel1);
                    gekaufteArtikel.add(artikel2);

                // Erzeugen des Rechnungsobjekts
                RechnungObjekt rechnung = new RechnungObjekt("Roha Ahmad", new Date(), gekaufteArtikel);

                //Rechnungsinformationen ausgeben
                System.out.println("Kunde: " + rechnung.getKunde());
                System.out.println("Datum: " + rechnung.getDatum());
                System.out.println("Gekaufte Artikel: ");
                for (Artikel artikel : rechnung.getGekaufteArtikel()) {
                    System.out.println("-" + artikel.getBezeichnung() + "(Stückzahl: " + artikel.getStueckzahl() + ",Preis:" + artikel.getPreis() + ")";
                }
                System.out.println("Gesamtpreis: " + rechnung.getGesamtpreis());
            }



            private EShop eShop; // die Bib-Verwaltung erledigt alle Aufgaben, die nichts mit Ein-/Ausgabe zu tun haben
            private BufferedReader in;

            public BibClientCUI(String datei) throws IOException {
                EShop = new eshop (datei);
                in = new BufferedReader(new InputStreamReader(System.in));
            }

        this.EShop = new eshop();
    }
    private void menue() {
        System.out.println("\n Befehle:");
        System.out.println("Artikel ausgeben: 'a'");
        System.out.println("Artikel löschen: 'b'");
        System.out.println("Artikel einfügen: 'c'");
        System.out.println("Artikel suchen: 'd'");
        System.out.println("Warenkorb anzeigen: 'e'");
        System.out.println("Warenkorb leeren: 'f'");
        System.out.println("---------------------");
        System.out.println("Beenden: 'x'");
        System.out.print("> "); // Prompt
        System.out.flush(); // ohne new line ausgeben
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


}


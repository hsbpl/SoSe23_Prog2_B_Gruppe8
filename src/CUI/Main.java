package CUI;

import Domain.*;
import ValueObjekt.Artikel;
import ValueObjekt.Kunde;
import ValueObjekt.Mitarbeiter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private Artikelverwaltung artikelverwaltung;
    private Kundenverwaltung kundenVerwaltung;
    private Mitarbeiterverwaltung mitarbeiterVerwaltung;

    private Warenkorb warenkorb;
    public Main() {
        this.eshop = new EShop();
    }
    public class EShop {


        public main() {
            this.artikelverwaltung = new Artikelverwaltung();
            this.kundenverwaltung = new Kundenverwaltung();
            this.mitarbeiterverwaltung = new Mitarbeiterverwaltung();
            this.warenkorb = new Warenkorb();

        }

        public void init() {
            // Beispielartikel hinzufügen
            artikelVerwaltung.artikelHinzufuegen(new Artikel("Apfel", "frisches Obst", 1.0, 10));

            // Beispielkunde hinzufügen
            kundenVerwaltung.kundeHinzufuegen(new Kunde("Max Mustermann", "12345", "Musterstraße 1"));

            // Beispielmitarbeiter hinzufügen
            mitarbeiterVerwaltung.mitarbeiterHinzufuegen(new Mitarbeiter("Anna Müller", "98765", "Musterweg 2", "Verkauf"));
        }

    }

    private void menue() {
        System.out.println("\nBefehle:");
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
                System.out.println(this.warenkorb.getArtikelListe());
                break;
            case "b":
                System.out.print("Artikelnummer: ");
                try {
                    int artikelnummer = Integer.parseInt(liesEingabe());
                    this.warenkorb.removeArtikel(artikelnummer);
                    System.out.println("Artikel gelöscht.");
                } catch (NumberFormatException e) {
                    System.out.println("Ungültige Eingabe!");
                }
}

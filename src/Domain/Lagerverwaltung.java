package Domain;

import ValueObjekt.Artikel;
import ValueObjekt.Mitarbeiter;

import java.util.ArrayList;
import java.util.List;

public class Lagerverwaltung {
    private List<Artikel> artikelListe;
    private List<Mitarbeiter> mitarbeiterListe;

    public Lagerverwaltung() {
        artikelListe = new ArrayList<>();
        mitarbeiterListe = new ArrayList<>();
    }
    public void neuenArtikelAnlegen(String bezeichnung, int bestand) {
        Artikel neuerArtikel = new Artikel(bezeichnung, bestand);
        artikelListe.add(neuerArtikel);
    }
    public void bestandErhoehen(String artikelBezeichnung, int Anzahl) {
        for (Artikel artikel : artikelListe) {
            if (artikel.getBezeichnung().equals(artikelBezeichnung)) {
                artikel.ArtikelbestandErhoehen(Anzahl);
                break;
            }
        }
    }
    public void neuenMitarbeiterRegistrieren(String userName, String passwort) {
        Mitarbeiter neuerMitarbeiter = new Mitarbeiter(userName, passwort);
        mitarbeiterListe.add(neuerMitarbeiter);
    }
    public Mitarbeiter mitarbeiterEinloggen(String benutzername, String passwort) {
        for (Mitarbeiter mitarbeiter : mitarbeiterListe) {
            if (mitarbeiter.getUserName().equals(benutzername) && mitarbeiter.userpasswort(passwort)) {
                return mitarbeiter;
            }
        }
        return null; // Mitarbeiter nicht gefunden oder falsches Passwort
    }
}



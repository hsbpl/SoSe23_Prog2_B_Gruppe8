package Domain;

import ValueObjekt.Artikel;
import ValueObjekt.Kunde;
import ValueObjekt.Mitarbeiter;

import java.util.ArrayList;
import java.util.List;

public class Mitarbeiterverwaltung {
    //extension von User
    //Konstruktor
    //mit Artikelverwaltung arbeiten, um neue Artike anlegne zu können zum Beispiel
    //Login
    private List<Mitarbeiter> listMitarbeiter = new ArrayList<>();
    private List<Mitarbeiter> mitarbeiterListe;
    private List<Artikel> artikelList ;
    private List<Kunde> kundeListe;

    public List<Mitarbeiter> getListMitarbeiter(){
        return listMitarbeiter;
    }




    public void neuenMitarbeiterRegistrieren(String userName, String passwort, String nachname, String vorname, int mitarbeiterNummer) {
        Mitarbeiter neuerMitarbeiter = new Mitarbeiter(userName, passwort,nachname,vorname,mitarbeiterNummer);
        mitarbeiterListe.add(neuerMitarbeiter);
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
            if (mitarbeiter.getUserName().equals(benutzername) && mitarbeiter.getUserName(passwort)) {
                return mitarbeiter;
            }
        }
        return null; // Mitarbeiter nicht gefunden oder falsches Passwort
    }
}

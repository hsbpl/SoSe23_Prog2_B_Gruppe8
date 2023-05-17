package Domain;

import ValueObjekt.Artikel;
import ValueObjekt.Kunde;
import ValueObjekt.Mitarbeiter;
import ValueObjekt.User;

import java.util.ArrayList;
import java.util.List;

public class Mitarbeiterverwaltung extends User {
    //extension von User
    //Konstruktor
    //mit Artikelverwaltung arbeiten, um neue Artike anlegne zu können zum Beispiel
    //Login
    private List<Mitarbeiter> listMitarbeiter = new ArrayList<>();
    private List<Mitarbeiter> mitarbeiterListe;

    public Mitarbeiterverwaltung(String userName, String passwort, String nachname, String vorname) {
        super(userName, passwort, nachname, vorname);
    }

    public Mitarbeiterverwaltung() {
        super();
    }

    public List<Mitarbeiter> getListMitarbeiter(){
        return listMitarbeiter;
    }
    public List<Mitarbeiter> getMitarbeiterListe() {
        return mitarbeiterListe;
    }
    public void setMitarbeiterListe(List<Mitarbeiter> mitarbeiterListe) {
        this.mitarbeiterListe = mitarbeiterListe;
    }

    public void neuenMitarbeiterRegistrieren(String userName, String passwort, String nachname, String vorname, int mitarbeiterNummer) {
        Mitarbeiter neuerMitarbeiter = new Mitarbeiter(userName, passwort,nachname,vorname,mitarbeiterNummer);
        mitarbeiterListe.add(neuerMitarbeiter);
    }

    public Mitarbeiter mitarbeiterEinloggen(String benutzername, String passwort) {
        for (Mitarbeiter mitarbeiter : mitarbeiterListe) {
            if (mitarbeiter.getUserName().equals(benutzername) && mitarbeiter.getPasswort().equals(passwort)) {
                return mitarbeiter;
            }
        }
        return null;
    }

    }





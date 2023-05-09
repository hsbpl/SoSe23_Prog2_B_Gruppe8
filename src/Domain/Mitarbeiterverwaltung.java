package Domain;

import ValueObjekt.Artikel;
import ValueObjekt.Kunde;
import ValueObjekt.Mitarbeiter;

import java.util.ArrayList;
import java.util.List;

public class Mitarbeiterverwaltung {
    private List<Mitarbeiter> listMitarbeiter = new ArrayList<>();
    private List<Artikel> artikelList ;
    private List<Kunde> kundeListe;

    public List<Mitarbeiter> getListMitarbeiter(){
        return listMitarbeiter;
    }

    public void registriereKunde(String name, String nummer, String adresse, String benutzername, String passwort) {
        Kunde neuerKunde = new Kunde(name, nummer, adresse, benutzername, passwort);
        kundenListe.add(neuerKunde);
        System.out.println("Der Kunde " + name + " wurde erfolgreich registriert.");
    }


    public void neuenMitarbeiterRegistrieren(String userName, String passwort) {
        Mitarbeiter neuerMitarbeiter = new Mitarbeiter(userName, passwort);
        mitarbeiterListe.add(neuerMitarbeiter);
    }
    public Mitarbeiter mitarbeiterEinloggen(String benutzername, String passwort) {
        for (Mitarbeiter mitarbeiter : mitarbeiterListe) {
            if (mitarbeiter.getUserName().equals(benutzername) && mitarbeiter.benutzername(passwort)) {
                return mitarbeiter;
            }
        }
        return null; // Mitarbeiter nicht gefunden oder falsches Passwort
    }


}

package Domain;

import ValueObjekt.Artikel;
import ValueObjekt.Kunde;
import ValueObjekt.Mitarbeiter;

import java.util.ArrayList;
import java.util.List;

public class Mitarbeiterverwaltung {
    private List<Mitarbeiter> listMitarbeiter = new ArrayList<>();
    private List<Mitarbeiter> mitarbeiterListe;
    private List<Artikel> artikelList ;
    private List<Kunde> kundeListe;

    public List<Mitarbeiter> getListMitarbeiter(){
        return listMitarbeiter;
    }




    public void neuenMitarbeiterRegistrieren(String userName, String passwort, String nachname, String vorname, int nummer, int mitarbeiterNummer) {
        Mitarbeiter neuerMitarbeiter = new Mitarbeiter(userName, passwort,nachname,vorname,nummer,mitarbeiterNummer);
        mitarbeiterListe.add(neuerMitarbeiter);
    }
}

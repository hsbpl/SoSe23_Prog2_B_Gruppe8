package ValueObjekt;
package Domain;
import ValueObjekt.Mitarbeiter;

import java.util.ArrayList;
import java.util.List;
import ValueObjekt.Mitarbeiter;



public class Mitarbeiter extends User {
//unterklasse private ?
private List<Mitarbeiter> mitarbeiterListe;

    public int mitarbeiterNummer;

    public Mitarbeiter(String userName, String passwort, String nachname, String vorname, int mitarbeiterNummer) {
        super (userName,  passwort, nachname, vorname;
        this.mitarbeiterNummer = mitarbeiterNummer;

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


    public int getMitarbeiterNummer() {
        return mitarbeiterNummer;
    }

    public void setMitarbeiterNummer(int mitarbeiterNummer) {
        this.mitarbeiterNummer = mitarbeiterNummer;
    }

}

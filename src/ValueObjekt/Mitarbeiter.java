package ValueObjekt;

import Domain.Warenkorb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Mitarbeiter extends User {
//unterklasse private ?
    public int mitarbeiterNummer;

    public Mitarbeiter(String userName, String passwort, String nachname, String vorname, int mitarbeiterNummer) {
        super (userName,  passwort, nachname, vorname);
        this.mitarbeiterNummer = mitarbeiterNummer;

    }



    public int getMitarbeiterNummer() {
        return mitarbeiterNummer;
    }

    public void setMitarbeiterNummer(int mitarbeiterNummer) {
        this.mitarbeiterNummer = mitarbeiterNummer;
    }

}


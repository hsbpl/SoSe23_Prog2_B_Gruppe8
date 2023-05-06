package ValueObjekt;

public class Mitarbeiter extends User {
//unterklasse private ?
    public int mitarbeiterNummer;

    public Mitarbeiter(String userName, String passwort, String nachname, String vorname, int nummer, int mitarbeiterNummer) {
        super (userName,  passwort, nachname, vorname,  nummer);
        this.mitarbeiterNummer = mitarbeiterNummer;
    }



    public int getMitarbeiterNummer() {
        return mitarbeiterNummer;
    }

    public void setMitarbeiterNummer(int mitarbeiterNummer) {
        this.mitarbeiterNummer = mitarbeiterNummer;
    }

}

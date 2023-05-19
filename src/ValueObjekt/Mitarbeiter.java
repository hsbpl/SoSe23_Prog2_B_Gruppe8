package ValueObjekt;




public class Mitarbeiter extends User {

     int mitarbeiterNummer;


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


    public String toString() {
        return "Mitarbeiter-" + super.toString();
    }
}


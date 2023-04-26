package ValueObjekt;

public class Mitarbeiter extends User {

    public int mitarbeiterNummer;

    public Mitarbeiter( String UserName, String passwort, String nachname, String vorname, int nummer, int mitarbeiterNummer) {
        super ( UserName,  passwort, nachname, vorname,  nummer);
        this.mitarbeiterNummer = mitarbeiterNummer;
    }



    public int getMitarbeiterNummer() {
        return mitarbeiterNummer;
    }

    public void setMitarbeiterNummer(int mitarbeiterNummer) {
        this.mitarbeiterNummer = mitarbeiterNummer;
    }

}

package ValueObjekt;

public class Mitarbeiter extends User {

    public int MitarbeiterNummer;

    public Mitarbeiter( String UserName, String passwort, String nachname, String vorname, int nummer, int mitarbeiterNummer) {
        super ( UserName,  passwort, nachname, vorname,  nummer);
        MitarbeiterNummer = mitarbeiterNummer;
    }



    public int getMitarbeiterNummer() {
        return MitarbeiterNummer;
    }

    public void setMitarbeiterNummer(int mitarbeiterNummer) {
        MitarbeiterNummer = mitarbeiterNummer;
    }

}

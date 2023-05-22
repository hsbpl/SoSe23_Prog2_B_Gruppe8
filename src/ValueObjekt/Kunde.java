package ValueObjekt;

public class Kunde extends User {

    private String kundenAdresse;

    public Kunde(String UserName, String passwort, String nachname, String vorname, String kundeAdresse) {
        super (UserName, passwort, nachname, vorname);
        this.kundenAdresse = kundeAdresse;
    }

    public String getKundenAdresse() {
        return kundenAdresse;
    }

    public void setKundenAdresse(String kundeAdresse) {
        this.kundenAdresse = kundeAdresse;
    }


    @Override
    public String toString() {
        return "Kunden-" + super.toString();
    }
}

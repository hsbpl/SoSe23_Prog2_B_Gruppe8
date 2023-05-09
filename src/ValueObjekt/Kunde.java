package ValueObjekt;

public class Kunde extends User {

    private String kundeAdresse;

    public Kunde(String UserName, String passwort, String nachname, String vorname, String kundeAdresse) {
         super (UserName, passwort, nachname, vorname, kundeAdresse);
         this.kundeAdresse = kundeAdresse;
    }

    public String getKundeAdresse() {
        return kundeAdresse;
    }


    public void setKundeAdresse(String kundeAdresse) {
        this.kundeAdresse = kundeAdresse;
    }
}

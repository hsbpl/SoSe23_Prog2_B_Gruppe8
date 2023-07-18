package Common;

import java.io.Serializable;

public class Kunde extends User implements Serializable {

    private String kundenAdresse;

    public Kunde(String UserName, String passwort, String nachname, String vorname, String kundeAdresse) {
        super(UserName, passwort, nachname, vorname, "KU");
        this.kundenAdresse = kundeAdresse;
        //ID wird in User-Klasse Automatisch generriert KU dient als Kennzeichnung, dass es sich hier um einen Kunden handelt
    }

    public String getKundenAdresse() {
        return kundenAdresse;
    }

    ;

    @Override
    public String toString() {
        return super.toString() + "\n";
    }
}

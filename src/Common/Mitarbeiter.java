package Common;


public class Mitarbeiter extends User {

    public Mitarbeiter(String userName, String passwort, String nachname, String vorname) {
        super(userName, passwort, nachname, vorname, "MI");
//ID wird in User-Klasse Automatisch generriert MI dient als Kennzeichnung, dass es sich hier um einen Mitarbeiter handelt
    }

    public String toString() {
        return "Mitarbeiter-" + super.toString();
    }
}



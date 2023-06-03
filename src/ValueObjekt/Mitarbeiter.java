package ValueObjekt;




public class Mitarbeiter extends User {



    public Mitarbeiter(String userName, String passwort, String nachname, String vorname) {
        super (userName,  passwort, nachname, vorname, "MI");

    }


    public String toString() {
        return "Mitarbeiter-" + super.toString();
    }
    }



package ValueObjekt;




public class Mitarbeiter extends User {



    public Mitarbeiter(String userName, String passwort, String nachname, String vorname, int id) {
        super (userName,  passwort, nachname, vorname, id);

    }


    public String toString() {
        return "Mitarbeiter-" + super.toString();
    }
    }



package ValueObjekt;

import java.util.List;

public class User {
//Oberklasse protected
    protected String userName;
    protected String passwort;
    protected String nachname;
    protected String vorname;
    protected int nummer;
    private List<Mitarbeiter>mitarbeiterList;

    public User(String userName, String passwort, String nachname, String vorname, int nummer) {
        this.userName = userName;
        this.passwort = passwort;
        this.nachname = nachname;
        this.vorname = vorname;
        this.nummer = nummer;
    }

    public String getUserName() {
        return userName;
    }

    public String getNachname() {
        return nachname;
    }

    public int getNummer() {
        return nummer;
    }

    public String getVorname() {
        return vorname;
    }
    public String getPasswort(){
        return passwort;
    }

    // die  Methode login  überprüft, ob es einen Mitarbeiter oder Kunden mit dem angegebenen Benutzernamen und Passwort gibt
    public boolean login(String benutzername, String passwort) {
        for (Mitarbeiter mitarbeiter : mitarbeiterList) {
            if (mitarbeiter.getUserName().equals(benutzername) && mitarbeiter.getPasswort().equals(passwort)) {
                System.out.println("Willkommen, " + mitarbeiter.getUserName() + "!");
                return true;
            }
        }
       /* for (Kunde kunde : kundenList) {
            if (kunde.getUserName().equals(benutzername) && kunde.getPasswort().equals(passwort)) {
                System.out.println("Willkommen, " + kunde.getUserName() + "!");
                return true;
            }
        }*/
        System.out.println("Benutzername oder Passwort ungültig. Bitte versuchen Sie es erneut.");
        return false;
    }

}






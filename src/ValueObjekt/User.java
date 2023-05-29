package ValueObjekt;

public class User {
    protected String userName;
    protected String passwort;
    protected String nachname;
    protected String vorname;
    protected int idNummer;

    public User(String userName, String passwort, String nachname, String vorname,int idNummer) {
        this.userName = userName;
        this.passwort = passwort;
        this.nachname = nachname;
        this.vorname = vorname;
        this.idNummer= idNummer;
    }


    public String getUserName() {
        return userName;
    }

    public String getPasswort(){
        return passwort;
    }

    public String toString() {
        return "Username: "+ userName + "\n" + "Name: " + vorname+" " + nachname;
    }
}






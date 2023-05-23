package ValueObjekt;

public class User {
    //Oberklasse protected
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
        this.idNummer=idNummer;
    }


    public int getIdNummer() {
        return idNummer;
    }

    public void setIdNummer(int idNummer) {
        this.idNummer = idNummer;
    }

    public String getUserName() {
        return userName;
    }

    public String getNachname() {
        return nachname;
    }

    public String getVorname() {
        return vorname;
    }
    public String getPasswort(){
        return passwort;
    }



    public String toString() {
        return "Username: "+ userName + "\n" +"Vor- und Nachname: " + vorname+" " + nachname;
    }
}






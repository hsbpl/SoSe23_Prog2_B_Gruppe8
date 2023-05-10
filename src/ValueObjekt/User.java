package ValueObjekt;

public class User {
//Oberklasse protected
    protected String userName;
    protected String passwort; 
    protected String nachname;
    protected String vorname;


    public User(String userName, String passwort, String nachname, String vorname) {
        this.userName = userName;
        this.passwort = passwort;
        this.nachname = nachname;
        this.vorname = vorname;
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

}






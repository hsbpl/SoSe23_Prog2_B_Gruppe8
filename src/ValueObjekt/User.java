package ValueObjekt;

public class User {
    protected String userName;
    protected String passwort;
    protected String nachname;
    protected String vorname;
    protected String idNummer;

    public User(String userName, String passwort, String nachname, String vorname,String idNummer) {
        this.userName = userName;
        this.passwort = passwort;
        this.nachname = nachname;
        this.vorname = vorname;
        this.idNummer= idNummer + generateId();
    }

    //TODO schauen ob das hier funktioniert!
    private static String generateId() {
        String prefix = "USR";
        String timestamp = String.valueOf(System.currentTimeMillis());
        String random = String.valueOf((int) (Math.random() * 1000));
        return prefix + timestamp + random;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswort(){
        return passwort;
    }
    public String getNachname(){return nachname;}
    public String getVorname(){ return vorname;}
    public String getidNummer(){return idNummer;}



    public String toString() {
        return "Username: "+ userName + "\n" + "Name: " + vorname+" " + nachname;
    }
}






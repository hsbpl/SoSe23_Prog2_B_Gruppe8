package ValueObjekt;

public class User {

    private String userName;
    private String passwort;
    private String nachname;
    private String vorname;
    private int nummer;

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

       /* public Mitarbeiter LoginAlsMitarbeiter (String UserName, String passwort) {


            // Vérifier les informations d'identification et retourner l'objet Employe
        }

        public Kunde LoginAlsKunde (String UserName, String passwort) {
            // Vérifier les informations d'identification et retourner l'objet Client
        }*/
}






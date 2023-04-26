package ValueObjekt;

public class User {

    private String UserName;
    private String passwort;
    private String nachname;
    private String vorname;
    private int Nummer;

    public User(String UserName, String passwort, String nachname, String vorname, int nummer) {
        this.UserName = UserName;
        this.passwort = passwort;
        this.nachname = nachname;
        this.vorname = vorname;
        this.Nummer = nummer;
    }

    public String getUserName() {
        return UserName;
    }

    public String getNachname() {
        return nachname;
    }

    public int getNummer() {
        return Nummer;
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






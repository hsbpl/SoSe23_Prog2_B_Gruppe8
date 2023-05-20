package Domain;

import ValueObjekt.Mitarbeiter;
import ValueObjekt.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mitarbeiterverwaltung extends User {
    //extension von User
    //Konstruktor
    //mit Artikelverwaltung arbeiten, um neue Artike anlegne zu können zum Beispiel
    //Login

    private String datei;
    private List<Mitarbeiter> listMitarbeiter = new ArrayList<>();
    private List<Mitarbeiter> mitarbeiterListe;

    private Mitarbeiter mitarbeiter;
      public void Mitarbeiterverwaltung(String datei) throws IOException {
               this.datei= datei;
           }

    public List<Mitarbeiter> getListMitarbeiter(){
        return listMitarbeiter;
    }
    public List<Mitarbeiter> getMitarbeiterListe() {
        return mitarbeiterListe;
    }
    public void setMitarbeiterListe(List<Mitarbeiter> mitarbeiterListe) {
        this.mitarbeiterListe = mitarbeiterListe;
    }
    public Mitarbeiterverwaltung(String userName, String passwort, String nachname, String vorname) {
        super(userName, passwort, nachname, vorname);
    }

    //  Mitarbeiter einloggen
    public Mitarbeiter mitarbeiterEinloggen(String benutzername, String passwort) {
        for (Mitarbeiter mitarbeiter : mitarbeiterListe) {
            if (mitarbeiter.getUserName().equals(benutzername) && mitarbeiter.getPasswort().equals(passwort)) {
                return mitarbeiter;
            }
        }
        return null;
    }


    //  Methode fügt einen neuen Mitarbeiter zur Mitarbeiterliste hinzu
    public Mitarbeiter neuemitarbeiterEinfuegen(String userName, String passwort, String nachname, String vorname, int mitarbeiterNummer) throws MitarbeiterExistiertBereitsException {
        if (mitarbeiterBereitsVorhanden(userName, passwort)) {
            throw new MitarbeiterExistiertBereitsException(userName, passwort);
        }

        Mitarbeiter neuerMitarbeiter = new Mitarbeiter(userName, passwort, nachname, vorname, mitarbeiterNummer);
        mitarbeiterListe.add(neuerMitarbeiter);
        return neuerMitarbeiter;
    }


    //Diese Methode überprüft, ob ein Mitarbeiter mit
    // dem angegebenen Benutzernamen und Passwort bereits in der Mitarbeiterliste vorhanden ist
    private boolean mitarbeiterBereitsVorhanden(String userName, String passwort) {
        for (Mitarbeiter mitarbeiter : mitarbeiterListe) {
            if (mitarbeiter.getUserName().equals(userName) && mitarbeiter.getPasswort().equals(passwort)) {
                return true;
            }
        }
        return false;
    }
        private class MitarbeiterExistiertBereitsException extends Throwable {
        public MitarbeiterExistiertBereitsException(String userName, String passwort) {
        }
    }
}





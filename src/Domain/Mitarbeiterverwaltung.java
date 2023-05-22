package Domain;

import ValueObjekt.Mitarbeiter;
import ValueObjekt.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mitarbeiterverwaltung{
    private List<Mitarbeiter> listMitarbeiter;

    public Mitarbeiterverwaltung() {
        this.listMitarbeiter = new ArrayList<>(Arrays.asList(m1));
    }
    Mitarbeiter m1 = new Mitarbeiter("m1", "123", "Mitarbeiter", "m1", 001);

    public List<Mitarbeiter> getListMitarbeiter() {
        return listMitarbeiter;
    }


    //  Mitarbeiter einloggen
    public Mitarbeiter mitarbeiterEinloggen(String benutzername, String passwort) {
        Mitarbeiter m = null;
        for (Mitarbeiter mitarbeiter : listMitarbeiter) {
            if (mitarbeiter.getUserName().equals(benutzername) && mitarbeiter.getPasswort().equals(passwort)) {
                m = mitarbeiter;
            }

        }
        return m;
    }

    //  Methode fügt einen neuen Mitarbeiter zur Mitarbeiterliste hinzu
    public Mitarbeiter mRegister(Mitarbeiter neu){
        if (listMitarbeiter.contains(neu)) {
            return null;
        } else {
            listMitarbeiter.add(neu);
            return neu;
        }
    }
}





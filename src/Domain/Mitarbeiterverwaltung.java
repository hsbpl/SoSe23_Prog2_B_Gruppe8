package Domain;

import Exceptions.ArtikelExistiertBereitsException;
import Exceptions.UserExistiertBereitsException;
import Persistence.FilePersistenceManager;
import Persistence.PersistenceManager;
import ValueObjekt.Kunde;
import ValueObjekt.Mitarbeiter;
import ValueObjekt.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Mitarbeiterverwaltung {
    private PersistenceManager pm = new FilePersistenceManager();

    private List<Mitarbeiter> listMitarbeiter;

    public void liesDaten(String datei) throws IOException {
        try {
            listMitarbeiter = pm.leseMitarbeiterList(datei);
        } catch (UserExistiertBereitsException e) {
            throw new RuntimeException(e);
        }

    }
    public void schreibeDaten(String datei) throws IOException{
        pm.schreibeMitarbeiterListe(listMitarbeiter, datei);

    }
    public Mitarbeiterverwaltung() {
        this.listMitarbeiter = new ArrayList<>(Arrays.asList(new Mitarbeiter("m1", "123", "Mitarbeiter", "m1")));
    }

    public List<Mitarbeiter> getListMitarbeiter() {
        return listMitarbeiter;
    }

    //  Mitarbeiter einloggen
    public Mitarbeiter mitarbeiterEinloggen(String username, String passwort) {
        Mitarbeiter loginstatus = null;
        for (Mitarbeiter m : listMitarbeiter) {
            if (m.getUserName().equals(username) && m.getPasswort().equals(passwort)){
                loginstatus = m;
            }
        }
        return loginstatus;
    }

    //  Methode fügt einen neuen Mitarbeiter zur Mitarbeiterliste hinzu
    public Mitarbeiter mRegister(Mitarbeiter neu){
        Mitarbeiter registrierungErfolgreich= listMitarbeiter.stream()
                .filter(a -> a.getUserName() ==neu.getUserName())
                .findFirst()
                .orElse(null);

        if(registrierungErfolgreich != null){
            listMitarbeiter.add(neu);

        }
        return registrierungErfolgreich;
    }

    public String registrierteMitarbeiter(){
        String liste = "";
        for(Mitarbeiter n : listMitarbeiter){
            liste += n.toString() +"\n";
        }
        return  liste;
    }
}





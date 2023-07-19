package Server.Domain;

import Common.Exceptions.LeeresTextfieldException;
import Common.Exceptions.UserExistiertBereitsException;
import Server.Persistence.FilePersistenceManager;
import Server.Persistence.PersistenceManager;
import Common.Mitarbeiter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Mitarbeiterverwaltung {
    private PersistenceManager pm = new FilePersistenceManager();

    private static List<Mitarbeiter> listMitarbeiter = new ArrayList<>();

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
        //this.listMitarbeiter = new ArrayList<>(Arrays.asList(new Mitarbeiter("m1", "123", "Mitarbeiter", "m1")));
    }

    public List<Mitarbeiter> getListMitarbeiter() {
        return listMitarbeiter;
    }

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
    public Mitarbeiter mRegister(Mitarbeiter neu) throws LeeresTextfieldException {

        if(neu.getUserName().isEmpty() || neu.getPasswort().isEmpty() || neu.getVorname().isEmpty() ||
                neu.getNachname().isEmpty()){
            throw new LeeresTextfieldException();
        }else{

        Mitarbeiter registrierungErfolgreich= listMitarbeiter.stream()
                .filter(a -> a.getUserName().equalsIgnoreCase(neu.getUserName()))
                .findFirst()
                .orElse(null);

        if(registrierungErfolgreich == null){
            listMitarbeiter.add(neu);
            System.out.println("Registrierung erfolgreich");
            registrierungErfolgreich = neu;
        } else {
            return null;
        }
        return registrierungErfolgreich;}
    }

    public String registrierteMitarbeiter(){
        String liste = "";
        for(Mitarbeiter n : listMitarbeiter){
            liste += n.toString() +"\n";
        }
        return  liste;
    }
    public Mitarbeiter getMitarbeiterByUsername(String username){
        for (Mitarbeiter mitarbeiter : listMitarbeiter){
            if (mitarbeiter.getUserName().equals(username)) {
                return mitarbeiter;
            }
        }

        return null;
    }
}





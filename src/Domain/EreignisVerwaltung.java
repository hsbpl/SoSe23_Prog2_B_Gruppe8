package Domain;

import Exceptions.ArtikelExistiertBereitsException;
import Exceptions.UserExistiertBereitsException;
import Persistence.FilePersistenceManager;
import Persistence.PersistenceManager;
import ValueObjekt.Ereignis;
import ValueObjekt.Mitarbeiter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class EreignisVerwaltung {
    private PersistenceManager pm = new FilePersistenceManager();

    public List<Ereignis> getEreignisListe;
    private List<Ereignis> ereignisList;
    private List<Ereignis> ereignisBestand = new Vector<Ereignis>();



    public EreignisVerwaltung(){
    ereignisList = new ArrayList<>();
    }

    public void liesDaten(String datei) throws IOException {
        try {
            ereignisList = pm.leseEreignislist(datei);
        } catch (ArtikelExistiertBereitsException e) {
            throw new RuntimeException(e);
        }
    }

    public void schreibeDaten (String datei) throws IOException {
            pm.schreibeEreignisListe(ereignisList, datei);
        }




}

package Domain;

import ValueObjekt.Artikel;
import ValueObjekt.Mitarbeiter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Mitarbeiterverwaltung {
    public void setListMitarbeiter(List<Mitarbeiter> listMitarbeiter) {
        this.listMitarbeiter = listMitarbeiter;
    }

    private List<Mitarbeiter> listMitarbeiter = new ArrayList<>();

    public List<Mitarbeiter> getListMitarbeiter(){
        return listMitarbeiter;
    }

    public void liesDaten(String datei) throws IOException {
        pm.openForReading(datei);

        Mitarbeiter mitarbeiter;
        do {
            mitarbeiter = pm.ladeMitarbeiter();
            if (mitarbeiter != null) {
                // Mitarbeiter in Liste einfügen
                try {
                    einfuegen(mitarbeiter);
                } catch (MitarbeiterExistiertBereitsException | ArtikelExistiertBereitsException exception) {
                }
            }
        } while (mitarbeiter != null);

        pm.close();
    }

    public void schreibeDaten(String datei) throws IOException  {
        pm.openForWriting(datei);

        if (!listMitarbeiter.isEmpty()) {
            Iterator<Mitarbeiter> iter = listMitarbeiter.iterator();
            while (iter.hasNext()) {
                Mitarbeiter mitarbeiter = (Mitarbeiter) iter.next();
                pm.speichereMitarbeiter(mitarbeiter);
            }
        }
        pm.close();
    }

    public void einfuegen(Mitarbeiter mitarbeiter) throws ArtikelExistiertBereitsException,
            MitarbeiterExistiertBereitsException {
        if (listMitarbeiter.contains(mitarbeiter)) {
            throw new MitarbeiterExistiertBereitsException(mitarbeiter, " - in 'einfuegen()'");
        }
        listMitarbeiter.add(mitarbeiter);
    }

    public void mitarbeiterLoeschen(Artikel artikel) {
        listMitarbeiter.remove(artikel);
    }
}

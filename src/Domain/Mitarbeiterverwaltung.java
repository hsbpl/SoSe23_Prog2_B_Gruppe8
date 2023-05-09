package Domain;

import ValueObjekt.Mitarbeiter;

import java.util.ArrayList;
import java.util.List;

public class Mitarbeiterverwaltung {
    private List<Mitarbeiter> listMitarbeiter = new ArrayList<>();

    public List<Mitarbeiter> getListMitarbeiter(){
        return listMitarbeiter;
    }
}

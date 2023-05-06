package Domain;

import ValueObjekt.Artikel;
import ValueObjekt.Kunde;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Eshop {
/*
Notizen fürs weitere

--> Datum wird für Rechnungen und Ein- Auslagerung benötigt.
Um Datum zu generieren:
Date datum = new Date();
benötigt: java.uutil.date

--> Überlegungen zum letzen Punkt
-evtl in Artikel/Lagerverwaltung
- als Val-Obj? "LagerAendern" mit
    - vererbung von Artikel (extends Artikel)
    - User user (kann sowohl Kunde als auch Mitarbeiter im bei veränderungen notieren)
    - Date datum = new Date();

    Konstruktor, get und set, toString

mgl Methode in Domain:
List <LagerAendern> änderungen = new ArrayList();

public List <Artikel>(Artikel artikel oder List <Artikel> artikel, User user){
//bei Liste:
Iterator it = artikel.iterator();
while(it.hasNext()){                        //wenn das geht
änderungen.add(new LagerAendern(it, user, LagerAendern.getDatum()));
}


 */

    /* NOCH NICHT FERTIG
public String rechnungErzeugen(List<Artikel> warenkorb, Kunde kunde){
    String kundenInfo ="Vielen Dank für Ihren Einkauf Lieber/Liebe " + kunde.getVorname() + " " + kunde.getNachname() + " " + ".";
    String einkauf = " ";


    Stream<Artikel> art = warenkorb.stream().count(a -> a .getBezeichnung == a.getBezeichnung);
    art.forEach(a -> einkauf = a.getBezeichnung() + " " + StückzahlAusCount + " stk." + a.getPreis() + a.getPreis()*StückzahlAusCount);


    return kundenInfo + "\n" + einkauf + "\n";

}*/
}

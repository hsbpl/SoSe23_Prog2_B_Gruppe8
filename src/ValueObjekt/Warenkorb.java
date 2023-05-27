package ValueObjekt;

import java.net.http.WebSocket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Warenkorb {

    private HashMap<Artikel, Integer> warenkorb;

    public Warenkorb() {
        this.warenkorb = new HashMap<>();
    }

    public HashMap<Artikel, Integer> getWarenkorb() {
        return warenkorb;
    }

    //Todo Tutorin fragen warum das nicht überschreibt
    @Override
    public String toString() {

        String einkaufsliste = "";
        double gesamtsumme = 0;
        for (Map.Entry<Artikel, Integer> artikel : getWarenkorb().entrySet()) {
            String aktuellerArtikel = artikel.getKey().getBezeichnung();
            Integer aktuelleMenge = artikel.getValue();
            double aktuellerPreis = artikel.getKey().getPreis();
            gesamtsumme = aktuellerPreis * aktuelleMenge;
            einkaufsliste = aktuelleMenge+ " " +aktuellerArtikel+"        " + aktuellerPreis + "€ \n";
          /*  einkaufsliste += getWarenkorb().get(artikel.getKey().getPreis()) +
            "x " + "Artikel:" + getWarenkorb().get(artikel.getKey().getBezeichnung()) + "Preis: " + "             " + getWarenkorb().get(artikel.getKey().getPreis());
            gesamtsumme += getWarenkorb().get(artikel.getValue()) * getWarenkorb().get(artikel.getKey().getPreis());

           */
        }
        return einkaufsliste + "\n" + gesamtsumme;
    }
}

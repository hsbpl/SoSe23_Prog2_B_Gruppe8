package ValueObjekt;

import java.util.HashMap;
import java.util.Map;

public class Warenkorb {

    private HashMap<Artikel, Integer> warenkorb;

    public Warenkorb() {
        this.warenkorb = new HashMap<>();
    }

    public HashMap<Artikel, Integer> getWarenkorb() {
        return warenkorb;
    }


    @Override
    public String toString() {

        String einkaufsliste = "\n";
        double gesamtsumme = 0;

        for (Map.Entry<Artikel, Integer> entry : warenkorb.entrySet()) {
            Artikel artikel = entry.getKey();
            Integer menge = entry.getValue();
            String aktuellerArtikel = artikel.getBezeichnung();
            double einzelPreis = artikel.getPreis();
            int aktuelleMenge = menge;

            String artikelDaten = aktuelleMenge + "x  " + aktuellerArtikel + "          Einzelpreis: " + einzelPreis + "€";

            einkaufsliste += "\n" + artikelDaten;
            gesamtsumme += aktuelleMenge * einzelPreis;
        }

        return einkaufsliste + "\n" + "\nGesamtsumme:" + "                " + gesamtsumme + "€";
    }
}

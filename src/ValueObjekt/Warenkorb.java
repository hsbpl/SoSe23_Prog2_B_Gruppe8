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

    //Todo Tutorin fragen warum das nicht überschreibt
    @Override
    public String toString() {

       String einkaufsliste = "\n";
        double gesamtsumme = 0;

        for (Map.Entry<Artikel, Integer> entry : getWarenkorb().entrySet()) {
            Artikel artikel = entry.getKey();
            Integer menge = entry.getValue();
            String aktuellerArtikel = artikel.getBezeichnung();
            double einzelPreis = artikel.getPreis();
            int aktuelleMenge = menge;

            String artikelDaten = aktuelleMenge + "x  " + aktuellerArtikel + "          " + einzelPreis;

            einkaufsliste += "\n"+ artikelDaten;
            gesamtsumme += aktuelleMenge * einzelPreis;
        }

       /* for (Map.Entry<Artikel, Integer> entry : getWarenkorb().entrySet()) {
            String aktuellerArtikel = entry.getKey().getBezeichnung().toString();
            int aktuelleMenge = entry.getValue();
            double aktuellerPreis = entry.getKey().getPreis();
            gesamtsumme = aktuellerPreis * aktuelleMenge;

        */
           /* einkaufsliste += aktuelleMenge+ " " +aktuellerArtikel+"        " + aktuellerPreis + "€ ";
          einkaufsliste += getWarenkorb().get(artikel.getKey().getPreis()) +
            "x " + "Artikel:" + getWarenkorb().get(artikel.getKey().getBezeichnung()) + "Preis: " + "             " + getWarenkorb().get(artikel.getKey().getPreis());
            gesamtsumme += getWarenkorb().get(artikel.getValue()) * getWarenkorb().get(artikel.getKey().getPreis());


        }*/
        return einkaufsliste + "\n" + gesamtsumme;
    }
}

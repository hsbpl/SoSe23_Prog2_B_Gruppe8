package Common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Warenkorb {

    private static Map<Artikel, Integer> warenkorb;

    public Warenkorb() {
        this.warenkorb = new HashMap<>();
    }

    public Map<Artikel, Integer> getWarenkorb() {
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
            double einzelPreis = artikel.getEinzelpreis();
            int aktuelleMenge = menge;

            String artikelDaten = aktuelleMenge + "x  " + aktuellerArtikel + "          Einzelpreis: " + einzelPreis + "€";

            einkaufsliste += "\n" + artikelDaten;
            gesamtsumme += aktuelleMenge * einzelPreis;
            gesamtsumme = Math.round(gesamtsumme * 100.0) / 100.0;
        }

        return einkaufsliste + "\n" + "\nGesamtsumme:" + "                " + gesamtsumme + "€";
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        for (Map.Entry<Artikel, Integer> entry : warenkorb.entrySet()) {
            Artikel artikel = entry.getKey();
            Integer menge = entry.getValue();
            String aktuellerArtikel = artikel.getBezeichnung();
            double einzelPreis = artikel.getEinzelpreis();
            int aktuelleMenge = menge;

            String artikelDaten = aktuelleMenge + "x  " + aktuellerArtikel + "          Einzelpreis: " + einzelPreis + "€";
            return Objects.equals(aktuellerArtikel, artikel.bezeichnung);
        }

        Artikel artikel = (Artikel) obj;
        return false;
    }
}

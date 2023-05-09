package ValueObjekt;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Rechnung {
    private Kunde kunde;
    private Date datum;
    private int anzahl;
    private double gesamtePreis;
    private List<Artikel> artikelList ;

    public Rechnung(Kunde kunde, Date datum, int anzahl, double gesamtePreis) {
        this.kunde = kunde;
        this.datum = datum;
        this.anzahl = anzahl;
        this.gesamtePreis = gesamtePreis;

    }

    public Kunde getKunde() {
        return kunde;
    }

    public Date getDatum() {
        return datum;
    }

    public int getAnzahl() {
        return anzahl;
    }

   // Methode getGesamtpreis, um den Gesamtpreis aller Artikel in der Rechnung zu berechnen und zurückzugebe

    public double getGesamtpreis() {
        double gesamtpreis = 0.0;
        for (Artikel artikel : artikelList) {
            gesamtpreis += artikel.getPreis();
        }
        return gesamtpreis;
    }



}

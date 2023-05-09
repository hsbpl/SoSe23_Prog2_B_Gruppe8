package ValueObjekt;

import java.util.Date;
import java.util.List;

public class RechnungObjekt {

    private Kunde kunde;
    Date datum = new Date();
    private int anzahl;
    private double gesamtePreis;
    private List<RechnungObjekt> artikelList;


    public RechnungObjekt(Kunde kunde, Date datum, int anzahl, double gesamtePreis, List<RechnungObjekt> artikelList) {
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

    public double getGesamtePreis() {
        return gesamtePreis;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public void setGesamtePreis(double gesamtePreis) {
        this.gesamtePreis = gesamtePreis;
    }

    public double getGesamtpreis() {
        double gesamtpreis = 0.0;
        for (RechnungObjekt artikel : artikelList) {
            gesamtpreis += artikel.getGesamtePreis();
        }
        return gesamtpreis;

    }
}

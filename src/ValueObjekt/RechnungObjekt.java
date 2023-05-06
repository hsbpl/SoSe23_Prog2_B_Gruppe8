package ValueObjekt;

import java.util.Date;

public class RechnungObjekt {
    // Datum hinzugefügt
    private Kunde kunde;
    Date datum = new Date();
    private int anzahl;
    private double gesamtePreis;

    public RechnungObjekt(Kunde kunde, Date datum, int anzahl, double gesamtePreis) {
        this.kunde = kunde;
        this.datum = datum;
        this.anzahl = anzahl;
        this.gesamtePreis = gesamtePreis;
    }

    //in Domain umsetzen?
    public double getGesamtePreis() {
        return gesamtePreis;
    }
}

package ValueObjekt;

public class RechnungObjekt {
    private Kunde kunde;
    private date Datum;
    private int anzahl;
    private double gesamtePreis;

    public RechnungObjekt(Kunde kunde, date datum, int anzahl, double gesamtePreis) {
        this.kunde = kunde;
        Datum = datum;
        this.anzahl = anzahl;
        this.gesamtePreis = gesamtePreis;
    }

    public double getGesamtePreis() {
        return gesamtePreis;
    }
}

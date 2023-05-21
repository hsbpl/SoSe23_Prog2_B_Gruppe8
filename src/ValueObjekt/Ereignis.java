package ValueObjekt;

import java.util.Date;

public class Ereignis {
    private Date datum;
    private int anzahl;
    private Artikel artikel;
    private Mitarbeiter mitarbeiter;
    private Kunde kunde;

    public Ereignis(Date datum, int anzahl, Artikel artikel, Mitarbeiter mitarbeiter, Kunde kunde) {
        this.datum = datum;
        this.anzahl = anzahl;
        this.artikel = artikel;
        this.mitarbeiter = mitarbeiter;
        this.kunde = kunde;
    }

    // Getters und Setters
    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    public Mitarbeiter getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }
}





package ValueObjekt;

public class Artikel {
    private String bezeichnung;
    private int artikelNummer;
    private int bestand;
    private double preis;


    public Artikel(String bezeichnung, int artikelNummer, int bestand, double preis) {
        this.bezeichnung = bezeichnung;
        this.artikelNummer = artikelNummer;
        this.bestand = bestand;
        this.preis = preis;
    }

    /*
    So wie ich es verstanden habe, sind setter dazu da den Wert eines Attributes zu ändern, meint ihr man
    sollte dann nur Preis und Bestand setten ?
    Wenn Ja, müssten wir das in den anderen Klassen auch nochmal nachschauen.

     */
    public void setBestand(int bestand) {
        this.bestand = bestand;
    }
    public String getBezeichnung() {
        return bezeichnung;
    }

    public double getPreis() {
        return preis;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public void ArtikelbestandErhoehen(int zahl){
        bestand = bestand + zahl;
    }
    public void ArtikelbestandVerringern( int zahl){
        bestand = bestand - zahl;
    }
}

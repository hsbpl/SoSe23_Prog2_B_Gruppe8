package ValueObjekt;

public class Artikel {
    private String bezeichnung;
    private int artikelNummer;
    private int bestand;
    private double preis;



    public Artikel(String bezeichnung, int artikeNummer, int bestand, double preis) {
       this.bezeichnung = bezeichnung;
        this.artikelNummer = artikelNummer;
        this.bestand = bestand;
        this.preis = preis;
    }




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

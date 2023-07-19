

package Common;


public class Artikel {
    protected String bezeichnung;
    protected int artikelNummer;
    protected int bestand;
    protected double einzelpreis;

    protected int erwerbmenge;



    public Artikel(String bezeichnung, int artikelNummer,int bestand, double preis) {
        this.bezeichnung = bezeichnung;
        this.artikelNummer = artikelNummer;
        this.bestand = bestand;
        this.einzelpreis = preis;

    }

    public Artikel(String bezeichnung, int artikelNummer,int bestand, double preis, int erwerbmenge) {
        this.bezeichnung = bezeichnung;
        this.artikelNummer = artikelNummer;
        this.bestand = bestand;
        this.einzelpreis = preis;
        this.erwerbmenge = erwerbmenge;
    }
    public Artikel(int artikelnummer, int bestand, int preis) {
    }

    public int getBestand() {
        return bestand;
    }

    public void setBestand(int bestand) {
        this.bestand = bestand;
    }
    public String getBezeichnung() {
        return bezeichnung;
    }
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public double getEinzelpreis() {
        return einzelpreis;
    }


    public int  getArtikelNummer() {
        return artikelNummer;
    }
    public int getErwerbmenge() {return erwerbmenge;}

    public String toString() {
        return "Artikel: "+ bezeichnung+ " Artikelnummer: " + artikelNummer  + " Preis: " + einzelpreis + " Bestand: " +bestand;
    }

}



package ValueObjekt;

public class Artikel {
    private String bezeichnung;
    private int artikelNummer;
    private int bestand;
    private double preis;
    private boolean verfuegbar;


    public Artikel(String bezeichnung, int artikelNummer, int bestand, double preis, boolean verfuegbar) {
        this.bezeichnung = bezeichnung;
        this.artikelNummer = artikelNummer;
        this.bestand = bestand;
        this.preis = preis;
        this.verfuegbar = verfuegbar;
    }

    public boolean inStock(){
        if(this.bezeichnung == bezeichnung && bestand >= 1){
                verfuegbar = true;
            }
        return verfuegbar;
        }

    public boolean inStock(int n){
        if(n == artikelNummer && bestand >= 1){
            verfuegbar = true;
        }
        return verfuegbar;
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


    public double getPreis() {
        return preis;
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

    public int  getArtikelNummer() {
        return artikelNummer;
    }

}

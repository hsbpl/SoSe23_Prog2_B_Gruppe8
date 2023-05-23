

package ValueObjekt;

public class Artikel {
    private String bezeichnung;
    private int artikelNummer;
    private int bestand;
    private int stueckzahl;
    private double preis;
    private boolean verfuegbar;
    private int einkaufsmenge;

    public Artikel(String bezeichnung, int artikelNummer,int bestand, double preis, boolean verfuegbar) {
        this.bezeichnung = bezeichnung;
        this.artikelNummer = artikelNummer;
        this.bestand = bestand;
        this.preis = preis;
        this.verfuegbar = verfuegbar;
        this.einkaufsmenge = 0;
    }


    public boolean inStock(){
        if(this.bezeichnung == bezeichnung && this.bestand >= 1){
            verfuegbar = true;
        }
        return verfuegbar;
    }

    public boolean mengeVerfügbar(int m){
        if(this.bezeichnung == bezeichnung && this.einkaufsmenge >= m){
            verfuegbar = true;
        }
        return verfuegbar;
    }



    public int getEinkaufsmenge(){
        return einkaufsmenge;
    }
    public void setEinkaufsmenge(int menge){
        einkaufsmenge += menge;
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
    public void artikelbestandErhoehen(int zahl){
        bestand = bestand + zahl;
    }
    public void artikelbestandVerringern(int zahl){
        bestand = bestand - zahl;
    }
    public void mengeErhoehen(int m){
        einkaufsmenge+= m;
    }
    public void mengeVerringern( int m){
        einkaufsmenge-=  m;
    }

    public int  getArtikelNummer() {
        return artikelNummer;
    }


    public String toString() {
        return "Artikel: "+ bezeichnung+ " Artikelnummer: " + artikelNummer  + " Preis: " + preis + " Bestand: " +bestand;
    }
}

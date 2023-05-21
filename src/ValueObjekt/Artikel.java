

package ValueObjekt;

import java.util.ArrayList;
import java.util.List;

public class Artikel {
    private String bezeichnung;
    private int artikelNummer;
    private int bestand;
    private int stueckzahl;
    private double preis;
    private boolean verfuegbar;
    private List<Ereignis> ereignisse;


    public Artikel(String bezeichnung, int stueckzahl, int artikelNummer, double preis, boolean verfuegbar) {
        this.bezeichnung = bezeichnung;
        this.artikelNummer = artikelNummer;
        this.bestand = bestand;
        this.preis = preis;
        this.verfuegbar = verfuegbar;
        this.stueckzahl= stueckzahl;
        this.ereignisse = new ArrayList<>();
    }

    public Artikel(String bezeichnung, String pc_de_dernière_génération, double v, int artikelnummer) {
    }

    public Artikel(int artikelnummer, double preis) {
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
    public int getStueckzahl(){
        return stueckzahl;
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


    public String toString() {
        return "Artikel: "+ bezeichnung+ " Artikelnummer: " + artikelNummer  + " Preis: " + preis + " Bestand: " +bestand;
    }

    public void setArtikelnummer(int artikelNummer) {
        this.artikelNummer = artikelNummer;
    }

    public void addEreignis(Ereignis ereignis){
        ereignisse.add(ereignis);
        // Aktualisiere den bestand basierend auf dem ereignis
        if (ereignis.getAnzahl()> 0){
            bestand += ereignis.getAnzahl();
        } else {
            bestand -= Math.abs(ereignis.getAnzahl());
        }
    }


    public List<Ereignis> getEreignisse(){
        return ereignisse;
    }

}

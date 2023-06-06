

package ValueObjekt;

import java.io.Serializable;

public class Artikel implements Serializable {
    protected String bezeichnung; //TODO nachdem enum verwendet wird String entfernen, habe ihn nur zum ausprobieren wieder reingesetzt
    protected int artikelNummer;
    protected int bestand;
    protected double einzelpreis;



    public Artikel(String bezeichnung, int artikelNummer,int bestand, double preis) {
        this.bezeichnung = bezeichnung;
        this.artikelNummer = artikelNummer;
        this.bestand = bestand;
        this.einzelpreis = preis;


    }

    //TODO Eine klasse kann keine 2 Konstruktoren habe der untere gehört eig enfernt
    public Artikel(int artikelnummer, String bestand, int preis) {
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


    public String toString() {
        return "Artikel: "+ bezeichnung+ " Artikelnummer: " + artikelNummer  + " Preis: " + einzelpreis + " Bestand: " +bestand;
    }

}

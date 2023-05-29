

package ValueObjekt;

import java.io.Serializable;

public class Artikel implements Serializable {
    protected String bezeichnung; //TODO nachdem enum verwendet wird String entfernen, habe ihn nur zum ausprobieren wieder reingesetzt
    protected int artikelNummer;
    protected int bestand;
    protected double preis;
    protected boolean verfuegbar;


    public Artikel(String bezeichnung, int artikelNummer,int bestand, double preis, boolean verfuegbar) {
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




    // Wir haben ein kleines Problem evtl, ich meine solche Custom Methoden sollen nicht in die ValueObjects selbst rein
    // Frage die Tutorin sonst.
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

}

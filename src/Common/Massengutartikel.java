package Common;

public class Massengutartikel extends Artikel{

    private int erwerbwareMenge;
    public Massengutartikel(String bezeichnung, int artikelNummer,int bestand, double preis, int erwerbwareMenge){
        super(bezeichnung, artikelNummer, bestand, preis);
        this.erwerbwareMenge = erwerbwareMenge;
    }

    public String getBezeichnung(){ return bezeichnung;}
    public int getArtikelNummer(){return artikelNummer;}
    public int getBestand(){return bestand;}
    public double getEinzelpreis(){return einzelpreis;}
    public int getErwerbwareMenge() {
        return erwerbwareMenge;
    }



    public String string(){
        return super.toString() + " Pakungspreis: " + erwerbwareMenge*einzelpreis+"€";
    }
}



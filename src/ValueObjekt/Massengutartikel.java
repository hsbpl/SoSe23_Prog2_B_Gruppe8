package ValueObjekt;

public class Massengutartikel extends Artikel{

    private int erwerbwareMenge;
    public Massengutartikel(String bezeichnung, int artikelNummer,int bestand, double preis, int erwerbwareMenge){
        super(bezeichnung,artikelNummer,bestand, preis);
        this.erwerbwareMenge = erwerbwareMenge;
    }

    public int getErwerbwareMenge() {
        return erwerbwareMenge;
    }


    public String string(){
       return super.toString() + " Pakungspreis: " + erwerbwareMenge*einzelpreis+"€";
    }
}



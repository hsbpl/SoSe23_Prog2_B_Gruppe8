package ValueObjekt;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class RechnungObjekt {

    private Kunde kunde;
    private List<Artikel> gekaufteArtikel;


    public RechnungObjekt(Kunde kunde, List<Artikel>gekaufteArtikel) {
        this.kunde = kunde;
        this.gekaufteArtikel = gekaufteArtikel;
    }

    public Kunde getKunde() {
        return kunde;
    }
    public List<Artikel>getGekaufteArtikel(){
        return gekaufteArtikel;
    }



    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }




    public String toString(){
        String einkauf = "";
        float gesamtpreis = 0;
        for(Artikel a : gekaufteArtikel){
            einkauf +="Artikel: "+  a.getBezeichnung() + " " + a.getPreis()+ "\n";
            gesamtpreis += a.getEinkaufsmenge()*a.getPreis();
        }

        return "-------------------------------"+ "\n"+
                LocalDateTime.now() + "\n" + kunde.toString() + "\n" + einkauf +"\n" + gesamtpreis+ "\n"+
                "Vielen Dank für Ihren Einkauf!"+ "\n" + "-------------------------------";
    }
}

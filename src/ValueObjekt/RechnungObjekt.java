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
        double gesamtpreis = 0;
        double zwischensumme = 0;
        for(Artikel a : gekaufteArtikel){
            zwischensumme = a.getEinkaufsmenge()*a.getPreis();
            einkauf += a.getEinkaufsmenge()+"x "+  a.getBezeichnung() + " " + a.getPreis()+"€" +  "_____" +zwischensumme+"\n";
            gesamtpreis += a.getEinkaufsmenge()*a.getPreis();
        }

        return "-------------------------------"+ "\n"+
                "Ihre Rechnung"+"\n"+
                "\n"+
                LocalDateTime.now() + "\n" + kunde.toString() + "\n" + einkauf +"\n" + "Gesamt:__________" + gesamtpreis+"€"+ "\n" +
                "Vielen Dank für Ihren Einkauf!"+ "\n" + "-------------------------------";
    }
//LocalDateTime auch hier umändern

}

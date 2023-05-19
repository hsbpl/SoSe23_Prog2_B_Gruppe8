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


 /*
 public void setGesamtePreis(double gesamtePreis) {
        this.gesamtePreis = gesamtePreis;
    }

   public double getGesamtpreis() {
        double gesamtpreis = 0.0;
        for (RechnungObjekt artikel : artikelList) {
            gesamtpreis += artikel.getGesamtePreis();
        }
        return gesamtpreis;
    }

  */

    public String toString(){
        String einkauf = "";
        float gesamtpreis = 0;
        for(Artikel a : gekaufteArtikel){
            einkauf ="Artikel: "+  a.getBezeichnung() + " " + a.getPreis();
            gesamtpreis += a.getPreis();
        }

        return LocalDateTime.now() + "\n" + kunde.toString() + "\n" + einkauf +"\n" + gesamtpreis+ "\n"+
                "Vielen Dank für Ihren Einkauf!";
    }
}

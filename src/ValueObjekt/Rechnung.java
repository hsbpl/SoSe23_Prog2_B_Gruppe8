package ValueObjekt;

import java.util.Date;

public class Rechnung {

    private Date datum = new Date();
    private Kunde kunde;
    //TODO Datum hinzufügen
    private Warenkorb warenkorb;


    public Rechnung(Kunde kunde, Warenkorb warenkorb) {
        this.warenkorb= warenkorb;
        this.kunde = kunde;
    }





//TODO toString Methode zum laufen bringen
        public String toString() {

           /*  String einkaufsliste = "";
           int gesamtsumme = 0;
            for (Map.Entry<Artikel, Integer> artikel : warenkorb.getWarenkorb().entrySet()) {
                einkaufsliste += warenkorb.getWarenkorb().get(artikel.getKey().getPreis()) +
                        "x " + "Artikel:" + warenkorb.getWarenkorb().get(artikel.getKey().getBezeichnung().toString()) + "Preis: " + "             " + warenkorb.getWarenkorb().get(artikel.getKey().getPreis());
                gesamtsumme += warenkorb.getWarenkorb().get(artikel.getValue()).intValue() * warenkorb.getWarenkorb().get(artikel.getKey().getPreis());
            }


            return einkaufsliste + "\n" + gesamtsumme;

            */
                return datum.toString()+ "\n" + "Kunde: " + kunde + "\n" + "Warenkorb: " + warenkorb.getWarenkorb().toString();        }


}

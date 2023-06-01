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






        public String toString() {


                return datum.toString()+ "\n" + "Kunde: " + kunde + "\n" + "Warenkorb: " + warenkorb.toString();        }


}

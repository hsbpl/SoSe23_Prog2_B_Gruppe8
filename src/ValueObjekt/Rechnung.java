package ValueObjekt;

import java.util.Date;

public class Rechnung {

    private Date datum = new Date();
    private Kunde kunde;
    private Warenkorb warenkorb;


    public Rechnung(Kunde kunde, Warenkorb warenkorb) {
        this.warenkorb = warenkorb;
        this.kunde = kunde;
    }


    public String toString() {


        return "__________________________\n" + "Rechnung\n" + "\n" + datum.toString() + "\n" + "\nKunden-" + kunde + "\n" + "Ihr Einkauf: " + warenkorb.toString();
    }


}

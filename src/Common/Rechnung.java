package Common;

import java.time.LocalDateTime;

public class Rechnung {

    private LocalDateTime datum;
    private Kunde kunde;
    private Warenkorb warenkorb;


    public Rechnung(Kunde kunde, Warenkorb warenkorb) {
        this.warenkorb = warenkorb;
        this.kunde = kunde;
        this.datum = LocalDateTime.now();
    }


    public String toString() {


        return "__________________________\n" + "Rechnung\n" + "\n" + datum.toString() + "\n" + "\nKunden-" + kunde + "\n" + "Ihr Einkauf: " + warenkorb.toString();
    }


}

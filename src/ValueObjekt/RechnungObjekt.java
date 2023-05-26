package ValueObjekt;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class RechnungObjekt extends Warenkorb {

    private Kunde kunde;
    //TODO Datum hinzufügen



    public RechnungObjekt(Kunde kunde, HashMap<Artikel, Integer> gekaufteArtikel) {
        super();
        this.kunde = kunde;
    }


    public String toString(){
     return super.toString();
    }


}

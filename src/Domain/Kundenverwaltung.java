package Domain;

import ValueObjekt.Artikel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Kundenverwaltung {
private List<Artikel> meinWarenkorb = new ArrayList();

    public Kundenverwaltung(List<Artikel> meinWarenkorb){
        this.meinWarenkorb = meinWarenkorb;
    }

    public List<Artikel> getMeinWarenkorb() {
        return meinWarenkorb;
    }

    /* Kunde legt gewünschte Menge an Artikeln in den Warenkorb, sofern sie vorhanden sind */
    public List<Artikel> reinlegen(Artikel artikel, int menge){
        /*durch ArtikelListe ArrayList gehen nach dem Artikel suchen : Iterator
        *
        * Iterator<Artikel> checkArtikelverwaltung = Artikelverwaltung.iterator;??
        * muss ich noch Fertigstellen
        * */
        for (int i = 0; i < menge; i++) {
            while (artikel.inStock()) {
                meinWarenkorb.add(artikel);
            }
        }
        return meinWarenkorb;
    }

    /* Warenkorb wird geleert*/
    public List<Artikel> leeren (){
        meinWarenkorb.clear();
        return meinWarenkorb;
    }


}


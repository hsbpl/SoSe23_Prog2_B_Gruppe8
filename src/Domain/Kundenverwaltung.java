package Domain;

import ValueObjekt.Artikel;
import ValueObjekt.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Kundenverwaltung {
private List<Artikel> meinWarenkorb = new ArrayList();
private List<User> kRegistrierung = new ArrayList();

    public Kundenverwaltung(List<Artikel> meinWarenkorb){
        this.meinWarenkorb = meinWarenkorb;
        this.kRegistrierung = kRegistrierung;
    }

    public List<Artikel> getMeinWarenkorb() {
        return meinWarenkorb;
    }

    public List<User> getKRegistrierung() {
        return kRegistrierung;
    }

    /* Kunde legt gewünschte Menge an Artikeln in den Warenkorb, sofern sie vorhanden sind */
    public List<Artikel> reinlegen(Artikel artikel, int menge){
        /*durch ArtikelListe ArrayList gehen nach dem Artikel suchen : Iterator
        *
        * Iterator<Artikel> checkArtikelverwaltung = Artikelverwaltung.iterator;??
        * muss ich noch Fertigstellen
        * ---> muss irgendwie auf die Artikelverwaltung zugreifen um sicher zu gehen,
        * das die Artikel aus dem Betand sind
        * */
        for (int i = 0; i < menge; i++) {
            while (artikel.inStock()) {
                meinWarenkorb.add(artikel);
            }
        }
        return meinWarenkorb;
    }

    /* Warenkorb wird geleert*/
    public void leeren (){
        meinWarenkorb.clear();
    }
    /*Es wird überprüft ob das Konto bereits existiert, Kunden können sich registrieren
    * Aber mein Kopf hat grad einen Knoten schau nochmal morgen rein welche for/if Reihenfolge Sinn macht
    * */
    public String registrierung(User neu){
        for (User u: kRegistrierung){
            if (u.equals(neu)){
                return "Das Konto existiert bereits.";
            }
        }
            kRegistrierung.add(neu);
            return "Herzlichen glückwusch zu deinem Eshop Account";
        }

}




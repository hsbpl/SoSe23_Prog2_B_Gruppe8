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

    /* Kunde legt gewünschte Menge an Artikeln in den Warenkorb, sofern sie vorhanden sind
    * Dammit das hier klappt muss in der Artikelverwaltung die Menge eingestellt werden */
    public String reinlegen(Artikel artikel, int menge){
        /* muss irgendwie auf die Liste in der Artikelverwaltung zugreifen, damit man überprüfen kann ob den gewünschte
        * Artikel überhaupt in Shop ist. ---> get Methode in Artikelvervaltung
        *
        * Evtl mit Iterator arbeiten
        *
        * if(artikel.equals(art in Artikelverwaltung)){
        * */
        for (int i = 0; i < menge; i++) {
            while (artikel.inStock()) {
                meinWarenkorb.add(artikel);
            }
        }
        return "Artikel: " + artikel + " Menge: " + menge + "x";
    }

    /* Es wird geprüft ob die zu entfernende Menge die Menge der Vorhandenen Artikel nicht
    * übersteigt und die Artikel werden aus dem Warenkorb entfernt*/
    public String rausnehmen(Artikel artikel, int menge){
        if (menge > 0 && artikel.getBestand() >= menge) {
            for (int i = 0; i < menge; i++) {
                meinWarenkorb.remove(artikel);
            }
        }
        return "Sie haben " + artikel + " " + menge + "x" + "aus dem Warenkorb genommen";
    }

    /* Warenkorb wird geleert*/
    public void leeren(){
        meinWarenkorb.clear();
    }

    /*Es wird überprüft ob das Konto bereits existiert, Kunden können sich registrieren */
    public String registrierung(User neu){
        String ausgabe = "";
        /*kann evtl auch mit Iterator gelöst werden ?*/
            for (User u: kRegistrierung){
                if (u.equals(neu)){
                ausgabe = "Das Konto existiert bereits.";
                } else{
                kRegistrierung.add(neu);
                ausgabe = "Herzlichen glückwusch zu deinem Eshop Account";

                }
            }
                return ausgabe;
    }

    /*
    vlt so mit Itarator?

    public String registrierungIt(User neu) {
        String ausgabe = "";
        Iterator it = kRegistrierung.iterator();

        while (it.hasNext()) {
            if (it.equals(neu)) {
                ausgabe = "Das Konto existiert bereits.";
            } else {
                kRegistrierung.add(neu);
                ausgabe = "Herzlichen glückwusch zu deinem Eshop Account";
            }
        }
        return ausgabe;
    }
    */


    /*Login evtl in Userverwaltung setzten, bin noch unsicher
    *
    *public String login(String username, String password){

        for (User u: kRegistrierung) {
            if (n.getUsername().equals(username) && n.getPassword().equals(password)){
                return "User " + username + "ist angemeldet";
            }
        }
        return "User nicht gefunden";
    }
    *  */

}




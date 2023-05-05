package Domain;

import ValueObjekt.Artikel;
import ValueObjekt.User;

import java.util.ArrayList;
import java.util.List;


public class Kundenverwaltung {
    private List<Artikel> meinWarenkorb = new ArrayList();
    private List<User> kRegistrierung = new ArrayList();


    /* Artikel a = new Artikel("Cola", 19282, 19, 1.5, true);*/


    public Kundenverwaltung(List<Artikel> meinWarenkorb) {
        this.meinWarenkorb = meinWarenkorb;
        this.kRegistrierung = kRegistrierung;
    }

    public List<Artikel> getMeinWarenkorb() {
        return meinWarenkorb;
    }

    public List<User> getKRegistrierung() {
        return kRegistrierung;
    }

    /* Kunde legt gewünschte Menge an Artikeln in den Warenkorb, sofern sie in der zu übergebenden
    Warenbestandsliste vorhanden sind.
     * */
    public void reinlegen(List<Artikel> warenbestand, Artikel artikel, int menge) {
        for (int i = 0; i < menge; i++) {
            if (warenbestand.contains(artikel))
                meinWarenkorb.add(artikel);
        }
    }


    /* Es wird geprüft ob der zu Vorhandenen Artikel im Warenkorb vorhanden ist.
    Der Artikel wird aus dem Warenkorb entfernt
     *
     * */
    public String rausnehmen(Artikel artikel, int menge) {
        if (meinWarenkorb.contains(artikel)) {
            for (int i = 0; i < menge; i++) {
                meinWarenkorb.remove(artikel);
            }
        }
        return "Sie haben " + artikel + " " + menge + "x" + "aus dem Warenkorb genommen";
    }

    /* Warenkorb wird geleert*/
    public void leeren() {
        meinWarenkorb.clear();
    }

    /*Es wird überprüft ob das Konto bereits existiert, Kunden können sich registrieren */
    public String register(User neu) {
        String ausgabe = "";
        if (kRegistrierung.contains(neu)) {
            ausgabe = "Das Konto existiert bereits.";
        } else {
            kRegistrierung.add(neu);
            ausgabe = "Herzlichen glückwusch zu deinem Eshop Account";
        }
        return ausgabe;
    }
    /*Es wird überprüft, ob Username und Passwort übereinstimmen, der Kunde kann sich einloggen. */
        public String login(String username, String password){

            for (User u : kRegistrierung) {
                if (u.getUserName().equals(username) && u.getPasswort().equals(password)) {
                    return "User " + username + "ist angemeldet";
                }
            }
            return "User nicht gefunden";
        }

    }





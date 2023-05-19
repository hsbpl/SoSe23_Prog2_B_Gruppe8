package Domain;

import ValueObjekt.Artikel;
import ValueObjekt.Kunde;
import ValueObjekt.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Kundenverwaltung {
    private List<Artikel> meinWarenkorb;
    private List<User> kRegistrierung;

    public Kundenverwaltung(){
        this.meinWarenkorb = new ArrayList<>();
        this.kRegistrierung = new ArrayList<>();
    }

    public List<Artikel> getMeinWarenkorb() {
        return meinWarenkorb;
    }

    public List<User> getKRegistrierung() {
        return kRegistrierung;
    }

   // Kunde k1 = new Kunde("k1", "123", "Mann", "Thomas", "Am Berg");


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
     
     * */
    public void rausnehmen(Artikel artikel, int menge) {
        for (int i = 0; i < menge; i++) {
            meinWarenkorb.removeIf(art -> art == artikel);
        }
    }

    /* Warenkorb wird geleert*/
    public void leeren() {
        meinWarenkorb.clear();
    }

/*
    //Artikel vom Warenkorb aus Bestand nehmen
    public void bestandAktualisieren(List <Artikel> bestandsliste){
        for(Artikel n : meinWarenkorb){
            bestandsliste.remove(n);
           // n.setBestand(n.getBestand()-1);
        }
    }

 */
    /*Es wird überprüft ob das Konto bereits existiert, Kunden können sich registrieren */
    public String register(User neu) {
        String ausgabe = "";
        if (kRegistrierung.contains(neu)) {
            ausgabe = "Das Konto existiert bereits.";
        } else {
            kRegistrierung.add(neu);
            ausgabe = "Wilkommen auf deinem Eshop Account";
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

    public String einkaufsliste(){
        String s = "";
        float gesamtsumme = 0;
        for(Artikel a : meinWarenkorb){
            s += a.getBezeichnung() + " " + a.getPreis() + "\n";
            gesamtsumme+= a.getPreis();
        }
        return s + "\n" + gesamtsumme;
    }

    }





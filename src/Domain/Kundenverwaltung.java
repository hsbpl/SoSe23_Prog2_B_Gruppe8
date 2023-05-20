package Domain;

import ValueObjekt.Artikel;
import ValueObjekt.Kunde;
import ValueObjekt.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Kundenverwaltung {
    private List<Artikel> meinWarenkorb;
    private List<Kunde> kRegistrierung;

    public Kundenverwaltung(){
        this.meinWarenkorb = new ArrayList<>();
        this.kRegistrierung = new ArrayList<>(Arrays.asList(k1));
    }

    public List<Artikel> getMeinWarenkorb() {
        return meinWarenkorb;
    }

    public List<Kunde> getKRegistrierung() {
        return kRegistrierung;
    }

    //Beispielkunde
   Kunde k1 = new Kunde("k1", "123", "Mann", "Thomas", "Am Berg");


    public Artikel choice(List<Artikel> warenliste, String warenname){
        Artikel art = null;
        for(Artikel a : warenliste){
            if(a.getBezeichnung() == warenname){
                art = a;
            }
        }
        return art;
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


    //gehört eig in die Artikelverwaltung
    public void bestandAktualisieren(){
        for(Artikel n : meinWarenkorb){
            n.setBestand(n.getBestand()-1);
          // bestandsliste.removeIf(art -> art.getBestand() <= 0); // will man den artikel ganz entfernen?
           ;
        }
    }


    /*Es wird überprüft ob das Konto bereits existiert, Kunden können sich registrieren */
    public Kunde register(Kunde neu) {
        if (kRegistrierung.contains(neu)) {
            return null;
        } else {
            kRegistrierung.add(neu);
            return neu;
        }

    }
    /*Es wird überprüft, ob Username und Passwort übereinstimmen, der Kunde kann sich einloggen. */
    public Kunde login(String username, String password){

        for (Kunde u : kRegistrierung) {
            if (u.getUserName().equals(username) && u.getPasswort().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public String registrierteKunden(){
        String liste = "";
        for(Kunde k : kRegistrierung){
            liste += k.toString() +"\n";
        }
        return  liste;
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





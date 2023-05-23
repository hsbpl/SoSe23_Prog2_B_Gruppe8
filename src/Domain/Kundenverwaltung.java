package Domain;

import ValueObjekt.Artikel;
import ValueObjekt.Kunde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Kundenverwaltung {
    private List<Artikel> meinWarenkorb;
    private List<Kunde> kunden;

    //Beispielkunde

    public Kundenverwaltung() {
        this.meinWarenkorb = new ArrayList<>();
        this.kunden = new ArrayList<>(Arrays.asList(new Kunde("k1", "abc", "Mann", "Thomas", 001, "Am Berg" )));
    }

    public List<Artikel> getMeinWarenkorb() {
        return meinWarenkorb;
    }

    public List<Kunde> getKRegistrierung() {
        return kunden;
    }


    public Artikel choice(List<Artikel> warenliste, String warenname) {
        Artikel art = null;
        for (Artikel a : warenliste) {
            if (a.getBezeichnung() == warenname) {
                art = a;
            }
        }
        return art;
    }

    /* Kunde legt gewünschte Menge an Artikeln in den Warenkorb, sofern sie in der zu übergebenden
    Warenbestandsliste vorhanden sind.
     * */
    public void reinlegen(List<Artikel> warenbestand, String artikel, int menge) {
        warenbestand.stream()
                .filter(a -> a.getBezeichnung().equals(artikel))
                .filter(a -> a.inStock() == true)
                .forEach(a -> {
                    a.mengeErhoehen(menge);
                    getMeinWarenkorb().add(a);
                });

    }

    /* Es wird geprüft ob der zu Vorhandenen Artikel im Warenkorb vorhanden ist.
    Der Artikel wird aus dem Warenkorb entfernt

     * */
    public void rausnehmen(String artikel, int menge) {
        meinWarenkorb.stream()
                .filter(a -> a.getBezeichnung().equals(artikel))
                .filter(a -> a.mengeVerfügbar(menge) == true)
                .forEach(a -> {
                    a.mengeVerringern(menge);
                });
    }

    /* Warenkorb wird geleert*/
    public void leeren() {
        meinWarenkorb.stream()
                .forEach(a -> {
                    a.artikelbestandVerringern(a.getEinkaufsmenge());
                });
        meinWarenkorb.clear();
    }

    public String kundenliste() {
        String s = "";
        for(Kunde k : kunden){
            s+= k.getUserName() +"\n";
        }
        return s;
    }
   public void bestandAktualisieren() {
        for (Artikel n : meinWarenkorb) {
            n.artikelbestandVerringern(0);
        }
    }



    /*Es wird überprüft ob das Konto bereits existiert, Kunden können sich registrieren */
    public Kunde register(Kunde neu) {
        if (kunden.contains(neu)) {
            return null;
        } else {
            kunden.add(neu);
            return neu;
        }

    }

    /*Es wird überprüft, ob Username und Passwort übereinstimmen, der Kunde kann sich einloggen. */
    public Kunde login(String username, String password) {
        Kunde loginstatus = null;
        for (Kunde u : kunden) {
            if (u.getUserName().equals(username) && u.getPasswort().equals(password) ){
                loginstatus = u;
            }
        }
        return loginstatus;
    }


    public String registrierteKunden(){
        String liste = "";
        for(Kunde k : kunden){
            liste += k.toString() +"\n";
        }
        return  liste;
    }
    public String einkaufsliste(){
        String s = "";
        float gesamtsumme = 0;
        for(Artikel a : meinWarenkorb){
            s += a.getEinkaufsmenge() + " "+ a.getBezeichnung() + " " + " " + a.getPreis() + "\n";
            gesamtsumme+= a.getEinkaufsmenge()*a.getPreis();
        }
        return s + "\n" + gesamtsumme;
    }




}





package Domain;

import ValueObjekt.Artikel;
import ValueObjekt.Kunde;
import ValueObjekt.Mitarbeiter;
import ValueObjekt.User;

import java.util.*;

public class EShop {


        // ToDO: ArtikelVerwaltung initialisieren (mit beispielartikel)
        // ToDO: Dasselbe mit Kunde und Mitarbeiterverwalunt




    //vorläufig zum testen

        //Beispielartikel
    Artikel cola = new Artikel("Coca Cola 1L", 17890, 40, 2, true);
    Artikel kuchen = new Artikel("Käsekuchen", 19002, 12, 4.99, true);
    Artikel chips = new Artikel("Chips", 39003, 100, 1.79, true);
    Artikel wasser = new Artikel("Wasser)", 3890, 400, 0.49, true);
    Artikel mehl = new Artikel("Mehl", 29290, 0, 0.39, false);

    //voläuffig zum ausprobieren
        List<Artikel> warenList = new ArrayList<>(Arrays.asList(cola, kuchen, chips, wasser, mehl));

    // Beispieluser
    Kunde k1 = new Kunde("k1", "123", "Mann", "Thomas", "Am Berg");
    Mitarbeiter m1 = new Mitarbeiter("mit1", "345", "Mitarbeiterin", "Dieerste", 1829);



    //Initialisierung
    Artikelverwaltung av = new Artikelverwaltung();
    Kundenverwaltung kv = new Kundenverwaltung(meinWarenkorb());
    Mitarbeiterverwaltung mv = new Mitarbeiterverwaltung();



    public List<Artikel> getAlleArtikel() {
        return null; // ToDO: Zugriff auf Artikelverwaltung
    }

    //Zugriff auf Warenkorb des Kunden
    public List<Artikel> meinWarenkorb() {
        return kv.getMeinWarenkorb();
    }

    public List<User> getAlleKundenkonten(){
       return kv.getKRegistrierung();
    }
//sobald artikelliste aus Artikelverwaltung da ist muss warenL mit austauschen
    public String reinWarenkorb(Artikel a, int anzahl){
        kv.reinlegen(warenList,a, anzahl);
        return kv.toString();
    }

    public void rausWarenkorb(Artikel a, int anzahl){
        kv.rausnehmen(a, anzahl);
    }

    public void warenkorbLeeren(){
        kv.leeren();
    }

    public String einloggen(User u){
        return kv.login(u.getUserName(), u.getPasswort());
    }

    public String registrieren(User u){
        return kv.register(u);
    }

}

package Domain;

import ValueObjekt.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

public class EShop {


        // ToDO: ArtikelVerwaltung initialisieren (mit beispielartikel)
        // ToDO: Dasselbe mit Kunde und Mitarbeiterverwalunt


    Artikelverwaltung av = new Artikelverwaltung();
    Kundenverwaltung kv = new Kundenverwaltung();
    Mitarbeiterverwaltung mv = new Mitarbeiterverwaltung();

    Mitarbeiter m1 = new Mitarbeiter("mit1", "345", "Mitarbeiterin", "Dieerste", 1829);


public EShop(Artikelverwaltung av, Kundenverwaltung kv, Mitarbeiterverwaltung mv ){
    this.av = av;
    this.kv = kv;
    this.mv = mv;

}

    public List<Artikel> getAlleArtikel() {
        return null; // ToDO: Zugriff auf Artikelverwaltung
    }

    //Zugriff auf Warenkorb des Kunden
    public List<Artikel> meinWarenkorb() {
        return kv.getMeinWarenkorb();
    }

    //Zugriff auf bereits registrierte Kunden
    public List<User> getAlleKundenkonten(){
       return kv.getKRegistrierung();
    }

    //Artikel im den Warenkorb legen
    //sobald artikelliste aus Artikelverwaltung da ist muss warenL mit austauschen
    public String reinWarenkorb(List<Artikel> warenbestand, Artikel artikel, int menge){
        kv.reinlegen(warenbestand,artikel, menge);
       return kv.toString();
    }

    //Artikel aus dem Warenkorb nehmen
    public void rausWarenkorb(Artikel ausWarenkorb, int menge){
        kv.rausnehmen(ausWarenkorb, menge);
    }

    public void warenkorbLeeren(){
        kv.leeren();
    }

    public String kundenlogin(String username, String password){
        return kv.login(username,password);
    }

    public String kundenregister(User neu){
        return kv.register(neu);
    }

    // bestandsliste aus Artikelverwaltung rein sobald sie da ist
    public String kaufen(List <Artikel> bestandsliste, Kunde kunde){
       String rechnung ="";
        kv.bestandAktualisieren(bestandsliste);
        warenkorbLeeren();
        //Rechnung erstellen noch reintun
        return rechnung;
    }

    public String einkauslist(){
        return kv.einkaufsliste();
    }





}

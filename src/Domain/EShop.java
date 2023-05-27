package Domain;

import ValueObjekt.*;

import java.util.*;

public class EShop {

    Artikelverwaltung av;
    Kundenverwaltung kv;
    Mitarbeiterverwaltung mv;

    public EShop(){
        this.av = new Artikelverwaltung();
        this.mv = new Mitarbeiterverwaltung();
        this.kv = new Kundenverwaltung();
    }
    public List<Artikel> getAlleArtikel() {
        return av.getArtikelListe();
    }
    public List<Mitarbeiter> getMitarbeiterList() {
        return mv.getListMitarbeiter();
    }

    public HashMap<Kunde,Warenkorb> getAlleGespeichertenWarenkörbe(){
        return kv.getGespeicherteWarenkörbe();
    }

    public Warenkorb neuenWarenkorbErstellen(Kunde k){
      return   kv.neuerWarenkorb(k);
    }

    public Mitarbeiter mitarbeiterRegistrieren(Mitarbeiter neu) {
        return mv.mRegister(neu);

    }

    public Mitarbeiter mitarbeiterLogin(String username, String passwort){
        return mv.mitarbeiterEinloggen(username, passwort);
    }


    //Listet alle Artikel aus der Artikelverwaltung auf
    public String artikelListen(){
        return av.artikelAusgeben();
    }

    public void artHinzufügen(Artikel a){
        av.artikelHinzufuegen(a);
    }

    public void bestandHöher(String artikelname, int menge, User u){
        av.bestandErhoehen(artikelname, menge, u);
    }

    public void bestanNiedriger(String artikelname, int menge, User u ){
        av.bestandVerringern(artikelname, menge, u);
    }

    public String alphaArtikel(){
        return av.artikelSortierenNachBezeichnung();
    }

    public String nummerArtikel(){
        return av.artikelSortierenNachArtikelnummer();
    }

    public void artikelAusDemSortimentEntfernen(int artikelnummer){
        av.artikelLoeschen(artikelnummer);
    }
    //Zugriff auf Warenkorb des Kunden


    //Artikel im den Warenkorb legen
    //sobald artikelliste aus Artikelverwaltung da ist muss warenL mit austauschen
    public String inDenWarenkorbLegen(String artikel, int menge, Warenkorb warenkorb){
        kv.reinlegen(av.getArtikelListe(),artikel, menge, warenkorb);
        return kv.toString();
    }

    public String ereignisListeAusgeben(){
        return av.ereignisseAusgeben();
    }

    //Artikel aus dem Warenkorb nehmen
    public void ArtikelAusdemWarenkorbNehmen(String artikel, int menge, Warenkorb warenkorb){
        kv.rausnehmen(artikel, menge, warenkorb);
    }

    public void kaufenUndWarenkorbLeeren(Warenkorb warenkorb){
        kv.beimKaufleeren(warenkorb,av.getArtikelListe());
    }

    public void warenkorbLeeren(Warenkorb warenkorb){
        kv.leeren(warenkorb);;
    }
    public Kunde kundenLogin(String username, String password){
        return kv.login(username,password);
    }

    public Kunde kundenRegistrieren(Kunde neu){
        return kv.register(neu);
    }



    //public void betsandAkt(Kunde kunde){
      //  kv.bestandAktualisieren(kunde, av.getEreignisse());
    //}

    // bestandsliste aus Artikelverwaltung rein sobald sie da ist
    public String kaufenUndRechnungEhalten(Kunde kunde, Warenkorb warenkorb){
        RechnungObjekt rechnung = new RechnungObjekt(kunde, warenkorb);
        String r =  rechnung.toString();
        // kv.beimKaufleeren(warenkorb,av.getArtikelListe());
        return r;
    }

  /*  public String ausgabeKundenkonten(){
        return kv.kundenliste();
    }

   */
    public String ausgabeMitarbeiterkonten(){
        return mv.registrierteMitarbeiter();
    }
    public String einkaufsliste(Warenkorb warenkorb){
        return kv.einkaufsliste(warenkorb);
    }

}

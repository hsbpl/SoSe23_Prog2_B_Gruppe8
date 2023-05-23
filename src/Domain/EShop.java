package Domain;

import ValueObjekt.*;

import java.util.*;

public class EShop {

    Artikelverwaltung av;
    Kundenverwaltung kv;
    Mitarbeiterverwaltung mv;
    public EShop() {
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
    public List<Artikel> meinWarenkorb() {
        return kv.getMeinWarenkorb();
    }

    public List<Kunde> getAlleKundenkonten(){
        return kv.getKRegistrierung();
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

    public void bestandHöher(String artikelname, int menge){
        av.bestandErhoehen(artikelname, menge);
    }

    public void bestanNiedriger(String artikelname, int menge){
        av.bestandVerringern(artikelname, menge);
    }

    public void alphaArtikel(){
        av.artikelSortierenNachBezeichnung();
    }

    public void nummerArtikel(){
        av.artikelSortierenNachArtikelnummer();
    }

    public void rausSortiment(int artikelnummer){
        av.artikelLoeschen(artikelnummer);
    }
    //Zugriff auf Warenkorb des Kunden


    //Artikel im den Warenkorb legen
    //sobald artikelliste aus Artikelverwaltung da ist muss warenL mit austauschen
    public String reinWarenkorb(List<Artikel> warenbestand, String artikel, int menge){
        kv.reinlegen(warenbestand,artikel, menge);
        return kv.toString();
    }

    //Artikel aus dem Warenkorb nehmen
    public void rausWarenkorb(String ausWarenkorb, int menge){
        kv.rausnehmen(ausWarenkorb, menge);
    }

    public void kaufenUndWarenkorbLeeren(){
        kv.beimKaufleeren();
    }

    public void warenkorbLeeren(){
        kv.leeren();;
    }
    public Kunde kundenlogin(String username, String password){
        return kv.login(username,password);
    }

    public Kunde kundenregister(Kunde neu){
        return kv.register(neu);
    }

    public Artikel artikelAuswaehlen(String a){
        return kv.choice(getAlleArtikel(), a);
    }

    public void betsandAkt(){
        kv.bestandAktualisieren();
    }

    // bestandsliste aus Artikelverwaltung rein sobald sie da ist
    public String kaufen(Kunde kunde){
        betsandAkt();
        RechnungObjekt rechnung = new RechnungObjekt(kunde,meinWarenkorb());
        return rechnung.toString();
    }

    public String ausgabeKundenkonten(){
        return kv.kundenliste();
    }
    public String ausgabeMitarbeiterkonten(){
        return mv.registrierteMitarbeiter();
    }
    public String einkaufsliste(){
        return kv.einkaufsliste();
    }

}

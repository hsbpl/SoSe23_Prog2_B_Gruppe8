package Domain;

import Exceptions.ArtikelExistiertNichtException;
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

    public List<Ereignis> getAlleEreignisse(){
        return av.getEreignisse();
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

    public String artikelListen(){
        return av.artikelAusgeben();
    }

    public void artHinzufügen(Artikel a, Mitarbeiter mitarbeiter){
        av.artikelHinzufuegen(a, mitarbeiter);
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

    public String inDenWarenkorbLegen(String artikel, int menge, Warenkorb warenkorb) throws ArtikelExistiertNichtException {
        if(!getAlleArtikel().contains(artikel)){
            throw new ArtikelExistiertNichtException();
        } else {
            kv.reinlegenOderMengeÄndern(getAlleArtikel(), artikel, menge, warenkorb);
            return kv.toString();
        }
    }
    public String ereignisListeAusgeben(){
        return av.ereignisseAusgeben();
    }

    public void kaufenUndWarenkorbLeeren(Warenkorb warenkorb, Kunde kunde){
        kv.beimKaufleerenUndBestandaktualisieren(warenkorb,getAlleArtikel(),kunde, getAlleEreignisse());
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

    public String kaufenUndRechnungEhalten(Kunde kunde, Warenkorb warenkorb){
        Rechnung rechnung = new Rechnung(kunde, warenkorb);
        String r =  rechnung.toString();
        kaufenUndWarenkorbLeeren(warenkorb, kunde);
        return r;
    }

  /*  public String ausgabeKundenkonten(){
        return kv.kundenliste();
    }
   */

    public String ausgabeMitarbeiterkonten(){
        return mv.registrierteMitarbeiter();
    }
    public String artikelImWarenkorb(Warenkorb warenkorb){
        return kv.einkaufsliste(warenkorb);
    }

}

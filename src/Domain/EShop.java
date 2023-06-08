package Domain;

import Exceptions.*;
import ValueObjekt.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import Persistence.FilePersistenceManager;
import ValueObjekt.Artikel;
import ValueObjekt.Mitarbeiter;
import ValueObjekt.Kunde;

public class EShop {
    private String datei = "";

    private Artikelverwaltung av;
    private Kundenverwaltung kv;
    private  Mitarbeiterverwaltung mv;

    public EShop(String datei) throws IOException {
        this.datei = datei;

        //hier pm anlegen
        av = new Artikelverwaltung();
        av.liesDaten(datei + "_ARTIKEL.txt");
        this.mv = new Mitarbeiterverwaltung();
        mv.liesDaten(datei + "_MITARBEITER.txt");
        this.kv = new Kundenverwaltung();
        kv.liesDaten(datei + "_KUNDEN.txt");

    }
    //TODO das mit der ereignisliste klären

    public List<Artikel> getAlleArtikel() {
        return av.getArtikelListe();
    }
    public List<Mitarbeiter> getAlleMitarbeiter() {
        return mv.getListMitarbeiter();
    }
    public List<Kunde> getAlleKunden(){return kv.getKundenListe();}

    public void schreibeArtikel() throws IOException{
        av.schreibeDaten(datei + "_ARTIKEL.txt");
    }

    public void schreibeMitarbeiter() throws IOException{
        mv.schreibeDaten(datei + "_MITARBEITER.txt");
    }

    public void schreibeKunde() throws IOException{
        kv.schreibeDaten(datei + "_KUNDEN.txt");
    }

    public List<Ereignis> getAlleEreignisse(){
        return av.getEreignisse();
    }
    public HashMap<Kunde,Warenkorb> getAlleGespeichertenWarenkörbe(){
        return kv.getGespeicherteWarenkörbeUndKunden();
    }

    public Warenkorb neuenWarenkorbErstellen(Kunde k){
      return   kv.neuerWarenkorb(k);
    }

    public Mitarbeiter mitarbeiterRegistrieren(Mitarbeiter neu) throws UserExistiertBereitsException{
        Mitarbeiter neuerMitarbeiter = mv.mRegister(neu);
        if(neuerMitarbeiter == null){
            throw new UserExistiertBereitsException();
        }
        return neuerMitarbeiter;

    }
    public Mitarbeiter mitarbeiterLogin(String username, String passwort) throws LoginFehlgeschlagenException {
        Mitarbeiter erfolgreicherLogin =mv.mitarbeiterEinloggen(username, passwort);
        if(erfolgreicherLogin == null){
            throw new LoginFehlgeschlagenException();
        } else{
        return erfolgreicherLogin;}
    }

    public String artikelListen(){
        return av.artikelAusgeben();
    }


    public void artHinzufügen(Artikel a, Mitarbeiter mitarbeiter) throws ArtikelExistiertBereitsException {
        av.artikelHinzufuegen(a, mitarbeiter);
    }


    public void bestandErhöhen(String artikelname, int menge, User u) throws ArtikelExistiertNichtException{
        if(!av.bestandErhoehen(artikelname, menge, u)){
            throw new ArtikelExistiertNichtException();}
    }

    //TODO jetzt funtioniert es hier nicht....
    public void bestanNiedriger(String artikelname, int menge, User u ) throws ArtikelExistiertNichtException, UngueltigeMengeException{
       if(!av.bestandVerringern(artikelname, menge, u)){
        throw new ArtikelExistiertNichtException();}
    }

    public String artikelAlphabetischAusgeben(){
        return av.artikelSortierenNachBezeichnung();
    }

    public String artikelNachArtikelnummerGeordnetAusgeben(){
        return av.artikelSortierenNachArtikelnummer();
    }

    public String ereignisseNachDatum(){return av.ereignisseSortiertNachDatum();}

    public void artikelAusDemSortimentEntfernen(int artikelnummer) throws ArtikelExistiertNichtException{
       if( av.artikelLoeschen(artikelnummer)){
           throw new ArtikelExistiertNichtException();
       };
    }
    public void inDenWarenkorbLegen(String artikel, int menge, Warenkorb warenkorb) throws ArtikelExistiertNichtException, UngueltigeMengeException {

       kv.reinlegenOderMengeÄndern(getAlleArtikel(), artikel, menge, warenkorb);


    }
    public String ereignisListeAusgeben(){
        return av.ereignisseAusgeben();
    }

    public void kaufenUndWarenkorbLeeren(Warenkorb warenkorb, Kunde kunde) throws WarenkorbIstLeerException{
        kv.beimKaufleerenUndBestandaktualisieren(warenkorb,getAlleArtikel(),kunde, getAlleEreignisse());
    }
    public void warenkorbLeeren(Warenkorb warenkorb){
        kv.leeren(warenkorb);;
    }
    public Kunde kundenLogin(String username, String password) throws LoginFehlgeschlagenException{
        Kunde erfolgreicherLogin=kv.login(username,password);
        if(erfolgreicherLogin == null){
            throw new LoginFehlgeschlagenException();
        } else{
            return erfolgreicherLogin;}
    }

    public Kunde kundenRegistrieren(Kunde neu) throws UserExistiertBereitsException {
        Kunde neuerKunde = kv.register(neu);
        if(neuerKunde == null){
            throw new UserExistiertBereitsException();
        }else{
        return neuerKunde;}
    }

    public String kaufenUndRechnungEhalten(Kunde kunde, Warenkorb warenkorb) throws WarenkorbIstLeerException{
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

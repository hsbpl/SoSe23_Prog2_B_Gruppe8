package Domain;

import Exceptions.*;
import ValueObjekt.*;

import java.io.File;
import java.util.*;
import Persistence.FilePersistenceManager;
import ValueObjekt.Artikel;
import ValueObjekt.Mitarbeiter;
import ValueObjekt.Kunde;

public class EShop {

    List<Artikel> artikelList = FilePersistenceManager.loadArticles();
    List<Mitarbeiter> mitarbeiterList = FilePersistenceManager.loadEmployees();
    List<Kunde> kundeList = FilePersistenceManager.loadCustomers();
    //Die methoden werden verwendet um die gespeicherten ArtikelListe usw. aus den Dateien zu laden. So werden die Daten beim Start des eShops automatisch geladen.
    Artikelverwaltung av;
    Kundenverwaltung kv;
    Mitarbeiterverwaltung mv;

    public EShop(){
        this.av = new Artikelverwaltung();
        this.mv = new Mitarbeiterverwaltung();
        this.kv = new Kundenverwaltung();
    }
    //Klassen aus der Persistence:
    public static final String ARTICLE_FILE = "artikel.txt";
    public static final String EMPLOYEE_FILE = "employees.txt";
    public static final String CUSTOMER_FILE = "customers.txt";

    // Ergänze die Methoden saveData und loadData
   public void saveData() {
        FilePersistenceManager.saveData(av.getArtikelListe(), mv.getListMitarbeiter(), kv.getKundenListe());
    }

    public void loadData() {
        FilePersistenceManager.loadData(av.getArtikelListe(), mv.getListMitarbeiter(), kv.getKundenListe());
    }
       //Die Methoden saveData() und loadData() werden verwendet, um die Daten zu speichern bzw. zu laden. Sie rufen die entsprechenden Methoden in der FilePersistenceManager-Klasse auf, um die Daten in den Dateien zu persistieren.





    public List<Artikel> getAlleArtikel() {
        return av.getArtikelListe();
    }
    public List<Mitarbeiter> getAlleMitarbeiter() {
        return mv.getListMitarbeiter();
    }
    public List<Kunde> getAlleKunden(){return kv.getKundenListe();}



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
        av.bestandErhoehen(artikelname, menge, u);
    }

    public void bestanNiedriger(String artikelname, int menge, User u ) throws ArtikelExistiertNichtException, UngueltigeMengeException {
        av.bestandVerringern(artikelname, menge, u);
    }

    public String artikelAlphabetischAusgeben(){
        return av.artikelSortierenNachBezeichnung();
    }

    public String artikelNachArtikelnummerGeordnetAusgeben(){
        return av.artikelSortierenNachArtikelnummer();
    }

    public void artikelAusDemSortimentEntfernen(int artikelnummer) throws ArtikelExistiertNichtException{
        av.artikelLoeschen(artikelnummer);
    }
    //TODO ArtikelExistiertnicht--> nochmal schauen  , UngültigeMenge
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

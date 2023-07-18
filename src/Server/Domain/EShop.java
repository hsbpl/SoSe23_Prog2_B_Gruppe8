package Server.Domain;

import Common.*;
import Common.Exceptions.*;
import Common.Ereignis;
import Common.Massengutartikel;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

import Common.Kunde;

public class EShop implements EShopInterface {

    private String datei = "";
    private Artikelverwaltung av;
    private Kundenverwaltung kv;
    private Mitarbeiterverwaltung mv;
    private PrintStream socketOut;
    public EShop(String datei) throws IOException {
        this.datei = datei;
        av = new Artikelverwaltung();
        mv = new Mitarbeiterverwaltung();
        kv = new Kundenverwaltung();

        av.liesDaten(datei + "_ARTIKEL.txt");
        mv.liesDaten(datei + "_MITARBEITER.txt");
        kv.liesDaten(datei + "_KUNDEN.txt");
        av.liesDatenEreignisse(datei + "_EREIGNIS.txt");

    }

    @Override
    public List<Artikel> getAlleArtikel() {
        return av.getArtikelListe();
    }
    @Override
    public List<Mitarbeiter> getAlleMitarbeiter() {
        return mv.getListMitarbeiter();
    }
    @Override
    public List<Kunde> getAlleKunden(){return kv.getKundenListe();}
    @Override
    public List<Ereignis> getAlleEreignisse(){return av.getEreignisListe();}

    @Override
    public void schreibeArtikel() throws IOException{
        av.schreibeDaten(datei + "_ARTIKEL.txt");
        schreibeEreignis();
    }

    @Override
    public void schreibeMitarbeiter() throws IOException{
        mv.schreibeDaten(datei + "_MITARBEITER.txt");
    }

    @Override
    public void schreibeKunde() throws IOException{
        kv.schreibeDaten(datei + "_KUNDEN.txt");
    }
    @Override
    public void schreibeEreignis() throws IOException{
        av.schreibeDatenEreignisse(datei + "_EREIGNIS.txt");
    }

    public Map<Kunde,Warenkorb> getAlleGespeichertenWarenkörbe(){
        return kv.getGespeicherteWarenkörbeUndKunden();
    }

    @Override
    public Warenkorb neuenWarenkorbErstellen(Kunde k){
      return   kv.neuerWarenkorb(k);
    }

    @Override
    public Mitarbeiter mitarbeiterRegistrieren(Mitarbeiter neu) throws UserExistiertBereitsException, LeeresTextfieldException{
        Mitarbeiter neuerMitarbeiter = mv.mRegister(neu);
        if(neuerMitarbeiter == null){
            throw new UserExistiertBereitsException();
        }
        return neuerMitarbeiter;

    }
    @Override
    public Mitarbeiter mitarbeiterLogin(String username, String passwort) throws LoginFehlgeschlagenException {
        Mitarbeiter erfolgreicherLogin =mv.mitarbeiterEinloggen(username, passwort);
        if(erfolgreicherLogin == null){
            throw new LoginFehlgeschlagenException();
        }

        return erfolgreicherLogin;
    }

    public String artikelListen(){
        return av.artikelAusgeben();
    }


    @Override
    public void artHinzufügen(Artikel a, Mitarbeiter mitarbeiter) throws ArtikelExistiertBereitsException,LeeresTextfieldException {
        av.artikelHinzufuegen(a, mitarbeiter);
    }
    @Override
    public void massengutArtikelHinzufügen(Massengutartikel a, Mitarbeiter mitarbeiter) throws ArtikelExistiertBereitsException, LeeresTextfieldException {
        av.massengutArtikelHinzufuegen(a, mitarbeiter);
    }



    @Override
    public void bestandErhöhen(String artikelname, int menge, User u) throws ArtikelExistiertNichtException, LeeresTextfieldException{
        if(!av.bestandErhoehen(artikelname, menge, u)){
            throw new ArtikelExistiertNichtException();}
    }


    @Override
    public void bestanNiedriger(String artikelname, int menge, User u) throws ArtikelExistiertNichtException, UngueltigeMengeException, LeeresTextfieldException{
       if(!av.bestandVerringern(artikelname, menge, u)){
        throw new ArtikelExistiertNichtException();}
    }

    public String artikelAlphabetischAusgebenString(){
        return av.artikelSortierenNachBezeichnungString();
    }

    @Override
    public List<Artikel>  artikelSortierenNachBezeichnung(){
       return av.artikelSortierenNachBezeichnung();
    }

    public String artikelNachArtikelnummerGeordnetAusgebenString(){
        return av.artikelSortierenNachArtikelnummerString();
    }

    @Override
    public List<Artikel> artikelNachArtikelnummerGeordnetAusgeben(){
        return av.artikelSortierenNachArtikelnummer();
    }

    @Override
    public List<Ereignis> ereignisseNachDatum(){return av.ereignisseSortiertNachDatum();}

    @Override
    public void inDenWarenkorbLegen(String artikel, int menge, Warenkorb warenkorb) throws ArtikelExistiertNichtException, UngueltigeMengeException {

       kv.reinlegenOderMengeÄndern(getAlleArtikel(), artikel, menge, warenkorb);
    }

    @Override
    public void artikelAusWarenkorbEntfernen(String artikel, Warenkorb warenkorb){
        kv.artikelAusDemWarenkorbNehmen(artikel, warenkorb);
    }
    public String ereignisListeAusgeben(){
        return av.ereignisseAusgeben();
    }

    public void kaufenUndWarenkorbLeeren(Warenkorb warenkorb, Kunde kunde) throws WarenkorbIstLeerException {
        kv.beimKaufleerenUndBestandaktualisieren(warenkorb,getAlleArtikel(),kunde, getAlleEreignisse());
    }
    @Override
    public void warenkorbLeeren(Warenkorb warenkorb){
        kv.leeren(warenkorb);;
    }
    @Override
    public Kunde kundenLogin(String username, String password) throws LoginFehlgeschlagenException{
        Kunde erfolgreicherLogin=kv.login(username,password);
        if(erfolgreicherLogin == null){
            throw new LoginFehlgeschlagenException();
        } else{
            return erfolgreicherLogin;}
    }

    @Override
    public Kunde kundenRegistrieren(Kunde neu) throws UserExistiertBereitsException, LeeresTextfieldException {
        Kunde neuerKunde = kv.register(neu);
        if(neuerKunde == null){
            throw new UserExistiertBereitsException();
        }else{
            System.out.println("es hat geklappt");
        return neuerKunde;}
    }

    @Override
    public String kaufenUndRechnungEhalten(Kunde kunde, Warenkorb warenkorb) throws WarenkorbIstLeerException, IOException {
        System.out.println(warenkorb.toString());
        Rechnung rechnung = new Rechnung(kunde, warenkorb);
        System.out.println(rechnung.toString());
        String r =  rechnung.toString();
        System.out.println(r);
        kaufenUndWarenkorbLeeren(warenkorb, kunde);
        schreibeArtikel();
        return r;
    }


    @Override
    public void handleGibHalloServer() throws IOException {
        String response = "Hallo, Client!";
        socketOut.println(response);
    }

    public String artikelImWarenkorb(Warenkorb warenkorb){
        return kv.einkaufsliste(warenkorb);
    }





}

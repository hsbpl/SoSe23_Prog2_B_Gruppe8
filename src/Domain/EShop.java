package Domain;

import ValueObjekt.*;

import java.util.*;

public class EShop {
private List<Mitarbeiter> mitarbeiterList ;
    private List<Artikel> artikelList ;
    private List<Kunde> kundeList ;

        // ToDO: ArtikelVerwaltung initialisieren (mit beispielartikel)
        // ToDO: Dasselbe mit Kunde und Mitarbeiterverwalunt


    Artikelverwaltung av;
    Kundenverwaltung kv;
    Mitarbeiterverwaltung mv;

    Mitarbeiter m1 = new Mitarbeiter("mit1", "341", "Kevine", "Michele", 1821);
    Mitarbeiter m2 = new Mitarbeiter("mit2", "342", "Sajana", "Dieerste", 1822);
    Mitarbeiter m3 = new Mitarbeiter("mit3", "343", "Roha", "Dieste", 1823);
    Mitarbeiter m4 = new Mitarbeiter("mit4", "344", "Lars", "Dieer", 18294);
    Mitarbeiter m5 = new Mitarbeiter("mit5", "345", "Philipp", "erste", 1825);


public EShop(){
    this.av = new Artikelverwaltung();
    this.kv = new Kundenverwaltung();
    this.mv = new Mitarbeiterverwaltung(m1.getUserName(), m1.getPasswort(), m1.getNachname(), m1.getVorname());

}

    public List<Mitarbeiter> getMitarbeiterList() {
        return mitarbeiterList;
    }
    // zeigen, ob der aktuelle Benutzer ein Mitarbeiter ist oder nicht
    public boolean istMitarbeiter = false;

   //  zeigen, ob der aktuelle Benutzer ein Kunde ist oder nicht
    public boolean istKunde = false;
    //   zeigen, ob der aktuelle Benutzer eingelogt ist oder nicht
    public boolean istlog = false;

    public User einloggen(String benutzername, String passwort) {
        if (mitarbeiterList.stream().anyMatch(m -> m.getUserName().equals(benutzername))) {
            for (Mitarbeiter mitarbeiter : mitarbeiterList) {
                if (mitarbeiter.getUserName().equals(benutzername) && mitarbeiter.getPasswort().equals(passwort)) {
                    istMitarbeiter = true;
                    istlog = true;
                    return mitarbeiter;
                }
            }
        } else if (kundeList.stream().anyMatch(k -> k.getUserName().equals(benutzername))) {
            for (Kunde kunde : kundeList) {
                if (kunde.getUserName().equals(benutzername) && kunde.getPasswort().equals(passwort)) {
                    istKunde = true;
                    istlog = true;
                    return kunde;
                }
            }
        }
        return null;
    }

    public void mitarbeiterRegistrieren(Mitarbeiter mitarbeiter) {
        mitarbeiterList.add(mitarbeiter);

    }
    public boolean istEinloggen(){return istlog;}


    public List<Artikel> getAlleArtikel() {
       return av.getArtikelListe();
    }

    //Listet alle Artikel aus der Artikelverwaltung auf
    public String artikelListen(){
    return av.artikelAusgeben();
    }
    //Zugriff auf Warenkorb des Kunden
    public List<Artikel> meinWarenkorb() {
        return kv.getMeinWarenkorb();
    }

    //Zugriff auf bereits registrierte Kunden
    public List<Kunde> getAlleKundenkonten(){
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
    public String kaufen(List <Artikel> bestandsliste, Kunde kunde){
        betsandAkt();
        RechnungObjekt rechnung = new RechnungObjekt(kunde,meinWarenkorb());
        warenkorbLeeren();
        return rechnung.toString();
    }

    public String einkaufsliste(){
        return kv.einkaufsliste();
    }

}

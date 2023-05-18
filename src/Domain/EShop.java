package Domain;

import ValueObjekt.*;

public class EShop {

        private Artikelverwaltung av;
        private Kundenverwaltung kv;
        private Mitarbeiterverwaltung mv;

        public EShop(Artikelverwaltung av, Kundenverwaltung kv, Mitarbeiterverwaltung mv) {
            this.av = av;
            this.kv = kv;
            this.mv = mv;
        }

        public void initializierung() {
            // Erstellung einiger Artikel
            Artikel cola = new Artikel("Coca Cola 1L", 17890, 40, 2, true);
            Artikel kuchen = new Artikel("Käsekuchen", 19002, 12, 4.99, true);
            Artikel chips = new Artikel("Chips", 39003, 100, 1.79, true);
            Artikel wasser = new Artikel("Wasser)", 3890, 400, 0.49, true);
            Artikel mehl = new Artikel("Mehl", 29290, 0, 0.39, false);

            // Hinzufügen von Artikeln zur Artikelverwaltung
            av.artikelHinzufuegen(cola);
            av.artikelHinzufuegen(kuchen);
            av.artikelHinzufuegen(chips);
            av.artikelHinzufuegen(wasser);
            av.artikelHinzufuegen(mehl);

            // Einige Kunden anlegen
            Kunde kevine = new Kunde("kunde1", "123", "Doe", "John", "langemarstr156");
            Kunde Sandjana = new Kunde("kunde2", "456", "Doe", "Jane", "peterstre123");
            Kunde Roha= new Kunde("kunde3", "789", "Smith", "Bob", "mainstr23");

            // Hinzufügen von Kunden zur Kundenverwaltung
            kv.register(kevine);
            kv.register(Sandjana);
            kv.register(Roha);

            // Erstellen einiger Mitarbeiter
            Mitarbeiter Lars = new Mitarbeiter("mit1", "345", "Mitarbeiterin", "Dieerste", 1829);
            Mitarbeiter Phillip = new Mitarbeiter("mit2", "678", "Mitarbeiter", "Dazweite", 1975);

            // Hinzufügen von Mitarbeitern zur Mitarbeiterverwaltung
            mv.neuenMitarbeiterRegistrieren(Lars);
            mv.neuenMitarbeiterRegistrieren(Phillip);
        }

        //Methode, um die Verwaltung von Artikeln zu erhalten
        public Artikelverwaltung getArtikelverwaltung() {
            return av;
        }

        // Methode, um die Verwaltung von Kunden zu erhalten
        public Kundenverwaltung getKundenverwaltung() {
            return kv;
        }

        // Methode, um die Verwaltung von Mitarbeitern zu erhalten
        public Mitarbeiterverwaltung getMitarbeiterverwaltung() {
            return mv;
        }


}


        // ToDO: ArtikelVerwaltung initialisieren (mit beispielartikel)
        // ToDO: Dasselbe mit Kunde und Mitarbeiterverwalunt


   /* Artikelverwaltung av = new Artikelverwaltung();
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
        //artikel aus der Liste des Warenkorb aus dem Bestand der av nehmen
        RechnungObjekt rechnung = new RechnungObjekt(kunde,meinWarenkorb());
        warenkorbLeeren();
        return rechnung.toString();
    }

    public String einkauslist(){
        return kv.einkaufsliste();
    }

<<<<<<< HEAD




}*/


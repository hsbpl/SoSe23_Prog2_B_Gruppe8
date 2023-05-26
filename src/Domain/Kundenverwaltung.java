package Domain;

import ValueObjekt.Artikel;
import ValueObjekt.Ereignis;
import ValueObjekt.Kunde;
import ValueObjekt.Warenkorb;

import java.util.*;


public class Kundenverwaltung {

    private List<Kunde> kunden;
    private List<Warenkorb> warenkörbe;
    private HashMap<Kunde,Warenkorb> gespeicherteWarenkörbe;


    public Kundenverwaltung() {
        this.kunden = new ArrayList<>(Arrays.asList(new Kunde("k1", "abc", "Mann", "Thomas",001, "Am Berg")));
        this.warenkörbe = new ArrayList<>();
        this.gespeicherteWarenkörbe = new HashMap<>();
        }


    public List<Kunde> getKRegistrierung() {
        return kunden;
    }

    public List<Warenkorb> getWarenkörbe() {
        return warenkörbe;
    }
    public HashMap<Kunde, Warenkorb> getGespeicherteWarenkörbe() {
        return gespeicherteWarenkörbe;
    }


    public void  mengeVerringern(Artikel artikel, int menge, Warenkorb warenkorb){
        int aktuelleMenge = warenkorb.getWarenkorb().get(artikel);
        aktuelleMenge -=menge;
    }
    public Artikel choice(List<Artikel> warenliste, String warenname) {
        Artikel art = null;
        for (Artikel a : warenliste) {
            if (a.getBezeichnung() == warenname) {
                art = a;
            }
        }
        return art;
    }

    /* Kunde legt gewünschte Menge an Artikeln in den Warenkorb, sofern sie in der zu übergebenden
    Warenbestandsliste vorhanden sind.
     * */
    public void reinlegen(List<Artikel> warenbestand, String artikel,int menge, Warenkorb warenkorb) {
        warenbestand.stream()
                .filter(a -> a.getBezeichnung().equals(artikel))
                .forEach(a -> { warenkorb.getWarenkorb().put(a,menge);

                });

    }

    public void rausnehmen(String artikel, int menge, Warenkorb warenkorb) {
     warenkorb.getWarenkorb().keySet().stream()
                .filter(a -> a.getBezeichnung().equals(artikel))
                .forEach(a -> {
                   mengeVerringern(a, menge, warenkorb);
                });
    }




    /* Warenkorb wird geleert*/
    public void beimKaufleeren(Warenkorb warenkorb, List<Artikel> warenbestand) {
        Map<Artikel, Integer> warenkorbMap = warenkorb.getWarenkorb();

        for (Map.Entry<Artikel, Integer> entry : warenkorbMap.entrySet()) {
            Artikel artikel = entry.getKey();
            int menge = entry.getValue();

            for (Artikel bestandsArtikel : warenbestand) {
                if (bestandsArtikel.equals(artikel)) {
                    bestandsArtikel.ArtikelbestandVerringern(menge);
                    break;
                }
            }
        }
        gespeicherteWarenkörbe.remove(warenkorb);

    }


    public void leeren(Warenkorb warenkorb) {

        warenkorb.getWarenkorb().clear();
    }
    public String kundenliste() {
        String s = "";
        for(Kunde k : kunden){
            s+= k.getUserName() +"\n";
        }
        return s;
    }
   /* public void bestandAktualisieren(Kunde k, List <Ereignis> ereignisse) {
        for (Artikel n : meinWarenkorb) {
            Ereignis e = new Ereignis("Bestand verringert",n.getEinkaufsmenge(),n,k);
            n.ArtikelbestandVerringern(0);
            ereignisse.add(e);
        }
    }

    */



    /*Es wird überprüft ob das Konto bereits existiert, Kunden können sich registrieren */
    public Kunde register(Kunde neu) {
        if (kunden.contains(neu)) {
            return null;
        } else {
            kunden.add(neu);
            Warenkorb meinWarenkorb = new Warenkorb();
            gespeicherteWarenkörbe.put(neu, meinWarenkorb);
            return neu;
        }

    }

    /*Es wird überprüft, ob Username und Passwort übereinstimmen, der Kunde kann sich einloggen. */
    public Kunde login(String username, String password) {
        Kunde loginstatus = null;
        for (Kunde u : kunden) {
            if (u.getUserName().equals(username) && u.getPasswort().equals(password) ){
                loginstatus = u;
                Warenkorb meinWarenkorb = new Warenkorb();
                gespeicherteWarenkörbe.put(u, meinWarenkorb);
            }
        }
        return loginstatus;
    }


    public String registrierteKundenAusgeben(){
        String liste = "";
        for(Kunde k : kunden){
            liste += k.toString() +"\n";
        }
        return  liste;
    }
    public String einkaufsliste(Warenkorb warenkorb){
        return warenkorb.toString();
    }




}





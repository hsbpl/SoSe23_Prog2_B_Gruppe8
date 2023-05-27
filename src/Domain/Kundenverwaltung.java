package Domain;

import ValueObjekt.Artikel;
import ValueObjekt.Ereignis;
import ValueObjekt.Kunde;
import ValueObjekt.Warenkorb;

import java.util.*;


public class Kundenverwaltung {


    private HashMap<Kunde, Warenkorb> kundenUndDazugehörigeWarenkörbe;

    public Kundenverwaltung() {
        this.kundenUndDazugehörigeWarenkörbe = new HashMap<>();
    }


    public HashMap<Kunde, Warenkorb> getGespeicherteWarenkörbe() {
        return kundenUndDazugehörigeWarenkörbe;
    }


    public int  mengeVerringern(Artikel artikel, int menge, Warenkorb warenkorb){
        int aktuelleMenge = warenkorb.getWarenkorb().get(artikel).intValue();
        int neueMenge = aktuelleMenge - menge;
        warenkorb.getWarenkorb().put(artikel, neueMenge);
        return neueMenge;
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
                .findFirst()
                .ifPresent(a -> { warenkorb.getWarenkorb().put(a,menge);
                //.forEach(a -> { warenkorb.getWarenkorb().put(a,menge);

                });

    }

    public void rausnehmen(String artikel, int menge, Warenkorb warenkorb) {
     warenkorb.getWarenkorb().keySet().stream()
                .filter(a -> a.getBezeichnung().equals(artikel))
                 .findFirst()
             .ifPresent(a -> {
                 mengeVerringern(a, menge, warenkorb);
             });
    }




    /* Warenkorb wird geleert*/ //TODO Methode checken, beim Bestand wurde bei, Vrsuch z viel abgezogen und Rechnung wurde nicht erstellt
    public void beimKaufleeren(Warenkorb warenkorb, List<Artikel> warenbestand) {
        warenkorb.getWarenkorb().forEach((artikel, integer) -> {
            warenbestand.stream()
                    .filter(bestandsartikel -> bestandsartikel.equals(artikel))
                    .forEach(bestandsartikel -> {
                        int zuReduzierendeMenge = integer;
                        int neuerBestand = bestandsartikel.getBestand() - zuReduzierendeMenge;
                        bestandsartikel.setBestand(neuerBestand);
                    });
        });
/*
        warenkorb.getWarenkorb().keySet().stream()
                .filter(a -> warenbestand.equals(a))
                .forEach(a -> { int bestandVerringern = warenkorb.getWarenkorb().get(a).intValue();
                    int aktualisierterBestand = a.getBestand() - bestandVerringern;
                });


                  /*  warenbestand.stream()
                            .filter(bestadartikel -> a.equals(warenbestand))
                            .forEach(bestadartikel -> bestadartikel.ArtikelbestandVerringern());
                        })

                   */

       /* Map<Artikel, Integer> warenkorbMap = warenkorb.getWarenkorb();

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

        */

    }


    public void leeren(Warenkorb warenkorb) {

        warenkorb.getWarenkorb().clear();
    }
   /*public void bestandAktualisieren(Kunde k, List <Ereignis> ereignisse) {
        for (Artikel n : meinWarenkorb) {
            Ereignis e = new Ereignis("Bestand verringert",n.getEinkaufsmenge(),n,k);
            n.ArtikelbestandVerringern(0);
            ereignisse.add(e);
        }
    }

    */



    /*Es wird überprüft ob das Konto bereits existiert, Kunden können sich registrieren */
    public Kunde register(Kunde neu) {
        if (kundenUndDazugehörigeWarenkörbe.containsKey(neu)) {
            return null;
        } else {
            kundenUndDazugehörigeWarenkörbe.put(neu, neuerWarenkorb(neu));
            return neu;
        }

    }

    /*Es wird überprüft, ob Username und Passwort übereinstimmen, der Kunde kann sich einloggen. */
    public Kunde login(String username, String password) {
        return kundenUndDazugehörigeWarenkörbe.keySet().stream()
                .filter(a -> a.getUserName().equals(username) && a.getPasswort().equals(password))
                .findFirst()
                .orElse(null);
    }


    public Warenkorb neuerWarenkorb(Kunde k){
        Warenkorb w = new Warenkorb();
        return kundenUndDazugehörigeWarenkörbe.put(k,w);
    }



   /* public String registrierteKundenAusgeben(){

        String liste = "";
        for(Kunde k : kunden){
            liste += k.toString() +"\n";
        }
        return  liste;
    }

    */
    public String einkaufsliste(Warenkorb warenkorb){
        return warenkorb.getWarenkorb().toString();
    }




}





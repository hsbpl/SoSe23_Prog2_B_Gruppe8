package Domain;

import ValueObjekt.Artikel;
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


   /* public Artikel choice(List<Artikel> warenliste, String warenname) {
        Artikel art = null;
        for (Artikel a : warenliste) {
            if (a.getBezeichnung() == warenname) {
                art = a;
            }
        }
        return art;
    }

    */

// man kann Waren in den Warenkorb legen oder die Menge Bereits vorhandener Artikel umändern.
    public void reinlegenOderMengeÄndern(List<Artikel> warenbestand, String artikel, int menge, Warenkorb warenkorb) {
        warenbestand.stream()
                .filter(a -> a.getBezeichnung().equals(artikel))
                .findFirst()
                .ifPresent(a -> {warenkorb.getWarenkorb().put(a,menge);

                });

    }

    //TODO sollte funktionieren aber dadurch das die String Methode nicht funktioniert habe ich gerade keine gewissheit
    //Die im Warenkorb enthaltenen Waren werden mit dem Warenbestand abgeglichen und deren Bestand wird aktualisiert.
    // danach wird der Warenkorb geleert
    public void beimKaufleerenUndBestandaktualisieren(Warenkorb warenkorb, List<Artikel> warenbestand) {
        warenkorb.getWarenkorb().forEach((artikel, menge) -> {
            warenbestand.stream()
                    .filter(bestandsartikel -> bestandsartikel.equals(artikel))
                    .forEach(bestandsartikel -> {
                        int zuReduzierendeMenge =menge;
                        int aktuellerBestand = bestandsartikel.getBestand() - zuReduzierendeMenge;
                        bestandsartikel.setBestand(aktuellerBestand);
                    });
        });

        leeren(warenkorb);

    }

//Der Warenkorb wird kompltett geleert
    public void leeren(Warenkorb warenkorb) {
        warenkorb.getWarenkorb().clear();
    }


    /*Es wird überprüft ob das Konto bereits existiert, Kunden können sich registrieren */
    public Kunde register(Kunde neu) {
        Kunde k = null;
        if (!kundenUndDazugehörigeWarenkörbe.containsKey(neu)){
        k = neu;
        }
        return k;
    }

    /*Es wird überprüft, ob Username und Passwort übereinstimmen, der Kunde kann sich einloggen. */
    public Kunde login(String username, String password) {
        return kundenUndDazugehörigeWarenkörbe.keySet().stream()
                .filter(a -> a.getUserName().equals(username) && a.getPasswort().equals(password))
                .findFirst()
                .orElse(null);
    }

    //Ein neuer Warenkorb wird erstellt und in der Hashmap gespeichert
    public Warenkorb neuerWarenkorb(Kunde k){
        Warenkorb w = new Warenkorb();
        kundenUndDazugehörigeWarenkörbe.put(k,w);
        return kundenUndDazugehörigeWarenkörbe.get(k);
    }


    //Liste der im Warenkorb gelegten Artikel wird ausgegeben
    public String einkaufsliste(Warenkorb warenkorb){
        return warenkorb.getWarenkorb().toString();
    }


}





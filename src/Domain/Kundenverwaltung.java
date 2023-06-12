package Domain;

import Exceptions.ArtikelExistiertNichtException;
import Exceptions.UngueltigeMengeException;
import Exceptions.UserExistiertBereitsException;
import Exceptions.WarenkorbIstLeerException;
import Persistence.FilePersistenceManager;
import Persistence.PersistenceManager;
import ValueObjekt.*;
import ValueObjekt.Enum;

import java.io.IOException;
import java.net.StandardSocketOptions;
import java.util.*;


public class Kundenverwaltung {
    private PersistenceManager pm = new FilePersistenceManager();

    private HashMap<String, Kunde> kundenliste;
    private HashMap<Kunde, Warenkorb> kundenUndDazugehörigeWarenkörbe;
    private Kunde kunde;
    private Artikelverwaltung av = new Artikelverwaltung();

    //Beispielkunde
    //Kunde k1 = new Kunde("k1", "abc", "Mann", "Thomas",  "Am Berg");

    public void liesDaten(String datei) throws IOException {
        this.kundenUndDazugehörigeWarenkörbe = new HashMap<>();
        try {
            kundenliste = pm.leseKundenListe(datei);
            for (Kunde kunde: kundenliste.values()) {
                kundenUndDazugehörigeWarenkörbe.put(kunde, null);
            }

        } catch (UserExistiertBereitsException e) {
            throw new RuntimeException(e);
        }

    }
    public void schreibeDaten(String datei) throws IOException{
        List<Kunde> kListe = new ArrayList<>(kundenliste.values());
        pm.schreibeKundeListe(kListe, datei);
    }
    public Kundenverwaltung() {
        //this.kundenUndDazugehörigeWarenkörbe = new HashMap<>();
        //kundenUndDazugehörigeWarenkörbe.put(k1, null);

        this.kundenliste = new HashMap<>();
    }

    public List<Kunde> getKundenListe() {
        return new ArrayList<>(kundenliste.values());
    }

    public HashMap<Kunde, Warenkorb> getGespeicherteWarenkörbeUndKunden() {
        return kundenUndDazugehörigeWarenkörbe;
    }

// man kann Waren in den Warenkorb legen oder die Menge Bereits vorhandener Artikel umändern.

    public void reinlegenOderMengeÄndern(List<Artikel> warenbestand, String artikel, int menge, Warenkorb warenkorb) throws UngueltigeMengeException, ArtikelExistiertNichtException {

        Artikel gefundenerArtikel = warenbestand.stream()
                .filter(a -> a.getBezeichnung().equals(artikel))
                .findFirst()
                .orElse(null);

        if (gefundenerArtikel != null)
        {
            if(gefundenerArtikel instanceof Massengutartikel) {
                int verkäuflich = ((Massengutartikel) gefundenerArtikel).getErwerbwareMenge();
                menge = menge * verkäuflich;
            }
            if (menge > gefundenerArtikel.getBestand()) {
                throw new UngueltigeMengeException();
            } else {
                warenkorb.getWarenkorb().put(gefundenerArtikel, menge);
            }
        } else {
            throw new ArtikelExistiertNichtException();
        }

    }

    //Die im Warenkorb enthaltenen Waren werden mit dem Warenbestand abgeglichen und deren Bestand wird aktualisiert.
    // danach wird der Warenkorb geleert
    public void beimKaufleerenUndBestandaktualisieren(Warenkorb warenkorb, List<Artikel> warenbestand, Kunde kunde, List<Ereignis> ereignisliste) throws WarenkorbIstLeerException {
        if (warenkorb.getWarenkorb().isEmpty()) {
            throw new WarenkorbIstLeerException();
        } else {

            warenkorb.getWarenkorb().forEach((artikel, menge) -> {
                warenbestand.stream()
                        .filter(bestandsartikel -> bestandsartikel.equals(artikel))
                        .forEach(bestandsartikel -> {
                            int zuReduzierendeMenge = menge;
                            int aktuellerBestand = bestandsartikel.getBestand() - zuReduzierendeMenge;
                            bestandsartikel.setBestand(aktuellerBestand);
                            Ereignis e = new Ereignis(menge, artikel, kunde, Enum.KAUF, aktuellerBestand);
                            System.out.println(e.getArtikel().getBezeichnung()+e.getAnzahl());
                            av.setEreignisListe(e);
                        });
            });
        }

        leeren(warenkorb);

    }

    //Der Warenkorb wird kompltett geleert
    public void leeren(Warenkorb warenkorb) {
        warenkorb.getWarenkorb().clear();
    }


    /*Es wird überprüft ob das Konto bereits existiert, Kunden können sich registrieren */
    public Kunde register(Kunde neu) {

        Kunde registrierungErfolgreich= kundenUndDazugehörigeWarenkörbe.keySet().stream()
                .filter(a -> a.getUserName() ==neu.getUserName())
                .findFirst()
                .orElse(null);

        if(registrierungErfolgreich != null){
            kundenUndDazugehörigeWarenkörbe.put(neu, null);

        }
        return registrierungErfolgreich;
    }

    /*Es wird überprüft, ob Username und Passwort übereinstimmen, der Kunde kann sich einloggen. */
    public Kunde login(String username, String password) {
        return kundenUndDazugehörigeWarenkörbe.keySet().stream()
                .filter(a -> a.getUserName().equals(username) && a.getPasswort().equals(password))
                .findFirst()
                .orElse(null);
    }

    //Ein neuer Warenkorb wird erstellt und in der Hashmap gespeichert
    public Warenkorb neuerWarenkorb(Kunde k) {
        Warenkorb w = new Warenkorb();
        kundenUndDazugehörigeWarenkörbe.put(k, w);
        return kundenUndDazugehörigeWarenkörbe.get(k);
    }

    public Kunde getKundeByUsername(String username){
       List<Kunde> kList =getKundenListe();
        for (Kunde kunde : kList){
            if (kunde.getUserName().equals(username)) {
                return kunde;
            }
        }

        return null;
    }


    //Liste der im Warenkorb gelegten Artikel wird ausgegeben
    public String einkaufsliste(Warenkorb warenkorb) {
        return warenkorb.toString();
    }


}





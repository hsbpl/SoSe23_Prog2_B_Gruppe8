package Domain;

import ValueObjekt.Artikel;
import ValueObjekt.Kunde;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Warenkorb{


        private Kunde kunde;
        private Map<Artikel, Integer> artikelMap;

        private List<Artikel> artikelListe;
        private Warenkorb warenkorb;
        public Warenkorb() {
            this.kunde = kunde;
            this.artikelMap = new HashMap<>();
        }

    public Warenkorb getWarenkorb() {
        return warenkorb;
    }

    public List<Artikel> getArtikelListe() {
        return getArtikelListe();
    }

    public Kunde getKunde() {
            return kunde;
        }

        public void setKunde(Kunde kunde) {
            this.kunde = kunde;
        }

        public Map<Artikel, Integer> getArtikelMap() {
            return artikelMap;
        }

        // Die addArtikel-Methode fügt einem Artikel eine bestimmte Stückzahl hinzu oder erhöht die Stückzahl,
        public void addArtikel(Artikel artikel, int stueckzahl) {
            int currentStueckzahl = artikelMap.getOrDefault(artikel, 0);
            artikelMap.put(artikel, currentStueckzahl + stueckzahl);
        }

        //Die updateArtikelStueckzahl-Methode ändert die Stückzahl eines Artikels im Warenkorb auf eine neue Stückzahl.
        public void updateArtikelStueckzahl(Artikel artikel, int neueStueckzahl) {
            artikelMap.put(artikel, neueStueckzahl);
        }

      //Die removeArtikel-Methode entfernt einen Artikel vollständig aus dem Warenkorb.
        public void removeArtikel(Artikel artikel) {
            artikelMap.remove(artikel);
        }
        //Die leereWarenkorb-Methode entfernt alle Artikel aus dem Warenkorb.
        public void leereWarenkorb() {
            artikelMap.clear();
        }
    }





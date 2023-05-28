package ValueObjekt;

import java.time.LocalDate;
import java.util.Date;

public class Ereignis {
    // Datum als Atribut
    private int anzahl;
    private Artikel artikel;
    private User user;
    private EreignisTyp ereignisTyp;

    public enum EreignisTyp{
        AUSLAGERUNG,
        EINLAGERUNG,
        KAUF,
        ANLEGEN,
    }
    //Die noch initialisieren

    String aktion;
    //enum ereignistyp{Auslagerung, Einlagerung};
    //neues Datum erzeugen
    //Datum muss nicht in den Konstruktor


    public Ereignis( int anzahl, Artikel artikel, User user, String aktion) {
        this.anzahl = anzahl;
        this.artikel = artikel;
        this.user = user;
        this.aktion = aktion;
        this.ereignisTyp = ereignisTyp;

    }


    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }
    public int getAnzahl() {
        return anzahl;
    }
    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public EreignisTyp getEreignisTyp(){
        return ereignisTyp;
    }
    public void setEreignisTyp(EreignisTyp ereignisTyp){
        this. ereignisTyp = ereignisTyp;
    }

    //LocalDateTime ändern auch in RechnungsObjekt
    public String toString(){
        return  anzahl +" durchgeführt über " +
                user + "Neuer Bestand: " + artikel.getBezeichnung()+ " " +artikel.getBestand();
    }
}





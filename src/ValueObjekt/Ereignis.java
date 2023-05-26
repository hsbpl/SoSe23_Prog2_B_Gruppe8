package ValueObjekt;

import java.util.Date;

public class Ereignis {
    // Datum als Atribut
    private int anzahl;
    private Artikel artikel;
    private User user;

    String aktion;
    //enum ereignistyp{Auslagerung, Einlagerung};
    //neues Datum erzeugen
    //Datum muss nicht in den Konstruktor


    public Ereignis( int anzahl, Artikel artikel, User user, String aktion) {
        this.anzahl = anzahl;
        this.artikel = artikel;
        this.user = user;
        this.aktion = aktion;

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

    //LocalDateTime ändern auch in RechnungsObjekt
    public String toString(){
        return "Datum: " +  + anzahl +" durchgeführt über " +
                user + "Neuer Bestand: " + artikel.getBezeichnung()+ " " +artikel.getBestand();
    }
}





package ValueObjekt;

import java.time.LocalDateTime;
import java.util.Date;

public class Ereignis {
    String aktion;
    private int anzahl;
    private Artikel artikel;
    private User user;

    public Ereignis(String aktion, int anzahl, Artikel artikel, User user) {
        this.aktion = aktion;
        this.anzahl = anzahl;
        this.artikel = artikel;
        this.user = user;

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

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public User getUser(User user){
        return user;
    }

    public String toString(){
    return "Datum: " + LocalDateTime.now() +" "+ aktion + "um" + anzahl + "durchgeführt über" +
            user + "Neuer Bestand: " + artikel.getBezeichnung()+ "" + artikel.getBestand();
    }
}





package ValueObjekt;

import java.util.Date;

public class Ereignis {
    // Datum als Atribut
    private Date datum = new Date();
    private int anzahl;
    private Artikel artikel;
    private User user;
    private Enum ereignistyp;
    //enum ereignistyp{Auslagerung, Einlagerung};


    public Ereignis( int anzahl, Artikel artikel, User user, Enum ereignistyp) {
        this.anzahl = anzahl;
        this.artikel = artikel;
        this.user = user;
        this.ereignistyp = ereignistyp;
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
        return  datum.toString() + "\n"+
                anzahl +" durchgeführt über " +
                user + "Neuer Bestand: " + artikel.getBezeichnung()+ " " +artikel.getBestand()
                + " "+ ereignistyp.toString();
    }
}





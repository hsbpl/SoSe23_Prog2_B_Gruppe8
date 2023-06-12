package ValueObjekt;

import java.util.Date;

public class Ereignis {
    // Datum als Atribut
    private Date datum = new Date();
    private int anzahl;
    private Artikel artikel;
    private User user;
    private Enum ereignistyp;

    private int aktualisierterBestand;

    public Ereignis( int anzahl, Artikel artikel, User user, Enum ereignistyp, int aktualisierterBestand) {
        this.anzahl = anzahl;
        this.artikel = artikel;
        this.user = user;
        this.ereignistyp = ereignistyp;
        this.aktualisierterBestand = aktualisierterBestand;
    }
    public int getAnzahl(){return anzahl;}
    public Artikel getArtikel(){return artikel;}
    public User getUser(){return user;}
    public Enum getEreignistyp(){return ereignistyp;}
    public int getAktualisierterBestand(){return aktualisierterBestand;}

    public Date getDatum(){
        return datum;
    }

    //TODO Bestand wird immer Aktuallisiert, statt das er den alten Bestand anzeigt, überlegen ob das abgeändert werden müsste)
    public String toString(){
        return  datum.toString() + "\n"+
                anzahl +" durchgeführt über " + ereignistyp.toString() +"; "+
                user + "Aktueller Bestand: " + artikel.getBezeichnung()+ " \n"+
                "Aktuellster Stand: " + aktualisierterBestand
                + "\n";
    }
}





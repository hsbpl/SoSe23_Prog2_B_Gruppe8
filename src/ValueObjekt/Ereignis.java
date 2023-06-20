package ValueObjekt;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ereignis {
    // Datum als Atribut
    private Date datum;
    private int anzahl;
    private Artikel artikel;
    private User user;
    private Enum ereignistyp;

    private SimpleDateFormat format;
    private int aktualisierterBestand;


    public Ereignis( int anzahl, Artikel artikel, User user, Enum ereignistyp, int aktualisierterBestand) {
        this.anzahl = anzahl;
        this.artikel = artikel;
        this.user = user;
        this.ereignistyp = ereignistyp;
        this.aktualisierterBestand = aktualisierterBestand;
        this.datum = new Date();
        this.format = new SimpleDateFormat("MMMM/dd/Y HH:mm");
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
        return  format.format(datum) + "\n"+
                anzahl +" durchgeführt über " + ereignistyp.toString() +"; "+
                user + "Aktueller Bestand: " + artikel.getBezeichnung()+ " \n"+
                "Aktuellster Stand: " + aktualisierterBestand
                + "\n";
    }
}





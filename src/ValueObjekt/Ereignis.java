package ValueObjekt;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Ereignis {
    // Datum als Atribut
    //private Date datum = new Date();
    //private LocalDateTime zeitstempel_orig = LocalDateTime.now();
    //private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    //private String zeitstempel_string = zeitstempel_orig.format(formatter);
    private LocalDateTime datum;
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
        this.datum = LocalDateTime.now();
    }

    public Ereignis( int anzahl, Artikel artikel, User user, Enum ereignistyp, int aktualisierterBestand, LocalDateTime zeitstempel) {
        this.anzahl = anzahl;
        this.artikel = artikel;
        this.user = user;
        this.ereignistyp = ereignistyp;
        this.aktualisierterBestand = aktualisierterBestand;
        this.datum = zeitstempel;
    }
    public int getAnzahl(){return anzahl;}
    public Artikel getArtikel(){return artikel;}
    public User getUser(){return user;}
    public Enum getEreignistyp(){return ereignistyp;}
    public int getAktualisierterBestand(){return aktualisierterBestand;}

    public LocalDateTime getDatum(){
        return datum;
    }

    //TODO Bestand wird immer Aktuallisiert, statt das er den alten Bestand anzeigt, überlegen ob das abgeändert werden müsste)
    public String toString(){
        return  getDatum().toString() + "\n"+
                anzahl +" durchgeführt über " + ereignistyp.toString() +"; "+
                user + "Aktueller Bestand: " + artikel.getBezeichnung()+ " \n"+
                "Aktuellster Stand: " + aktualisierterBestand
                + "\n";
    }
}
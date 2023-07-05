package Common;

import java.time.LocalDateTime;

public class Ereignis {

    private int anzahl;
    private Artikel artikel;
    private User user;
    private Enum ereignistyp;
    private int aktualisierterBestand;
    private LocalDateTime datum;

    // Initialer Konstruktor um beim Erstellen eines
    // Ereignisses neben den wichtigen Eigenschaften, den aktuellen Zeitstempel zu generieren.
    public Ereignis( int anzahl, Artikel artikel, User user, Enum ereignistyp, int aktualisierterBestand) {
        this.anzahl = anzahl;
        this.artikel = artikel;
        this.user = user;
        this.ereignistyp = ereignistyp;
        this.aktualisierterBestand = aktualisierterBestand;
        this.datum = LocalDateTime.now();
    }

    // Überladener Konstruktor für das Lesen des Zeitstempels in der FilePersistence aus der Textdatei
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


    public String toString(){
        return  getDatum().toString() + "\n"+
                anzahl +" durchgeführt über " + ereignistyp.toString() +"; "+
                user + "Artikel: " + artikel.getBezeichnung()+ " \n"+
                "Aktuellster Stand: " + aktualisierterBestand
                + "\n";
    }
}
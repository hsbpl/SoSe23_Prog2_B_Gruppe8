package ValueObjekt;

import java.util.Date;

public class Ereignis {
    // Datum als Atribut
    private Date datum = new Date();
    private int anzahl;
    private Artikel artikel;
    private User user;
    private Enum ereignistyp;



    public Ereignis( int anzahl, Artikel artikel, User user, Enum ereignistyp) {
        this.anzahl = anzahl;
        this.artikel = artikel;
        this.user = user;
        this.ereignistyp = ereignistyp;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    public Artikel getArtikel() {
        return artikel;
    }

public Date getDatum(){
        return datum;
    }


    //LocalDateTime ändern auch in RechnungsObjekt
    public String toString(){
        return  datum.toString() + "\n"+
                anzahl +" durchgeführt über " + ereignistyp.toString() +" ;"+
                user + "Aktueller Bestand: " + artikel.getBezeichnung()+ " " +artikel.getBestand()
                + "\n";
    }
}





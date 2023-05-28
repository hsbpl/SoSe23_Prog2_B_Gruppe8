package ValueObjekt;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Datum {

    private SimpleDateFormat datum;

    public Datum(){
        datum = new SimpleDateFormat();
    }

    @Override
    public String toString(){
        return "Datum: " + datum;
    }
}

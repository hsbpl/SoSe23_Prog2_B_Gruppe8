package Common;

import java.text.SimpleDateFormat;

public class Datum {

    private SimpleDateFormat datum;

    public Datum(){
        datum = new SimpleDateFormat();
    }

    @Override
    public String toString(){
        return "Datum: " + datum;
    }

    public SimpleDateFormat getDatum(){
        return datum;
    }
}

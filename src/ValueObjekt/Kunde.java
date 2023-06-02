package ValueObjekt;

import java.io.Serializable;
import java.util.Map;

public class Kunde extends User implements Serializable {

        private String kundenAdresse;


        public Kunde(String UserName, String passwort, String nachname, String vorname, int idNr, String kundeAdresse) {
            super (UserName, passwort, nachname, vorname, idNr);
            this.kundenAdresse = kundeAdresse;
        }
        public String getKundenAdresse(){return kundenAdresse;};






    @Override
        public String toString() {
            return  super.toString() + "\n";
        }
}

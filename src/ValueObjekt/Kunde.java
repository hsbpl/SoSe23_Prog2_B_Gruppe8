package ValueObjekt;

import java.util.Map;

public class Kunde extends User {

        private String kundenAdresse;


        public Kunde(String UserName, String passwort, String nachname, String vorname, int idNr, String kundeAdresse) {
            super (UserName, passwort, nachname, vorname, idNr);
            this.kundenAdresse = kundeAdresse;

        }

    public void setWarenkorb(Warenkorb warenkorb) {

    }



    @Override
        public String toString() {
            return "Kunden-" + super.toString() + "\n";
        }
}

package ValueObjekt;

import java.util.Map;

public class Kunde extends User {

        private String kundenAdresse;
        private Warenkorb warenkorb;

        public Kunde(String UserName, String passwort, String nachname, String vorname, int idNr, String kundeAdresse) {
            super (UserName, passwort, nachname, vorname, idNr);
            this.kundenAdresse = kundeAdresse;
            this.warenkorb = new Warenkorb();
        }

    public void setWarenkorb(Warenkorb warenkorb) {
        this.warenkorb = warenkorb;
    }

    public Warenkorb getWarenkorb() {
        return warenkorb;
    }

    @Override
        public String toString() {
            return "Kunden-" + super.toString() + "\n";
        }
}

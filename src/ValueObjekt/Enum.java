package ValueObjekt;

public enum Enum {
    AUSLAGERUNG,
    EINLAGERUNG,
    KAUF,
    ANLEGEN;


    public String toString() {
        switch (this.ordinal()){
            case 0:
                return "AUSLAGERUNG";
            case 1:
                return "EINLAGERUNG";
            case 2:
                return "KAUF";
            case 3:
                return "NEU ANLEGEN";
            default:
                return null;
        }

    }
}

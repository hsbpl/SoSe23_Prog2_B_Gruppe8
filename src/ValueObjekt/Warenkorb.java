package ValueObjekt;

import java.net.http.WebSocket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Warenkorb {

  private Map<Artikel, Integer> meinWarenkorb;

  public Warenkorb(){
      this.meinWarenkorb = new HashMap<>();
  }

    public Map<Artikel, Integer> getMeinWarenkorb() {
        return meinWarenkorb;
    }

    public void setMeinWarenkorb(Map<Artikel, Integer> meinWarenkorb) {
        this.meinWarenkorb = meinWarenkorb;
    }
}

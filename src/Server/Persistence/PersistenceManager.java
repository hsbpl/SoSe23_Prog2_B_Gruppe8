package Server.Persistence;


import Common.Ereignis;
import Common.Exceptions.ArtikelExistiertBereitsException;
import Common.Exceptions.EreignisExistiertBereitsException;
import Common.Exceptions.UserExistiertBereitsException;
import Common.Artikel;
import Common.Kunde;
import Common.Mitarbeiter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PersistenceManager {

    List<Artikel> leseArtikelListe(String datei) throws IOException, ArtikelExistiertBereitsException;

    void schreibeArtikelListe(List<Artikel> liste, String datei) throws IOException;

    Map<String, Kunde> leseKundenListe(String datei) throws IOException, UserExistiertBereitsException;

    void schreibeKundeListe(List<Kunde> liste, String datei) throws IOException;

    List<Mitarbeiter> leseMitarbeiterList(String datei) throws IOException, UserExistiertBereitsException;

    void schreibeMitarbeiterListe(List<Mitarbeiter> liste, String datei) throws IOException;

    List<Ereignis> leseEreignisList(String datei) throws IOException, EreignisExistiertBereitsException;

    void schreibeEreignisListe(List<Ereignis> liste, String datei) throws IOException;

    List<Artikel> leseMassengutListe(String datei) throws IOException, EreignisExistiertBereitsException;

    void schreibeMassengutListe(List<Artikel> liste, String datei) throws IOException;
}



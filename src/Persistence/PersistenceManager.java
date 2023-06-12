package Persistence;


import Exceptions.ArtikelExistiertBereitsException;
import Exceptions.EreignisExistiertBereitsException;
import Exceptions.UserExistiertBereitsException;
import Exceptions.UserExistiertNichtException;
import ValueObjekt.Artikel;
import ValueObjekt.Ereignis;
import ValueObjekt.Kunde;
import ValueObjekt.Mitarbeiter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public interface PersistenceManager {

    public List<Artikel> leseArtikelListe(String datei) throws IOException, ArtikelExistiertBereitsException;
    public void schreibeArtikelListe(List<Artikel> liste, String datei) throws IOException;

    public HashMap<String, Kunde> leseKundenListe(String datei) throws IOException, UserExistiertBereitsException;
    public void schreibeKundeListe(List<Kunde> liste, String datei) throws IOException;

    public List<Mitarbeiter> leseMitarbeiterList(String datei) throws IOException, UserExistiertBereitsException;
    public void schreibeMitarbeiterListe(List<Mitarbeiter> liste, String datei) throws IOException;


    public List<Ereignis> leseEreignisList(String datei) throws IOException, EreignisExistiertBereitsException;
    public void schreibeEreignisListe(List<Ereignis> liste, String datei) throws IOException;
}



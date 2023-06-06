package Persistence;


import ValueObjekt.Artikel;
import ValueObjekt.Kunde;
import ValueObjekt.Mitarbeiter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface PersistenceManager {
    public void openForReading(String datei) throws FileNotFoundException;

    public void openForWriting(String datei) throws IOException;


    public  void saveArticles(List<Artikel> artikel);

    public  List<Artikel> loadArticles();

    public  void saveMitarbeiter(List<Mitarbeiter> mitarbeiterList);

    public  List<Mitarbeiter> loadMitarbeiter();

    public  void saveKunde(List<Kunde> kundeList);

    public  List<Kunde> loadKunde();


    }
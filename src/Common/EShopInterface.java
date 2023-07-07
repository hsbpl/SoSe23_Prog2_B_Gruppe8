package Common;

import Common.Exceptions.*;

import java.io.IOException;
import java.util.List;

public interface EShopInterface {
    List<Artikel> getAlleArtikel();

    List<Mitarbeiter> getAlleMitarbeiter();

    List<Kunde> getAlleKunden();

    List<Ereignis> getAlleEreignisse();

    void schreibeArtikel() throws IOException;

    void schreibeMitarbeiter() throws IOException;

    void schreibeKunde() throws IOException;

    void schreibeEreignis() throws IOException;

    Warenkorb neuenWarenkorbErstellen(Kunde k);

    Mitarbeiter mitarbeiterRegistrieren(Mitarbeiter neu) throws UserExistiertBereitsException, LeeresTextfieldException;

    Mitarbeiter mitarbeiterLogin(String username, String passwort) throws LoginFehlgeschlagenException;

    void artHinzufügen(Artikel a, Mitarbeiter mitarbeiter) throws ArtikelExistiertBereitsException, LeeresTextfieldException;

    void massengutArtikelHinzufügen(Massengutartikel a, Mitarbeiter mitarbeiter) throws ArtikelExistiertBereitsException, LeeresTextfieldException;

    void bestandErhöhen(String artikelname, int menge, User u) throws ArtikelExistiertNichtException, LeeresTextfieldException;

    void bestanNiedriger(String artikelname, int menge, User u) throws ArtikelExistiertNichtException, UngueltigeMengeException, LeeresTextfieldException;

    List<Artikel> artikelSortierenNachBezeichnung();

    List<Artikel> artikelNachArtikelnummerGeordnetAusgeben();

    List<Ereignis> ereignisseNachDatum();

    void inDenWarenkorbLegen(String artikel, int menge, Warenkorb warenkorb) throws ArtikelExistiertNichtException, UngueltigeMengeException;

    void artikelAusWarenkorbEntfernen(String artikel, Warenkorb warenkorb);

    void warenkorbLeeren(Warenkorb warenkorb);

    Kunde kundenLogin(String username, String password) throws LoginFehlgeschlagenException;

    Kunde kundenRegistrieren(Kunde neu) throws UserExistiertBereitsException, LeeresTextfieldException;

    String kaufenUndRechnungEhalten(Kunde kunde, Warenkorb warenkorb) throws WarenkorbIstLeerException, IOException;
     void disconnect() throws IOException;
    }

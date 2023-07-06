package Client.net;

import Common.*;
import Common.Exceptions.*;

import java.io.IOException;
import java.util.List;

public class EshopClient implements EShopInterface {
    @Override
    public List<Artikel> getAlleArtikel() {
        return null;
    }

    @Override
    public List<Mitarbeiter> getAlleMitarbeiter() {
        return null;
    }

    @Override
    public List<Kunde> getAlleKunden() {
        return null;
    }

    @Override
    public List<Ereignis> getAlleEreignisse() {
        return null;
    }

    @Override
    public void schreibeArtikel() throws IOException {

    }

    @Override
    public void schreibeMitarbeiter() throws IOException {

    }

    @Override
    public void schreibeKunde() throws IOException {

    }

    @Override
    public void schreibeEreignis() throws IOException {

    }

    @Override
    public Warenkorb neuenWarenkorbErstellen(Kunde k) {
        return null;
    }

    @Override
    public Mitarbeiter mitarbeiterRegistrieren(Mitarbeiter neu) throws UserExistiertBereitsException, LeeresTextfieldException {
        return null;
    }

    @Override
    public Mitarbeiter mitarbeiterLogin(String username, String passwort) throws LoginFehlgeschlagenException {
        return null;
    }

    @Override
    public void artHinzufügen(Artikel a, Mitarbeiter mitarbeiter) throws ArtikelExistiertBereitsException, LeeresTextfieldException {

    }

    @Override
    public void massengutArtikelHinzufügen(Massengutartikel a, Mitarbeiter mitarbeiter) throws ArtikelExistiertBereitsException, LeeresTextfieldException {

    }

    @Override
    public void bestandErhöhen(String artikelname, int menge, User u) throws ArtikelExistiertNichtException, LeeresTextfieldException {

    }

    @Override
    public void bestanNiedriger(String artikelname, int menge, User u) throws ArtikelExistiertNichtException, UngueltigeMengeException, LeeresTextfieldException {

    }

    @Override
    public List<Artikel> artikelSortierenNachBezeichnung() {
        return null;
    }

    @Override
    public List<Artikel> artikelNachArtikelnummerGeordnetAusgeben() {
        return null;
    }

    @Override
    public List<Ereignis> ereignisseNachDatum() {
        return null;
    }

    @Override
    public void inDenWarenkorbLegen(String artikel, int menge, Warenkorb warenkorb) throws ArtikelExistiertNichtException, UngueltigeMengeException {

    }

    @Override
    public void artikelAusWarenkorbEntfernen(String artikel, Warenkorb warenkorb) {

    }

    @Override
    public void warenkorbLeeren(Warenkorb warenkorb) {

    }

    @Override
    public Kunde kundenLogin(String username, String password) throws LoginFehlgeschlagenException {
        return null;
    }

    @Override
    public Kunde kundenRegistrieren(Kunde neu) throws UserExistiertBereitsException, LeeresTextfieldException {
        return null;
    }

    @Override
    public String kaufenUndRechnungEhalten(Kunde kunde, Warenkorb warenkorb) throws WarenkorbIstLeerException, IOException {
        return null;
    }
}

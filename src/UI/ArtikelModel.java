package UI;

import ValueObjekt.Artikel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ArtikelModel extends AbstractTableModel{

    private List<Artikel> artikelList;
    private String[] header = {"Bezeichnung", "Artikelnummer", "Bestand", "Preis"};

    public ArtikelModel(List<Artikel> aktuelleArtikel) {
    artikelList = new ArrayList<>();
    artikelList.addAll(aktuelleArtikel);

    }

   public void setArtikelListe(List<Artikel> aktuelleArtikel){
    artikelList.clear();
    artikelList.addAll(aktuelleArtikel);
    }

    @Override
    public int getRowCount() {
        return artikelList.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Artikel gewaehlterArtikel = artikelList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return gewaehlterArtikel.getBezeichnung();
            case 1:
                return gewaehlterArtikel.getArtikelNummer();
            case 2:
                return gewaehlterArtikel.getBestand();
            case 3:
                return gewaehlterArtikel.getEinzelpreis();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }
/*
    public class BookTableModel extends AbstractTableModel {
        private List<Buch> buecher;

        private String[] spaltenNamen = {"Nummer", "Titel", "verfügbar"};

        public BookTableModel(List<Buch> aktuelleBuecher) { // aktuelle bücher werden hier
            buecher = new ArrayList<>(); // bücher = bücherliste
            buecher.addAll(aktuelleBuecher); //aktuelle bücher = wenn etwas dazu kommt
        }

        public void setBooks(List<Buch> aktuelleBuecher) {
            buecher.clear();
            buecher.addAll(aktuelleBuecher);
            fireTableDataChanged();
        }


        @Override
        public int getRowCount() {
            return buecher.size();
        }

        @Override
        public int getColumnCount() {
            return spaltenNamen.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Buch gewaehltesBuch = buecher.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return gewaehltesBuch.getNummer();
                case 1:
                    return gewaehltesBuch.getTitel();
                case 2:
                    return gewaehltesBuch.isVerfuegbar();
                default:
                    return null;
            }
        }

        /**
         * Zusätzlich überschrieben, um die Spaltennamen anzuzeigen
         * @param column Spaltenindex, der im Array 'spaltenNamen' verwendet wird
         * @return

        @Override
        public String getColumnName(int column) {
            return spaltenNamen[column];
        }
        */

}

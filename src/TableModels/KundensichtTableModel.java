package TableModels;

import ValueObjekt.Artikel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class KundensichtTableModel extends AbstractTableModel {
    private List<Artikel> artikelList;
    private String[] header = {"Bezeichnung", "Artikelnummer", "Preis"};

    public KundensichtTableModel(List<Artikel> aktuelleArtikel) {
        artikelList = new ArrayList<>();
        artikelList.addAll(aktuelleArtikel);

    }

    public void setArtikelListe(List<Artikel> aktuelleArtikel){
        artikelList.clear();
        artikelList.addAll(aktuelleArtikel);
        fireTableDataChanged();
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
                return gewaehlterArtikel.getEinzelpreis();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }
}

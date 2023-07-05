package Client.UI.TableModels;

import Common.Artikel;
import Common.Massengutartikel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ArtikelTableModel extends AbstractTableModel {

    private List<Artikel> artikelList;
    private String[] header = {"Bezeichnung", "Artikelnummer", "Bestand", "Preis", "Massengut"};

    public ArtikelTableModel(List<Artikel> aktuelleArtikel) {
        artikelList = new ArrayList<>();
        artikelList.addAll(aktuelleArtikel);

    }

    public void setArtikelListe(List<Artikel> aktuelleArtikel) {
        artikelList.clear();
        artikelList.addAll(aktuelleArtikel);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        if (artikelList != null) {
            return artikelList.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
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
            case 4:
                if (gewaehlterArtikel instanceof Massengutartikel)
                    return ((Massengutartikel) gewaehlterArtikel).getErwerbwareMenge();
                else {
                    return 1;
                }
            default:
                return null;
        }
    }
}



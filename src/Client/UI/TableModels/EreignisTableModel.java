package Client.UI.TableModels;

import Common.Ereignis;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class EreignisTableModel extends AbstractTableModel {

    private List<Ereignis> ereignisse;
    private String[] header = {"Datum", "Artikel", "Artikelnummer", "Ereignistyp", "Menge", "Aktualisierter Bestand", "Beteiligter User"};

    public EreignisTableModel(List<Ereignis> ereignisList) {
        super();
        ereignisse = new ArrayList<>();
        ereignisse.addAll(ereignisList);
    }

    public void setEreignisse(List<Ereignis> ereignisList) {
        ereignisse.clear();
        ereignisse.addAll(ereignisList);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        if (ereignisse != null) {
            return ereignisse.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    public String getColumnName(int columnIndex) {
        return header[columnIndex];
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ereignis gewaehltesEreignis = ereignisse.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return gewaehltesEreignis.getDatum();
            case 1:
                return gewaehltesEreignis.getArtikel().getBezeichnung();
            case 2:
                return gewaehltesEreignis.getArtikel().getArtikelNummer();
            case 3:
                return gewaehltesEreignis.getEreignistyp();
            case 4:
                return gewaehltesEreignis.getAnzahl();
            case 5:
                return gewaehltesEreignis.getAktualisierterBestand();
            case 6:
                return gewaehltesEreignis.getUser().getUserName();
            default:
                return null;
        }
    }

}



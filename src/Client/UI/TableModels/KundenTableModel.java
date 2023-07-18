package Client.UI.TableModels;

import Common.Kunde;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class KundenTableModel extends AbstractTableModel {

    private List<Kunde> kunden;
    private String[] header = {"Vorname", "Nachname", "Username", "Id.Nr.", "Adresse"};

    public KundenTableModel(List<Kunde> kundeList) {
        kunden = new ArrayList<>();
        kunden.addAll(kundeList);
    }


    @Override
    public int getRowCount() {
        return kunden.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kunde gewaehlterKunde = kunden.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return gewaehlterKunde.getVorname();
            case 1:
                return gewaehlterKunde.getNachname();
            case 2:
                return gewaehlterKunde.getUserName();
            case 3:
                return gewaehlterKunde.getidNummer();
            case 4:
                return gewaehlterKunde.getKundenAdresse();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }
}

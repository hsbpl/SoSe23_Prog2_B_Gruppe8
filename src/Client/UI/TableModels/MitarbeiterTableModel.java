package Client.UI.TableModels;

import Common.Mitarbeiter;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MitarbeiterTableModel extends AbstractTableModel {

    private List<Mitarbeiter> mitarbeiter;
    private String[] header = {"Vorname", "Nachname", "Username", "Id.Nr."};

    public MitarbeiterTableModel(List<Mitarbeiter> mitarbeiterList) {
        mitarbeiter = new ArrayList<>();
        mitarbeiter.addAll(mitarbeiterList);
    }


    @Override
    public int getRowCount() {
        return mitarbeiter.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Mitarbeiter gewaehlterMitarbeiter = mitarbeiter.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return gewaehlterMitarbeiter.getVorname();
            case 1:
                return gewaehlterMitarbeiter.getNachname();
            case 2:
                return gewaehlterMitarbeiter.getUserName();
            case 3:
                return gewaehlterMitarbeiter.getidNummer();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }
}

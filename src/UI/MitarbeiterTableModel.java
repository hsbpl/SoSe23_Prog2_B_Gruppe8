package UI;

import ValueObjekt.Mitarbeiter;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MitarbeiterTableModel extends AbstractTableModel {

    private List<Mitarbeiter> mitarbeiter;
    private String[] header = {"Vorname", "Nachname", "Username", "Id.Nr.", "Adresse"};

    public MitarbeiterTableModel(List<Mitarbeiter> mitarbeiterList){
        mitarbeiter = new ArrayList<>();
        mitarbeiter.addAll(mitarbeiterList);
    }


    public void setMitarbeiterliste(List<Mitarbeiter> mitarbeiterList){
        mitarbeiter.clear();
        mitarbeiter.addAll(mitarbeiterList);
        fireTableDataChanged();
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
        Mitarbeiter gewaehlterKunde = mitarbeiter.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return gewaehlterKunde.getVorname();
            case 1:
                return gewaehlterKunde.getNachname();
            case 2:
                return gewaehlterKunde.getUserName();
            case 3:
                return gewaehlterKunde.getidNummer();

            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }
}

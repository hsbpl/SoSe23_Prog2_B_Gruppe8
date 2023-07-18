package Client.UI.TableModels;

import Common.Artikel;
import Common.Warenkorb;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WarenkorbTableModel extends AbstractTableModel {
    private Warenkorb waren;
    private String[] header = {"Bezeichnung", "Artikelnummer", "Menge", "Preis"};

    public WarenkorbTableModel(Warenkorb einkaufsliste) {
        waren = einkaufsliste;
    }

    public void setWarenkorb(Warenkorb einkaufsliste){
        waren = einkaufsliste;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return waren.getWarenkorb().size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        List<Artikel> artikelList = new ArrayList<>(waren.getWarenkorb().keySet());
        Artikel gewaehlterArtikel = artikelList.get(rowIndex);
        int menge = waren.getWarenkorb().get(gewaehlterArtikel);

        switch (columnIndex) {
            case 0:
                return gewaehlterArtikel.getBezeichnung();
            case 1:
                return gewaehlterArtikel.getArtikelNummer();
            case 2:
                return menge;
            case 3:
                return gewaehlterArtikel.getEinzelpreis();
            default:
                return null;
        }

    }
    public double getGesamtpreis(){
        double gesamtpreis = 0.0;
        for (Map.Entry<Artikel, Integer> entry : waren.getWarenkorb().entrySet()) {
            Artikel artikel = entry.getKey();
            int menge = entry.getValue();
            gesamtpreis += artikel.getEinzelpreis() * menge;
        }
        return gesamtpreis;
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }
}

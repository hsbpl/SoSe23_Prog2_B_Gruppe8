package UI;

import Domain.EShop;
import ValueObjekt.Artikel;
import ValueObjekt.Kunde;
import ValueObjekt.Warenkorb;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;

public class KundenbereichGUI extends JFrame {
    private EShop eShop;
    private Kunde eingeloggterKunde;
    private Warenkorb warenKorbDesKunden;

    public KundenbereichGUI(Kunde eingeloggterKunde, Warenkorb warenKorbDesKunden, EShop eShop) throws IOException {
        super("Kundenbereich");
        this.eingeloggterKunde = eingeloggterKunde;
        this.warenKorbDesKunden = warenKorbDesKunden;
        this.eShop = eShop;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Erstellen der GUI-Komponenten
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel infoPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Willkommen, " + eingeloggterKunde.getVorname() + "!");
        JButton logoutButton = new JButton("Ausloggen");
        JButton artikelKaufenButton = new JButton("Artikel kaufen");
        JButton artikelEntfernenButton = new JButton("Artikel entfernen");
        JButton warenkorbButton = new JButton("Warenkorb anzeigen");
        JButton warenkorbLeerenButton = new JButton("Warenkorb leeren");
        JPanel warenkorbPanel = new JPanel(new BorderLayout());
        JTextArea rechnungsTextArea = new JTextArea();
        JScrollPane rechnungsScrollPane = new JScrollPane(rechnungsTextArea);
        JButton rechnungGenerierenButton = new JButton("Rechnung generieren");

        rechnungsTextArea.setEditable(false);

        warenkorbPanel.setBorder(BorderFactory.createTitledBorder("Warenkorb"));

        DefaultTableModel artikelTableModel = new DefaultTableModel();
        JTable artikelTable = new JTable(artikelTableModel);
        JScrollPane artikelScrollPane = new JScrollPane(artikelTable);

        // Artikel-Tabelle konfigurieren
        artikelTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        artikelTable.setRowSelectionAllowed(true);

        // Hinzufügen des ListSelectionListeners für die Artikel-Tabelle
        artikelTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = artikelTable.getSelectedRow();
                    if (selectedRow != -1) {
                        String selectedArtikel = (String) artikelTableModel.getValueAt(selectedRow, 0);
                        int option = JOptionPane.showConfirmDialog(null, "Möchten Sie den Artikel\n" + selectedArtikel + "\nzum Warenkorb hinzufügen?", "Artikel zum Warenkorb hinzufügen", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            Artikel artikel = eShop.getAlleArtikel().get(selectedRow);
                            warenKorbDesKunden.getWarenkorb().put(artikel, 1);
                            JOptionPane.showMessageDialog(null, "Artikel erfolgreich zum Warenkorb hinzugefügt.");
                        }
                        artikelTable.clearSelection();
                    }
                }
            }
        });

        // Artikel-Tabelle modellieren
        artikelTableModel.addColumn("Artikel");
        artikelTableModel.addColumn("Preis");
        artikelTableModel.addColumn("Stückzahl");

        for (Artikel artikel : eShop.getAlleArtikel()) {
            artikelTableModel.addRow(new Object[]{artikel.getBezeichnung(), artikel.getEinzelpreis(), 0});
        }

        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // ActionListener für Ausloggen-Button
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Möchten Sie sich wirklich ausloggen?", "Ausloggen", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    StartGUI s = new StartGUI(eShop);
                    dispose();
                }
            }
        });

        // ActionListener für Artikel kaufen-Button
        artikelKaufenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = artikelTable.getSelectedRow();
                if (selectedRow != -1) {
                    Artikel artikel = eShop.getAlleArtikel().get(selectedRow);
                    int stueckzahl = Integer.parseInt(artikelTableModel.getValueAt(selectedRow, 2).toString());
                    if (stueckzahl > 0) {
                        warenKorbDesKunden.getWarenkorb().put(artikel, stueckzahl);
                        artikelTableModel.setValueAt(stueckzahl, selectedRow, 2);
                        JOptionPane.showMessageDialog(null, "Artikel erfolgreich zum Warenkorb hinzugefügt.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Bitte geben Sie eine gültige Stückzahl ein.");
                    }
                    artikelTable.clearSelection();
                } else {
                    JOptionPane.showMessageDialog(null, "Bitte wählen Sie einen Artikel aus.");
                }
            }
        });

        // ActionListener für Artikel entfernen-Button
        artikelEntfernenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = artikelTable.getSelectedRow();
                if (selectedRow != -1) {
                    artikelTableModel.removeRow(selectedRow);
                }
            }
        });

        // ActionListener für Warenkorb anzeigen-Button
        warenkorbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder warenkorbText = new StringBuilder();
                for (Map.Entry<Artikel, Integer> eintrag : warenKorbDesKunden.getWarenkorb().entrySet()) {
                    Artikel artikel = eintrag.getKey();
                    int menge = eintrag.getValue();
                    warenkorbText.append(artikel.toString()).append(" - Menge: ").append(menge).append("\n");
                }
                JOptionPane.showMessageDialog(null, warenkorbText.toString(), "Warenkorb anzeigen", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // ActionListener für Warenkorb leeren-Button
        warenkorbLeerenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                warenKorbDesKunden.getWarenkorb().clear();
                aktualisiereWarenkorb(rechnungsTextArea); // Aktualisierung des Warenkorbs
            }
        });


        // ActionListener für Rechnung generieren-Button
        rechnungGenerierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code zum Generieren der Rechnung
                String rechnungstext = generiereRechnungstext();
                rechnungsTextArea.setText(rechnungstext);
            }
        });

        infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        infoPanel.add(titleLabel);

        buttonPanel.add(logoutButton);
        buttonPanel.add(artikelKaufenButton);
        buttonPanel.add(artikelEntfernenButton);
        buttonPanel.add(warenkorbButton);
        buttonPanel.add(warenkorbLeerenButton);

        mainPanel.add(warenkorbPanel, BorderLayout.CENTER);
        mainPanel.add(rechnungsScrollPane, BorderLayout.EAST);
        mainPanel.add(rechnungGenerierenButton, BorderLayout.SOUTH);
        mainPanel.add(infoPanel, BorderLayout.NORTH);
        mainPanel.add(artikelScrollPane, BorderLayout.WEST);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
        this.pack();
    }

    private void aktualisiereWarenkorb(JTextArea rechnungsTextArea) {
        StringBuilder warenkorbText = new StringBuilder();
        for (Map.Entry<Artikel, Integer> eintrag : warenKorbDesKunden.getWarenkorb().entrySet()) {
            Artikel artikel = eintrag.getKey();
            int menge = eintrag.getValue();
            warenkorbText.append(artikel.toString()).append(" - Menge: ").append(menge).append("\n");
        }
        rechnungsTextArea.setText(warenkorbText.toString());
    }


    private String generiereRechnungstext() {
        StringBuilder rechnungstext = new StringBuilder();
        rechnungstext.append("Rechnung\n");
        rechnungstext.append("Kunde: ").append(eingeloggterKunde.getVorname()).append(" ").append(eingeloggterKunde.getNachname()).append("\n");
        rechnungstext.append("----------\n");
        rechnungstext.append("Artikel\t\tPreis\t\tStückzahl\tGesamtpreis\n");
        rechnungstext.append("----------\n");
        double gesamtsumme = 0;

        for (Map.Entry<Artikel, Integer> eintrag : warenKorbDesKunden.getWarenkorb().entrySet()) {
            Artikel artikel = eintrag.getKey();
            int menge = eintrag.getValue();
            double gesamtpreis = artikel.getEinzelpreis() * menge;
            rechnungstext.append(artikel.getBezeichnung()).append("\t\t").append(artikel.getEinzelpreis()).append("\t\t").append(menge).append("\t\t").append(gesamtpreis).append("\n");
            gesamtsumme += gesamtpreis;
        }

        rechnungstext.append("----------\n");
        rechnungstext.append("Gesamtsumme: ").append(gesamtsumme);

        return rechnungstext.toString();
    }

    private void kundenbereich() {
    }
}

package UI;

import Domain.EShop;
import ValueObjekt.Artikel;
import ValueObjekt.Kunde;
import ValueObjekt.Warenkorb;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
        //String datei = "ESHOP";
        this.eShop = eShop;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        kundenbereich();

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
        DefaultListModel<String> artikelListModel = new DefaultListModel<>();
        JList<String> artikelList = new JList<>(artikelListModel);
        JScrollPane artikelScrollPane = new JScrollPane(artikelList);

        // Artikel-Liste
        artikelListModel = new DefaultListModel<>();
        artikelList = new JList<>(artikelListModel);
        artikelScrollPane = new JScrollPane(artikelList);
        artikelScrollPane.setPreferredSize(new Dimension(300, getHeight()));

        // Erstellen der JPanel-Komponente für den Scrollbereich
        JPanel artikelPanel = new JPanel(new BorderLayout());
        artikelPanel.add(artikelScrollPane, BorderLayout.CENTER);

        // Hinzufügen des ListSelectionListener für die ArtikelListe
        JList<String> finalArtikelList1 = artikelList;
        DefaultListModel<String> finalArtikelListModel2 = artikelListModel;
        artikelList.addListSelectionListener(new ListSelectionListener() {


            //TODO ich wollte dir nichts kaputt machen hier habe ich die Artikelliste als Jtable hingelegt kannst ihn statt der Liste verwenden
            private JTable tabelle;
            private ArtikelTableModel model;
            private JScrollPane tablePane;
            private Component artikellistTable(){
                tabelle = new JTable();
                model =new ArtikelTableModel(eShop.getAlleArtikel());
                tabelle.setModel(new ArtikelTableModel(eShop.getAlleArtikel()));
                tablePane = new JScrollPane(tabelle);

                return tablePane;
            }

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Abfragen, ob der ausgewählte Artikel zum Warenkorb hinzugefügt werden soll
                    int selectedIndex = finalArtikelList1.getSelectedIndex();
                    if (selectedIndex != -1) {
                        String selectedArtikel = finalArtikelListModel2.getElementAt(selectedIndex);
                        int option = JOptionPane.showConfirmDialog(null, "Möchten Sie den Artikel\n" + selectedArtikel + "\nzum Warenkorb hinzufügen?", "Artikel zum Warenkorb hinzufügen", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            // Artikel zum Warenkorb hinzufügen
                            String artikel = eShop.artikelListen();
                            warenKorbDesKunden.getWarenkorb();
                            JOptionPane.showMessageDialog(null, "Artikel erfolgreich zum Warenkorb hinzugefügt.");
                        }
                        // Auswahl in der ArtikelListe zurücksetzen
                        finalArtikelList1.clearSelection();
                    }
                }
            }
        });

        // Laden aller Artikel aus dem EShop
        for (Artikel artikel : eShop.getAlleArtikel()) {
            artikelListModel.addElement(artikel.toString());
        }

        // Konfigurieren der GUI-Komponenten
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
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

        infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        infoPanel.add(titleLabel);

        // Hinzufügen der ActionListener für den "Artikel kaufen"-Button
        artikelKaufenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // Hinzufügen des ActionListener für den "Artikel entfernen"-Button
        JList<String> finalArtikelList = artikelList;
        DefaultListModel<String> finalArtikelListModel = artikelListModel;
        artikelEntfernenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ausgewählten Artikel aus der Liste entfernen
                int selectedIndex = finalArtikelList.getSelectedIndex();
                if (selectedIndex != -1) {
                    finalArtikelListModel.remove(selectedIndex);
                }
            }
        });

        // Hinzufügen des ActionListener für den "Warenkorb anzeigen"-Button
        warenkorbButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Dialogbox zum Anzeigen des Warenkorbs anzeigen
                StringBuilder warenkorbText = new StringBuilder();
                for (Map.Entry<Artikel, Integer> eintrag : warenKorbDesKunden.getWarenkorb().entrySet()) {
                    Artikel artikel = eintrag.getKey();
                    int menge = eintrag.getValue();
                    warenkorbText.append(artikel.toString()).append(" - Menge: ").append(menge).append("\n");
                }

                JOptionPane.showMessageDialog(null, warenkorbText.toString(), "Warenkorb anzeigen", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Hinzufügen des ActionListener für den "Warenkorb leeren"-Button
        DefaultListModel<String> finalArtikelListModel1 = artikelListModel;
        warenkorbLeerenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Warenkorb leeren
                warenKorbDesKunden.getWarenkorb().clear();
                finalArtikelListModel1.clear();
            }
        });

        // Zusammenstellen der GUI
        buttonPanel.add(logoutButton);
        buttonPanel.add(artikelKaufenButton);
        buttonPanel.add(artikelEntfernenButton);
        buttonPanel.add(warenkorbButton);
        buttonPanel.add(warenkorbLeerenButton);

        mainPanel.add(infoPanel, BorderLayout.NORTH);
        mainPanel.add(artikelPanel, BorderLayout.WEST); // Artikel-Liste links anzeigen
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
    }

    private void kundenbereich() {
    }

   /* public static void main(String[] args) {
        try {
            KundenbereichGUI kundenbereichGUI = new KundenbereichGUI(new Kunde("roha","1234", "ahmad", "roha", "Berlinerstraße"), new Warenkorb());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    */
}





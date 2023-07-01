package UI;

import Domain.EShop;
import Exceptions.ArtikelExistiertNichtException;
import Exceptions.UngueltigeMengeException;
import Exceptions.WarenkorbIstLeerException;
import TableModels.KundensichtTableModel;
import TableModels.WarenkorbTableModel;
import ValueObjekt.Artikel;
import ValueObjekt.Kunde;
import ValueObjekt.Warenkorb;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Map;

public class KundenbereichGUI extends JFrame {
    //todo exceptionhandling
    //todo unten rechts gesamtsumme bei Warenkorb
    //todo evtl Suchleiste zur artikeltabelle
    //todo es gibt Problemem bei den Massengutartikeln beim Reinlegen wird nicht die kaufbare menge gegen gerechnet
    //todo zuvor hat die reinlegen methode automatische die neumals zu verändernde Menge so aktuallisiert, jetzt wird aber einfach drauf gerechnet
    //todo dadurch das das vorherige problem auftritt wird beim kaufen nicht die Richtige Ware aus dem bestand gezogen bzw man kann auch ins minus gehen
    //todo Im algemeinen scheint das Problem in der Methode zu sitzen, mit der man die Waren in den Korb gelegt werden

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
        JButton warenkorbLeerenButton = new JButton("Warenkorb leeren");
        JPanel warenkorbPanel = new JPanel(new BorderLayout());
        JTextArea rechnungsTextArea = new JTextArea();
        JScrollPane rechnungsScrollPane = new JScrollPane(rechnungsTextArea);
        WarenkorbTableModel warenkorbTableModel;
        JTable warenkorbTabelle;
        JScrollPane warenkorbPane;

        rechnungsTextArea.setEditable(false);


        warenkorbPanel.add(rechnungsScrollPane, BorderLayout.CENTER);
        //warenkorbanzeige/tabelle
        warenkorbPanel.setBorder(BorderFactory.createTitledBorder("Warenkorb"));
        warenkorbTabelle = new JTable();
        warenkorbTableModel = new WarenkorbTableModel(warenKorbDesKunden);
        warenkorbTabelle.setModel(warenkorbTableModel);
        warenkorbPane = new JScrollPane(warenkorbTabelle);
        warenkorbPanel.add(warenkorbPane);



        KundensichtTableModel artikelTableModel = new KundensichtTableModel(eShop.getAlleArtikel());
        TableRowSorter sorter = new TableRowSorter<>(artikelTableModel);
        JTable artikelTable = new JTable(artikelTableModel);
        artikelTable.setRowSorter(sorter);
        JScrollPane artikelScrollPane = new JScrollPane(artikelTable);
        artikelScrollPane.setMinimumSize(new Dimension(300, 400));
        JTextField suchleiste = new JTextField(30);

        //artikelScrollPane.add(suchleiste, BorderLayout.NORTH); //todo herausfinden warum die Suchleiste nicht hinzuge -evtl weil scrollpane keinen layout manager hat
        suchleiste.setPreferredSize(getPreferredSize());


        artikelTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        artikelTable.setRowSelectionAllowed(true);
        suchleiste.getDocument().addDocumentListener(new DocumentListener() {
            String suche = suchleiste.getText();
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                if (suche.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(suche));
                    System.out.println("typed");
                }
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                if (suche.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(suche));
                    System.out.println("typed");
                }
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                if (suche.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(suche));
                    System.out.println("typed");
                }
            }
        });


        // Hinzufügen des ListSelectionListeners für die Artikel-Tabelle
        artikelTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = artikelTable.getSelectedRow();

                    if (selectedRow != -1) {
                        String artikelBezeichnung = artikelTable.getValueAt(selectedRow, 0).toString();
                        int option = JOptionPane.showConfirmDialog(null, "Möchten Sie den Artikel\n" + artikelBezeichnung + "\nzum Warenkorb hinzufügen?", "Artikel zum Warenkorb hinzufügen", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            try {
                                String mengenString = JOptionPane.showInputDialog("Menge eingeben");
                                int menge = Integer.parseInt(mengenString);

                                eShop.inDenWarenkorbLegen(artikelBezeichnung, menge, warenKorbDesKunden);
                                warenkorbTableModel.setWarenkorb(warenKorbDesKunden);
                            } catch (ArtikelExistiertNichtException ex) {
                                //kann hier eig nicht vorkommen, da die Artikel durchs cliken ausgewählt werden.
                                //Nur relevant für CUI
                            } catch (UngueltigeMengeException ex) {
                                System.err.println("*********************************************************************************\n" +
                                        "\nGewünschte Menge übersteigt Bestand!\n"+
                                        "*********************************************************************************\n");
                                JOptionPane.showMessageDialog(null, "Gewünschte Menge übersteigt Bestand.", "Fehler", JOptionPane.ERROR_MESSAGE);
                            }catch (NumberFormatException exc){
                                System.err.println("*********************************************************************************\n" +
                                        "\nBitte geben Sie eine Zahl im Textfeld ein ein!\n"+
                                        "*********************************************************************************\n");
                                JOptionPane.showMessageDialog(null, "Bitte geben Sie eine Zahl ein!",
                                        "Eingabe falsch", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            }
        });
        
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


        artikelKaufenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String rechnung = eShop.kaufenUndRechnungEhalten(eingeloggterKunde, warenKorbDesKunden);
                    JOptionPane.showMessageDialog(null, rechnung, "Vielen Dank für Ihren Einkauf", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(rechnung);
                } catch (WarenkorbIstLeerException ex) {
                    System.err.println("*********************************************************************************\n" +
                            "\nGewünschte Menge übersteigt Bestand!\n"+
                            "*********************************************************************************\n");
                    JOptionPane.showMessageDialog(null, "Der Warenkorb ist leer.", "Fehler", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Fehler beim Generieren der Rechnung.", "Fehler", JOptionPane.ERROR_MESSAGE);
                } /*catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ein Fehler ist aufgetreten.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
                */
            }
        });




        warenkorbTabelle.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int row = warenkorbTabelle.getSelectedRow();

                String artikel = warenkorbTabelle.getValueAt(row, 0).toString();

                int option = JOptionPane.showConfirmDialog(null, "Artikel aus dem Warenkorb entfernen?", "Artikel entferne", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {

                        eShop.artikelAusWarenkorbEntfernen(artikel, warenKorbDesKunden);
                        warenkorbTableModel.setWarenkorb(warenKorbDesKunden);
                    // Bei Doppelklick soll gefragt werden, ob man den Artikel aus dem Warenkorb entfernen möchte
                    // bei
                }
                if (option == JOptionPane.NO_OPTION) {

                    int optionpane = JOptionPane.showConfirmDialog(null, "Menge des Gewählten Artikels ändern?", "Menge aktualisieren", JOptionPane.YES_NO_OPTION);
                    if (optionpane == JOptionPane.YES_OPTION) {
                    //todo probleme beim abgleichen des bestands
                        String mengenString = JOptionPane.showInputDialog("Menge des gewählten Artikels abändern");
                        int menge = Integer.parseInt(mengenString);
                        try {
                            //todo die erste Methode wurde zuvor nicht benötigt und die anwenundung macht auch für weitere Verwendung probleme
                            eShop.artikelAusWarenkorbEntfernen(artikel, warenKorbDesKunden);
                            eShop.inDenWarenkorbLegen(artikel, menge, warenKorbDesKunden);
                            warenkorbTableModel.setWarenkorb(warenKorbDesKunden);
                        } catch (ArtikelExistiertNichtException e) {
                            //kann hier nicht pasieren, da per click gewählt wird
                        } catch (UngueltigeMengeException e) {
                            System.err.println("*********************************************************************************\n" +
                                    "\nGewünschte Menge übersteigt Bestand!\n"+
                                    "*********************************************************************************\n");
                            JOptionPane.showMessageDialog(null, "Der Warenkorb ist leer.", "Fehler", JOptionPane.ERROR_MESSAGE);
                        }

                    }

                }
            }


            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });


        // ActionListener für Warenkorb leeren-Button
        warenkorbLeerenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eShop.warenkorbLeeren(warenKorbDesKunden);
                    warenkorbTableModel.setWarenkorb(warenKorbDesKunden);

                    aktualisiereWarenkorb(rechnungsTextArea); // Aktualisierung des Warenkorbs
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Warenkorb konnte nicht aktualisiert werden.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }

            }
        });


        infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        infoPanel.add(titleLabel);

        buttonPanel.add(logoutButton);
        buttonPanel.add(artikelKaufenButton);
        buttonPanel.add(warenkorbLeerenButton);

        mainPanel.add(warenkorbPanel, BorderLayout.CENTER);
        //mainPanel.add(rechnungsScrollPane, BorderLayout.EAST);
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
            rechnungstext.append(artikel.getBezeichnung()).append("\t\t")
                    .append(artikel.getEinzelpreis()).append("\t\t")
                    .append(menge).append("\t\t")
                    .append(gesamtpreis).append("\n");
            gesamtsumme += gesamtpreis;
        }

        rechnungstext.append("----------\n");
        rechnungstext.append("Gesamtsumme: ").append(gesamtsumme);

        return rechnungstext.toString();
    }
}

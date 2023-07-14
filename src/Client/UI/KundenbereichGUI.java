package Client.UI;

import Client.UI.TableModels.KundensichtTableModel;
import Client.UI.TableModels.WarenkorbTableModel;
import Common.EShopInterface;
import Server.Domain.EShop;
import Common.Exceptions.ArtikelExistiertNichtException;
import Common.Exceptions.UngueltigeMengeException;
import Common.Exceptions.WarenkorbIstLeerException;
import Common.Kunde;
import Common.Warenkorb;
import Client.UI.menus.FileMenu;
import Client.UI.menus.HelpMenu;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;


public class KundenbereichGUI extends JFrame {
    public static int status;
    //TOdo alles was nicht verwendet wird löschen
    //todo checken, ob die Exception Texte sinn ergeben

    private EShopInterface eshop;
    private Kunde eingeloggterKunde;
    private Warenkorb warenKorbDesKunden;
    private JLabel gesamtsumme;
    private  DecimalFormat decimalFormat;
    private WarenkorbTableModel warenkorbTableModel;

    public KundenbereichGUI(Kunde eingeloggterKunde, Warenkorb warenKorbDesKunden, EShopInterface eshop) throws IOException {
        super("Kundenbereich");
        this.eingeloggterKunde = eingeloggterKunde;
        this.warenKorbDesKunden = warenKorbDesKunden;
        this.eshop = eshop;
        this.warenkorbTableModel = new WarenkorbTableModel(warenKorbDesKunden);

        setupMenu();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        gesamtsumme = new JLabel();
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
        JTable warenkorbTabelle;
        JScrollPane warenkorbPane;
        JPanel suchleistenPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTextField suchleiste = new JTextField(30);
        gesamtsumme = new JLabel("Gesamtsumme: " + warenkorbTableModel.getGesamtpreis());
        rechnungsTextArea.setEditable(false);

        gesamtsumme.setText("Gesamtsumme: " + String.format("%.2f", warenkorbTableModel.getGesamtpreis()));

        decimalFormat = new DecimalFormat("0.00");
        gesamtsumme = new JLabel("Gesamtsumme: " + formatiereGesamtsumme(warenkorbTableModel.getGesamtpreis()));

        warenkorbPanel.add(rechnungsScrollPane, BorderLayout.CENTER);
        warenkorbPanel.setBorder(BorderFactory.createTitledBorder("Warenkorb"));
        warenkorbTabelle = new JTable();
        warenkorbTableModel = new WarenkorbTableModel(warenKorbDesKunden);
        warenkorbTabelle.setModel(warenkorbTableModel);
        warenkorbPane = new JScrollPane(warenkorbTabelle);
        warenkorbPanel.add(warenkorbPane, BorderLayout.CENTER);
        warenkorbPanel.add(gesamtsumme, BorderLayout.SOUTH);

        suchleistenPanel.add(suchleiste);

        mainPanel.add(suchleistenPanel, BorderLayout.NORTH);


        KundensichtTableModel artikelTableModel = new KundensichtTableModel(eshop.getAlleArtikel());
        TableRowSorter sorter = new TableRowSorter<>(artikelTableModel);
        JTable artikelTable = new JTable(artikelTableModel);
        artikelTable.setRowSorter(sorter);
        JScrollPane artikelScrollPane = new JScrollPane(artikelTable);
        artikelScrollPane.setMinimumSize(new Dimension(300, 400));


        suchleiste.setPreferredSize(getPreferredSize());

        artikelTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        artikelTable.setRowSelectionAllowed(true);

        suchleiste.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                filterArtikelTable();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                filterArtikelTable();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                filterArtikelTable();
            }

            private void filterArtikelTable() {
                String suchtext = suchleiste.getText();
                TableRowSorter<DefaultTableModel> rowSorter = (TableRowSorter<DefaultTableModel>) artikelTable.getRowSorter();
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + suchtext)); // (?i) für case-insensitive Suche
            }
        });

        artikelTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int row = artikelTable.getSelectedRow();
                String artikelBezeichnung = artikelTable.getValueAt(row, 0).toString();

                if(mouseEvent.getClickCount() == 2){
                    int option = JOptionPane.showConfirmDialog(null, "Möchten Sie den Artikel\n" + artikelBezeichnung + "\nzum Warenkorb hinzufügen?", "Artikel zum Warenkorb hinzufügen", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        try {
                            String mengenString = JOptionPane.showInputDialog("Menge eingeben");
                            int menge = Integer.parseInt(mengenString);

                            eshop.inDenWarenkorbLegen(artikelBezeichnung, menge, warenKorbDesKunden);
                            warenkorbTableModel.setWarenkorb(warenKorbDesKunden);
                            aktualisiereGesamtsumme();
                        } catch (ArtikelExistiertNichtException ex) {

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

        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // ActionListener für Ausloggen-Button
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Möchten Sie sich wirklich ausloggen?", "Ausloggen", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    StartGUI s = new StartGUI(eshop);
                    dispose();
                }
            }
        });


        artikelKaufenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String rechnung = eshop.kaufenUndRechnungEhalten(eingeloggterKunde, warenKorbDesKunden);
                    JOptionPane.showMessageDialog(null, rechnung, "Vielen Dank für Ihren Einkauf", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(rechnung);
                    aktualisiereGesamtsumme();
                } catch (WarenkorbIstLeerException ex) {
                    System.err.println("*********************************************************************************\n" +
                            "\nGewünschte Menge übersteigt Bestand!\n"+
                            "*********************************************************************************\n");
                    JOptionPane.showMessageDialog(null, "Der Warenkorb ist leer.", "Fehler", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Fehler beim Generieren der Rechnung.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        warenkorbTabelle.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int row = warenkorbTabelle.getSelectedRow();

                String artikel = warenkorbTabelle.getValueAt(row, 0).toString();

                int option = JOptionPane.showConfirmDialog(null, "Artikel aus dem Warenkorb entfernen?", "Artikel entferne", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {

                    eshop.artikelAusWarenkorbEntfernen(artikel, warenKorbDesKunden);
                    warenkorbTableModel.setWarenkorb(warenKorbDesKunden);
                    aktualisiereGesamtsumme();
                    // Bei Doppelklick soll gefragt werden, ob man den Artikel aus dem Warenkorb entfernen möchte

                }
                if (option == JOptionPane.NO_OPTION) {

                    int optionpane = JOptionPane.showConfirmDialog(null, "Menge des Gewählten Artikels ändern?", "Menge aktualisieren", JOptionPane.YES_NO_OPTION);
                    if (optionpane == JOptionPane.YES_OPTION) {
                        String mengenString = JOptionPane.showInputDialog("Menge des gewählten Artikels abändern");
                        int menge = Integer.parseInt(mengenString);
                        try {
                            eshop.inDenWarenkorbLegen(artikel, menge, warenKorbDesKunden);
                            warenkorbTableModel.setWarenkorb(warenKorbDesKunden);
                            aktualisiereGesamtsumme();
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
                eshop.warenkorbLeeren(warenKorbDesKunden);
                warenkorbTableModel.setWarenkorb(warenKorbDesKunden);

                // aktualisiereWarenkorb(rechnungsTextArea);
                aktualisiereGesamtsumme();
            }
        });


        infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        infoPanel.add(titleLabel);

        buttonPanel.add(logoutButton);
        buttonPanel.add(artikelKaufenButton);
        buttonPanel.add(warenkorbLeerenButton);

        mainPanel.add(warenkorbPanel, BorderLayout.CENTER);
        mainPanel.add(infoPanel, BorderLayout.NORTH);
        mainPanel.add(artikelScrollPane, BorderLayout.WEST);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(suchleistenPanel, BorderLayout.NORTH);
        mainPanel.add(warenkorbPanel, BorderLayout.CENTER);
        mainPanel.add(infoPanel, BorderLayout.WEST);
        mainPanel.add(artikelScrollPane, BorderLayout.EAST);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
        this.pack();

        aktualisiereGesamtsumme();
    }
    private void setupMenu() {
        // Menuleiste anlegen ...
        JMenuBar mBar = new JMenuBar();

        JMenu fileMenu = new FileMenu(this, eshop);
        mBar.add(fileMenu);

        JMenu helpMenu = new HelpMenu();
        mBar.add(helpMenu);

        this.setJMenuBar(mBar);
    }

    private void aktualisiereGesamtsumme() {
        double gesamtsummeWert = warenkorbTableModel.getGesamtpreis();
        gesamtsumme.setText("Gesamtsumme: " + formatiereGesamtsumme(gesamtsummeWert));
        gesamtsumme.revalidate();
    }

    private String formatiereGesamtsumme(double gesamtsumme) {
        return decimalFormat.format(gesamtsumme);
    }


}

package Client.UI;

import Client.UI.TableModels.KundenTableModel;
import Client.UI.TableModels.MitarbeiterTableModel;
import Common.EShopInterface;
import Common.Exceptions.*;
import Client.UI.TableModels.ArtikelTableModel;
import Client.UI.TableModels.EreignisTableModel;
import Common.Artikel;
import Common.Massengutartikel;
import Common.Mitarbeiter;
import Client.UI.menus.FileMenu;
import Client.UI.menus.HelpMenu;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;

public class MitarbeiterBereichGUI extends JFrame implements ActionListener, MouseListener, DocumentListener {

    private EShopInterface eshop;
    private int textfieldSize = 50;
    private int digitInputTextfieldsize = 10;

    //Componenten

    private Mitarbeiter eingeloggterMitarbeiter;
    private JButton zurückButton = new JButton("Ausloggen");
    private JButton massengutArtikelAnlegenPopupButton = new JButton(" Massengutartikel anlegen ");
    private JButton artikelAnlegenPopupButton = new JButton("   Einzelartikel anlegen   ");
    private JButton mitarbeiterkontoAnlegen = new JButton("Registrieren");
    private JTextField usernameTextfield = new JTextField(textfieldSize);
    private JTextField passwotTextfield = new JTextField(textfieldSize);
    private JTextField nachnameTextfield = new JTextField(textfieldSize);
    private JTextField vornameTextfield = new JTextField(textfieldSize);
    private JButton registerButton = new JButton("Mitarbeiter Registrieren");
    private JButton bestandVerringernButton = new JButton("Verringern");
    private JButton anlegenButtonErhöhen = new JButton("  Erhöhen ");
    private JTextField bezeichnungsTextfieldVeränderung = new JTextField(textfieldSize);
    private JTextField mengenänderungstextfeld = new JTextField(digitInputTextfieldsize);
    private JTextField bezeichnungsTextfieldEinzelartikelAnlegen = new JTextField(textfieldSize);
    private JTextField artikelnummerTextfieldEinzelartikelAnlegen = new JTextField(textfieldSize);
    private JTextField bestandTextfieldEinzelartikelAnlegen = new JTextField(textfieldSize);
    private JTextField preisTextfieldEinzelartikelAnlegen = new JTextField(textfieldSize);
    private JTextField verkäuflicheMengefield = new JTextField(textfieldSize);
    private JTextField bezeichnungsTextfieldMassengutartikelAnlegen = new JTextField(textfieldSize);
    private JTextField artikelnummerTextfieldMassengutartikelAnlegen = new JTextField(textfieldSize);
    private JTextField bestandTextfieldMassengutartikeAnlegen = new JTextField(textfieldSize);
    private JTextField preisTextfieldMassengutartikelAnlegen = new JTextField(textfieldSize);
    private JButton anlegenEinzelartikelAbschließen = new JButton("Neuen artikel anlegen");
    private JButton anlegenMassengutArtikelAbschliessen = new JButton("Neuen artikel anlegen");
    private JComboBox<String> listenauswahl;
    private JDialog popup;
    private JPanel midpanel;
    private JScrollPane scrollPaneArtikelliste;
    private JTable artikelTabelle;
    private ArtikelTableModel model;
    private JScrollPane tablePane;

    private JTable mitarbeiterTabelle;

    private MitarbeiterTableModel mitarbeiterTableModel;

    private JTable kundenTabelle;
    private KundenTableModel kundenTableModel;

    private JTable ereignisTabelle;
    private EreignisTableModel ereignisTableModel;

    private JTextField sucheArtikel;

    private TableRowSorter sorter;


    public MitarbeiterBereichGUI(Mitarbeiter eingeloggterMitarbeiter, EShopInterface eshop) {
        this.eshop = eshop;

        //Menu definieren
        setupMenu();

        this.eingeloggterMitarbeiter = eingeloggterMitarbeiter;

        this.setTitle("\"Roha & Sanjana's Eshop\""); //Title des Jframe wird erstellt
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sorgt dafür, das beim klicken des Exit das fenster auch geschlossen wird
        this.setResizable(true); // erlaubt uns die Größe des fensters zu ändern

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        mitarbeiterbereich();
        this.setVisible(true);//sorgt dafür das der Frame auch zu sehen ist

    }

    private void setupMenu() {
        // Menuleiste anlegen
        JMenuBar mBar = new JMenuBar();

        JMenu fileMenu = new FileMenu(this, eshop);
        mBar.add(fileMenu);

        JMenu helpMenu = new HelpMenu();
        mBar.add(helpMenu);

        this.setJMenuBar(mBar);
    }


    //Zusammenbau  aller Himmelsrichtungen
    private void mitarbeiterbereich() {
        JPanel mitarbeiterFenster = new JPanel();
        mitarbeiterFenster.setVisible(true);
        mitarbeiterFenster.setLayout(new BorderLayout(5, 5));


        mitarbeiterFenster.add(westpanel(), BorderLayout.WEST);
        mitarbeiterFenster.add(northpanel(), BorderLayout.NORTH);
        mitarbeiterFenster.add(midpanel(), BorderLayout.CENTER);
        mitarbeiterFenster.add(eastpanel(), BorderLayout.EAST);


        add(mitarbeiterFenster);
    }

    private JPanel northpanel() {
        JPanel northpanel = new JPanel();
        northpanel.setVisible(true);//Jpanel ist sichtbar
        northpanel.setLayout(new FlowLayout()); // sorgt dafür das alles auf der Y-Achse liegt
        northpanel.setPreferredSize(new Dimension(300, 100));

        zurückButton.addActionListener(this);
        northpanel.add(zurückButton, BorderLayout.EAST);

        registerButton.addActionListener(this);
        northpanel.add(registerButton, BorderLayout.EAST);

        return northpanel;

    }

    private JPanel westpanel() {
        JPanel westpanel = new JPanel();
        westpanel.setVisible(true);
        westpanel.setLayout(new BoxLayout(westpanel, BoxLayout.Y_AXIS));
        westpanel.setPreferredSize(new Dimension(300, 100));

        westpanel.add(Box.createVerticalStrut(40));
        westpanel.add(bestandVerändern());
        westpanel.add(Box.createVerticalStrut(40));


        artikelAnlegenPopupButton.addActionListener(this);
        massengutArtikelAnlegenPopupButton.addActionListener(this);

        westpanel.add(Box.createVerticalStrut(60));
        westpanel.add(artikelAnlegenPopupButton);
        westpanel.add(Box.createVerticalStrut(20));
        westpanel.add(massengutArtikelAnlegenPopupButton);

        return westpanel;
    }

    private JPanel eastpanel() {
        JPanel eastpanel = new JPanel();
        eastpanel.setVisible(true);//Jpanel ist sichtbar
        eastpanel.setLayout(new BoxLayout(eastpanel, BoxLayout.Y_AXIS));
        eastpanel.setBorder(BorderFactory.createTitledBorder("Listen ausgeben lassen"));
        eastpanel.add(listenCombobox());

        return eastpanel;
    }

    private JPanel midpanel() {
        midpanel = new JPanel();
        midpanel.setVisible(true);
        midpanel.setLayout(new FlowLayout());

        scrollPaneArtikelliste = new JScrollPane(artikellistTable());
        scrollPaneArtikelliste.setPreferredSize(new Dimension(700, 500));

        sucheArtikel = new JTextField(textfieldSize);
        setPreferredSize(getPreferredSize());
        sucheArtikel.getDocument().addDocumentListener(this);

        midpanel.add(sucheArtikel, BorderLayout.NORTH);
        midpanel.add(scrollPaneArtikelliste);


        return midpanel;
    }

    //wird beim Anlegen neuer Artikel und beim Registrieren der Mitarbeiter hinzugefügt
    private JDialog popup(Component component, String usage) {
        popup = new JDialog();
        popup.setVisible(true);
        popup.setSize(300, 500);
        popup.setLocationRelativeTo(null);
        popup.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        popup.setResizable(false);
        popup.setTitle(usage);

        popup.add(component);

        return popup;
    }


    private JPanel registrierung() {
        JPanel registerfenster = new JPanel();
        registerfenster.setVisible(true);
        registerfenster.setLayout(new BoxLayout(registerfenster, BoxLayout.Y_AXIS));
        registerfenster.setBorder(BorderFactory.createTitledBorder("Mitarbeiter Resgistrieren"));

        usernameTextfield.setMaximumSize(usernameTextfield.getPreferredSize());
        passwotTextfield.setMaximumSize(passwotTextfield.getPreferredSize());
        nachnameTextfield.setMaximumSize(nachnameTextfield.getPreferredSize());
        vornameTextfield.setMaximumSize(vornameTextfield.getPreferredSize());

        registerfenster.add(Box.createVerticalStrut(30));
        registerfenster.add(new JLabel("Username: "));
        registerfenster.add(usernameTextfield);
        registerfenster.add(new JLabel("Passwort: "));
        registerfenster.add(passwotTextfield);
        registerfenster.add(new JLabel("Nachname: "));
        registerfenster.add(nachnameTextfield);
        registerfenster.add(new JLabel("Vorname: "));
        registerfenster.add(vornameTextfield);

        mitarbeiterkontoAnlegen.addActionListener(this);
        registerfenster.add(mitarbeiterkontoAnlegen);

        return registerfenster;
    }

    // Feld zum Anlegen neuer Artikel
    private JPanel neuenArtikelAnlegen() {
        JPanel anlegen = new JPanel();
        anlegen.setVisible(true);
        anlegen.setLayout(new BoxLayout(anlegen, BoxLayout.Y_AXIS));
        anlegen.setBorder(BorderFactory.createTitledBorder("Einzelartikel"));
        anlegen.add(Box.createVerticalStrut(50));

        bezeichnungsTextfieldEinzelartikelAnlegen.setMaximumSize(bezeichnungsTextfieldEinzelartikelAnlegen.getPreferredSize());
        artikelnummerTextfieldEinzelartikelAnlegen.setMaximumSize(artikelnummerTextfieldEinzelartikelAnlegen.getPreferredSize());
        bestandTextfieldEinzelartikelAnlegen.setMaximumSize(bestandTextfieldEinzelartikelAnlegen.getPreferredSize());
        preisTextfieldEinzelartikelAnlegen.setMaximumSize(preisTextfieldEinzelartikelAnlegen.getPreferredSize());

        anlegen.add(new JLabel("Artikelbezeichnung: "));
        anlegen.add(bezeichnungsTextfieldEinzelartikelAnlegen);
        anlegen.add(new JLabel("Artikelnummer: "));
        anlegen.add(artikelnummerTextfieldEinzelartikelAnlegen);
        anlegen.add(new JLabel("Bestand: "));
        anlegen.add(bestandTextfieldEinzelartikelAnlegen);
        anlegen.add(new JLabel("Preis in €: "));
        anlegen.add(preisTextfieldEinzelartikelAnlegen);

        anlegenEinzelartikelAbschließen.addActionListener(this);
        anlegen.add(anlegenEinzelartikelAbschließen);

        return anlegen;
    }

    // Feld zum Anlegen neuer MassengutArtikel
    private JPanel neuenMassengutartikelAnlegen() {
        JPanel anlegen = new JPanel();
        anlegen.setVisible(true);
        anlegen.setLayout(new BoxLayout(anlegen, BoxLayout.Y_AXIS));
        anlegen.setBorder(BorderFactory.createTitledBorder("Massengutartikel"));

        anlegen.add(Box.createVerticalStrut(50));


        bezeichnungsTextfieldMassengutartikelAnlegen.setMaximumSize(bezeichnungsTextfieldMassengutartikelAnlegen.getPreferredSize());
        artikelnummerTextfieldMassengutartikelAnlegen.setMaximumSize(artikelnummerTextfieldMassengutartikelAnlegen.getPreferredSize());
        bestandTextfieldMassengutartikeAnlegen.setMaximumSize(bestandTextfieldMassengutartikeAnlegen.getPreferredSize());
        preisTextfieldMassengutartikelAnlegen.setMaximumSize(preisTextfieldMassengutartikelAnlegen.getPreferredSize());
        verkäuflicheMengefield.setMaximumSize(verkäuflicheMengefield.getPreferredSize());

        anlegen.add(new JLabel("Artikelbezeichnung: "));
        anlegen.add(bezeichnungsTextfieldMassengutartikelAnlegen);
        anlegen.add(new JLabel("Artikelnummer: "));
        anlegen.add(artikelnummerTextfieldMassengutartikelAnlegen);
        anlegen.add(new JLabel("Bestand: "));
        anlegen.add(bestandTextfieldMassengutartikeAnlegen);
        anlegen.add(new JLabel("Preis in €: "));
        anlegen.add(preisTextfieldMassengutartikelAnlegen);
        anlegen.add(new JLabel("Minimale Einkaufsmenge: "));
        anlegen.add(verkäuflicheMengefield);

        anlegenMassengutArtikelAbschliessen.addActionListener(this);
        anlegen.add(anlegenMassengutArtikelAbschliessen);

        return anlegen;
    }

    //Kann beides, Erhöhen und Verringern
    public JPanel bestandVerändern() {
        JPanel bestandsAenderung = new JPanel();
        bestandsAenderung.setVisible(true);
        bestandsAenderung.setLayout(new BoxLayout(bestandsAenderung, BoxLayout.Y_AXIS));
        bestandsAenderung.setBorder(BorderFactory.createTitledBorder("Bestand aktualisieren"));


        bestandsAenderung.add(Box.createVerticalStrut(20));

        bezeichnungsTextfieldVeränderung.setMaximumSize(bezeichnungsTextfieldVeränderung.getPreferredSize());
        mengenänderungstextfeld.setMaximumSize(mengenänderungstextfeld.getPreferredSize());


        bestandsAenderung.add(new JLabel("Artikelbezeichnung: "));
        bestandsAenderung.add(bezeichnungsTextfieldVeränderung);
        bestandsAenderung.add(new JLabel("Mengenänderung: "));
        bestandsAenderung.add(mengenänderungstextfeld);

        bestandVerringernButton.addActionListener(this);
        anlegenButtonErhöhen.addActionListener(this);

        bestandsAenderung.add(bestandVerringernButton);
        bestandsAenderung.add(anlegenButtonErhöhen);

        return bestandsAenderung;
    }

    private JComboBox<String> listenCombobox() {
        String[] listen = {"Registrierte Mitarbeiter ausgeben", "Registrierte Kunden ausgeben", "Ereignisse ausgeben"};
        listenauswahl = new JComboBox<>(listen);
        listenauswahl.addActionListener(this);

        return listenauswahl;
    }


    private JScrollPane artikellistTable() {

        artikelTabelle = new JTable();
        model = new ArtikelTableModel(eshop.getAlleArtikel());
        sorter = new TableRowSorter<>(model);
        artikelTabelle.setModel(model);
        artikelTabelle.setRowSorter(sorter);
        artikelTabelle.addMouseListener(this);
        tablePane = new JScrollPane(artikelTabelle);

        return tablePane;
    }

    private JTable kundenTable() {
        kundenTabelle = new JTable();
        kundenTableModel = new KundenTableModel(eshop.getAlleKunden());
        sorter = new TableRowSorter<>(kundenTableModel);
        kundenTabelle.setRowSorter(sorter);
        kundenTabelle.setModel(kundenTableModel);

        return kundenTabelle;
    }


    private JTable mitarbeiterTable() {
        mitarbeiterTabelle = new JTable();
        mitarbeiterTableModel = new MitarbeiterTableModel(eshop.getAlleMitarbeiter());
        sorter = new TableRowSorter<>(mitarbeiterTableModel);
        mitarbeiterTabelle.setModel(mitarbeiterTableModel);
        mitarbeiterTabelle.setRowSorter(sorter);
        mitarbeiterTabelle.setModel(mitarbeiterTableModel);

        return mitarbeiterTabelle;
    }


    private JTable ereignisTable() {
        ereignisTabelle = new JTable();
        ereignisTableModel = new EreignisTableModel(eshop.ereignisseNachDatum());
        sorter = new TableRowSorter<>(ereignisTableModel);
        ereignisTabelle.setModel(ereignisTableModel);
        ereignisTabelle.setRowSorter(sorter);


        return ereignisTabelle;
    }

    private enum Eventsource {
        AUSLOGGEN,
        BESTAND_VERRINGERN,
        BESTAND_ERHÖHEN,
        EINZELARTIKEL_ANLEGEN_FENSTER,
        EINZELARTIKEL_ANLEGEN,
        MASSENGUTARTIKEL_ANLEGEN_FENSTER,
        MASSENGUTARTIKEL_ANLEGEN,
        REGISTRIERUNGSFENSTER,
        MITARBEITER_REGISTRIEREN,
        LISTEN_AUSGEBE,

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        Eventsource source = null;

        if (actionEvent.getSource() == zurückButton) {
            source = Eventsource.AUSLOGGEN;
        } else if (actionEvent.getSource() == bestandVerringernButton) {
            source = Eventsource.BESTAND_VERRINGERN;
        } else if (actionEvent.getSource() == anlegenButtonErhöhen) {
            source = Eventsource.BESTAND_ERHÖHEN;
        } else if (actionEvent.getSource() == artikelAnlegenPopupButton) {
            source = Eventsource.EINZELARTIKEL_ANLEGEN_FENSTER;
        } else if (actionEvent.getSource() == anlegenEinzelartikelAbschließen) {
            source = Eventsource.EINZELARTIKEL_ANLEGEN;
        } else if (actionEvent.getSource() == massengutArtikelAnlegenPopupButton) {
            source = Eventsource.MASSENGUTARTIKEL_ANLEGEN_FENSTER;
        } else if (actionEvent.getSource() == anlegenMassengutArtikelAbschliessen) {
            source = Eventsource.MASSENGUTARTIKEL_ANLEGEN;
        } else if (actionEvent.getSource() == registerButton) {
            source = Eventsource.REGISTRIERUNGSFENSTER;
        } else if (actionEvent.getSource() == mitarbeiterkontoAnlegen) {
            source = Eventsource.MITARBEITER_REGISTRIEREN;
        } else if (actionEvent.getSource() == listenauswahl) {
            source = Eventsource.LISTEN_AUSGEBE;
        }

        //Methoden darunter
        switch (source) {
            case AUSLOGGEN:
                StartGUI s = new StartGUI(eshop);
                this.dispose();
                break;
            case BESTAND_VERRINGERN:
                bestandVerringern();
                break;
            case BESTAND_ERHÖHEN:
                bestanErhoehen();
                break;
            case EINZELARTIKEL_ANLEGEN_FENSTER:
                popup(neuenArtikelAnlegen(), "Neuen Einzelartikel anlegen");
                break;
            case EINZELARTIKEL_ANLEGEN:
                artikelAnlegenAbschließen();
                break;
            case MASSENGUTARTIKEL_ANLEGEN_FENSTER:
                popup(neuenMassengutartikelAnlegen(), "Neuen Massengutartikel anlegen");
                break;
            case MASSENGUTARTIKEL_ANLEGEN:
                massengutartikelAnlegenAbschließen();
                break;
            case REGISTRIERUNGSFENSTER:
                popup(registrierung(), "Neuen Mitarbeiter Registrieren");
                break;
            case MITARBEITER_REGISTRIEREN:
                mitarbeiterRegistrieren();
                break;
            case LISTEN_AUSGEBE:
                gewählteListeAusgeben();
                break;
            default:
                System.out.println("Event detected");
                break;
        }

    }


    // Methoden der Switchcase
    private void gewählteListeAusgeben() {
        String selectedListItem = listenauswahl.getSelectedItem().toString();

        if (selectedListItem.equals("Registrierte Mitarbeiter ausgeben")) {
            JScrollPane mitarbeiterScrollPane = new JScrollPane(mitarbeiterTable());
            JOptionPane.showMessageDialog(null, mitarbeiterScrollPane, "Registrierte Mitarbeiter", JOptionPane.PLAIN_MESSAGE);
        } else if (selectedListItem.equals("Registrierte Kunden ausgeben")) {
            JScrollPane kundenScrollPane = new JScrollPane(kundenTable());
            JOptionPane.showMessageDialog(null, kundenScrollPane, "Registrierte Kunden", JOptionPane.PLAIN_MESSAGE);
        } else if (selectedListItem.equals("Ereignisse ausgeben")) {
            JScrollPane ereignisScrollPane = new JScrollPane(ereignisTable());
            JOptionPane.showMessageDialog(null, ereignisScrollPane, "Ereignisse nach Datum geordnet", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void mitarbeiterRegistrieren() {
        try {

            String username = usernameTextfield.getText();
            String pw = passwotTextfield.getText();
            String nachname = nachnameTextfield.getText();
            String vorname = vornameTextfield.getText();

            //vornameTextfield.getText().isEmpty()


            Mitarbeiter neuerMitarbeiter = new Mitarbeiter(username, pw, nachname, vorname);
            eshop.mitarbeiterRegistrieren(neuerMitarbeiter);

            eshop.schreibeMitarbeiter();
            System.out.println("Registriert: " + neuerMitarbeiter);
            popup.dispose();
        } catch (UserExistiertBereitsException e) {
            String kontoExistiertSchon = "Dieses Konto existiert bereits. Bitte versuchen Sie es nochmal.\n";
            System.err.println(
                    "*********************************************************************************\n" +
                            kontoExistiertSchon +
                            "*********************************************************************************\n");

            popup.dispose();
            JOptionPane.showMessageDialog(null, kontoExistiertSchon, "Konto existiert bereits", JOptionPane.ERROR_MESSAGE);

        } catch (LeeresTextfieldException e) {
            String leeresTextfeld = "Bitte füllen Sie alle Textfelder aus.\n";
            System.err.println(
                    "*********************************************************************************\n" +
                            leeresTextfeld +
                            "*********************************************************************************\n");
            popup.dispose();
            JOptionPane.showMessageDialog(null, leeresTextfeld, "Leere Textfelder", JOptionPane.ERROR_MESSAGE);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void massengutartikelAnlegenAbschließen() {
        try {
            String bezeichnung = bezeichnungsTextfieldMassengutartikelAnlegen.getText();
            int artikelnummer = Integer.parseInt(artikelnummerTextfieldMassengutartikelAnlegen.getText());
            int bestand = Integer.parseInt(bestandTextfieldMassengutartikeAnlegen.getText());
            double preis = Double.parseDouble(preisTextfieldMassengutartikelAnlegen.getText());
            int zumKaufVerfügbar = Integer.parseInt(verkäuflicheMengefield.getText());

            Massengutartikel m = new Massengutartikel(bezeichnung, artikelnummer, bestand, preis, zumKaufVerfügbar);
            eshop.massengutArtikelHinzufügen(m, eingeloggterMitarbeiter);
            eshop.schreibeArtikel();
            eshop.schreibeEreignis();


            System.out.println("Erstellt: " + m);
            model.setArtikelListe(eshop.getAlleArtikel());
            popup.dispose();
        } catch (NumberFormatException e) {
            String numberFormat = "Bitte Nummer richtig eingegeben.\n";
            System.err.println("*********************************************************************************\n" +
                    numberFormat + "\nBitte achten Sie beim Preis darauf ein “.“ zu verwenden." +
                    "*********************************************************************************\n");
            popup.dispose();
            JOptionPane.showMessageDialog(null, numberFormat + "\nBitte achten Sie beim Preis darauf ein “.“ zu verwenden.", "Eingabenfehler", JOptionPane.ERROR_MESSAGE);

        } catch (ArtikelExistiertBereitsException e) {
            String artikelExistiert = "Der von Ihnen gewählte Artikel existiert bereits. Bitte versuchen Sie es nochmal.\n";
            System.err.println("*********************************************************************************\n" +
                    artikelExistiert +
                    "*********************************************************************************\n");

            popup.dispose();
            JOptionPane.showMessageDialog(null, artikelExistiert, "Artikel im Bestand vorhanden", JOptionPane.ERROR_MESSAGE);


        } catch (InputMismatchException e) {
            System.err.println(
                    "*********************************************************************************\n" +
                            "Ungültige Eingabe!\n" +
                            "Bei Eingabe der Artikelnummer bitte nur Zahlen verwenden.\n" +
                            "Ungültige Eingabe! Bei Eingabe des Preises achten Sie darauf ein Komma zu verwenden.\n" +
                            "Bitte versuchen Sie es nochmal.\n" +

                            "*********************************************************************************\n");
        } catch (LeeresTextfieldException e) {
            String leeresTextfeld = "Bitte füllen Sie alle Textfelder aus.\n";
            System.err.println("*********************************************************************************\n" +
                    leeresTextfeld +
                    "*********************************************************************************\n");

            JOptionPane.showMessageDialog(null, leeresTextfeld, "Leere Textfelder", JOptionPane.ERROR_MESSAGE);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void artikelAnlegenAbschließen() {
        try {
            String bezeichnung = bezeichnungsTextfieldEinzelartikelAnlegen.getText();
            int artikelnummer = Integer.parseInt(artikelnummerTextfieldEinzelartikelAnlegen.getText());
            int bestand = Integer.parseInt(bestandTextfieldEinzelartikelAnlegen.getText());
            double preis = Double.parseDouble(preisTextfieldEinzelartikelAnlegen.getText());
            Artikel a = new Artikel(bezeichnung, artikelnummer, bestand, preis);
            eshop.artHinzufügen(a, eingeloggterMitarbeiter);
            eshop.schreibeEreignis();
            eshop.schreibeArtikel();
            popup.dispose();

            model.setArtikelListe(eshop.getAlleArtikel());

            System.out.println("Erstellt: " + a);
        } catch (NumberFormatException e) {
            String numberFormat = "Bitte Nummer richtig eingegeben.\n" + "Bitte achten Sie beim Preis darauf ein “.“ zu verwenden.\n";
            System.err.println("*********************************************************************************\n" +
                    numberFormat +
                    "*********************************************************************************\n");
            popup.dispose();
            JOptionPane.showMessageDialog(null, numberFormat, "Eingabenfehler", JOptionPane.ERROR_MESSAGE);


        } catch
        (ArtikelExistiertBereitsException e) {
            String artikelExistiert = "Der von Ihnen gewählte Artikel existiert bereits. Bitte versuchen Sie es nochmal.\n";
            System.err.println("*********************************************************************************\n" +
                    artikelExistiert +
                    "*********************************************************************************\n");

            popup.dispose();
            JOptionPane.showMessageDialog(null, artikelExistiert, "Artikel im Bestand vorhanden", JOptionPane.ERROR_MESSAGE);


        } catch (InputMismatchException e) {
            System.err.println(
                    "*********************************************************************************\n" +
                            "Ungültige Eingabe!\n" +
                            "Bei Eingabe der Artikelnummer bitte nur Zahlen verwenden.\n" +
                            "Ungültige Eingabe! Bei Eingabe des Preises achten Sie darauf ein Komma zu verwenden.\n" +
                            "Bitte versuchen Sie es nochmal.\n" +

                            "*********************************************************************************\n");
        } catch (LeeresTextfieldException e) {
            String leeresTextfeld = "Bitte füllen Sie alle Textfelder aus.\n";
            System.err.println("*********************************************************************************\n" +
                    leeresTextfeld +
                    "*********************************************************************************\n");

            JOptionPane.showMessageDialog(null, leeresTextfeld, "Leere Textfelder", JOptionPane.ERROR_MESSAGE);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void bestanErhoehen() {
        try {

            String artikelname = bezeichnungsTextfieldVeränderung.getText();
            int menge = Integer.parseInt(mengenänderungstextfeld.getText());
            eshop.bestandErhöhen(artikelname, menge, eingeloggterMitarbeiter);

            //ereignisTableModel.setEreignisse(eshop.ereignisseNachDatum());
            model.setArtikelListe(eshop.getAlleArtikel());


            bezeichnungsTextfieldVeränderung.setText("");
            mengenänderungstextfeld.setText("");

            eshop.schreibeArtikel();
            eshop.schreibeEreignis();

        } catch (ArtikelExistiertNichtException e) {
            String artikelExistiertNicht = "Der von Ihnen gewählte Artikel existiert nicht. Bitte versuchen Sie es nochmal.\n";
            System.err.println("*********************************************************************************\n" +
                    artikelExistiertNicht +
                    "*********************************************************************************\n");

            JOptionPane.showMessageDialog(null, artikelExistiertNicht, "Artikel nicht im Bestand vorhanden", JOptionPane.ERROR_MESSAGE);

            bezeichnungsTextfieldVeränderung.setText("");
            mengenänderungstextfeld.setText("");


        } catch (LeeresTextfieldException e) {
            String leeresTextfeld = "Bitte füllen Sie alle Textfelder aus.\n";
            System.err.println("*********************************************************************************\n" +
                    leeresTextfeld +
                    "*********************************************************************************\n");

            JOptionPane.showMessageDialog(null, leeresTextfeld, "Leere Textfelder", JOptionPane.ERROR_MESSAGE);


        } catch (NumberFormatException e) {
            String numberFormat = "Bitte Nummer richtig eingegeben.\n";
            System.err.println("*********************************************************************************\n" +
                    numberFormat +
                    "*********************************************************************************\n");

            JOptionPane.showMessageDialog(null, numberFormat, "Eingabenfehler", JOptionPane.ERROR_MESSAGE);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void bestandVerringern() {
        try {

            String artikelbez = bezeichnungsTextfieldVeränderung.getText();
            int menge = Integer.parseInt(mengenänderungstextfeld.getText());
            eshop.bestanNiedriger(artikelbez, menge, eingeloggterMitarbeiter);
            model.setArtikelListe(eshop.getAlleArtikel());

            mengenänderungstextfeld.setText("");
            bezeichnungsTextfieldVeränderung.setText("");

            eshop.schreibeArtikel();
            eshop.schreibeEreignis();


        } catch (ArtikelExistiertNichtException e) {
            String artikelExistiertNicht = "Der von Ihnen gewählte Artikel existiert nicht. Bitte versuchen Sie es nochmal.\n";
            System.err.println("*********************************************************************************\n" +
                    artikelExistiertNicht +
                    "*********************************************************************************\n");

            JOptionPane.showMessageDialog(null, artikelExistiertNicht, "Artikel nicht im Bestand vorhanden", JOptionPane.ERROR_MESSAGE);

            bezeichnungsTextfieldVeränderung.setText("");
            mengenänderungstextfeld.setText("");

        } catch (UngueltigeMengeException u) {
            String mengeZuHoch = "Die von Ihnen gewählte Menge ist zu höher als die im Bestand vorhandene Menge .\nBitte versuchen Sie es nochmal.\n";
            System.err.println("*********************************************************************************\n" +
                    mengeZuHoch +
                    "*********************************************************************************\n");


            JOptionPane.showMessageDialog(null, mengeZuHoch, "Angegebene Menge nicht vorhanden", JOptionPane.ERROR_MESSAGE);


        } catch (LeeresTextfieldException e) {
            String leeresTextfeld = "Bitte füllen Sie alle Textfelder aus.\n";
            System.err.println("*********************************************************************************\n" +
                    leeresTextfeld +
                    "*********************************************************************************\n");

            JOptionPane.showMessageDialog(null, leeresTextfeld, "Leere Textfelder", JOptionPane.ERROR_MESSAGE);


        } catch (NumberFormatException e) {
            String numberFormat = "Bitte Nummer richtig eingegeben.\n";
            System.err.println("*********************************************************************************\n" +
                    numberFormat +
                    "*********************************************************************************\n");

            JOptionPane.showMessageDialog(null, numberFormat, "Eingabenfehler", JOptionPane.ERROR_MESSAGE);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            int row = artikelTabelle.getSelectedRow();
            int column = artikelTabelle.getSelectedColumn();

            if (column == 0) {
                String value = artikelTabelle.getValueAt(row, column).toString();

                bezeichnungsTextfieldVeränderung.setText("");
                bezeichnungsTextfieldVeränderung.setText(value);


                System.out.println("Clicked value: " + value);
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

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {

        aktualisiereTabelle(sucheArtikel.getText());

    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        aktualisiereTabelle(sucheArtikel.getText());
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
    }


    private void aktualisiereTabelle(String suche) {
        RowFilter<ArtikelTableModel, Object> filter = new RowFilter<ArtikelTableModel, Object>() {
            public boolean include(Entry<? extends ArtikelTableModel, ? extends Object> entry) {
                String searchText = suche.toLowerCase(); // Suchtext in Kleinbuchstaben umwandeln
                ArtikelTableModel model = entry.getModel();
                int columnCount = model.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    String cellText = model.getValueAt((int) entry.getIdentifier(), i).toString();
                    if (cellText.toLowerCase().contains(searchText)) { // Vergleich in Kleinbuchstaben durchführen
                        return true;
                    }
                }
                return false;
            }
        };
        sorter.setRowFilter(filter);
    }

}

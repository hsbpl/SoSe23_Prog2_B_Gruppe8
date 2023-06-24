package UI;

import Domain.EShop;
import Exceptions.*;
import ValueObjekt.Artikel;
import ValueObjekt.Kunde;
import ValueObjekt.Massengutartikel;
import ValueObjekt.Mitarbeiter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class MitarbeiterBereichGUI extends JFrame implements ActionListener {


    private EShop eshop;
    private int textfieldSize = 50;
    private int digitInputTextfieldsize = 10;

    private Mitarbeiter eingeloggterMitarbeiter;
    private JButton zurückButton = new JButton("Ausloggen");
    private JButton massengutArtikelAnlegenPopupButton = new JButton(" Massengutartikel anlegen ");
    private JButton artikelAnlegenPopupButton = new JButton("  Einzelartikel anlegen   ");
    private JButton mitarbeiterkontoAnlegen = new JButton("Registrieren");
    private JTextField usernameTextfield = new JTextField(textfieldSize);
    private JTextField passwotTextfield = new JTextField(textfieldSize);
    private JTextField nachnameTextfield = new JTextField(textfieldSize);
    private JTextField vornameTextfield = new JTextField(textfieldSize);
    private JButton registerButton = new JButton("Mitarbeiter Registrieren");
    private JButton bestandVerringernButton = new JButton("Aktualisieren");
    private JTextField bezeichnungsTextfieldVerringerung = new JTextField(textfieldSize);
    private JTextField veringerungsTextfield = new JTextField(digitInputTextfieldsize);
    private JButton anlegenButtonErhöhen = new JButton("Aktualisieren");
    private JTextField bezeichnungsTextfieldErhöhung = new JTextField(textfieldSize);
    private JTextField erhöhungTextfield = new JTextField(digitInputTextfieldsize);
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
    private JComboBox <String> artikelausgabe;
    private JDialog popup;
    private JDialog listpopup;
    private JPanel midpanel;
    private JScrollPane scrollPaneArtikelliste;
    private JTable tabelle;
    private ArtikelTableModel model;
    private JScrollPane tablePane;



    public MitarbeiterBereichGUI(Mitarbeiter eingeloggterMitarbeiter, EShop eshop){
        this.eshop = eshop;

        this.eingeloggterMitarbeiter = eingeloggterMitarbeiter;

        this.setTitle("\"Roha & Sanjana's Eshop\""); //Title des Jframe wird erstellt
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sorgt dafür, das beim klicken des Exit das fenster auch geschlossen wird
        this.setResizable(true); // erlaubt uns die Größe des fensters zu ändern

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        mitarbeiterbereich();
        this.setVisible(true);//sorgt dafür das der Frame auch zu sehen ist

    }


    //Extrafenster wenn der Mitarbeiter eingeloggt / registriert ist
    private void mitarbeiterbereich() {
        JPanel mitarbeiterFenster = new JPanel();
        mitarbeiterFenster.setVisible(true);//sorgt dafür das der Frame auch zu sehen ist
        mitarbeiterFenster.setLayout(new BorderLayout(5, 5)); //aufteilung in borderlayout, die Zahlen sind für den Abstand da


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

    private Component westpanel() {
        JPanel westpanel = new JPanel();
        westpanel.setVisible(true);//Jpanel ist sichtbar
        westpanel.setLayout(new BoxLayout(westpanel, BoxLayout.Y_AXIS)); // sorgt dafür das alles auf der Y-Achse liegt
        westpanel.setPreferredSize(new Dimension(300, 100));

        westpanel.add(Box.createVerticalStrut(40));
        westpanel.add(bestandErhöhen());  //Erhöhen und verringern der warenmengen soll im WEsten angezeigt werden
        westpanel.add(Box.createVerticalStrut(40));
        westpanel.add(bestandVerringern());


        artikelAnlegenPopupButton.addActionListener(this);
        massengutArtikelAnlegenPopupButton.addActionListener(this);

        westpanel.add(Box.createVerticalStrut(60));
        westpanel.add(artikelAnlegenPopupButton);
        westpanel.add(Box.createVerticalStrut(20));
        westpanel.add(massengutArtikelAnlegenPopupButton);

        return westpanel;
    }

    private Component eastpanel() {
        JPanel eastpanel = new JPanel();
        eastpanel.setVisible(true);//Jpanel ist sichtbar
        eastpanel.setLayout(new BoxLayout(eastpanel, BoxLayout.Y_AXIS)); // sorgt dafür das alles auf der Y-Achse liegt

        eastpanel.add(listenCombobox());

        return eastpanel;
    }

    private JPanel midpanel() {
        midpanel = new JPanel();
        midpanel.setVisible(true);//Jpanel ist sichtbar
        midpanel.setLayout(new FlowLayout());


        String[] listen = { "Alphabetische Ausgabe", "Nummerische Ausgabe"};
        artikelausgabe = new JComboBox<>(listen);
        artikelausgabe.addActionListener(this);

        scrollPaneArtikelliste = new JScrollPane(artikellistTable()); //liste wird dem scrollpane hinzugefügt
        scrollPaneArtikelliste.setPreferredSize(new Dimension(700, 500));

        midpanel.add(scrollPaneArtikelliste);
        midpanel.add(artikelausgabe);

        return midpanel;
    }

    private Component popup(Component component, String usage) {
        popup = new JDialog();
        popup.setVisible(true);
        popup.setSize(300, 500);
        popup.setLocationRelativeTo(null);//popup erscheint in der mitte
        popup.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); //Sorgt dafür, das beim klicken des Exit das fenster auch geschlossen wird
        popup.setResizable(false); // erlaubt uns die Größe des fensters zu ändern
        popup.setTitle(usage);

        popup.add(component);

        return popup;
    }

    private Component listpopup(JList jList, String usage) {
        listpopup = new JDialog();
        listpopup.setVisible(true);
        listpopup.setSize(500, 500);
        listpopup.setLocationRelativeTo(null);//popup erscheint in der mitte
        listpopup.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); //Sorgt dafür, das beim klicken des Exit das fenster auch geschlossen wird
        listpopup.setResizable(true); // erlaubt uns die Größe des fensters zu ändern
        listpopup.setTitle(usage);

        JScrollPane scrollPane = new JScrollPane(jList); //liste wird dem scrollpane hinzugefügt
        listpopup.add(scrollPane);

        return listpopup;
    }



    private Component registrierung() {
        JPanel registerfenster = new JPanel();
        registerfenster.setVisible(true);
        registerfenster.setLayout(new BoxLayout(registerfenster, BoxLayout.Y_AXIS));


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
    private Component neuenMassengutartikelAnlegen() {
        JPanel anlegen = new JPanel();
        anlegen.setVisible(true);
        anlegen.setLayout(new BoxLayout(anlegen, BoxLayout.Y_AXIS));

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

    public JPanel bestandErhöhen() {
        JPanel erhöhen = new JPanel();
        erhöhen.setVisible(true);
        erhöhen.setLayout(new BoxLayout(erhöhen, BoxLayout.Y_AXIS));


        erhöhen.add(new JLabel("Artikelbestand erhöhen"));
        erhöhen.add(Box.createVerticalStrut(20));

        bezeichnungsTextfieldErhöhung.setMaximumSize(bezeichnungsTextfieldErhöhung.getPreferredSize());
        erhöhungTextfield.setMaximumSize(erhöhungTextfield.getPreferredSize());


        erhöhen.add(new JLabel("Artikelbezeichnung: "));
        erhöhen.add(bezeichnungsTextfieldErhöhung);
        erhöhen.add(new JLabel("Zu erhöhende Menge: "));
        erhöhen.add(erhöhungTextfield);

        anlegenButtonErhöhen.addActionListener(this);
        erhöhen.add(anlegenButtonErhöhen);

        return erhöhen;
    }

    public JPanel bestandVerringern() {
        JPanel veringern = new JPanel();
        veringern.setVisible(true);
        veringern.setLayout(new BoxLayout(veringern, BoxLayout.Y_AXIS));


        veringern.add(new JLabel("Artikelbestand veringern"));
        veringern.add(Box.createVerticalStrut(20));

        bezeichnungsTextfieldVerringerung.setMaximumSize(bezeichnungsTextfieldVerringerung.getPreferredSize());
        veringerungsTextfield.setMaximumSize(veringerungsTextfield.getPreferredSize());


        veringern.add(new JLabel("Artikelbezeichnung: "));
        veringern.add(bezeichnungsTextfieldVerringerung);
        veringern.add(new JLabel("Zu verringernde Menge: "));
        veringern.add(veringerungsTextfield);

        bestandVerringernButton.addActionListener(this);
        veringern.add(bestandVerringernButton);

        return veringern;
    }


    private JComboBox<String> listenCombobox() {
        String[] listen = { "Registrierte Mitarbeiter ausgeben", "Registrierte Kunden ausgeben", "Ereignisse ausgeben"};
        listenauswahl = new JComboBox<>(listen);
        listenauswahl.addActionListener(this);

        return listenauswahl;
    }




    private JList<String> kundenliste() { //abändern?

        ArrayList<Kunde> kunden = new ArrayList<>();
        int position = 0;
        for (Kunde k : eshop.getAlleGespeichertenWarenkörbe().keySet()) {
            kunden.add(k);
        }

        JList<String> liste = new JList(kunden.toArray());
        return liste;

    }

    private JScrollPane artikellistTable(){
        tabelle = new JTable();
        model =new ArtikelTableModel(eshop.getAlleArtikel());
        tabelle.setModel(model);
        tablePane = new JScrollPane(tabelle);

        return tablePane;
    }
    private JList<String> mitarbeiterliste() {
        JList<String> mitarbeiterliste = new JList(eshop.getAlleMitarbeiter().toArray());
        return mitarbeiterliste;
    }


    private JList<String> erignisliste() {

        eshop.ereignisseNachDatum();
        JList<String> ereignisse = new JList(eshop.getAlleEreignisse().toArray());
        return ereignisse;
    }



    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        //todo Artikelliste Aktualisieren hinzufügen
        //todo suchleiste
        //todo andere listen in tables umwandeln
        //todo souts verlegen
        //todo nach erhöhung/verringerung textfelder leeren

        //Strings für Exceptions
        String kontoExistiertSchon = "Dieses Konto existiert bereits. Bitte versuchen Sie es nochmal.\n";
        String numberFormat = "Bitte Nummer nicht richtig eingegeben.\n";
        String leeresTextfeld = "Bitte füllen Sie alle Textfelder aus.\n";
        String artikelExistiert = "Der von Ihnen gewählte Artikel existiert bereits. Bitte versuchen Sie es nochmal.\n";
        String mengeZuHoch = "Die von Ihnen gewählte Menge ist zu höher als die im Bestand vorhandene Menge .\nBitte versuchen Sie es nochmal.\n";
        String artikelExistiertNicht = "Der von Ihnen gewählte Artikel existiert nicht. Bitte versuchen Sie es nochmal.\n";

        if (actionEvent.getSource() == zurückButton) {

            StartGUI s = new StartGUI(eshop);
            this.dispose();

        } else if (actionEvent.getSource() == bestandVerringernButton) {
            try {

                String artikelbez = bezeichnungsTextfieldVerringerung.getText();
                int menge = Integer.parseInt(veringerungsTextfield.getText());
                eshop.bestanNiedriger(artikelbez, menge, eingeloggterMitarbeiter);

                eshop.schreibeArtikel();
                eshop.schreibeEreignis();


            } catch (ArtikelExistiertNichtException e) {
                JOptionPane.showMessageDialog(null, artikelExistiertNicht, "Artikel nicht im Bestand vorhanden", JOptionPane.INFORMATION_MESSAGE);

                bezeichnungsTextfieldVerringerung.setText("");
                veringerungsTextfield.setText("");
                System.err.println("*********************************************************************************\n" +
                        artikelExistiertNicht+
                        "*********************************************************************************\n");

            } catch (UngueltigeMengeException u) {

                JOptionPane.showMessageDialog(null, mengeZuHoch, "Angegebene Menge nicht vorhanden", JOptionPane.INFORMATION_MESSAGE);

                System.err.println("*********************************************************************************\n" +
                        mengeZuHoch +
                        "*********************************************************************************\n");

            } catch (LeeresTextfieldException e) {
                JOptionPane.showMessageDialog(null, leeresTextfeld, "Leere Textfelder", JOptionPane.INFORMATION_MESSAGE);

                System.err.println("*********************************************************************************\n" +
                        leeresTextfeld +
                        "*********************************************************************************\n");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, numberFormat, "Eingabenfehler", JOptionPane.INFORMATION_MESSAGE);
                System.err.println("*********************************************************************************\n" +
                        numberFormat+
                        "*********************************************************************************\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (actionEvent.getSource() == anlegenButtonErhöhen) {

            try {

                String artikelname = bezeichnungsTextfieldErhöhung.getText();
                int menge = Integer.parseInt(erhöhungTextfield.getText());
                eshop.bestandErhöhen(artikelname, menge, eingeloggterMitarbeiter);

                eshop.schreibeArtikel();
                eshop.schreibeEreignis();

            } catch (ArtikelExistiertNichtException e) {
                JOptionPane.showMessageDialog(null, artikelExistiertNicht, "Artikel nicht im Bestand vorhanden", JOptionPane.INFORMATION_MESSAGE);

                bezeichnungsTextfieldErhöhung.setText("");
                erhöhungTextfield.setText("");

                System.err.println("*********************************************************************************\n" +
                        artikelExistiertNicht+
                        "*********************************************************************************\n");
            } catch (LeeresTextfieldException e) {
                JOptionPane.showMessageDialog(null, leeresTextfeld, "Leere Textfelder", JOptionPane.INFORMATION_MESSAGE);

                System.err.println("*********************************************************************************\n" +
                        leeresTextfeld+
                        "*********************************************************************************\n");

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, numberFormat, "Eingabenfehler", JOptionPane.INFORMATION_MESSAGE);

                System.err.println("*********************************************************************************\n" +
                        numberFormat+
                        "*********************************************************************************\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (actionEvent.getSource() == artikelAnlegenPopupButton) {

            popup(neuenArtikelAnlegen(), "Neuen Einzelartikel anlegen");

        } else if (actionEvent.getSource() == anlegenEinzelartikelAbschließen) {

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
                System.out.println("Erstellt: "+a);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, numberFormat+"\nBitte achten Sie beim Preis darauf ein “.“ zu verwenden.", "Eingabenfehler", JOptionPane.INFORMATION_MESSAGE);
                System.err.println("*********************************************************************************\n" +
                        numberFormat + "\nBitte achten Sie beim Preis darauf ein “.“ zu verwenden."+
                        "*********************************************************************************\n");
            } catch
            (ArtikelExistiertBereitsException e) {
                JOptionPane.showMessageDialog(null, artikelExistiert, "Artikel im Bestand vorhanden", JOptionPane.INFORMATION_MESSAGE);
                bezeichnungsTextfieldEinzelartikelAnlegen.setText("");
                artikelnummerTextfieldEinzelartikelAnlegen.setText("");
                bestandTextfieldEinzelartikelAnlegen.setText("");
                preisTextfieldEinzelartikelAnlegen.setText("");

                System.err.println("*********************************************************************************\n" +
                        artikelExistiert +
                        "*********************************************************************************\n");

            } catch (InputMismatchException e) {
                System.err.println(
                        "*********************************************************************************\n" +
                                "Ungültige Eingabe!\n" +
                                "Bei Eingabe der Artikelnummer bitte nur Zahlen verwenden.\n" +
                                "Ungültige Eingabe! Bei Eingabe des Preises achten Sie darauf ein Komma zu verwenden.\n" +
                                "Bitte versuchen Sie es nochmal.\n" +

                                "*********************************************************************************\n");
            } catch (LeeresTextfieldException e) {
                JOptionPane.showMessageDialog(null, leeresTextfeld, "Leere Textfelder", JOptionPane.INFORMATION_MESSAGE);
                System.err.println("*********************************************************************************\n" +
                        leeresTextfeld +
                        "*********************************************************************************\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (actionEvent.getSource() == massengutArtikelAnlegenPopupButton) {
            popup(neuenMassengutartikelAnlegen(), "Neuen Massengutartikel anlegen");
        } else if (actionEvent.getSource() == anlegenMassengutArtikelAbschliessen) {
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
                System.out.println("Erstellt: "+m);
                popup.dispose();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, numberFormat+"\nBitte achten Sie beim Preis darauf ein “.“ zu verwenden.", "Eingabenfehler", JOptionPane.INFORMATION_MESSAGE);
                System.err.println("*********************************************************************************\n" +
                        numberFormat + "\nBitte achten Sie beim Preis darauf ein “.“ zu verwenden."+
                        "*********************************************************************************\n");
            } catch (ArtikelExistiertBereitsException e) {
                JOptionPane.showMessageDialog(null, artikelExistiert, "Artikel im Bestand vorhanden", JOptionPane.INFORMATION_MESSAGE);

                bezeichnungsTextfieldMassengutartikelAnlegen.setText("");
                artikelnummerTextfieldMassengutartikelAnlegen.setText("");
                bestandTextfieldMassengutartikeAnlegen.setText("");
                preisTextfieldMassengutartikelAnlegen.setText("");
                verkäuflicheMengefield.setText("");


                System.err.println("*********************************************************************************\n" +
                        artikelExistiert +
                        "*********************************************************************************\n");

            } catch (InputMismatchException e) {
                System.err.println(
                        "*********************************************************************************\n" +
                                "Ungültige Eingabe!\n" +
                                "Bei Eingabe der Artikelnummer bitte nur Zahlen verwenden.\n" +
                                "Ungültige Eingabe! Bei Eingabe des Preises achten Sie darauf ein Komma zu verwenden.\n" +
                                "Bitte versuchen Sie es nochmal.\n" +

                                "*********************************************************************************\n");
            } catch (LeeresTextfieldException e) {
                JOptionPane.showMessageDialog(null, leeresTextfeld, "Leere Textfelder", JOptionPane.INFORMATION_MESSAGE);
                System.err.println("*********************************************************************************\n" +
                        leeresTextfeld +
                        "*********************************************************************************\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (actionEvent.getSource() == registerButton) {

            popup(registrierung(), "Neuen Mitarbeiter Registrieren");

        } else if (actionEvent.getSource() == mitarbeiterkontoAnlegen) {
            try {

                String username = usernameTextfield.getText();
                String pw = passwotTextfield.getText();
                String nachname = nachnameTextfield.getText();
                String vorname = vornameTextfield.getText();

                Mitarbeiter neuerMitarbeiter = new Mitarbeiter(username, pw, nachname, vorname);
                eshop.mitarbeiterRegistrieren(neuerMitarbeiter);

                System.out.println("Registriert: "+neuerMitarbeiter);
                eshop.schreibeMitarbeiter();
                popup.dispose();
            } catch (UserExistiertBereitsException e) {
                popup.dispose();
                JOptionPane.showMessageDialog(null, kontoExistiertSchon, "Konto existiert bereits", JOptionPane.INFORMATION_MESSAGE);
                usernameTextfield.setText("");
                passwotTextfield.setText("");
                nachnameTextfield.setText("");
                vornameTextfield.setText("");

                System.err.println(
                        "*********************************************************************************\n" +
                                kontoExistiertSchon +
                                "*********************************************************************************\n");

            } catch (LeeresTextfieldException e) {
                JOptionPane.showMessageDialog(null, leeresTextfeld, "Leere Textfelder", JOptionPane.INFORMATION_MESSAGE);

                System.err.println(
                        "*********************************************************************************\n" +
                                leeresTextfeld +
                                "*********************************************************************************\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (actionEvent.getSource() == listenauswahl) {
            String selectedListItem = listenauswahl.getSelectedItem().toString();
            if (selectedListItem.equals("Registrierte Mitarbeiter ausgeben")) {

                listpopup(mitarbeiterliste(), "Registrierte Mitarbeiter");
            } else if (selectedListItem.equals("Registrierte Kunden ausgeben")) {

                listpopup(kundenliste(), "Registrierte Kunden");
            } else if (selectedListItem.equals("Ereignisse ausgeben")) {


                listpopup(erignisliste(), "Ereignisse nach Datum geordnet");
            }
        } else if (actionEvent.getSource() == artikelausgabe) {

            String selectedArtikelItem = artikelausgabe.getSelectedItem().toString();
            if (selectedArtikelItem.equals("Alphabetische Ausgabe")) {

                List<Artikel> alphabetical = eshop.artikelSortierenNachBezeichnung();

                model.setArtikelListe(alphabetical);

            } else if (selectedArtikelItem.equals("Nummerische Ausgabe")) {
                List<Artikel> nummerical = eshop.artikelNachArtikelnummerGeordnetAusgeben();

                model.setArtikelListe(nummerical);
            }
        }


    }
}

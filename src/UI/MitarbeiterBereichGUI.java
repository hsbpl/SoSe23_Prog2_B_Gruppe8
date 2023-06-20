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
    JPanel midpanel;
    JScrollPane scrollPaneArtikelliste;

    JList artikelListe;


    public MitarbeiterBereichGUI(Mitarbeiter eingeloggterMitarbeiter) throws IOException {
        String datei = "ESHOP";
        eshop = new EShop(datei);

        this.eingeloggterMitarbeiter = eingeloggterMitarbeiter;

        this.setTitle("\"Roha & Sanjana's Eshop\""); //Title des Jframe wird erstellt
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sorgt dafür, das beim klicken des Exit das fenster auch geschlossen wird
        this.setResizable(true); // erlaubt uns die Größe des fensters zu ändern
        this.setVisible(true);//sorgt dafür das der Frame auch zu sehen ist
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        mitarbeiterbereich();

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


        String[] listen = {"Ausgabenoption auswählen", "Alphabetische Ausgabe", "Nummerische Ausgabe"};
        artikelausgabe = new JComboBox<>(listen);
        artikelausgabe.addActionListener(this);

        scrollPaneArtikelliste = new JScrollPane(artikelListe()); //liste wird dem scrollpane hinzugefügt
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




    private JList<String> kundenliste() { //abändern

        ArrayList<Kunde> kunden = new ArrayList<>();
        int position = 0;
        for (Kunde k : eshop.getAlleGespeichertenWarenkörbe().keySet()) {
            kunden.add(k);
        }

        JList<String> liste = new JList(kunden.toArray());
        return liste;

    }


    private JList<String> artikelListe() {


        artikelListe = new JList();
        artikelListe.setListData(eshop.getAlleArtikel().toArray());


        return artikelListe;

    }

    //todo dopplung der listen stoppen
    private JList<String> mitarbeiterliste() {
        JList<String> mitarbeiterliste = new JList(eshop.getAlleMitarbeiter().toArray());
        return mitarbeiterliste;
    }

    private JList<String> erignisliste() {

        eshop.ereignisseNachDatum();
        JList<String> ereignisse = new JList(eshop.getAlleEreignisse().toArray());
        return ereignisse;
    }

    private enum operation {
        AUSLOGGEN,
        BESTANDSERHÖHUNG,
        BESTANDSVERRINGERUNG,
        ARTIKELANLEGEN_POPUP,
        MASSENGUTARTIKELANLEGEN_POPUP,

        MASSENGUTARTIKELANLEGEN_ABSCHLIESSEN,
        ARTIKELANLEGEN_ABSCHLIESSEN,

        ARTIKELLISTEAUGEBEN_ALPHABETISCH,

        ARTIKELLISTEAUGEBEN_ARTIKElNUMMER,

        KUNDENLISTEAUSGEBEN,
        MITARBEITERLISTEAUSGEBEN,
        EREIGNISAUSGEBEN,
        MITARBEITER_REGISTRIEREN_POPUP,
        REGISTRIERUNG_ABSCHLIESSEN,
        DEFAULT_ARTIKELAUSGABE


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        operation operation = null;

        if (actionEvent.getSource() == zurückButton) {
            operation = operation.AUSLOGGEN;

        } else if (actionEvent.getSource() == bestandVerringernButton) {
            operation = operation.BESTANDSVERRINGERUNG;

        } else if (actionEvent.getSource() == anlegenButtonErhöhen) {
            operation = MitarbeiterBereichGUI.operation.BESTANDSERHÖHUNG;

        } else if (actionEvent.getSource() == artikelAnlegenPopupButton) {
            operation = MitarbeiterBereichGUI.operation.ARTIKELANLEGEN_POPUP;

        } else if (actionEvent.getSource() == anlegenEinzelartikelAbschließen) {
            operation = MitarbeiterBereichGUI.operation.ARTIKELANLEGEN_ABSCHLIESSEN;

        } else if (actionEvent.getSource() == massengutArtikelAnlegenPopupButton) {
            operation = MitarbeiterBereichGUI.operation.MASSENGUTARTIKELANLEGEN_POPUP;

        } else if (actionEvent.getSource() == anlegenMassengutArtikelAbschliessen) {
            operation = MitarbeiterBereichGUI.operation.MASSENGUTARTIKELANLEGEN_ABSCHLIESSEN;

        } else if (actionEvent.getSource() == registerButton) {
            operation = MitarbeiterBereichGUI.operation.MITARBEITER_REGISTRIEREN_POPUP;

        } else if (actionEvent.getSource() == mitarbeiterkontoAnlegen) {
            operation = MitarbeiterBereichGUI.operation.REGISTRIERUNG_ABSCHLIESSEN;

        } else if (listenauswahl.getSelectedItem() == "Registrierte Mitarbeiter ausgeben") {
            operation = operation.MITARBEITERLISTEAUSGEBEN;

        } else if (listenauswahl.getSelectedItem() == "Registrierte Kunden ausgeben") {
            operation = MitarbeiterBereichGUI.operation.KUNDENLISTEAUSGEBEN;

        } else if (listenauswahl.getSelectedItem() == "Ereignisse ausgeben") {
            operation = MitarbeiterBereichGUI.operation.EREIGNISAUSGEBEN;
        }else if(artikelausgabe.getSelectedItem() == "Alphabetische Ausgabe"){
            operation = MitarbeiterBereichGUI.operation.ARTIKELLISTEAUGEBEN_ALPHABETISCH;
        }else if(artikelausgabe.getSelectedItem() == "Nummerische Ausgabe"){
            operation = MitarbeiterBereichGUI.operation.ARTIKELLISTEAUGEBEN_ARTIKElNUMMER;
        }



        switch (operation) {
            case AUSLOGGEN:
                try {
                    StartGUI s = new StartGUI();
                    this.dispose();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                break;
            case BESTANDSVERRINGERUNG:
                try {

                    String artikelbez = bezeichnungsTextfieldVerringerung.getText();
                    int menge = Integer.parseInt(veringerungsTextfield.getText());
                    eshop.bestanNiedriger(artikelbez, menge, eingeloggterMitarbeiter);

                    eshop.schreibeArtikel();
                    eshop.schreibeEreignis();


                } catch (ArtikelExistiertNichtException e) {
                    System.err.println("*********************************************************************************\n" +
                            "Der von Ihnen gewählte Artikel existiert nicht. Bitte versuchen Sie es nochmal.\n" +
                            "*********************************************************************************\n");

                } catch (UngueltigeMengeException u) {
                    System.err.println("*********************************************************************************\n" +
                            "Die von Ihnen gewählte Menge ist zu höher als die Bestandsmenge. Bitte versuchen Sie es nochmal.\n" +
                            "*********************************************************************************\n");

                } catch (LeeresTextfieldException e) {
                    System.err.println("*********************************************************************************\n" +
                            "Bezeichnungstextfeld ist leer.\n" +
                            "*********************************************************************************\n");

                } catch (NumberFormatException e) {
                    System.err.println("*********************************************************************************\n" +
                            "Nummerneingabe falsch.\n" +
                            "*********************************************************************************\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;

            case BESTANDSERHÖHUNG:
                try {

                    String artikelname = bezeichnungsTextfieldErhöhung.getText();
                    int menge = Integer.parseInt(erhöhungTextfield.getText());
                    eshop.bestandErhöhen(artikelname, menge, eingeloggterMitarbeiter);

                    eshop.schreibeArtikel();
                    eshop.schreibeEreignis();

                } catch (ArtikelExistiertNichtException e) {
                    System.err.println("*********************************************************************************\n" +
                            "Der von Ihnen gewählte Artikel existiert nicht. Bitte versuchen Sie es nochmal.\n" +
                            "*********************************************************************************\n");
                } catch (LeeresTextfieldException e) {
                    System.err.println("*********************************************************************************\n" +
                            "Bezeichnungstextfeld ist leer.\n" +
                            "*********************************************************************************\n");
                } catch (NumberFormatException e) {
                    System.err.println("*********************************************************************************\n" +
                            "Nummerneingabe falsch.\n" +
                            "*********************************************************************************\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                break;

            case ARTIKELANLEGEN_POPUP:

                popup(neuenArtikelAnlegen(), "Neuen Einzelartikel anlegen");

                break;
            case ARTIKELANLEGEN_ABSCHLIESSEN:
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
                    System.err.println("*********************************************************************************\n" +
                            "Ungültige Eingabe in einem der Zahlenfelder. Bitte achten Sie bei den Zahlen darauf mit ein “.“ zu verwenden. \n" +
                            "*********************************************************************************\n");
                } catch
                (ArtikelExistiertBereitsException e) {
                    System.err.println("*********************************************************************************\n" +
                            "Der von Ihnen gewählte Artikel existiert bereits. Bitte versuchen Sie es nochmal.\n" +
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
                    System.err.println(
                            "*********************************************************************************\n" +
                                    "Bitte füllen Sie alle Textfelder aus.\n" +
                                    "*********************************************************************************\n");

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case MASSENGUTARTIKELANLEGEN_POPUP:
                popup(neuenMassengutartikelAnlegen(), "Neuen Massengutartikel anlegen");
                break;

            case MASSENGUTARTIKELANLEGEN_ABSCHLIESSEN:

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
                    System.err.println("*********************************************************************************\n" +
                            "Ungültige Eingabe in einem der Zahlenfelder. Bitte achten Sie bei den Zahlen darauf mit ein “.“ zu verwenden. \n" +
                            "*********************************************************************************\n");
                } catch (ArtikelExistiertBereitsException e) {
                    System.err.println("*********************************************************************************\n" +
                            "Der von Ihnen gewählte Artikel existiert bereits. Bitte versuchen Sie es nochmal.\n" +
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
                    System.err.println(
                            "*********************************************************************************\n" +
                                    "\"Bitte füllen Sie alle Textfelder aus.\\n\"" +
                                    "*********************************************************************************\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                break;
            case KUNDENLISTEAUSGEBEN:
                listpopup(kundenliste(), "Registrierte Kunden");

                break;
            case MITARBEITERLISTEAUSGEBEN:
                listpopup(mitarbeiterliste(), "Registrierte Mitarbeiter");

                break;
            case EREIGNISAUSGEBEN:
                listpopup(erignisliste(), "Ereignisse nach Datum geordnet");
                break;

            case MITARBEITER_REGISTRIEREN_POPUP:
                popup(registrierung(), "Neuen Mitarbeiter Registrieren");
                break;
            case REGISTRIERUNG_ABSCHLIESSEN:

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
                    System.err.println(
                            "*********************************************************************************\n" +
                                    "Dieses Konto Existiert bereits. Bitte versuchen Sie es nochmal.\n" +
                                    "*********************************************************************************\n");

                } catch (LeeresTextfieldException e) {
                    System.err.println(
                            "*********************************************************************************\n" +
                                    "Bitte füllen Sie alle Textfelder aus.\n" +
                                    "*********************************************************************************\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case ARTIKELLISTEAUGEBEN_ALPHABETISCH:

                artikelListe.setListData(eshop.artikelSortierenNachBezeichnung().toArray());

                break;
            case ARTIKELLISTEAUGEBEN_ARTIKElNUMMER:
                artikelListe.setListData(eshop.artikelNachArtikelnummerGeordnetAusgeben().toArray());

                break;

            default:

                break;
        }
    }
}

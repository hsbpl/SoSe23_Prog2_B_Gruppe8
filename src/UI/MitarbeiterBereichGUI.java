package UI;

import Domain.EShop;
import Exceptions.ArtikelExistiertBereitsException;
import Exceptions.ArtikelExistiertNichtException;
import Exceptions.UngueltigeMengeException;
import Exceptions.UserExistiertBereitsException;
import ValueObjekt.Artikel;
import ValueObjekt.Kunde;
import ValueObjekt.Massengutartikel;
import ValueObjekt.Mitarbeiter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.InputMismatchException;

public class MitarbeiterBereichGUI extends JFrame implements ActionListener {

    //todo bei textfield datentypen die keine string sind parsen
    private EShop eshop;
    int textfieldSize = 50;
    int digitInputTextfieldsize = 10;

    Mitarbeiter eingeloggterMitarbeiter;
    JButton zurückButton = new JButton("Ausloggen");
    JButton massengutArtikelAnlegenPopup = new JButton(" Massengutartikel anlegen ");
    JButton artikelAnlegenPopup = new JButton("  Einzelartikel anlegen   ");
    JButton mitarbeiterkontoAnlegen = new JButton("Registrieren");
    JTextField usernameTextfield = new JTextField(textfieldSize);
    JTextField passwotTextfield = new JTextField(textfieldSize);
    JTextField nachnameTextfield = new JTextField(textfieldSize);
    JTextField vornameTextfield = new JTextField(textfieldSize);
    JButton registerButton = new JButton("Mitarbeiter Registrieren");
    JButton bestandVerringernButton = new JButton("Aktualisieren");
    JTextField bezeichnungsTextfieldVerringerung = new JTextField(textfieldSize);
    JTextField veringerungsTextfield = new JTextField(digitInputTextfieldsize);
    JButton anlegenButtonErhöhen = new JButton("Aktualisieren");
    JTextField bezeichnungsTextfieldErhöhung = new JTextField(textfieldSize);
    JTextField erhöhungTextfield = new JTextField(digitInputTextfieldsize);
    JTextField bezeichnungsTextfieldEinzelartikelAnlegen = new JTextField(textfieldSize);
    JTextField artikelnummerTextfieldEinzelartikelAnlegen = new JTextField(textfieldSize);
    JTextField bestandTextfieldEinzelartikelAnlegen = new JTextField(textfieldSize);
    JTextField preisTextfieldEinzelartikelAnlegen = new JTextField(textfieldSize);
    JTextField verkäuflicheMengefield = new JTextField(textfieldSize);
    JTextField bezeichnungsTextfieldMassengutartikelAnlegen = new JTextField(textfieldSize);
    JTextField artikelnummerTextfieldMassengutartikelAnlegen = new JTextField(textfieldSize);
    JTextField bestandTextfieldMassengutartikeAnlegen = new JTextField(textfieldSize);
    JTextField preisTextfieldMassengutartikelAnlegen = new JTextField(textfieldSize);
    JButton anlegenEinzelartikelAbschließen = new JButton("Neuen artikel anlegen");
    JButton anlegenMassengutArtikelAbschliessen = new JButton("Neuen artikel anlegen");

    JComboBox<String> listenauswahl;

    public MitarbeiterBereichGUI(Mitarbeiter eingeloggterMitarbeiter) throws IOException {
        String datei = "ESHOP";
        eshop = new EShop(datei);

        this.eingeloggterMitarbeiter = eingeloggterMitarbeiter;

        this.setTitle("\"Roha & Sanjana's Eshop\""); //Title des Jframe wird erstellt
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sorgt dafür, das beim klicken des Exit das fenster auch geschlossen wird
        this.setResizable(true); // erlaubt uns die Größe des fensters zu ändern
        this.setVisible(true);//sorgt dafür das der Frame auch zu sehen ist
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //todo layout festlegung
        mitarbeiterbereich();

    }


    //Extrafenster wenn der Mitarbeiter eingeloggt / registriert ist
    private void mitarbeiterbereich() {
        JPanel mitarbeiterFenster = new JPanel();
        mitarbeiterFenster.setVisible(true);//sorgt dafür das der Frame auch zu sehen ist
        mitarbeiterFenster.setLayout(new BorderLayout(5, 5)); //aufteilung in borderlayout, die Zahlen sind für den Abstand da
        //todo  // mit setPrefferedSize(new Dimension(100, 100   )); kann man die Größe der einzelnen NSWOC anpasssen

        mitarbeiterFenster.add(westpanel(), BorderLayout.WEST);
        mitarbeiterFenster.add(northpanel(), BorderLayout.NORTH);
        mitarbeiterFenster.add(midpanel(), BorderLayout.CENTER);
        mitarbeiterFenster.add(eastpanel(), BorderLayout.EAST);


        add(mitarbeiterFenster);
    }

    private Component northpanel() {
        JPanel northpanel = new JPanel();
        northpanel.setVisible(true);//Jpanel ist sichtbar
        northpanel.setLayout(new FlowLayout()); // sorgt dafür das alles auf der Y-Achse liegt
        northpanel.setPreferredSize(new Dimension(300, 100));


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


        artikelAnlegenPopup.addActionListener(this);
        massengutArtikelAnlegenPopup.addActionListener(this);

        westpanel.add(Box.createVerticalStrut(60));
        westpanel.add(artikelAnlegenPopup);
        westpanel.add(Box.createVerticalStrut(20));
        westpanel.add(massengutArtikelAnlegenPopup);

        return westpanel;
    }

    private Component eastpanel() {
        JPanel eastpanel = new JPanel();
        eastpanel.setVisible(true);//Jpanel ist sichtbar
        eastpanel.setLayout(new BoxLayout(eastpanel, BoxLayout.Y_AXIS)); // sorgt dafür das alles auf der Y-Achse liegt
        //eastpanel.setPreferredSize(new Dimension(300, 100));

        //TODO listenausgabe
        hinzufügenKundenliste();
        hinzufügenMitarbeiterliste();

        zurückButton.addActionListener(this);
        eastpanel.add(zurückButton);

        registerButton.addActionListener(this);
        eastpanel.add(registerButton);

        eastpanel.add(listenCombobox());

        return eastpanel;
    }

    private Component midpanel() {
        JPanel midpanel = new JPanel();
        midpanel.setVisible(true);//Jpanel ist sichtbar
        midpanel.setLayout(new FlowLayout()); // sorgt dafür das alles auf der Y-Achse liegt
        // midpanel.setPreferredSize(new Dimension(300, 100));

        midpanel.add(hinzufügenArtikelListeStart());
        return midpanel;
    }

    private Component popup(Component component, String usage) {
        JFrame popup = new JFrame();
        popup.setVisible(true);
        popup.setSize(300, 500);
        popup.setLocationRelativeTo(null);//popup erscheint in der mitte
        popup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sorgt dafür, das beim klicken des Exit das fenster auch geschlossen wird
        popup.setResizable(false); // erlaubt uns die Größe des fensters zu ändern
        popup.setTitle(usage);

        popup.add(component);

        return popup;
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
    private Component neuenArtikelAnlegen() {
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

    public Component bestandErhöhen() {
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

    public Component bestandVerringern() {
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

    //todo combolist zur listen ausgabe

    private Component listenCombobox() {
        String[] listen = {"Auszugebende Liste Auswählen", "Registrierte Mitarbeiter", "Registrierte Kunden", "Ereignisse"};
        listenauswahl = new JComboBox<>(listen);
        listenauswahl.addActionListener(this);

        return listenauswahl;
    }


    //todo die listen machen probleme, evtl listen disposen beim öfnen eines neuen fensters vorm öffnen

    private void hinzufügenKundenliste() { //abändern
        Kunde[] kunden = new Kunde[eshop.getAlleGespeichertenWarenkörbe().size()];
        int position = 0;
        for (Kunde k : eshop.getAlleGespeichertenWarenkörbe().keySet()) {
            kunden[position++] = k;
        }

        add(new JList<>(kunden), BorderLayout.CENTER);

    }

    private Component hinzufügenArtikelListeStart() {

        JList<String> artikelListe = new JList(eshop.getAlleArtikel().toArray());
        return artikelListe;

    }

    private Component hinzufügenMitarbeiterliste() {
        JList<String> mitarbeiterliste = new JList(eshop.getAlleMitarbeiter().toArray());
        return mitarbeiterliste;
    }


    private enum operation { //todo eventhandling
        AUSLOGGEN,
        BESTANDSERHÖHUNG,
        BESTANDSVERRINGERUNG,
        ARTIKELANLEGEN_POPUP,
        MASSENGUTARTIKELANLEGEN_POPUP,

        MASSENGUTARTIKELANLEGEN_ABSCHLIESSEN,
        ARTIKELANLEGEN_ABSCHLIESSEN,

        KUNDENLISTEAUSGEBEN,
        MITARBEITERLISTEAUSGEBEN,
        EREIGNISAUSGEBEN,

        MITARBEITER_REGISTRIEREN_POPUP,

        REGISTRIERING_ABSCHLIESSEN,


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

        } else if (actionEvent.getSource() == artikelAnlegenPopup) {
            operation = MitarbeiterBereichGUI.operation.ARTIKELANLEGEN_POPUP;

        } else if (actionEvent.getSource() == anlegenEinzelartikelAbschließen) {
            operation = MitarbeiterBereichGUI.operation.MASSENGUTARTIKELANLEGEN_ABSCHLIESSEN;
        } else if (actionEvent.getSource() == massengutArtikelAnlegenPopup) {
            operation = MitarbeiterBereichGUI.operation.MASSENGUTARTIKELANLEGEN_POPUP;
        } else if (actionEvent.getSource() == anlegenMassengutArtikelAbschliessen) {
            operation = MitarbeiterBereichGUI.operation.MASSENGUTARTIKELANLEGEN_ABSCHLIESSEN;
        } else if (actionEvent.getSource() == registerButton) {
            operation = MitarbeiterBereichGUI.operation.MITARBEITER_REGISTRIEREN_POPUP;
        } else if (actionEvent.getSource() == mitarbeiterkontoAnlegen) {
            operation = MitarbeiterBereichGUI.operation.REGISTRIERING_ABSCHLIESSEN;
        } else if (listenauswahl.getSelectedItem() == "Registrierte Mitarbeiter") {

            operation = MitarbeiterBereichGUI.operation.MITARBEITERLISTEAUSGEBEN;

        } else if (listenauswahl.getSelectedItem() == "Registrierte Kunden") {
            operation = MitarbeiterBereichGUI.operation.KUNDENLISTEAUSGEBEN;
        } else if (listenauswahl.getSelectedItem() == "Ereignisse") {
            operation = MitarbeiterBereichGUI.operation.EREIGNISAUSGEBEN;
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
                    System.out.println(eshop.ereignisListeAusgeben());

                } catch (ArtikelExistiertNichtException e) {
                    System.err.println("*********************************************************************************\n" +
                            "Der von Ihnen gewählte Artikel existiert nicht. Bitte versuchen Sie es nochmal.\n" +
                            "*********************************************************************************\n");

                } catch (UngueltigeMengeException u) {
                    System.err.println("*********************************************************************************\n" +
                            "Die von Ihnen gewählte Menge ist zu höher als die Bestandsmenge. Bitte versuchen Sie es nochmal.\n" +
                            "*********************************************************************************\n");

                }
                break;

            case BESTANDSERHÖHUNG:
                try {

                    String artikelname = bezeichnungsTextfieldErhöhung.getText();
                    int menge = Integer.parseInt(erhöhungTextfield.getText());
                    eshop.bestandErhöhen(artikelname, menge, eingeloggterMitarbeiter);
                    System.out.println(eshop.ereignisListeAusgeben());
                } catch (ArtikelExistiertNichtException e) {
                    System.out.println("*********************************************************************************\n" +
                            "Der von Ihnen gewählte Artikel existiert nicht. Bitte versuchen Sie es nochmal.\n" +
                            "*********************************************************************************\n");
                }

                break;

            case ARTIKELANLEGEN_POPUP:

                popup(neuenArtikelAnlegen(), "Neuen Einzelartikel anlegen");

                break;
            case ARTIKELANLEGEN_ABSCHLIESSEN:
                try {
                    String bezeichnung = bestandTextfieldEinzelartikelAnlegen.getText();
                    int artikelnummer = Integer.parseInt(artikelnummerTextfieldEinzelartikelAnlegen.getText());
                    int bestand = Integer.parseInt(bezeichnungsTextfieldEinzelartikelAnlegen.getText());
                    double preis = Double.parseDouble(preisTextfieldEinzelartikelAnlegen.getText());
                    eshop.artHinzufügen(new Artikel(bezeichnung, artikelnummer, bestand, preis), eingeloggterMitarbeiter);
                } catch (ArtikelExistiertBereitsException e) {
                    System.out.println("*********************************************************************************\n" +
                            "Der von Ihnen gewählte Artikel existiert bereits. Bitte versuchen Sie es nochmal.\n" +
                            "*********************************************************************************\n");

                } catch (InputMismatchException e) {
                    System.out.println(
                            "*********************************************************************************\n" +
                                    "Ungültige Eingabe!\n" +
                                    "Bei Eingabe der Artikelnummer bitte nur Zahlen verwenden.\n" +
                                    "Ungültige Eingabe! Bei Eingabe des Preises achten Sie darauf ein Komma zu verwenden.\n" +
                                    "Bitte versuchen Sie es nochmal.\n" +

                                    "*********************************************************************************\n");
                }

                break;
            case MASSENGUTARTIKELANLEGEN_POPUP:
                popup(neuenMassengutartikelAnlegen(), "Neuen Massengutartikel anlegen");
                break;

            case MASSENGUTARTIKELANLEGEN_ABSCHLIESSEN:

                try {
                    String bezeichnung = bestandTextfieldMassengutartikeAnlegen.getText();
                    int artikelnummer = Integer.parseInt(artikelnummerTextfieldMassengutartikelAnlegen.getText());
                    int bestand = Integer.parseInt(bestandTextfieldMassengutartikeAnlegen.getText());
                    double preis = Double.parseDouble(preisTextfieldMassengutartikelAnlegen.getText());
                    int zumKaufVerfügbar = Integer.parseInt(preisTextfieldMassengutartikelAnlegen.getText());
                    eshop.artHinzufügen(new Massengutartikel(bezeichnung, artikelnummer, bestand, preis, zumKaufVerfügbar), eingeloggterMitarbeiter);
                } catch (ArtikelExistiertBereitsException e) {
                    System.out.println("*********************************************************************************\n" +
                            "Der von Ihnen gewählte Artikel existiert bereits. Bitte versuchen Sie es nochmal.\n" +
                            "*********************************************************************************\n");

                } catch (InputMismatchException e) {
                    System.out.println(
                            "*********************************************************************************\n" +
                                    "Ungültige Eingabe!\n" +
                                    "Bei Eingabe der Artikelnummer bitte nur Zahlen verwenden.\n" +
                                    "Ungültige Eingabe! Bei Eingabe des Preises achten Sie darauf ein Komma zu verwenden.\n" +
                                    "Bitte versuchen Sie es nochmal.\n" +

                                    "*********************************************************************************\n");
                }

                break;
            case KUNDENLISTEAUSGEBEN:
//todo listen hier implementen
                System.out.println("Kundenliste gewählt");

                break;
            case MITARBEITERLISTEAUSGEBEN:
                System.out.println("Mitarbeiterliste gewählt");

                break;
            case EREIGNISAUSGEBEN:
                System.out.println("Ereignisse gewählt");
                break;

            case MITARBEITER_REGISTRIEREN_POPUP:
                popup(registrierung(), "Neuen Mitarbeiter Registrieren");
                break;
            case REGISTRIERING_ABSCHLIESSEN:

                try {

                    String username = usernameTextfield.getText();
                    String pw = passwotTextfield.getText();
                    String nachname = nachnameTextfield.getText();
                    String vorname = vornameTextfield.getText();
                    Mitarbeiter neuerMitarbeiter = new Mitarbeiter(username, pw, nachname, vorname);
                    System.out.println(eshop.mitarbeiterRegistrieren(neuerMitarbeiter));
                    //todo popup bei erfolgreicher registrierung
                } catch (UserExistiertBereitsException e) {
                    System.out.println(
                            "*********************************************************************************\n" +
                                    "Dieses Konto Existiert bereits. Bitte versuchen Sie es nochmal.\n" +
                                    "*********************************************************************************\n");

                }
                break;
            default:
                //TODO fehlerbehebung - wnn operation null ist zb oder  textfelder leer sind
                break;
        }
    }
}

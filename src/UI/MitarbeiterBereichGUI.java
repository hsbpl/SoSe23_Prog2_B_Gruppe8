package UI;

import Domain.EShop;
import Exceptions.ArtikelExistiertNichtException;
import Exceptions.UngueltigeMengeException;
import ValueObjekt.Kunde;
import ValueObjekt.Mitarbeiter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MitarbeiterBereichGUI extends JFrame implements ActionListener {

    //todo bei textfield datentypen die keine string sind parsen
    private EShop eshop;
    int textfieldSize = 30;
    Mitarbeiter eingeloggterMitarbeiter;
    JButton zurückButton= new JButton("Ausloggen");
    JButton kundenliste = new JButton("Liste aller Kunden ausgeben");
    JButton mitarbeiterliste= new JButton("Liste aller Mitarbeiter ausgeben lassen");
    JButton ereignisliste = new JButton("Ereignisliste ausgeben");
    JButton massengutArtikelAnlegenButton = new JButton("Neuen Massengutartikel anlegen");
    JButton artikelAnlegenButton = new JButton("Neuen Einzelartikel anlegen");
    JTextField usernameTextfield= new JTextField(textfieldSize);
    JTextField passwotTextfield = new JTextField(textfieldSize);
    JTextField nachnameTextfield= new JTextField(textfieldSize);
    JTextField vornameTextfield = new JTextField(textfieldSize);
    JButton registerButton = new JButton("Registrieren");
    JButton bestandVerringernButton = new JButton("Bestand aktualisieren");
    JTextField bezeichnungsTextfieldVerringerung = new JTextField(textfieldSize);
    JTextField veringerungsTextfield = new JTextField(textfieldSize);
    JButton anlegenButtonErhöhen = new JButton("Bestand aktualisieren");
    JTextField bezeichnungsTextfieldErhöhung = new JTextField(textfieldSize);
    JTextField erhöhungTextfield = new JTextField(textfieldSize);
    JTextField bezeichnungsTextfieldEinzelartikelAnlegen = new JTextField(textfieldSize);
    JTextField artikelnummerTextfieldEinzelartikelAnlegen = new JTextField(textfieldSize);
    JTextField bestandTextfieldEinzelartikelAnlegen = new JTextField(textfieldSize);
    JTextField preisTextfieldEinzelartikelAnlegen = new JTextField(textfieldSize);
    JTextField verkäuflicheMengefield= new JTextField(textfieldSize);
    JTextField bezeichnungsTextfieldMassengutartikelAnlegen = new JTextField(textfieldSize);
    JTextField artikelnummerTextfieldMassengutartikelAnlegen = new JTextField(textfieldSize);
    JTextField bestandTextfieldMassengutartikeAnlegen = new JTextField(textfieldSize);
    JTextField preisTextfieldMassengutartikelAnlegen = new JTextField(textfieldSize);
    JButton anlegenButtonEinzelartikel = new JButton("Neuen artikel anlegen");
    JButton anlegenButtonMassengutArtikel = new JButton("Neuen artikel anlegen");

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
    private void mitarbeiterbereich(){
        JPanel mitarbeiterFenster = new JPanel();
        mitarbeiterFenster.setVisible(true);//sorgt dafür das der Frame auch zu sehen ist
        mitarbeiterFenster.setLayout(new BorderLayout(5,5)); //aufteilung in borderlayout, die Zahlen sind für den Abstand da
      //todo  // mit setPrefferedSize(new Dimension(100, 100   )); kann man die Größe der einzelnen NSWOC anpasssen

        mitarbeiterFenster.add(westpanel(),BorderLayout.WEST);
        mitarbeiterFenster.add(northpanel(),BorderLayout.NORTH );
        mitarbeiterFenster.add(midpanel(),BorderLayout.CENTER);
        mitarbeiterFenster.add(eastpanel(), BorderLayout.EAST);


        add(mitarbeiterFenster);
    }

private Component northpanel(){
    JPanel northpanel = new JPanel();
    northpanel.setVisible(true);//Jpanel ist sichtbar
    northpanel.setLayout(new FlowLayout()); // sorgt dafür das alles auf der Y-Achse liegt
    northpanel.setPreferredSize(new Dimension(300, 100));

    zurückButton.addActionListener(this);
    northpanel.add(zurückButton);

    return northpanel;

}

private Component westpanel(){
    JPanel westpanel = new JPanel();
    westpanel.setVisible(true);//Jpanel ist sichtbar
    westpanel.setLayout(new BoxLayout(westpanel, BoxLayout.Y_AXIS)); // sorgt dafür das alles auf der Y-Achse liegt
    westpanel.setPreferredSize(new Dimension(300, 100));

    westpanel.add(Box.createVerticalStrut(40));
    westpanel.add(bestandErhöhen());  //Erhöhen und verringern der warenmengen soll im WEsten angezeigt werden
    westpanel.add(Box.createVerticalStrut(40));
    westpanel.add(bestandVerringern());


    artikelAnlegenButton.addActionListener(this);
    massengutArtikelAnlegenButton.addActionListener(this);

    westpanel.add(Box.createVerticalStrut(40));
    westpanel.add(artikelAnlegenButton);
    westpanel.add(Box.createVerticalStrut(40));
    westpanel.add(massengutArtikelAnlegenButton);

    return westpanel;
}

private Component eastpanel(){
    JPanel eastpanel = new JPanel();
    eastpanel.setVisible(true);//Jpanel ist sichtbar
    eastpanel.setLayout(new BoxLayout(eastpanel, BoxLayout.Y_AXIS)); // sorgt dafür das alles auf der Y-Achse liegt
    eastpanel.setPreferredSize(new Dimension(300, 100));

    //TODO listenausgabe
    hinzufügenKundenliste();
    hinzufügenMitarbeiterliste();

    return eastpanel;
}

private Component midpanel(){
    JPanel midpanel = new JPanel();
    midpanel.setVisible(true);//Jpanel ist sichtbar
    midpanel.setLayout(new FlowLayout()); // sorgt dafür das alles auf der Y-Achse liegt
   // midpanel.setPreferredSize(new Dimension(300, 100));

    midpanel.add(hinzufügenArtikelListeStart());
    return midpanel;
}

    private Component registerPopup(Component component){
        JFrame popup = new JFrame();
        popup.setVisible(true);
        popup.setSize(300, 500);
        popup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sorgt dafür, das beim klicken des Exit das fenster auch geschlossen wird
        popup.setResizable(false); // erlaubt uns die Größe des fensters zu ändern
        popup.setTitle("Mitarbeiter Registrierung");

        popup.add(component);

        return popup;
    }

    private Component registrierung(){
        JPanel registerfenster = new JPanel();
        registerfenster.setVisible(true);
        registerfenster.setLayout(new BoxLayout(registerfenster, BoxLayout.Y_AXIS));

        usernameTextfield.setMaximumSize(usernameTextfield.getPreferredSize());
        passwotTextfield.setMaximumSize(passwotTextfield.getPreferredSize());
        nachnameTextfield.setMaximumSize(nachnameTextfield.getPreferredSize());
       vornameTextfield.setMaximumSize(vornameTextfield.getPreferredSize());

        registerfenster.add(new JLabel("Username: "));
        registerfenster.add(usernameTextfield);
        registerfenster.add(new JLabel("Passwort: "));
        registerfenster.add(passwotTextfield);
        registerfenster.add(new JLabel("Nachname: "));
        registerfenster.add(nachnameTextfield);
         registerfenster.add(new JLabel("Vorname: "));
        registerfenster.add(vornameTextfield);

        registerfenster.add(registerButton);

        return registerfenster;
    }

    // Feld zum Anlegen neuer Artikel
    private Component neuenArtikelAnlegen(){
        JPanel anlegen = new JPanel();
        anlegen.setVisible(true);
        anlegen.setLayout(new BoxLayout(anlegen, BoxLayout.Y_AXIS));
        anlegen.add(new JLabel("Artikel anlegen"));

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
        anlegen.add(anlegenButtonEinzelartikel);

        return anlegen;
    }

    // Feld zum Anlegen neuer MassengutArtikel
    private Component neuenMassengutartikelAnlegen(){
        JPanel anlegen = new JPanel();
        anlegen.setVisible(true);
        anlegen.setLayout(new BoxLayout(anlegen, BoxLayout.Y_AXIS));
        anlegen.add(new JLabel("Artikel anlegen"));

        bezeichnungsTextfieldMassengutartikelAnlegen.setMaximumSize(bezeichnungsTextfieldMassengutartikelAnlegen.getPreferredSize());
        artikelnummerTextfieldMassengutartikelAnlegen.setMaximumSize(artikelnummerTextfieldMassengutartikelAnlegen.getPreferredSize());
        bestandTextfieldMassengutartikeAnlegen.setMaximumSize(bestandTextfieldMassengutartikeAnlegen.getPreferredSize());
        preisTextfieldMassengutartikelAnlegen.setMaximumSize(preisTextfieldMassengutartikelAnlegen.getPreferredSize());

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

        anlegen.add(anlegenButtonMassengutArtikel);

        return anlegen;
    }

    public Component bestandErhöhen(){
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
        erhöhen.add(anlegenButtonErhöhen);

        return erhöhen;
    }

    public Component bestandVerringern(){
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
        veringern.add(bestandVerringernButton);

        return veringern;
    }

    //todo combolist zur leisten ausgabe

    /*private Component listenCombobox(){
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>();
        JComboBox <JList> auswahl = new JComboBox<>();

    }

     */

    //todo die listen machen probleme

    private void hinzufügenKundenliste(){ //abändern
        Kunde[] kunden = new Kunde[eshop.getAlleGespeichertenWarenkörbe().size()];
        int position = 0;
        for (Kunde k : eshop.getAlleGespeichertenWarenkörbe().keySet()){
            kunden[position++] = k;
        }

        add(new JList<>(kunden), BorderLayout.CENTER);

    }
    private Component hinzufügenArtikelListeStart(){

        JList<String> artikelListe= new JList(eshop.getAlleArtikel().toArray());
        return artikelListe;

    }
    private Component hinzufügenMitarbeiterliste(){
        JList<String> mitarbeiterliste = new JList(eshop.getAlleMitarbeiter().toArray());
        return mitarbeiterliste;
    }


    private enum operation{ //todo eventhandling
        AUSLOGGEN,
        BESTANDSERHÖHUNG,
        BESTANDSVERRINGERUNG,
        ARTIKELANLEGEN,
        MASSENGUTARTIKELANLEGEN,
        KUNDENLISTEAUSGEBEN,
        MITARBEITERLISTEAUSGEBEN,
        EREIGNISAUSGEBEN,
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        operation operation = null;

        if(actionEvent.getSource() == zurückButton){
            operation = operation.AUSLOGGEN;

        } else if(actionEvent.getSource() ==bestandVerringernButton){
            operation = operation.BESTANDSVERRINGERUNG;

        }else if (actionEvent.getSource() == anlegenButtonErhöhen){
            operation = MitarbeiterBereichGUI.operation.BESTANDSERHÖHUNG;

        } else if(actionEvent.getSource() == anlegenButtonEinzelartikel){
            operation = MitarbeiterBereichGUI.operation.ARTIKELANLEGEN;

        } else if(actionEvent.getSource() == massengutArtikelAnlegenButton){
            operation = MitarbeiterBereichGUI.operation.MASSENGUTARTIKELANLEGEN;
        }else if(actionEvent.getSource() == kundenliste){

            operation = MitarbeiterBereichGUI.operation.KUNDENLISTEAUSGEBEN;
        }else if(actionEvent.getSource() == mitarbeiterliste){

            operation = operation.MITARBEITERLISTEAUSGEBEN;
        }else  if(actionEvent.getSource() == ereignisliste){

            operation = MitarbeiterBereichGUI.operation.EREIGNISAUSGEBEN;
        }



        switch (operation){
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

            case ARTIKELANLEGEN:

                break;
            case MASSENGUTARTIKELANLEGEN:

                break;
            case KUNDENLISTEAUSGEBEN:

                break;
            case MITARBEITERLISTEAUSGEBEN:

                break;
            case EREIGNISAUSGEBEN:

                break;
            default:
                //TODO fehlerbehebung - wnn operation null ist zb oder  textfelder leer sind
                break;
        }
    }
}

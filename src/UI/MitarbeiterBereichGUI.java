package UI;

import Domain.EShop;
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

        //aufteilung in borderlayout, die Zahlen sind für den Abstand da
        // mit setPrefferedSize(new Dimension(100, 100   )); kann man die Größe der einzelnen NSWOC anpasssen
        /*
        falls man Icon oben rechts hinzufügen möchen
        ImageIcon image = new ImageIcon("speicherort"); //erstelle ein image
        this.setIconImage(image.getImage());
         */
        //todo layout festlegung
        mitarbeiiterbereich();

    }


    //Extrafenster wenn der Mitarbeiter eingeloggt / registriert ist
    private void mitarbeiiterbereich(){
        JFrame mitarbeiterFenster = new JFrame();

        mitarbeiterFenster.setTitle("\"Roha & Sanjana's Eshop\""); //Title des Jframe wird erstellt
        mitarbeiterFenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sorgt dafür, das beim klicken des Exit das fenster auch geschlossen wird
        mitarbeiterFenster.setLocation(0, 500);
        mitarbeiterFenster.setResizable(true); // erlaubt uns die Größe des fensters zu ändern
        mitarbeiterFenster.setVisible(true);//sorgt dafür das der Frame auch zu sehen ist
        mitarbeiterFenster.setLayout(new BorderLayout(5,5)); //aufteilung in borderlayout, die Zahlen sind für den Abstand da
      //todo  // mit setPrefferedSize(new Dimension(100, 100   )); kann man die Größe der einzelnen NSWOC anpasssen

        mitarbeiterFenster.add(zurückButton, BorderLayout.NORTH);
        mitarbeiterFenster.add(bestandErhöhen(), BorderLayout.WEST);  //Erhöhen und verringern der warenmengen soll im WEsten angezeigt werden
        mitarbeiterFenster.add(bestandVerringern(),BorderLayout.WEST);
        mitarbeiterFenster.add(artikelAnlegenButton, BorderLayout.WEST);
        mitarbeiterFenster.add(massengutArtikelAnlegenButton,BorderLayout.WEST);

        mitarbeiterFenster.add(mitarbeiterliste, BorderLayout.EAST);
        mitarbeiterFenster.add(kundenliste, BorderLayout.EAST);

        hinzufügenArtikelListeStart();

        add(mitarbeiterFenster);
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
        veringern.add(new JLabel("Artikel anlegen"));


        bezeichnungsTextfieldVerringerung.setMaximumSize(bezeichnungsTextfieldVerringerung.getPreferredSize());
        veringerungsTextfield.setMaximumSize(veringerungsTextfield.getPreferredSize());

        veringern.add(new JLabel("Artikelbezeichnung: "));
        veringern.add(bezeichnungsTextfieldVerringerung);
        veringern.add(new JLabel("Zu verringernde Menge: "));
        veringern.add(veringerungsTextfield);
        veringern.add(bestandVerringernButton);

        return veringern;
    }


    private void hinzufügenKundenliste(EShop eshop){
        Kunde[] kunden = new Kunde[eshop.getAlleGespeichertenWarenkörbe().size()];
        int position = 0;
        for (Kunde k : eshop.getAlleGespeichertenWarenkörbe().keySet()){
            kunden[position++] = k;
        }

        add(new JList<>(kunden), BorderLayout.CENTER);

    }
    private void hinzufügenArtikelListeStart(){
       add(new JList(eshop.getAlleArtikel().toArray()), BorderLayout.CENTER);
    }
    private void hinzufügenMitarbeiterliste(EShop eshop){
        add(new JList(eshop.getAlleMitarbeiter().toArray()), BorderLayout.CENTER);
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

                break;
            case BESTANDSVERRINGERUNG:

                break;
            case BESTANDSERHÖHUNG:

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

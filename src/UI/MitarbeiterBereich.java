package UI;

import Domain.EShop;
import ValueObjekt.Kunde;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MitarbeiterBereich extends JFrame {


    JButton zurückButton= new JButton("Ausloggen");
    JButton kundenliste = new JButton("Liste aller Kunden ausgeben");
    JButton mitarbeiterliste= new JButton("Liste aller Mitarbeiter ausgeben lassen");

    JButton massengutArtikelAnlegenButton = new JButton("Neuen Massengutartikel anlegen");

    JButton artikelAnlegenButton = new JButton("Neuen Einzelartikel anlegen");
    public MitarbeiterBereich() throws IOException {
        super("Roha & Sanjana's Eshop");
        String datei = "ESHOP";
        EShop eshop = new EShop(datei);//TODO nachdem die persistence festgelegt hat was noch in den Konstruktor kommt hier korrigieren

        this.setTitle("\"Roha & Sanjana's Eshop\""); //Title des Jframe wird erstellt
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sorgt dafür, das beim klicken des Exit das fenster auch geschlossen wird
        this.setSize(640, 480); // größe des Frames //TODO welche größe passt am besten
        this.setLocation(0, 500);
        this.setResizable(true); // erlaubt uns die Größe des fensters zu ändern
        this.setVisible(true);//sorgt dafür das der Frame auch zu sehen ist
        //aufteilung in borderlayout, die Zahlen sind für den Abstand da
        // mit setPrefferedSize(new Dimension(100, 100   )); kann man die Größe der einzelnen NSWOC anpasssen
        /*
        falls man Icon oben rechts hinzufügen möchen
        ImageIcon image = new ImageIcon("speicherort"); //erstelle ein image
        this.setIconImage(image.getImage());
         */

        mitarbeiiterbereich();




    }


    //Extrafenster wenn der Mitarbeiter eingeloggt / registriert ist
    private void mitarbeiiterbereich() throws IOException {
        JFrame mitarbeiterFenster = new JFrame();

        mitarbeiterFenster.setTitle("\"Roha & Sanjana's Eshop\""); //Title des Jframe wird erstellt
        mitarbeiterFenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sorgt dafür, das beim klicken des Exit das fenster auch geschlossen wird
        mitarbeiterFenster.setSize(640, 480); // größe des Frames //TODO welche größe passt am besten
        mitarbeiterFenster.setLocation(0, 500);
        mitarbeiterFenster.setResizable(true); // erlaubt uns die Größe des fensters zu ändern
        mitarbeiterFenster.setVisible(true);//sorgt dafür das der Frame auch zu sehen ist
        mitarbeiterFenster.setLayout(new BorderLayout(5,5)); //aufteilung in borderlayout, die Zahlen sind für den Abstand da
        // mit setPrefferedSize(new Dimension(100, 100   )); kann man die Größe der einzelnen NSWOC anpasssen

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


    private Component registrierung(){
        JPanel registerfenster = new JPanel();
        registerfenster.setVisible(true);
        registerfenster.setLayout(new BoxLayout(registerfenster, BoxLayout.Y_AXIS));
        registerfenster.add(new JLabel("Kundenregistrierung"));

        JTextField usernameTextfield= new JTextField(30);
        usernameTextfield.add(new JLabel("Username: "));
        JTextField passwotTextfield = new JTextField(30);
        passwotTextfield.add(new JLabel("Passwort: "));
        JTextField nachnameTextfield= new JTextField(30);
        nachnameTextfield.add(new JLabel("Nachname: "));
        JTextField vornameTextfield = new JTextField(30);
        vornameTextfield.add(new JLabel("Vorname: "));


        JButton registerButton = new JButton("Registrieren");

        registerfenster.add(usernameTextfield);
        registerfenster.add(passwotTextfield);
        registerfenster.add(nachnameTextfield);
        registerfenster.add(vornameTextfield);
        registerfenster.add(usernameTextfield);
        registerfenster.add(registerButton);

        return registerfenster;
    }

    // Feld zum Anlegen neuer Artikel
    private Component neuenArtikelAnlegen(){
        JPanel anlegen = new JPanel();
        anlegen.setVisible(true);
        anlegen.setLayout(new BoxLayout(anlegen, BoxLayout.Y_AXIS));
        anlegen.add(new JLabel("Artikel anlegen"));
//TODO Textfeld verzerrung verhindern

        JTextField bezeichnungsTextfield= new JTextField(30);
        bezeichnungsTextfield.add(new JLabel("Artikelbezeichnung: "));

        JTextField artikelnummerTextfield = new JTextField(30);
        artikelnummerTextfield.add(new JLabel("Artikelnummer: "));

        JTextField bestandTextfield = new JTextField(30);
        bestandTextfield.add(new JLabel("Bestand: "));

        JTextField preisTextfield= new JTextField(30);
        preisTextfield.add(new JLabel("Preis in €: "));

        JButton anlegenButton = new JButton("Anlegen");

        anlegen.add(bezeichnungsTextfield);
        anlegen.add(artikelnummerTextfield);
        anlegen.add(bestandTextfield);
        anlegen.add(preisTextfield);
        anlegen.add(anlegenButton);

        return anlegen;
    }

    // Feld zum Anlegen neuer MassengutArtikel
    private Component neuenMassengutartikelAnlegen(){
        JPanel anlegen = new JPanel();
        anlegen.setVisible(true);
        anlegen.setLayout(new BoxLayout(anlegen, BoxLayout.Y_AXIS));
        anlegen.add(new JLabel("Artikel anlegen"));

        JTextField bezeichnungsTextfield= new JTextField(30);
        bezeichnungsTextfield.add(new JLabel("Artikelbezeichnung: "));

        JTextField artikelnummerTextfield = new JTextField(30);
        artikelnummerTextfield.add(new JLabel("Artikelnummer: "));

        JTextField bestandTextfield = new JTextField(30);
        bestandTextfield.add(new JLabel("Bestand: "));

        JTextField preisTextfield= new JTextField(30);
        preisTextfield.add(new JLabel("Preis in €: "));

        JTextField verkäuflicheMengefield= new JTextField(30);
        verkäuflicheMengefield.add(new JLabel("Minimale Kaufmenge: "));

        JButton anlegenButton = new JButton("Neuen artikel anlegen");

        anlegen.add(bezeichnungsTextfield);
        anlegen.add(artikelnummerTextfield);
        anlegen.add(bestandTextfield);
        anlegen.add(preisTextfield);
        anlegen.add(anlegenButton);
        anlegen.add(verkäuflicheMengefield);

        return anlegen;
    }

    public Component bestandErhöhen(){
        JPanel erhöhen = new JPanel();
        erhöhen.setVisible(true);
        erhöhen.setLayout(new BoxLayout(erhöhen, BoxLayout.Y_AXIS));
        erhöhen.add(new JLabel("Artikelbestand erhöhen"));

        JTextField bezeichnungsTextfield= new JTextField(30);
        bezeichnungsTextfield.add(new JLabel("Artikelbezeichnung: "));

        JTextField erhöhungTextfield = new JTextField(30);
        erhöhungTextfield.add(new JLabel("Zu erhöhende Menge: "));

        JButton anlegenButton = new JButton("Bestand aktualisieren");

        erhöhen.add(bezeichnungsTextfield);
        erhöhen.add(erhöhungTextfield);
        erhöhen.add(anlegenButton);

        return erhöhen;
    }

    public Component bestandVerringern(){
        JPanel veringern = new JPanel();
        veringern.setVisible(true);
        veringern.setLayout(new BoxLayout(veringern, BoxLayout.Y_AXIS));
        veringern.add(new JLabel("Artikel anlegen"));

        JTextField bezeichnungsTextfield= new JTextField(30);
        bezeichnungsTextfield.add(new JLabel("Artikelbezeichnung: "));

        JTextField veringerungsTextfield = new JTextField(30);
        veringerungsTextfield.add(new JLabel("Zu verringernde Menge: "));

        JButton anlegenButton = new JButton("Bestand aktualisieren");

        veringern.add(bezeichnungsTextfield);
        veringern.add(veringerungsTextfield);
        veringern.add(anlegenButton);

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
    private void hinzufügenMitarbeiterliste(EShop eshop){
        add(new JList(eshop.getAlleMitarbeiter().toArray()), BorderLayout.CENTER);
    }
    private void hinzufügenArtikelListeStart() throws IOException {
        String datei = "ESHOP";
        EShop eshop = new EShop(datei);
        add(new JList(eshop.getAlleArtikel().toArray()), BorderLayout.CENTER);
    }
}

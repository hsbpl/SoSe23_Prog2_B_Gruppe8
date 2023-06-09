package UI;

import Domain.EShop;
import ValueObjekt.Kunde;
import ValueObjekt.Warenkorb;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class GUI extends JFrame { // durch die Extension wird die GUI zur childclass von JFrame

    /**
     * JLabel : zum display von Text,image, (auch beides)
     *Jpanel : dient als Container um andere Komponenten zu halten
     *
     *BoxLayout: verwendet NOSW und center
     *
     */
    private EShop eshop;

    public GUI() throws IOException {
        super("Roha & Sanjana's Eshop");
       //eshop = new EShop();//TODO nachdem die persistence festgelegt hat was noch in den Konstruktor kommt hier korrigieren



        this.setTitle("\"Roha & Sanjana's Eshop\""); //Title des Jframe wird erstellt
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sorgt dafür, das beim klicken des Exit das fenster auch geschlossen wird
        this.setSize(640, 480); // größe des Frames //TODO welche größe passt am besten
        this.setLocation(0, 500);
        this.setResizable(true); // erlaubt uns die Größe des fensters zu ändern
        this.setVisible(true);//sorgt dafür das der Frame auch zu sehen ist
        this.setLayout(new BorderLayout(5,5)); //aufteilung in borderlayout, die Zahlen sind für den Abstand da
        // mit setPrefferedSize(new Dimension(100, 100   )); kann man die Größe der einzelnen NSWOC anpasssen

        add(hinzufügenLoginBereichStart(), BorderLayout.WEST);

       hinzufügenArtikelListeStart(); //TODO vesuchen sobald der ehop reingesetzt wurde
        /*
        falls man Icon oben rechts hinzufügen möchen
        ImageIcon image = new ImageIcon("speicherort"); //erstelle ein image
        this.setIconImage(image.getImage());
         */
    }

    private Component hinzufügenLoginBereichStart(){
        JPanel westpanel = new JPanel(); //neues Jpanel
        westpanel.setVisible(true);//Jpanel ist sichtbar
        westpanel.setSize(300, 300);
        westpanel.setLayout(new BoxLayout(westpanel, BoxLayout.Y_AXIS)); // sorgt dafür das alles auf der Y-Achse liegt
        westpanel.add(kundeLogin()); //Kundenloginbereich wird hinzugefügt
        westpanel.add(mitarbeiterLogin()); //Mitarbeiterloginbereichwird hinzugefügt

        JButton registrierungsButton= new JButton("Als neuer Kunde registrieren"); //Button erstellt der später seperat Reg öffnen soll
        westpanel.add(registrierungsButton); // button wird dem westpanel hinzugefügt

        return westpanel;

    }

    private Component kundeLogin(){
        JPanel loginfenster = new JPanel(); //neues Jpanel
        loginfenster.setVisible(true);//Jpanel ist sichtbar
        loginfenster.setSize(300, 300);
        loginfenster.setLayout(new BoxLayout(loginfenster, BoxLayout.Y_AXIS));
        //loginfenster.setBounds(); müsste man verwenden, wenn man keinen Layoutmanager verwendet. ausrichtung des panesl


        loginfenster.add(new JLabel("Kundenlogin")); // dem Loginbereich ein Label zur Bezeichnung hinugefügt

        JTextField usernameTextfield= new JTextField(30); //neues Textfeld erstellt, die nummer innen stellt die größe des textfeldes ein
        usernameTextfield.add(new JLabel("Username: ")); // Textfeld ein Label hinzugefügt
        //usernameTextfield.setMaximumSize(usernameTextfield.getPreferredSize()); //todo die Textfelder sollen ihre größe aufgrund des BoxLaxouts nicht verändern

        JTextField passwotTextfield = new JTextField(30);
        passwotTextfield.add(new JLabel("Passwort: "));

        JButton loginButton = new JButton("Einloggen"); //button erstellt

        loginfenster.add(usernameTextfield); //dem loginfenster werden die einzelnen komponenten hinzugefügt
        loginfenster.add(passwotTextfield);
        loginfenster.add(loginButton);
        return loginfenster;
    }


    //TODO soll angezeigt werden wenn der user auf die Registrieungsoption klickt
    private Component kundenregistrierungPopup(){
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
        JTextField adressenTextfield= new JTextField(30);
        adressenTextfield.add(new JLabel("Adresse: "));

        JButton registerButton = new JButton("Registrieren");

        registerfenster.add(usernameTextfield);
        registerfenster.add(passwotTextfield);
        registerfenster.add(nachnameTextfield);
        registerfenster.add(vornameTextfield);
        registerfenster.add(usernameTextfield);
        registerfenster.add(adressenTextfield);
        registerfenster.add(registerButton);

        return registerfenster;
    }
    private Component mitarbeiterLogin(){
        JPanel loginfenster = new JPanel();
        loginfenster.setVisible(true);
        loginfenster.setLayout(new BoxLayout(loginfenster, BoxLayout.Y_AXIS));
        loginfenster.add(new JLabel("Mitarbeiterlogin"));

        JTextField usernameTextfield= new JTextField(30);
        usernameTextfield.add(new JLabel("Username: "));
        JTextField passwotTextfield = new JTextField(30);
        passwotTextfield.add(new JLabel("Passwort: "));

        JButton loginButton = new JButton("Einloggen");

        loginfenster.add(usernameTextfield); //dem loginfenster werden die einzelnen komponenten hinzugefügt
        loginfenster.add(passwotTextfield);
        loginfenster.add(loginButton);

        return loginfenster;
    }


    //Extrafenster wenn der Mitarbeiter eingeloggt / registriert ist
    private void mitarbeiiterbereich(){
        JFrame mitarbeiterFenster = new JFrame();

        mitarbeiterFenster.setTitle("\"Roha & Sanjana's Eshop\""); //Title des Jframe wird erstellt
        mitarbeiterFenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sorgt dafür, das beim klicken des Exit das fenster auch geschlossen wird
        mitarbeiterFenster.setSize(640, 480); // größe des Frames //TODO welche größe passt am besten
        mitarbeiterFenster.setLocation(0, 500);
        mitarbeiterFenster.setResizable(true); // erlaubt uns die Größe des fensters zu ändern
        mitarbeiterFenster.setVisible(true);//sorgt dafür das der Frame auch zu sehen ist
        mitarbeiterFenster.setLayout(new BorderLayout(5,5)); //aufteilung in borderlayout, die Zahlen sind für den Abstand da
        // mit setPrefferedSize(new Dimension(100, 100   )); kann man die Größe der einzelnen NSWOC anpasssen
        //todo komponenten anordnen

    }

    // Feld zum Anlegen neuer Artikel
    private void neuenArtikelAnlegen(){
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
    }

    // Feld zum Anlegen neuer MassengutArtikel
    private void neuenMassengutartikelAnlegen(){
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
    }

public void bestandErhöhen(){
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

}

    public void bestandVerringern(){
        JPanel erhöhen = new JPanel();
        erhöhen.setVisible(true);
        erhöhen.setLayout(new BoxLayout(erhöhen, BoxLayout.Y_AXIS));
        erhöhen.add(new JLabel("Artikel anlegen"));

        JTextField bezeichnungsTextfield= new JTextField(30);
        bezeichnungsTextfield.add(new JLabel("Artikelbezeichnung: "));

        JTextField veringerungsTextfield = new JTextField(30);
        veringerungsTextfield.add(new JLabel("Zu verringernde Menge: "));

        JButton anlegenButton = new JButton("Bestand aktualisieren");

        erhöhen.add(bezeichnungsTextfield);
        erhöhen.add(veringerungsTextfield);
        erhöhen.add(anlegenButton);
    }




    //TODO Kunden aus der Hashmap als Array wiedergeben

    private void hinzufügenKundenliste(){
        Kunde[] kunden = new Kunde[eshop.getAlleGespeichertenWarenkörbe().size()];
        int position = 0;
        for (Kunde k : eshop.getAlleGespeichertenWarenkörbe().keySet()){
            kunden[position++] = k;
        }

        add(new JList<>(kunden), BorderLayout.CENTER);

    }
    private void hinzufügenMitarbeiterliste(){
        add(new JList(eshop.getAlleMitarbeiter().toArray()), BorderLayout.CENTER);
    }
private void hinzufügenArtikelListeStart(){
    add(new JList(eshop.getAlleArtikel().toArray()), BorderLayout.CENTER);
}
   public static void main(String[] args) throws IOException {
        new GUI(); // GUI wird erstellt
    }
}

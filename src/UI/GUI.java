package UI;

import Domain.EShop;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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

       //hinzufügenArtikelListeStart(); //TODO vesuchen sobald der ehop reingesetzt wurde
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
        westpanel.setLayout(new FlowLayout()); //TODO welchen Layoutmanager
        westpanel.add(kundeLogin()); //Kundenloginbereich wird hinzugefügt
        westpanel.add(mitarbeiterLogin()); //Mitarbeiterloginbereichwird hinzugefügt

        return westpanel;

    }

    private Component kundeLogin(){
        JPanel loginfenster = new JPanel(); //neues Jpanel
        loginfenster.setVisible(true);//Jpanel ist sichtbar
        this.setSize(300, 300);
        //loginfenster.setBounds(); müsste man verwenden, wenn man keinen Layoutmanager verwendet. ausrichtung des panesl
        //TODO loginfenster.setLayout() //ausrichtung des Inhalts setzten
        loginfenster.add(new JLabel("Kundenlogin")); // dem Loginbereich ein Label zur Bezeichnung hinugefügt
        JTextField usernameTextfield= new JTextField(30); //neues Textfeld erstellt, die nummer innen stellt die größe des textfeldes ein
        usernameTextfield.add(new JLabel("Username: ")); // Textfeld ein Label hinzugefügt
        JTextField passwotTextfield = new JTextField(30);
        passwotTextfield.add(new JLabel("Passwort: "));
        JButton loginButton = new JButton("Einloggen"); //button erstellt

        loginfenster.add(usernameTextfield); //dem loginfenster werden die einzelnen komponenten hinzugefügt
        loginfenster.add(passwotTextfield);
        loginfenster.add(loginButton);
        return loginfenster;
    }
    private Component kundenregistrierungPopup(){
        JPanel registerfenster = new JPanel();
        registerfenster.setVisible(true);
        registerfenster.add(new JLabel("Kundenregistrierung"));
//TODO loginfenster.setLayout() //ausrichtung des Inhalts setzten

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
        loginfenster.add(new JLabel("Mitarbeiterlogin"));
//TODO loginfenster.setLayout() //ausrichtung des Inhalts setzten


        //TODO loginfenster.setLayout(BoxLayout);  checken wie das mit dem Layout gemacht wird
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


    private void mitarbeiiterbereich(){

    }

    private void neuenArtikelAnlegen(){
        JPanel anlegen = new JPanel();
        anlegen.setVisible(true);
        anlegen.add(new JLabel("Artikel anlegen"));}}
//TODO setLayout() //ausrichtung des Inhalts setzten



        /*JTextField usernameTextfield= new JTextField(30);
        usernameTextfield.add(new JLabel("Artikelbezeichnung: "));
        JTextField passwotTextfield = new JTextField(30);
        passwotTextfield.add(new JLabel("Artikelnummer: "));
        JTextField usernameTextfield= new JTextField(30);
        usernameTextfield.add(new JLabel("Bestand: "));
        JTextField passwotTextfield = new JTextField(30);
        passwotTextfield.add(new JLabel("Preis: "));
        JTextField usernameTextfield= new JTextField(30);

    }*/


    //TODO Kunden aus der Hashmap als Array wiedergeben
/*
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

 */
package UI;

import Domain.EShop;
import ValueObjekt.Kunde;
import ValueObjekt.Warenkorb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

public class GUI extends JFrame implements ActionListener { // durch die Extension wird die GUI zur childclass von JFrame

    /**
     * JLabel : zum display von Text,image, (auch beides)
     *Jpanel : dient als Container um andere Komponenten zu halten
     *
     *BoxLayout: verwendet NOSW und center
     *
     */
    private EShop eshop;

    //TODO ist es in ordnung das hier zu platzieren
    JButton mitarbeiterbereich = new JButton("Mitarbeiterberich"); //Buttons für Northpanel um in andere Bereiche zu kommen
    JButton kundenbereich = new JButton("Kundenbereich");

    public GUI() throws IOException {
        super("Roha & Sanjana's Eshop");
        String datei = "ESHOP";
        eshop = new EShop(datei);//TODO vlt mit einem weiteren Konstruktor lösen



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
        add(startpage());

    }


    //TODO action event reinlesen so wie ich es mir dachte scheint es nicht ganz zu funktionieren

    @Override  //Damit beim Klicken der Buttons auch etwas passiert muss das hier umgesetzt werden
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == mitarbeiterbereich){
            try {
                MitarbeiterBereich m = new MitarbeiterBereich();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private Component startpage(){
        JPanel start = new JPanel(); //neues Jpanel
        start.setVisible(true);//Jpanel ist sichtbar
        start.setSize(300, 300);
        start.setLayout(new BorderLayout(5,5));


        start.add(hinzufügenLoginBereichStart(), BorderLayout.WEST); //Logins im Westen hinzugefügt

        start.add(mitarbeiterbereich,BorderLayout.NORTH);
        mitarbeiterbereich.addActionListener(this); //damit beim Drücken des Buttons auch was geschieht
        start.add(kundenbereich,BorderLayout.NORTH);
        kundenbereich.addActionListener(this);

        hinzufügenArtikelListeStart(); //Artikelliste wird im center angezeigt

        return start;
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

   /* public Component bereichauswahl(){
        JPanel bereichsauswahl = new JPanel();
        bereichsauswahl.setVisible(true);
        bereichsauswahl.setLayout(new BoxLayout(bereichsauswahl, BoxLayout.X_AXIS));



        JButton mitarbeiterbereich = new JButton("Mitarbeiterberich");
        JButton kundenbereich = new JButton("Kundenbereich");


        bereichsauswahl.add(mitarbeiterbereich);
        bereichsauswahl.add(kundenbereich);

        return bereichsauswahl;
    }

    */



private void hinzufügenArtikelListeStart(){
    add(new JList(eshop.getAlleArtikel().toArray()), BorderLayout.CENTER);
}
   public static void main(String[] args) throws IOException {
        new GUI(); // GUI wird erstellt
    }


}

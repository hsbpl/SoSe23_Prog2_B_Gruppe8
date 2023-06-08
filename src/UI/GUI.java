package UI;

import Domain.EShop;
import ValueObjekt.Mitarbeiter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUI extends JFrame {
    private EShop eshop;

    public GUI() throws IOException {
        super("Roha & Sanjana's Eshop");
      //  eshop = new EShop();//TODO nachdem die persistence festgelegt hat was noch in den Konstruktor kommt hier korrigieren
        hauptfenster();
    }

    private void hauptfenster(){
        JFrame frame= new JFrame();

        setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        setSize(640, 480);
        setLocation(0, 500);
        setVisible(true);

       /* frame.add(hinzufügenLoginKunde(););
        frame.add(hinzufügenMitarbeiter();); ...
        */
    }
    private void hinzufügenLoginKundeLogin(){
        JPanel loginfenster = new JPanel();
        loginfenster.setVisible(true);
        loginfenster.add(new JLabel("Kundenlogin"));

       //TODO Das untere zum loginfensterhinzufügen

        //TODO loginfenster.setLayout(BoxLayout);  checken wie das mit dem Layout gemacht wird
        JTextField usernameTextfield= new JTextField();
        usernameTextfield.add(new JLabel("Username: "));
        JTextField passwotTextfield = new JTextField();
        passwotTextfield.add(new JLabel("Passwort: "));

        JButton loginButton = new JButton("Einloggen");

    }
    private void hinzufügenKundenregistrierung(){
        JPanel loginfenster = new JPanel();
        loginfenster.setVisible(true);
        loginfenster.add(new JLabel("Kundenregistrierung"));

        //TODO Das untere zum loginfensterhinzufügen
        //TODO loginfenster.setLayout(BoxLayout);  checken wie das mit dem Layout gemacht wird

        JTextField usernameTextfield= new JTextField();
        usernameTextfield.add(new JLabel("Username: "));
        JTextField passwotTextfield = new JTextField();
        passwotTextfield.add(new JLabel("Passwort: "));
        JTextField nachnameTextfield= new JTextField();
        nachnameTextfield.add(new JLabel("Nachname: "));
        JTextField vornameTextfield = new JTextField();
        vornameTextfield.add(new JLabel("Vorname: "));
        JTextField adressenTextfield= new JTextField();
        adressenTextfield.add(new JLabel("Adresse: "));

        JButton registerButton = new JButton("Registrieren");
    }
    private void hinzufügenMitarbeiterLogin(){
        JPanel loginfenster = new JPanel();
        loginfenster.setVisible(true);
        loginfenster.add(new JLabel("Mitarbeiterlogin"));

        //TODO Das untere zum loginfensterhinzufügen

        //TODO loginfenster.setLayout(BoxLayout);  checken wie das mit dem Layout gemacht wird
        JTextField usernameTextfield= new JTextField();
        usernameTextfield.add(new JLabel("Username: "));
        JTextField passwotTextfield = new JTextField();
        passwotTextfield.add(new JLabel("Passwort: "));

        JButton loginButton = new JButton("Einloggen");
    }
private void hinzufügenArtikelListe(){
    add(new JList(eshop.getAlleArtikel().toArray()), BorderLayout.CENTER);
}
   public static void main(String[] args) throws IOException {
        new GUI();
    }
}

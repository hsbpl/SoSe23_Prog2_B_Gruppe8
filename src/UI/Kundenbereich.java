package UI;

import Domain.EShop;

import javax.swing.*;
import java.awt.*;

public class Kundenbereich extends JFrame{

    JButton zurückButton = new JButton("Ausloggen");
    public Kundenbereich(){
        super("Roha & Sanjana's Eshop");
        EShop eshop = new EShop();//TODO nachdem die persistence festgelegt hat was noch in den Konstruktor kommt hier korrigieren

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

        ;
    }


    public void kundenbereich(){

        JFrame kundenFenster = new JFrame();

        kundenFenster.setTitle("\"Roha & Sanjana's Eshop\""); //Title des Jframe wird erstellt
        kundenFenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sorgt dafür, das beim klicken des Exit das fenster auch geschlossen wird
        kundenFenster.setSize(640, 480); // größe des Frames //TODO welche größe passt am besten
        kundenFenster.setLocation(0, 500);
        kundenFenster.setResizable(true); // erlaubt uns die Größe des fensters zu ändern
        kundenFenster.setVisible(true);//sorgt dafür das der Frame auch zu sehen ist
        kundenFenster.setLayout(new BorderLayout(5,5)); //aufteilung in borderlayout, die Zahlen sind für den Abstand da
        // mit setPrefferedSize(new Dimension(100, 100   )); kann man die Größe der einzelnen NSWOC anpasssen

        kundenFenster.add(zurückButton, BorderLayout.NORTH);

        hinzufügenArtikelListeStart();

        kundenFenster.add(kundenregistrierung(),BorderLayout.EAST);
        add(kundenFenster);
    }


    private Component kundenregistrierung(){
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

    private void hinzufügenArtikelListeStart(){
        add(new JList(eshop.getAlleArtikel().toArray()), BorderLayout.CENTER);
    }
}

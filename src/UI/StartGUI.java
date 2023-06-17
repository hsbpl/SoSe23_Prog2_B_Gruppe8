package UI;

import Domain.EShop;
import Exceptions.LoginFehlgeschlagenException;
import Exceptions.UserExistiertBereitsException;
import ValueObjekt.Kunde;
import ValueObjekt.Mitarbeiter;
import ValueObjekt.Warenkorb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartGUI extends JFrame implements ActionListener {

    private EShop eshop;
    int textfieldSize = 50;

//Kundenlogin Teile
    JTextField passwortTextfield = new JTextField(textfieldSize); // Passworteingabe Kundenlogin

    JTextField usernameTextfield= new JTextField(textfieldSize); //UsernameTextfed Kundenlogin
    JButton loginButton = new JButton("Einloggen"); //button erstellt

    //Option zur Kundenregistrieung Button
    JButton registrierungsButton= new JButton("Als neuer Kunde registrieren"); //Button erstellt der später seperat Reg öffnen soll

    //Mitarbeiterlogin Teile
    JTextField usernameTextfieldMitarbeiter = new JTextField(textfieldSize);

    JTextField passwortTextfieldMitarbeiter = new JTextField(textfieldSize);

    JButton loginButtonMitarbeiter = new JButton("Einloggen");

    JList<String> artikelListe;

//Kundenregistrierungsteile
    JTextField usernameTextfieldRegistrierung= new JTextField(textfieldSize);

    JTextField passwotTextfieldRegistrierung = new JTextField(textfieldSize);

    JTextField nachnameTextfield= new JTextField(textfieldSize);

    JTextField vornameTextfield = new JTextField(textfieldSize);

    JTextField adressenTextfield= new JTextField(textfieldSize);

    JButton neuenKundenAnlegenButton = new JButton("Registrieren");


    public StartGUI() throws IOException {
        super("Roha & Sanjana's Eshop");
        String datei = "ESHOP";
        eshop = new EShop(datei);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sorgt dafür, das beim klicken des Exit das fenster auch geschlossen wird
        this.setLocation(0, 500);
        this.setResizable(true); // erlaubt uns die Größe des fensters zu ändern
        this.setVisible(true);//sorgt dafür das der Frame auch zu sehen ist
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); //Maximiert das fenster

        add(startpage());

    }





    private Component startpage(){
        JPanel start = new JPanel(); //neues Jpanel
        start.setVisible(true);//Jpanel ist sichtbar
        start.setSize(300, 300);
        start.setLayout(new BorderLayout(5,5));


        start.add(hinzufügenLoginBereichStart(), BorderLayout.WEST); //Logins im Westen hinzugefügt


        JPanel northpanel = new JPanel();
        northpanel.setVisible(true);//Jpanel ist sichtbar
        northpanel.setSize(400, 400);
        northpanel.setLayout(new FlowLayout());
        northpanel.setPreferredSize(new Dimension( 200,100));


        northpanel.add(Box.createVerticalStrut(100)); //sorgt für einen Abstand vor dem nächsten component
        JLabel wilkommen = new JLabel("Wilkommen!\nBitte loggen Sie sich zu allererst ein!");
        northpanel.add(wilkommen);

        start.add(northpanel, BorderLayout.NORTH);

        start.add(artikelListe(),BorderLayout.CENTER);

        return start;
    }



    private Component hinzufügenLoginBereichStart(){
        JPanel westpanel = new JPanel(); //neues Jpanel
        westpanel.setVisible(true);//Jpanel ist sichtbar
        westpanel.setLayout(new BoxLayout(westpanel, BoxLayout.Y_AXIS)); // sorgt dafür das alles auf der Y-Achse liegt
        westpanel.setPreferredSize(new Dimension(300, 100)); //sorgt für eine feste größe des westpanels

        westpanel.add(Box.createVerticalStrut(40));
        westpanel.add(kundeLogin()); //Kundenloginbereich wird hinzugefügt
        westpanel.add(Box.createVerticalStrut(40));
        westpanel.add(mitarbeiterLogin()); //Mitarbeiterloginbereichwird hinzugefügt
        westpanel.add(Box.createVerticalStrut(40));

        registrierungsButton.addActionListener(this);
        westpanel.add(registrierungsButton); // button wird dem westpanel hinzugefügt

        return westpanel;

    }

    private Component registerPopup(){
        JDialog popup = new JDialog();
        popup.setVisible(true);
        popup.setSize(300, 500);
        popup.setLocationRelativeTo(null);//popup erscheint in der mitte
        popup.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); //Sorgt dafür, das beim klicken des Exit das fenster auch geschlossen wird
        popup.setResizable(false); // erlaubt uns die Größe des fensters zu ändern
        popup.setTitle("Kunden Registrierung");
        popup.add(Box.createVerticalStrut(60));
        popup.add(kundenregistrierung());

        return popup;
    }

    private Component kundeLogin(){
        JPanel loginfenster = new JPanel(); //neues Jpanel
        loginfenster.setVisible(true);//Jpanel ist sichtbar
        loginfenster.setSize(300, 300);
        loginfenster.setLayout(new BoxLayout(loginfenster, BoxLayout.Y_AXIS));


        loginfenster.add(new JLabel("Kundenlogin")); // dem Loginbereich ein Label zur Bezeichnung hinugefügt

        usernameTextfield.add(new JLabel("Username: ")); // Textfeld ein Label hinzugefügt
        passwortTextfield.add(new JLabel("Passwort: "));
        usernameTextfield.setMaximumSize(usernameTextfieldMitarbeiter.getPreferredSize());
        passwortTextfield.setMaximumSize(passwortTextfieldMitarbeiter.getPreferredSize());

        loginButton.addActionListener(this);

        loginfenster.add(new JLabel("Username: "));
        loginfenster.add(usernameTextfield); //dem loginfenster werden die einzelnen komponenten hinzugefügt
        loginfenster.add(new JLabel("Passwort: "));
        loginfenster.add(passwortTextfield);
        loginfenster.add(loginButton);
        return loginfenster;
    }


    private Component mitarbeiterLogin(){
        JPanel loginfenster = new JPanel();
        loginfenster.setVisible(true);
        loginfenster.setLayout(new BoxLayout(loginfenster, BoxLayout.Y_AXIS));
        loginfenster.add(new JLabel("Mitarbeiterlogin"));


        usernameTextfieldMitarbeiter.add(new JLabel("Username: "));
        passwortTextfieldMitarbeiter.add(new JLabel("Passwort: "));
        usernameTextfieldMitarbeiter.setMaximumSize(usernameTextfieldMitarbeiter.getPreferredSize());
        passwortTextfieldMitarbeiter.setMaximumSize(passwortTextfieldMitarbeiter.getPreferredSize());

        loginButtonMitarbeiter.addActionListener(this);

        loginfenster.add(new JLabel("Username: "));
        loginfenster.add(usernameTextfieldMitarbeiter); //dem loginfenster werden die einzelnen komponenten hinzugefügt

        loginfenster.add(new JLabel("Passwort: "));
        loginfenster.add(passwortTextfieldMitarbeiter);

        loginfenster.add(loginButtonMitarbeiter);

        return loginfenster;
    }

    private Component kundenregistrierung(){
        JPanel registerfenster = new JPanel();
        registerfenster.setVisible(true);
        registerfenster.setSize(300, 500);
        registerfenster.setLayout(new BoxLayout(registerfenster, BoxLayout.Y_AXIS));
       usernameTextfieldRegistrierung.setMaximumSize(usernameTextfieldRegistrierung.getPreferredSize());
       passwotTextfieldRegistrierung.setMaximumSize(passwotTextfieldRegistrierung.getPreferredSize());
       nachnameTextfield.setMaximumSize(nachnameTextfield.getPreferredSize());
       vornameTextfield.setMaximumSize(vornameTextfield.getPreferredSize());
       adressenTextfield.setMaximumSize(adressenTextfield.getPreferredSize());

       neuenKundenAnlegenButton.addActionListener(this);

        registerfenster.add(new JLabel("Username: "));
        registerfenster.add(usernameTextfieldRegistrierung);

        registerfenster.add(new JLabel("Passwort: "));
        registerfenster.add(passwotTextfieldRegistrierung);

        registerfenster.add(new JLabel("Nachname: "));
        registerfenster.add(nachnameTextfield);

        registerfenster.add(new JLabel("Vorname: "));
        registerfenster.add(vornameTextfield);;

        registerfenster.add(new JLabel("Adresse: "));
        registerfenster.add(adressenTextfield);

        registerfenster.add(neuenKundenAnlegenButton);

        return registerfenster;
    }

    //todo die listen machen probleme

    private JList<String> artikelListe(){

            artikelListe = new JList(eshop.getAlleArtikel().toArray());

        return artikelListe;
    }



    private enum Loginverfahren{
        KUNDEN_LOGIN,
        KUNDEN_REGISTRIERUNG_POPUP,
        MITARBEITER_LOGIN,
        NEUES_KUNDENKONTO_ANLEGEN

    }
    @Override  //Damit beim Klicken der Buttons auch etwas passiert muss das hier umgesetzt werden
    public void actionPerformed(ActionEvent actionEvent) {

        Loginverfahren loginverfahren = null;


        if(actionEvent.getSource() == loginButton){
            loginverfahren = Loginverfahren.KUNDEN_LOGIN;
        } else if (actionEvent.getSource() == loginButtonMitarbeiter){
            loginverfahren = Loginverfahren.MITARBEITER_LOGIN;
        } else if (actionEvent.getSource() == registrierungsButton){
            loginverfahren = Loginverfahren.KUNDEN_REGISTRIERUNG_POPUP;
        } else if (actionEvent.getSource() == neuenKundenAnlegenButton){
            loginverfahren = Loginverfahren.NEUES_KUNDENKONTO_ANLEGEN;
        }

        switch (loginverfahren){
            case KUNDEN_LOGIN:
                try {
                    String username = usernameTextfield.getText();
                    String passwort = passwortTextfield.getText();
                    Kunde aktuellerKunde = eshop.kundenLogin(username, passwort);
                    System.out.println(aktuellerKunde);
                    Warenkorb warenkorb = eshop.neuenWarenkorbErstellen(aktuellerKunde);

                    KundenbereichGUI k = new KundenbereichGUI(aktuellerKunde,warenkorb);
                    this.dispose();
                    System.out.println( "Erfolgreich Eingeloggt: "+ aktuellerKunde);

                } catch (LoginFehlgeschlagenException e) {
                    System.err.println(
                            "*********************************************************************************\n" +
                                    "Username oder Passwort falsch. Bitte versuchen Sie es nochmal\n" +
                                    "*********************************************************************************\n");

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;

            case KUNDEN_REGISTRIERUNG_POPUP:

                registerPopup();

                break;

            case MITARBEITER_LOGIN:
                try {
                    String username = usernameTextfieldMitarbeiter.getText();
                    String passwort = passwortTextfieldMitarbeiter.getText();
                    Mitarbeiter mitarbeiter = eshop.mitarbeiterLogin(username, passwort);

                    MitarbeiterBereichGUI m = new MitarbeiterBereichGUI(mitarbeiter);
                    //remove(hinzufügenArtikelListeStart());
                    this.dispose();

                    //TODO SCHÖNHEIT - Textfeld nach verwendung leeren
                    System.out.println("Erfolgreich Eingeloggt: "+mitarbeiter);

                } catch (LoginFehlgeschlagenException e) {
                    System.err.println(
                            "*********************************************************************************\n" +
                                    "Username oder Passwort falsch. Bitte versuchen Sie es nochmal\n" +
                                    "*********************************************************************************\n");

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                break;
            case NEUES_KUNDENKONTO_ANLEGEN: //TODO hier funktioniert die registrierung nicht
                try {
                    String username = usernameTextfieldRegistrierung.getText();
                    String passwort = passwotTextfieldRegistrierung.getText();
                    String nachname = nachnameTextfield.getText();
                    String vorname = vornameTextfield.getText();
                    String adresse = adressenTextfield.getText();
                    Kunde kunde = new Kunde(username, passwort, nachname, vorname, adresse);
                    eshop.kundenRegistrieren(kunde);
                    Warenkorb w = eshop.neuenWarenkorbErstellen(kunde);

                    KundenbereichGUI k = new KundenbereichGUI(kunde, w);
//todo fenster bei registrierung automatisch schließen lassem
                    this.dispose();
                    System.out.println(kunde);
                }catch (UserExistiertBereitsException e) {
                    System.err.println(
                            "*********************************************************************************\n" +
                                    "Dieses Konto Existiert bereits. Bitte versuchen Sie es nochmal.\n" +
                                    "*********************************************************************************\n");

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            default:
                //TODO fehlerbehebung
                break;
        }


    }

}

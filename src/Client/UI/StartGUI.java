package Client.UI;

import Client.net.EshopClient;
import Common.EShopInterface;
import Server.Domain.EShop;
import Common.Exceptions.LeeresTextfieldException;
import Common.Exceptions.LoginFehlgeschlagenException;
import Common.Exceptions.UserExistiertBereitsException;
import Client.UI.TableModels.ArtikelTableModel;
import Common.Kunde;
import Common.Mitarbeiter;
import Common.Warenkorb;
import Client.UI.menus.FileMenu;
import Client.UI.menus.HelpMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
public class StartGUI extends JFrame implements ActionListener {

    private EShopInterface eshop;
    int textfieldSize = 50;

    //Kundenlogin Teile
    private JTextField passwortTextfield = new JTextField(textfieldSize); // Passworteingabe Kundenlogin
    private JTextField usernameTextfield = new JTextField(textfieldSize); //UsernameTextfed Kundenlogin
    private JButton loginButton = new JButton("Einloggen"); //button erstellt
    private JButton registrierungsButton = new JButton("Als neuer Kunde registrieren"); //Button erstellt der später seperat Reg öffnen soll
    //Mitarbeiterlogin Teile
    private JTextField usernameTextfieldMitarbeiter = new JTextField(textfieldSize);
    private JTextField passwortTextfieldMitarbeiter = new JTextField(textfieldSize);
    private JButton loginButtonMitarbeiter = new JButton("Einloggen");
    //Kundenregistrierungsteile
    private JTextField usernameTextfieldRegistrierung = new JTextField(textfieldSize);
    private JTextField passwortTextfieldRegistrierung = new JTextField(textfieldSize);
    private JTextField nachnameTextfield = new JTextField(textfieldSize);
    private JTextField vornameTextfield = new JTextField(textfieldSize);
    private JTextField adressenTextfield = new JTextField(textfieldSize);
    private JButton neuenKundenAnlegenButton = new JButton("Registrieren");
    private JDialog popup;
    private JPanel midpanel;
    private JTable tabelle;
    private ArtikelTableModel model;
    private JScrollPane tablePane;
    private JMenuBar menuBar;



    public StartGUI() throws IOException {
        super("Roha & Sanjana's Eshop");
        eshop = new EShop("ESHOP"); //todo wenn Client-Server nicht funktioniert statt new EshopClient das hier
        //eshop = new EshopClient();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sorgt dafür, das beim klicken des Exit das fenster auch geschlossen wird
        this.setResizable(true); // erlaubt uns die Größe des fensters zu ändern
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); //Maximiert das fenster

        setupMenu();

        startpage();

        this.setVisible(true);//sorgt dafür das der Frame auch zu sehen ist

    }

    public StartGUI(EShopInterface eShop){
        super("Roha & Sanjana's Eshop");
      //  String datei = "ESHOP";
        eshop = eShop;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sorgt dafür, das beim klicken des Exit das fenster auch geschlossen wird
        this.setResizable(true); // erlaubt uns die Größe des fensters zu ändern
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); //Maximiert das fenster

        setupMenu();

        startpage();

        this.setVisible(true);//sorgt dafür das der Frame auch zu sehen ist
    }

    private void setupMenu() {
        // Menuleiste anlegen ...
        JMenuBar mBar = new JMenuBar();

        JMenu fileMenu = new FileMenu((JFrame) this, eshop);
        mBar.add(fileMenu);

        JMenu helpMenu = new HelpMenu();
        mBar.add(helpMenu);

        menuBar = mBar;

        this.setJMenuBar(mBar);
    }



    private void startpage() {
        JPanel start = new JPanel(); //neues Jpanel
        start.setVisible(true);//Jpanel ist sichtbar
        start.setLayout(new BorderLayout(5,5));

        start.add(westpanel(), BorderLayout.WEST); //Logins im Westen hinzugefügt
        start.add(northpanel(), BorderLayout.NORTH);
        start.add(midpanel(), BorderLayout.CENTER);


        add(start);
    }

    private JPanel northpanel(){
        JPanel northpanel = new JPanel();
        northpanel.setVisible(true);//Jpanel ist sichtbar
        northpanel.setLayout(new FlowLayout());
        northpanel.setPreferredSize(new Dimension(200, 100));


        northpanel.add(Box.createVerticalStrut(100)); //sorgt für einen Abstand vor dem nächsten component
        JLabel wilkommen = new JLabel("Wilkommen!\nBitte loggen Sie sich zu allererst ein!");
        wilkommen.setFont(new Font("Arial", Font.BOLD, 24));
        northpanel.add(wilkommen);

        return northpanel;
    }


    private JPanel westpanel() {
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


    private JPanel midpanel() {

        midpanel = new JPanel();
        midpanel.setVisible(true);//Jpanel ist sichtbar
        midpanel.setLayout(new BoxLayout(midpanel, BoxLayout.Y_AXIS));

        midpanel.add(artikellistTable());

        return midpanel;
    }



    private JDialog registerPopup() {
        popup = new JDialog();
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

    private JPanel kundeLogin() {
        JPanel loginfenster = new JPanel(); //neues Jpanel
        loginfenster.setVisible(true);//Jpanel ist sichtbar
        loginfenster.setSize(300, 300);
        loginfenster.setLayout(new BoxLayout(loginfenster, BoxLayout.Y_AXIS));
        loginfenster.setBorder(BorderFactory.createTitledBorder("Kundenlogin"));


        usernameTextfield.add(new JLabel("Username: ")); // Textfeld ein Label hinzugefügt
        passwortTextfield.add(new JLabel("Passwort: "));
        usernameTextfield.setMaximumSize(usernameTextfieldMitarbeiter.getPreferredSize());
        passwortTextfield.setMaximumSize(passwortTextfieldMitarbeiter.getPreferredSize());

        passwortTextfield.addKeyListener(new EnterKeyListener());
        usernameTextfield.addKeyListener(new EnterKeyListener());




        loginButton.addActionListener(this);

        loginfenster.add(new JLabel("Username: "));
        loginfenster.add(usernameTextfield); //dem loginfenster werden die einzelnen komponenten hinzugefügt
        loginfenster.add(new JLabel("Passwort: "));
        loginfenster.add(passwortTextfield);
        loginfenster.add(loginButton);
        return loginfenster;
    }


    private JPanel mitarbeiterLogin() {
        JPanel loginfenster = new JPanel();
        loginfenster.setVisible(true);
        loginfenster.setLayout(new BoxLayout(loginfenster, BoxLayout.Y_AXIS));
        loginfenster.setBorder(BorderFactory.createTitledBorder("Mitarbeiterlogin"));

        usernameTextfieldMitarbeiter.add(new JLabel("Username: "));
        passwortTextfieldMitarbeiter.add(new JLabel("Passwort: "));
        usernameTextfieldMitarbeiter.setMaximumSize(usernameTextfieldMitarbeiter.getPreferredSize());
        passwortTextfieldMitarbeiter.setMaximumSize(passwortTextfieldMitarbeiter.getPreferredSize());

        usernameTextfieldMitarbeiter.addKeyListener(new EnterKeyListener());
        passwortTextfieldMitarbeiter.addKeyListener(new EnterKeyListener());

        loginButtonMitarbeiter.addActionListener(this);

        loginfenster.add(new JLabel("Username: "));
        loginfenster.add(usernameTextfieldMitarbeiter); //dem loginfenster werden die einzelnen komponenten hinzugefügt

        loginfenster.add(new JLabel("Passwort: "));
        loginfenster.add(passwortTextfieldMitarbeiter);

        loginfenster.add(loginButtonMitarbeiter);

        return loginfenster;
    }

    private class EnterKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (e.getSource() == usernameTextfield) {
                    passwortTextfield.requestFocus();
                } else if (e.getSource() == passwortTextfield) {
                    loginButton.doClick();
                } else if (e.getSource() == usernameTextfieldMitarbeiter) {
                    passwortTextfieldMitarbeiter.requestFocus();
                } else if (e.getSource() == passwortTextfieldMitarbeiter) {
                    loginButtonMitarbeiter.doClick();
                }
            }
        }
    }


    private JPanel kundenregistrierung() {
        JPanel registerfenster = new JPanel();
        registerfenster.setVisible(true);
        registerfenster.setSize(300, 500);
        registerfenster.setBorder(BorderFactory.createTitledBorder("Kundenregistrierung"));
        registerfenster.setLayout(new BoxLayout(registerfenster, BoxLayout.Y_AXIS));
        usernameTextfieldRegistrierung.setMaximumSize(usernameTextfieldRegistrierung.getPreferredSize());
        passwortTextfieldRegistrierung.setMaximumSize(passwortTextfieldRegistrierung.getPreferredSize());
        nachnameTextfield.setMaximumSize(nachnameTextfield.getPreferredSize());
        vornameTextfield.setMaximumSize(vornameTextfield.getPreferredSize());
        adressenTextfield.setMaximumSize(adressenTextfield.getPreferredSize());

        neuenKundenAnlegenButton.addActionListener(this);

        registerfenster.add(new JLabel("Username: "));
        registerfenster.add(usernameTextfieldRegistrierung);

        registerfenster.add(new JLabel("Passwort: "));
        registerfenster.add(passwortTextfieldRegistrierung);

        registerfenster.add(new JLabel("Nachname: "));
        registerfenster.add(nachnameTextfield);

        registerfenster.add(new JLabel("Vorname: "));
        registerfenster.add(vornameTextfield);
        ;

        registerfenster.add(new JLabel("Adresse: "));
        registerfenster.add(adressenTextfield);

        registerfenster.add(neuenKundenAnlegenButton);

        return registerfenster;
    }


    private JScrollPane artikellistTable(){
        tabelle = new JTable();
        model =new ArtikelTableModel(eshop.getAlleArtikel());
        tabelle.setModel(model);
        tablePane = new JScrollPane(tabelle);

        return tablePane;
    }

    private enum Eventsource{
        KUNDEN_LOGIN,
        MITARBEITER_LOGIN,
        REGISTRIERUNGSFENSTER_ÖFFNEN,
        KUNDEN_REGISTRIEREN

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        Eventsource source = null;

        if (actionEvent.getSource() == loginButton) {
            source = Eventsource.KUNDEN_LOGIN;
        } else if (actionEvent.getSource() == loginButtonMitarbeiter) {
            source = Eventsource.MITARBEITER_LOGIN;
        } else if (actionEvent.getSource() == registrierungsButton) {
            source = Eventsource.REGISTRIERUNGSFENSTER_ÖFFNEN;
        } else if (actionEvent.getSource() == neuenKundenAnlegenButton) {
            source = Eventsource.KUNDEN_REGISTRIEREN;
        }

        switch (source){
            case KUNDEN_LOGIN:
                kundenLoginEvent();
                break;

            case MITARBEITER_LOGIN:
                mitarbeiterLoginEvent();
                break;

            case REGISTRIERUNGSFENSTER_ÖFFNEN:
                registerPopup();
                break;

            case KUNDEN_REGISTRIEREN:
                kundenRegistrierungEvent();
                break;

            default:
                System.out.println("Event Occured");
                break;
        }

    }

    private void kundenRegistrierungEvent() {
        try {
            String username = usernameTextfieldRegistrierung.getText();
            String passwort = passwortTextfieldRegistrierung.getText();
            String nachname = nachnameTextfield.getText();
            String vorname = vornameTextfield.getText();
            String adresse = adressenTextfield.getText();

            Kunde kunde = new Kunde(username, passwort, nachname, vorname, adresse);
            eshop.kundenRegistrieren(kunde);
            Warenkorb w = eshop.neuenWarenkorbErstellen(kunde);

            eshop.schreibeKunde();
            KundenbereichGUI k = new KundenbereichGUI(kunde, w, eshop);


            this.dispose();

            popup.dispose();
            System.err.println("Erfolgreich registriert: "+kunde);
            //}
        } catch (UserExistiertBereitsException e) {
            String kontoExistiertSchon = "Dieses Konto existiert bereits. Bitte versuchen Sie es nochmal.\n";

            System.err.println("*********************************************************************************\n" +
                    kontoExistiertSchon +
                    "*********************************************************************************\n");
            popup.dispose();
            JOptionPane.showMessageDialog(null, kontoExistiertSchon, "Konto existiert bereits", JOptionPane.ERROR_MESSAGE);

        } catch (IOException e) {
            throw new RuntimeException(e);

        } catch (LeeresTextfieldException e) {
            String leeresTextfeld = "Bitte füllen Sie alle Textfelder aus.\n";
            System.err.println("*********************************************************************************\n" +
                    leeresTextfeld +
                    "*********************************************************************************\n");
            popup.dispose();
            JOptionPane.showMessageDialog(null, leeresTextfeld, "Leere Textfelder", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void mitarbeiterLoginEvent() {
        try {
            String username = usernameTextfieldMitarbeiter.getText();
            String passwort = passwortTextfieldMitarbeiter.getText();
            Mitarbeiter mitarbeiter = eshop.mitarbeiterLogin(username, passwort);

            MitarbeiterBereichGUI m = new MitarbeiterBereichGUI(mitarbeiter, eshop);

            this.dispose();


            System.out.println("Erfolgreich eingeloggt: " + mitarbeiter);

        } catch (LoginFehlgeschlagenException e) {
            e.printStackTrace();
            String unOpwFalsch = "Username oder Passwort falsch. Bitte versuchen Sie es nochmal.\n";
            System.err.println("*********************************************************************************\n" +
                    unOpwFalsch +
                    "*********************************************************************************\n");

            JOptionPane.showMessageDialog(null, unOpwFalsch, "Login Fehlgeschlagen", JOptionPane.ERROR_MESSAGE);
            usernameTextfieldMitarbeiter.setText("");
            passwortTextfieldMitarbeiter.setText("");


        }
    }

    private void kundenLoginEvent() {
        try {
            String username = usernameTextfield.getText();
            String passwort = passwortTextfield.getText();
            Kunde aktuellerKunde = eshop.kundenLogin(username, passwort);
            System.out.println(aktuellerKunde);
            Warenkorb warenkorb = eshop.neuenWarenkorbErstellen(aktuellerKunde);

            KundenbereichGUI k = new KundenbereichGUI(aktuellerKunde, warenkorb, eshop);
            this.dispose();
            System.out.println("Erfolgreich eingeloggt: " + aktuellerKunde);

        } catch (LoginFehlgeschlagenException e) {
            String unOpwFalsch = "Username oder Passwort falsch. Bitte versuchen Sie es nochmal.\n";
            System.err.println("*********************************************************************************\n" +
                    unOpwFalsch +
                    "*********************************************************************************\n");

            JOptionPane.showMessageDialog(null, unOpwFalsch, "Login Fehlgeschlagen", JOptionPane.ERROR_MESSAGE);
            usernameTextfield.setText("");
            passwortTextfield.setText("");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}

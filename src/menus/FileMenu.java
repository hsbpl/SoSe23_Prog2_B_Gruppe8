package menus;

import Domain.EShop;
import UI.StartGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FileMenu extends JMenu implements ActionListener {

    private JFrame frame;
    private EShop eShop;

    public FileMenu(JFrame frame, EShop eShop) {
        super("File");

        this.frame = frame;
        this.eShop =eShop;

        JMenuItem mi = new JMenuItem("Sichern");
        //mi.setEnabled(false);
        mi.addActionListener(this);
        this.add(mi);

        this.addSeparator();

        mi = new JMenuItem("Beenden");
        mi.addActionListener(this);
        this.add(mi);

        this.addSeparator();

        mi = new JMenuItem("Ausloggen");
        mi.addActionListener(this);
        this.add(mi);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        System.out.println(command);

        if (command.equals("Sichern")) {
            try {
                eShop.schreibeArtikel();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (command.equals("Beenden")) {
            frame.setVisible(false);
            frame.dispose();
            System.exit(0);
        } else if (command.equals("Ausloggen")) {
            frame.setVisible(false);
            frame.dispose();
            StartGUI eShop = new StartGUI( this.eShop);
        }
    }
}
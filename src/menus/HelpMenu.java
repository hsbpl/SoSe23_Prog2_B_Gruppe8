package menus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class HelpMenu extends JMenu implements ActionListener {
   // todo mehr hinzufügen
    public HelpMenu() {
        super("Hilfe");

        JMenuItem miP = new JMenuItem("Programmierer");
        miP.addActionListener(this);
        add(miP);
        //m.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()== "Programmers") {
            JOptionPane.showMessageDialog(null, "Dieses Programm wurde Programmiert von Roha und Sanjana bei Problemen melden sie sich bei den beiden","Programmierer",1);

        }

        System.out.println("Klick auf Menu '" + e.getActionCommand() + "'.");
    }
}


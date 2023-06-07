package UI;

import Domain.EShop;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUI extends JFrame {
    private EShop eshop;

    public GUI() throws IOException {
        super("Roha & Sanjana's Eshop");
        eshop = new EShop();//TODO nachdem die persistence festgelegt hat was noch in den Konstruktor kommt hier korrigieren

        JFrame frame= new JFrame();

        setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        setSize(640, 480);
        setLocation(0, 500);
        setVisible(true);
    }

private void hinzufügenArtikelListe(){
    add(new JList(eshop.getAlleArtikel().toArray()), BorderLayout.CENTER);
}
   public static void main(String[] args) throws IOException {
        new GUI();
    }
}

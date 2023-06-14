package UI;

import Domain.EShop;
import ValueObjekt.Kunde;
import ValueObjekt.Warenkorb;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class KundenbereichGUI extends JFrame{

    private EShop eShop;
    private Kunde eingeloggterKunde;

    int textfielsize;
    Warenkorb warenKorbDesKunden;
    JButton zurückButton = new JButton("Ausloggen");
    public KundenbereichGUI(Kunde eingeloggterKunde, Warenkorb warenKorbDesKunden) throws IOException { //todo hier und mitarbeiterbereich wird der kunde nach dem Login übergeben
        super("Roha & Sanjana's Eshop");
        this.eingeloggterKunde = eingeloggterKunde;
        this.warenKorbDesKunden = warenKorbDesKunden;
        String datei = "ESHOP";
       eShop = new EShop(datei);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sorgt dafür, das beim klicken des Exit das fenster auch geschlossen wird
        this.setResizable(true); // erlaubt uns die Größe des fensters zu ändern
        this.setVisible(true);//sorgt dafür das der Frame auch zu sehen ist
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
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

    }




    private void hinzufügenArtikelListeStart(){
        // add(new JList(eshop.getAlleArtikel().toArray()), BorderLayout.CENTER);
    }
}

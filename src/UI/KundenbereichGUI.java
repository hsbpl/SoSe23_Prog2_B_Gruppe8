package UI;

import Domain.EShop;
import ValueObjekt.Artikel;
import ValueObjekt.Kunde;
import ValueObjekt.Warenkorb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;

public class KundenbereichGUI extends JFrame {
    private EShop eShop;
    private Kunde eingeloggterKunde;
    private Warenkorb warenKorbDesKunden;

    public KundenbereichGUI(Kunde eingeloggterKunde, Warenkorb warenKorbDesKunden) throws IOException {
        super("Kundenbereich");
        this.eingeloggterKunde = eingeloggterKunde;
        this.warenKorbDesKunden = warenKorbDesKunden;
        String datei = "ESHOP";
        eShop = new EShop(datei);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Erstellen der GUI-Komponenten
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel infoPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Willkommen, " + eingeloggterKunde.getVorname() + "!");
        JButton logoutButton = new JButton("Ausloggen");
        JButton artikelHinzufuegenButton = new JButton("Artikel hinzufügen");
        JButton artikelEntfernenButton = new JButton("Artikel entfernen");
        JButton warenkorbButton = new JButton("Warenkorb anzeigen");
        JButton warenkorbLeerenButton = new JButton("Warenkorb leeren");
        DefaultListModel<String> artikelListModel = new DefaultListModel<>();
        JList<String> artikelList = new JList<>(artikelListModel);
        JScrollPane artikelScrollPane = new JScrollPane(artikelList);

// Konfigurieren der GUI-Komponenten
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Möchten Sie sich wirklich ausloggen?", "Ausloggen", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    // Logik zum Ausloggen des Kunden hier einfügen
                    dispose(); // Schließt das Kundenbereich-Fenster
                }
            }
        });

// Anpassen der Info-Panel-Eigenschaften
        infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

// Hinzufügen der GUI-Komponenten zum Info-Panel
        infoPanel.add(titleLabel);

// Zusammenstellen der GUI
        buttonPanel.add(logoutButton);
        buttonPanel.add(artikelHinzufuegenButton);
        buttonPanel.add(artikelEntfernenButton);
        buttonPanel.add(warenkorbButton);
        buttonPanel.add(warenkorbLeerenButton);

        mainPanel.add(infoPanel, BorderLayout.NORTH);
        mainPanel.add(artikelScrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
    }
}





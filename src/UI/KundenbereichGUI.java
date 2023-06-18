package UI;

import Domain.EShop;
import ValueObjekt.Artikel;
import ValueObjekt.Kunde;
import ValueObjekt.Warenkorb;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Willkommen, " + eingeloggterKunde.getVorname() + "!");
        JButton logoutButton = new JButton("Ausloggen");
        DefaultListModel<String> artikelListModel = new DefaultListModel<>();
        JList<String> artikelList = new JList<>(artikelListModel);
        JScrollPane artikelScrollPane = new JScrollPane(artikelList);

        // Konfigurieren der GUI-Komponenten
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logik zum Ausloggen des Kunden hier einfügen
                dispose(); // Schließt das Kundenbereich-Fenster
            }
        });
        // Hinzufügen der Artikel im Warenkorb zur Liste
        for (Map.Entry<Artikel, Integer> entry : warenKorbDesKunden.getWarenkorb().entrySet()) {
            Artikel artikel = entry.getKey();
            int menge = entry.getValue();
            artikelListModel.addElement(artikel.getBezeichnung() + " (Menge: " + menge + ")");
        }

        // Zusammenstellen der GUI
        infoPanel.add(titleLabel);
        infoPanel.add(logoutButton);
        mainPanel.add(infoPanel, BorderLayout.NORTH);
        mainPanel.add(artikelScrollPane, BorderLayout.CENTER);
        this.add(mainPanel);
    }
}


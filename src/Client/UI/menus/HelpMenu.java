package Client.UI.menus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpMenu extends JMenu implements ActionListener {

    public HelpMenu() {
        super("Hilfe");

        JMenuItem miP = new JMenuItem("Programmierer");
        miP.addActionListener(this);
        add(miP);

        JMenuItem miC = new JMenuItem("Kontakt");
        miC.addActionListener(this);
        add(miC);

        JMenuItem miH = new JMenuItem("FAQ");
        miH.addActionListener(this);
        add(miH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if (actionCommand.equals("Programmierer")) {
            JOptionPane.showMessageDialog(null, "Dieses Programm wurde von Roha und Sanjana programmiert.", "Programmierer", JOptionPane.INFORMATION_MESSAGE);

        } else if (actionCommand.equals("Kontakt")) {
            String message = JOptionPane.showInputDialog(null, "Geben Sie Ihre Frage oder Ihr Problem ein:", " Kontakt", JOptionPane.QUESTION_MESSAGE);

            // Benutzer-Nachricht wird in der Konsole ausgegeben
            System.out.println("Kunden-Nachricht: " + message);

            JOptionPane.showMessageDialog(null, "Nachricht wurde verschickt.", "Nachricht versendet", JOptionPane.INFORMATION_MESSAGE);


        } else if (actionCommand.equals("FAQ")) {
            String[] faqQuestions = {
                    "Frage 1: Wie kann ich ein Konto erstellen?",
                    "Frage 2: Wie lange dauert der Versand?",
                    "Frage 3: Welche Zahlungsmethoden werden akzeptiert?",
                    "Frage 4: Wie kann ich den Kundenservice kontaktieren?",
            };

            String[] faqAnswers = {
                    "Antwort 1: Um ein Konto zu erstellen, klicken Sie auf die Schaltfläche 'als neuer Kunde Registrieren' auf der Startseite...",
                    "Antwort 2: Die Versanddauer variiert je nach Standort und Versandart. In der Regel beträgt sie 3-5 Werktage...",
                    "Antwort 3: Wir akzeptieren Kreditkarten (Visa, MasterCard) und PayPal als Zahlungsmethoden...",
                    "Antwort 4: Sie können den Kundenservice im Hilfemenü unter Kontakt finden und eine Nachricht hinterlassen.",
            };

            String faq = "";
            for (int i = 0; i < faqQuestions.length; i++) {
                faq += faqQuestions[i] + "\n\n";
            }

            String selectedQuestion = (String) JOptionPane.showInputDialog(null, faq, "Häufig gestellte Fragen", JOptionPane.INFORMATION_MESSAGE, null, faqQuestions, faqQuestions[0]);

            // Wenn eine Frage ausgewählt wurde, zeigt es die entsprechende Antwort an
            if (selectedQuestion != null) {
                int index = -1;
                for (int i = 0; i < faqQuestions.length; i++) {
                    if (faqQuestions[i].equals(selectedQuestion)) {
                        index = i;
                        break;
                    }
                }
                if (index >= 0 && index < faqAnswers.length) {
                    JOptionPane.showMessageDialog(null, faqAnswers[index], selectedQuestion, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

    }
}
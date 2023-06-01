package Persistence;

import ValueObjekt.Artikel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class FilePersistenceManager {
    private static final String ARTICLE_FILE = "artikel.txt";
    private static final String EMPLOYEE_FILE = "employees.txt";
    private static final String CUSTOMER_FILE = "customers.txt";
    private static final String TRANSACTION_FILE = "transactions.txt";

    public static void saveArticles(List<Artikel> artikel) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARTICLE_FILE))) {
            for (Artikel article : artikel) {
                writer.println(article.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Artikel> loadArticles() {
        List<Artikel> articles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARTICLE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int artikelnummer = Integer.parseInt(parts[0]);
                String bestand = parts[1];
                int preis = Integer.parseInt(parts[2]);
                Artikel artikel = new Artikel(artikelnummer, bestand, preis);
                articles.add(artikel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return articles;
    }

    //TODO: Methoden für Mitarbeiter, Kunden, Ein- und Auslagerung muss ich noch Implementieren
}




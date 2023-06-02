package Persistence;

import ValueObjekt.Artikel;
import ValueObjekt.Kunde;
import ValueObjekt.Mitarbeiter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class FilePersistenceManager {
    private static final String ARTICLE_FILE = "artikel.txt";
    private static final String EMPLOYEE_FILE = "employees.txt";
    private static final String CUSTOMER_FILE = "customers.txt";
    private static final String TRANSACTION_FILE = "transactions.txt";

    public static void saveData(List<Artikel> artikelList, List<Mitarbeiter> mitarbeiterList, List<Kunde> kundeList){
        saveArticles(artikelList);
        saveMitarbeiter(mitarbeiterList);
        saveKunde(kundeList);
    }



    public static void loadData(List<Artikel> artikelList, List<Mitarbeiter> mitarbeiterList, List<Kunde> kundeList){
        artikelList.addAll(loadArticles());
        mitarbeiterList.addAll(loadMitarbeiter());
        kundeList.addAll(loadKunde());
    }

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
        List<Artikel> artikelList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARTICLE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int artikelnummer = Integer.parseInt(parts[0]);
                String bestand = parts[1];
                int preis = Integer.parseInt(parts[2]);
                Artikel artikel = new Artikel(artikelnummer, bestand, preis);
                artikelList.add(artikel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return artikelList;
    }
    public static void saveMitarbeiter(List<Mitarbeiter> mitarbeiterList){
        try (PrintWriter writer = new PrintWriter(new FileWriter(EMPLOYEE_FILE))){
            for (Mitarbeiter mitarbeiter : mitarbeiterList){
                writer.println(mitarbeiter.getidNummer() + "," + mitarbeiter.getUserName() + "," + mitarbeiter.getPasswort() + "," + mitarbeiter.getNachname() + "," + mitarbeiter.getVorname());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static List<Mitarbeiter> loadMitarbeiter(){
        List<Mitarbeiter> mitarbeiterList = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idNummer = Integer.parseInt(parts[0]);
                String userName = parts[1];
                String passwort = parts[2];
                String nachname = parts[3];
                String vorname = parts[4];
                Mitarbeiter mitarbeiter = new Mitarbeiter( userName, passwort, nachname, vorname, idNummer);
                mitarbeiterList.add(mitarbeiter);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return mitarbeiterList;

    }

    public static void saveKunde(List<Kunde> kundeList){
        try (PrintWriter writer = new PrintWriter(new FileWriter(CUSTOMER_FILE))){
            for (Kunde kunde : kundeList){
                writer.println(kunde.getUserName() + "," + kunde.getPasswort() + "," + kunde.getNachname() + "," + kunde.getVorname() + "," + kunde.getidNummer() + "," + kunde.getKundenAdresse());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static List<Kunde> loadKunde(){
        List<Kunde> kundeList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_FILE))){
            String line;
            while ((line = reader.readLine()) !=null){
                String[] parts = line.split(",");
                String UserName = parts[0];
                String passwort = parts[1];
                String nachname = parts[2];
                String vorname = parts[3];
                int idNr = Integer.parseInt(parts[4]);
                String kundeAdresse = parts[5];
                Kunde kunde = new Kunde(UserName, passwort, nachname, vorname, idNr, kundeAdresse);
                kundeList.add(kunde);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return kundeList;
    }




    //TODO: Methoden für Mitarbeiter, Kunden, Ein- und Auslagerung muss ich noch Implementieren
}




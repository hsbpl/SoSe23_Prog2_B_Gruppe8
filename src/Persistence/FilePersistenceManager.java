package Persistence;

import ValueObjekt.Artikel;
import ValueObjekt.Kunde;
import ValueObjekt.Mitarbeiter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FilePersistenceManager implements PersistenceManager{

   private BufferedReader reader = null;
   private PrintWriter writer = null;

    public List<Kunde> loadCustomers() {return loadKunde();}
    public List<Mitarbeiter> loadEmployees() {return loadMitarbeiter();}

    public void openForReading(String datei) throws FileNotFoundException{
       reader = new BufferedReader(new FileReader(datei));
   }
   public void openForWriting(String datei) throws IOException{
       writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));
   }

    public boolean close() {
        if (writer != null)
            writer.close();

        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                // TODO Auto-generierter catch block
                e.printStackTrace();

                return false;
            }
        }

        return true;
    }


    //Der obere Codeabschnitt zeigt die Methode close, die verwendet wird um Printwriter und BufferReader zu schließen
    private static final String ARTICLE_FILE = "ESHOP_ARTIKEL.txt";
    private static final String EMPLOYEE_FILE = "ESHOP_MITARBEITER.txt";
    private static final String CUSTOMER_FILE = "ESHOP_KUNDEN.txt";
    private static final String DATA_FILE = "data.txt";


    public void saveData(List<Artikel> artikelList, List<Mitarbeiter> mitarbeiterList, List<Kunde> kundeList){
        saveArticles(artikelList);
        saveMitarbeiter(mitarbeiterList);
        saveKunde(kundeList);
    }



    public void loadData(List<Artikel> artikelList, List<Mitarbeiter> mitarbeiterList, List<Kunde> kundeList){
        artikelList.addAll(loadArticles());
        mitarbeiterList.addAll(loadMitarbeiter());
        kundeList.addAll(loadKunde());
    }

    public void saveArticles(List<Artikel> artikel) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARTICLE_FILE))) {
            for (Artikel article : artikel) {
                writer.println(article.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Artikel> loadArticles() {
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
    public void saveMitarbeiter(List<Mitarbeiter> mitarbeiterList){
        try (PrintWriter writer = new PrintWriter(new FileWriter(EMPLOYEE_FILE))){
            for (Mitarbeiter mitarbeiter : mitarbeiterList){
                writer.println(mitarbeiter.getidNummer() + "," + mitarbeiter.getUserName() + "," + mitarbeiter.getPasswort() + "," + mitarbeiter.getNachname() + "," + mitarbeiter.getVorname());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<Mitarbeiter> loadMitarbeiter(){
        List<Mitarbeiter> mitarbeiterList = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String idNummer = parts[0];
                //TODO hier dasselbe wie bei Mitarbeiter
                String userName = parts[1];
                String passwort = parts[2];
                String nachname = parts[3];
                String vorname = parts[4];
                Mitarbeiter mitarbeiter = new Mitarbeiter( userName, passwort, nachname, vorname);
                mitarbeiterList.add(mitarbeiter);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return mitarbeiterList;

    }

    public void saveKunde(List<Kunde> kundeList){
        try (PrintWriter writer = new PrintWriter(new FileWriter(CUSTOMER_FILE))){
            for (Kunde kunde : kundeList){
                writer.println(kunde.getUserName() + "," + kunde.getPasswort() + "," + kunde.getNachname() + "," + kunde.getVorname() + "," + kunde.getidNummer() + "," + kunde.getKundenAdresse());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public List<Kunde> loadKunde(){
        List<Kunde> kundeList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_FILE))){
            String line;
            while ((line = reader.readLine()) !=null){
                String[] parts = line.split(",");
                String UserName = parts[0];
                String passwort = parts[1];
                String nachname = parts[2];
                String vorname = parts[3];
                String idNr = parts[4]; //TODO habe Id automatisch generieren lassen, ist jetzt ein String. müssten schauen wie das hier gespeichert werden muss. Habe auch deshalb die Id unten rausgenommen.
                String kundeAdresse = parts[5];
                Kunde kunde = new Kunde(UserName, passwort, nachname, vorname, kundeAdresse);
                kundeList.add(kunde);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return kundeList;
    }

    private String liesZeile() throws IOException{
        if (reader != null)
            return reader.readLine();
        else
            return "";
    }

    private void schreibeZeile(String daten) {
        if (writer != null)
            writer.println(daten);
    }
}




    //TODO: Methoden für Mitarbeiter, Kunden, Ein- und Auslagerung muss ich noch Implementieren





/*package Persistence;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


//Exceptions
public class PersistenceManager {
    private static final String TRANSACTION_FILE = "transactions.txt";


    public static List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTION_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int transactionId = Integer.parseInt(parts[0]);
                Transaction transaction = new Transaction(transactionId, /* other parameters );
       /*         transactions.add(transaction);

                // Parse the line and create Transaction objects
                // Add the Transaction objects to the transactions list
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public static void saveTransactions(List<Transaction> transactions) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TRANSACTION_FILE))) {
            for (Transaction transaction : transactions) {
                writer.println(transaction.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

*/

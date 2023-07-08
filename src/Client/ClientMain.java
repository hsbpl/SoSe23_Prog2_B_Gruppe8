package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientMain {
         public static void main(String[] args) {
            String serverAddress = "127.0.0.1"; // Server-IP-Adresse
            int serverPort = 1399; // Server-Port
            int numClients = 5; // Anzahl der Clients, die gleichzeitig ausgeführt werden könnten

            for (int i = 0; i < numClients; i++) {
                Thread clientThread = new Thread(() -> {
                    try {
                        // Verbindung zum Server herstellen
                        Socket socket = new Socket(serverAddress, serverPort);
                        System.out.println("Verbindung hergestellt.");

                        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                        // Nachricht an den Server senden
                        output.println("HALLO_SERVER");

                        // Antwort vom Server empfangen
                        String response = input.readLine();
                        System.out.println("Antwort vom Server: " + response);

                        // Verbindung beenden
                        socket.close();
                        System.out.println("Verbindung geschlossen.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                clientThread.start();
            }
        }
    }



package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int serverPort = 1399;
        int numClients = 5;

        for (int i = 0; i < numClients; i++) {
            Thread clientThread = new Thread(() -> {
                try {
                    // Verbindung zum Server herstellen
                    Socket socket = new Socket(serverAddress, serverPort);
                    System.out.println("Verbindung hergestellt.");

                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                    // Antwort vom Server empfangen
                    String response;
                    while ((response = input.readLine()) != null) {
                        System.out.println("Antwort vom Server: " + response);
                    }

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

package Server.net;

import Common.EShopInterface;
import Common.Exceptions.ArtikelExistiertBereitsException;
import Common.Exceptions.UserExistiertBereitsException;
import Server.Domain.EShop;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


//todo erstmal nur hier reinkopiert gegenchecken ob das okay so ist

public class EshopServer {
    public static final int DEFAULT_PORT = 1399;
    protected int port;
    protected ServerSocket serverSocket;
    private EShopInterface eShopInterface = new EShop("items");

    public EshopServer() throws IOException, ArtikelExistiertBereitsException, UserExistiertBereitsException {
        if (port == 0) {
            port = 1399;
        }
        this.port = port;

        try {
            this.serverSocket = new ServerSocket(port);
            InetAddress ia = InetAddress.getLocalHost();
            System.out.println("Host: " + ia.getHostName());
            System.out.println("Server: " + ia.getHostAddress() + " Port: " + port);
            System.out.println("Waiting for connections...\n");
        }catch (IOException var3) {
            fail(var3, "Ein Ausnahmefall ist beim Anlegen des Server-Sockets aufgetreten");
        }
        this.acceptClientRequestProcessor();
    }

    private void acceptClientRequestProcessor() { //wird aufgerufen, um auf Verbindungsanfragen von Clients zu warten und diese zu verarbeiten.
        try {
            while(true) {
                Socket clientSocket = this.serverSocket.accept();
                ClientRequestProcessor c = new ClientRequestProcessor(clientSocket, this.eShopInterface);
                Thread t = new Thread(c);
                t.start();
            }
        } catch (IOException var4) {
            fail(var4, "Fehler während des Verbindungssuche");
        }
    }
    //diese Schleife wird gestartet, die auf neue Verbindungsanfragen wartet. Sobald eine Verbindung akzeptiert wird, wird ein ClientRequestProcessor erstellt und in einem separaten Thread gestartet, um die Anfragen des Clients zu verarbeiten.

//todo main kann man noch etwas sauberer programmieren
    public static void main(String[] args) throws IOException {
        int port = DEFAULT_PORT;

        try {

            EShopInterface eshop = new EShop("ESHOP");

            ServerSocket ss = new ServerSocket(port);
            System.out.println("Server laeuft und wartet auf eingehende Verbindungen!");


            while (true) {
                Socket s = ss.accept();

                ClientRequestProcessor c = new ClientRequestProcessor(s, eshop);

                Thread t = new Thread(c);
                t.start();

                System.err.println("Client verbunden!");
            }

        } catch (IOException e) {
            e.printStackTrace();
            fail(e, "Fehler wärend der Server-Erzeugung");
        }
    }


    private static void fail(Exception e, String msg) {
        System.err.println(msg + ": " + e);
        System.exit(1);
    }

}

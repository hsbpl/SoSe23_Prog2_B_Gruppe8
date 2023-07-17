package Server.net;

import Common.EShopInterface;
import Common.Exceptions.ArtikelExistiertBereitsException;
import Common.Exceptions.UserExistiertBereitsException;
import Server.Domain.EShop;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EshopServer {

    public static void main(String[] args) throws IOException {
        EShopInterface bib = new EShop("Eshop");

        ServerSocket ss = new ServerSocket(1399);
        System.out.println("Server laeuft und wartet auf eingehende Verbindungen!");

        while (true) {
            Socket s = ss.accept();

            ClientRequestProcessor c = new ClientRequestProcessor(s, bib);


            Thread t = new Thread(c);
            t.start();

            System.err.println("Client verbunden!");
        }


/*
    public void acceptClientRequestProcessor() {



        try {
            while (running) {
                Socket clientSocket = this.serverSocket.accept();
                ClientRequestProcessor c = new ClientRequestProcessor(clientSocket, this.eShopInterface);
                Thread t = new Thread(c);
                t.start();
            }
        } catch (IOException var4) {
            System.err.println("Fehler während des Verbindungssuche: " + var4.getMessage());
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public static void main(String[] args) throws IOException {
        int port = DEFAULT_PORT;

        try {
            EShopInterface eshop = new EShop("ESHOP");

            EshopServer server = new EshopServer(port, eshop);
            server.start();
        } catch (IOException | ArtikelExistiertBereitsException | UserExistiertBereitsException e) {
            e.printStackTrace();
            fail(e, "Fehler während der Server-Erzeugung");
        }
    }
    public void start() {
        acceptClientRequestProcessor();
    }


    private static void fail(Exception e, String msg) {
        System.err.println(msg + ": " + e);
        e.printStackTrace();
        System.exit(1);
    }

 */

    }
}

package Server.net;

import Common.EShopInterface;
import Server.Domain.EShop;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EshopServer {
//todo erstmal nur hier reinkopiert gegenchecken ob das okay so ist
    public static void main(String[] args) throws IOException {

        EShopInterface eshop = new EShop("ESHOP");

        ServerSocket ss = new ServerSocket(1399);
        System.out.println("Server laeuft und wartet auf eingehende Verbindungen!");

        /*
        while(true) {
            Socket s = ss.accept();

            ClientRequestProcessor c = new ClientRequestProcessor(s, eshop);

            // Parallele Abarbeitung des Clients starten
            Thread t = new Thread(c);
            t.start();



            System.err.println("Client verbunden!");
        }
        */
    }

}

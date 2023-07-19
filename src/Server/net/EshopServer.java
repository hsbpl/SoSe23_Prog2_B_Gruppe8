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
    }
}




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
    public static final int DEFAULT_PORT = 1399;
    protected int port;
    protected ServerSocket serverSocket;
    private EShopInterface eShopInterface;

    public EshopServer(int port, EShopInterface eshop) throws IOException, ArtikelExistiertBereitsException, UserExistiertBereitsException {
        if (port == 0) {
            port = DEFAULT_PORT;
        }
        this.port = port;
        this.eShopInterface = eshop;

        try {
            this.serverSocket = new ServerSocket(port);
            InetAddress ia = InetAddress.getLocalHost();
            System.out.println("Host: " + ia.getHostName());
            System.out.println("Server: " + ia.getHostAddress() + " Port: " + port);
            System.out.println("Waiting for connections...\n");
        } catch (IOException var3) {
            fail(var3, "Ein Ausnahmefall ist beim Anlegen des Server-Sockets aufgetreten");
        }
    }

    public void acceptClientRequestProcessor() {
        try {
            while (true) {
                Socket clientSocket = this.serverSocket.accept();
                ClientRequestProcessor c = new ClientRequestProcessor(clientSocket, this.eShopInterface);
                Thread t = new Thread(c);
                t.start();
            }
        } catch (IOException var4) {
            System.err.println("Fehler während des Verbindungssuche: " + var4.getMessage());
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

}

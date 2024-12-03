package Threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class SimpleServer {
    public static final int PORT = 8100;
    public static boolean running = true;
    public static ServerSocket serverSocket = null;

    private static final List<Socket> clients = new ArrayList<>();

    public void start() throws IOException {
        serverSocket = new ServerSocket(PORT);
        serverSocket.setSoTimeout(5_000);

        while (running) {
            try {
                while (running) {
                    System.out.println ("Waiting for a client ...");

//                    System.out.println("Server: " + serverSocket.getChannel());

                    Socket socket = serverSocket.accept();
                    new ClientThread(socket).start();
                    clients.add(socket);
                }
            } catch (SocketTimeoutException e) {
            } catch (IOException e) {
                System.err.println("Ops... " + e);
            }
        }
        while (clients.size() > 0) {
            try {
                System.out.println("There are " + clients.size() + " more clients connected to the server.");
                sleep(5_000);
            } catch (InterruptedException e) {
                throw new RuntimeException("Uncaught", e);
            }
        }

        serverSocket.close();
    }

    public static void main ( String [] args ) throws IOException {

//        try {
//            (new DAO.User()).createTable();
//            (new DAO.Message()).createTable();
//            (new DAO.Friendship()).createTable();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        (new SimpleServer()).start();
    }

    public static void stop() {
        running = false;
    }

    public static void logout(Socket socket) {
        clients.remove(socket);
    }
}

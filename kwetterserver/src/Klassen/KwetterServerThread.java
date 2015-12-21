package Klassen;

import java.io.*;
import java.net.Socket;

/**
 * Created by Wim Hambrouck on 25/08/2015.
 */
public class KwetterServerThread extends Thread {
    private KwetterServer server = null;
    private Socket socket = null;
    private int ID = -1;
    private ObjectInputStream streamIn = null;
    private ObjectOutputStream streamOut = null;

    public KwetterServerThread(KwetterServer _server, Socket _socket) {
        super();
        server = _server;
        socket = _socket;
        ID = socket.getPort();
    }

    public void send(String msg) {
        try {
            streamOut.writeBytes(msg);
            streamOut.flush();
        } catch (IOException ioe) {
            System.out.println(ID + " ERROR sending: " + ioe.getMessage());
            server.remove(ID);
            interrupt();
        }
    }

    public int getID() {
        return ID;
    }

    public void run() {
        System.out.println("Server Thread " + ID + " running.");
        while (true) {
            try {
                server.handle(ID, (Bericht) streamIn.readObject());
            } catch (IOException ioe) {
                System.out.println(ID + " ERROR reading: " + ioe.getMessage());
                server.remove(ID);
                stop();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void open() throws IOException {
        streamIn = new ObjectInputStream(new
                BufferedInputStream(socket.getInputStream()));
        streamOut = new ObjectOutputStream(new
                BufferedOutputStream(socket.getOutputStream()));
    }

    public void close() throws IOException {
        if (socket != null) socket.close();
        if (streamIn != null) streamIn.close();
        if (streamOut != null) streamOut.close();
    }
}
package Klassen;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created by Wim Hambrouck on 25/08/2015.
 */
public class KwetterClientThread extends Thread {

    private Socket socket = null;
    private KwetterClient client = null;
    private ObjectInputStream streamIn = null;

    public KwetterClientThread(KwetterClient _client, Socket _socket) {
        client = _client;
        socket = _socket;
        open();
        start();
    }

    public void open() {
        try {
            streamIn = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ioe) {
            System.out.println("Fout bij openen InputStream: " + ioe);
            client.stop();
        }
    }

    public void close() {
        try {
            if (streamIn != null) streamIn.close();
        } catch (IOException ioe) {
            System.out.println("Fout bij sluiten InputStream: " + ioe);
        }
    }

    public void run() {
        while (true) {
            try {
                Verzendbaar object =(Verzendbaar)streamIn.readObject();
                client.handle(object);
            } catch (IOException ioe) {
                System.out.println("Input/Output fout: " + ioe.getMessage());
                client.stop();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

package Klassen;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * Created by Wim Hambrouck on 25/08/2015.
 */
public class KwetterClient implements Runnable{
    private Socket socket = null;
    private Thread thread = null;
    private ObjectOutputStream streamOut = null;
    private KwetterClientThread client = null;

    public KwetterClient(String serverName, int serverPort) {
        System.out.println("Bezig met verbinden...");
        try {
            socket = new Socket(serverName, serverPort);
            System.out.println("Verbonden: " + socket);
            start();
        } catch (UnknownHostException uhe) {
            System.out.println("Host onbekend: " + uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println("Onverwachte fout: " + ioe.getMessage());
        }
    }

    /**
     * Writes een bericht object in de outputstream
     *
     * @param bericht
     * @throws IOException
     */

    public void verzendBericht(Bericht bericht) throws IOException {
        try {
            streamOut.writeObject(bericht);
            streamOut.flush();

        } catch (IOException e) {
            System.out.println("Fout: " + e.getMessage());
            throw new IOException(e.toString());
        }
    }


    public void run() {
        boolean test = true;
        while (test) {
            try {

                Bericht x = new Bericht(1, "hello wim", new Date());
                // ByteArrayOutputStream bo = new ByteArrayOutputStream();
                //ObjectOutputStream so = new ObjectOutputStream(bo);
                //so.flush();
                streamOut.writeObject(x);
                streamOut.flush();
                test = false;
            } catch (IOException ioe) {
                System.out.println("Fout: " + ioe.getMessage());
                stop();
            }
        }
    }


    /**
     * Handelt het object af dat ontvangen is.
     * @param object
     */
    public void handle(Verzendbaar object) {

        if (object instanceof Bericht) {
            Bericht bericht = (Bericht) object;
        } else if (object instanceof Gebruiker) {
            Gebruiker gebruiker = (Gebruiker) object;
        }
    }

    public void start() throws IOException {

        streamOut = new ObjectOutputStream(socket.getOutputStream());
        if (thread == null) {
            client = new KwetterClientThread(this, socket);
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() {
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
        try {

            if (streamOut != null) streamOut.close();
            if (socket != null) socket.close();
        } catch (IOException ioe) {
            System.out.println("Fout tijdens sluiten...");
        }
        client.close();
        client.interrupt();
    }

    public static void main(String args[]) {
        KwetterClient client = null;
        if (args.length != 2) {
            System.out.println("Verkeerde argumenten, verwacht: java ChatClient host poort");
        } else {
            client = new KwetterClient(args[0], Integer.parseInt(args[1]));
        }
    }
}

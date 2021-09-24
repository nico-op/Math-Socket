
package canva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author josea
 */
public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader input;

    public void startConnection(String ipAddress, Integer port) throws IOException {
        clientSocket = new Socket("127.0.0.1", 8080); //Conecta al cliente con el servidor en la máquina local a traves del puerto 8080
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Client connected! ...");
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = input.readLine();
        return resp;
    }

    public void stopConnection() throws IOException {
        input.close();
        out.close();
        clientSocket.close();
        System.out.println("Client disconnected! ...");
    }
    
     public static void main(String[] args) {
        Client client = new Client();
        try {
            client.startConnection("127.0.0.1",8080);
            String msg = client.sendMessage("Hello from client!");
            System.out.println("Server: "+msg);
            //client.stopConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

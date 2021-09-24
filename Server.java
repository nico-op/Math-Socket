
package canva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author josea
 */
public class Server {
    static Server singletonn;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader input;


    public void listen(int port) throws IOException {
        serverSocket = new ServerSocket(port); //Este constructor recibe el puerto por el que escucha el serverSocket
        System.out.println("Servidor escuchando");
        clientSocket = serverSocket.accept(); //Servidor acepta las conexiones que le llegan

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String msg = input.readLine();

        // Logic
        System.out.println("Client: "+msg);
        out.println("Hello from server");
        new Login().setVisible(true);
        //Login login = new Login();
        //login.setVisible(true);
        
    }

    public void stop() throws IOException {
        input.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
    
    public void valConexion(){
        new Login().setVisible(true);
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.listen(8080);
            //new Login().setVisible(true);
        } catch (IOException e) {
        }
    }

    void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

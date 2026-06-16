import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class ServerWithSocket {
    static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            System.out.println("Server started");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread request = new Thread(() ->  processRequest(clientSocket));
                request.start();
            }
        }
    }

    private static void processRequest(Socket clientSocket) {
        try (clientSocket) {
            InputStream clientIS = clientSocket.getInputStream();
            StringBuilder stringBuilder = new StringBuilder();
            int data;
            do {
                data = clientIS.read();
                stringBuilder.append((char) data);
            } while (clientIS.available() > 0);
            String request = stringBuilder.toString();
            System.out.println(request);

            Thread.sleep(250);

            String json = Files.readString(Path.of("items.json"));

            OutputStream clientOS = clientSocket.getOutputStream();
            PrintStream clientOut = new PrintStream(clientOS);

            clientOut.println("HTTP/1.1 200 OK");
            clientOut.println("Content-type: application/json; charset=UTF-8");
            clientOut.println();
            clientOut.println(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

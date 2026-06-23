
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientWithSocket {
    static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 8000)){
            OutputStream clientOS = socket.getOutputStream();
            PrintStream clientOut = new PrintStream(clientOS);
            clientOut.println("GET /items.json HTTP/1.1");
            clientOut.println();

            InputStream clientIS = socket.getInputStream();
            Scanner scanner = new Scanner(clientIS);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        }
    }
}

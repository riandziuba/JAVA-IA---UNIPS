import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerWithSocket {
    static void main(String[] args) throws Exception {
        try (ExecutorService executorService = Executors.newFixedThreadPool(50)) {
            try (ServerSocket serverSocket = new ServerSocket(8000)) {
                System.out.println("Server started");
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    executorService.execute(() -> processRequest(clientSocket));
                }
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
            String firstLine = request.substring(0, request.indexOf("\r\n"));
            String body = request.substring(request.indexOf("\r\n\r\n"));
            System.out.println(request);

            System.out.println("body " + body);
            Thread.sleep(250);
            String response = "";
            Path path = Path.of("items.json");
            String json = Files.readString(path);
            switch (firstLine) {
                case "GET /items HTTP/1.1":
                    response = json;
                break;
                case "GET /items/total HTTP/1.1":
                    int total = new JSONArray(json).length();
                    response = new JSONObject().put("total", total).toString();
                    break;
                case "POST /items HTTP/1.1":
                    try {
                        JSONObject itemJson = new JSONObject(body);
                        JSONArray items = new JSONArray(json);
                        items.put(itemJson);
                        Files.writeString(path, items.toString());
                        response = new JSONObject().put("message", "Item cadastrado com sucesso").toString();
                    } catch (Exception e) {
                        response = new JSONObject().put("message", "Item não cadastrado, tente novamente mais tarde").toString();
                    }
            }


            OutputStream clientOS = clientSocket.getOutputStream();
            PrintStream clientOut = new PrintStream(clientOS);

            clientOut.println("HTTP/1.1 200 OK");
            clientOut.println("Content-type: application/json; charset=UTF-8");
            clientOut.println();
            clientOut.println(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

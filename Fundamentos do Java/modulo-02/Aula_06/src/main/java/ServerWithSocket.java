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
    private static final Object ITEMS_FILE_LOCK = new Object();
    /*
    * Thread Safe Equivalents
    * ArrayList = CopyOnWriteArratList
    * HashSet/LinkedHashSet = CopyOnWriteArratSet
    * TreeSet = ConcurrentSkipListSet
    * HashMap/LikendHashMap = ConcurrentHashMap
    * TreeMap = ConcurrentSkipListMap
    * */
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
            String[] requestChunks = request.split("\\r\\n\\r\\n");
            String requestLineAndHeaders = requestChunks[0];
            String[] requestLineAndHeadersChunk = requestLineAndHeaders.split("\\r\\n");
            String requestLine = requestLineAndHeadersChunk[0];
            String[] requestLineChunks = requestLine.split(" ");

            String method = requestLineChunks[0];
            String requestURI = requestLineChunks[1];
            System.out.println(request);

            Thread.sleep(250);
            String response = "";
            Path path = Path.of("items.json");
            String requestLineReturn = "";
            switch (method) {
                case "GET":
                    switch (requestURI) {
                        case "/items":
                            synchronized (ITEMS_FILE_LOCK) {
                                response = Files.readString(path);
                            }
                            break;
                        case "/items/total":
                            int total;
                            synchronized (ITEMS_FILE_LOCK) {
                                total = new JSONArray(Files.readString(path)).length();
                            }
                            response = new JSONObject().put("total", total).toString();
                            break;
                    }
                    requestLineReturn = "HTTP/1.1 200 OK";
                break;
                case "POST":
                    if (requestChunks.length == 1) {
                        requestLineReturn = "HTTP/1.1 400 Bad Request";
                        response = new JSONObject().put("message", "Item não cadastrado, não há dados").toString();
                        break;
                    }
                    String body = requestChunks[1];
                    switch (requestURI) {
                        case "/items":
                            try {
                                JSONObject itemJson = new JSONObject(body);
                                synchronized (ITEMS_FILE_LOCK) {
                                    JSONArray items = new JSONArray(Files.readString(path));
                                    items.put(itemJson);
                                    Files.writeString(path, items.toString());
                                }
                                response = new JSONObject().put("message", "Item cadastrado com sucesso").toString();
                                requestLineReturn = "HTTP/1.1 201 Created";
                            } catch (Exception e) {
                                response = new JSONObject().put("message", "Item não cadastrado, tente novamente mais tarde. Erro:" + e.getMessage()).toString();
                                requestLineReturn = "HTTP/1.1 500 Internal Server Error";
                            }
                            break;
                    }
            }


            OutputStream clientOS = clientSocket.getOutputStream();
            PrintStream clientOut = new PrintStream(clientOS);
            if (response.isEmpty()) {
                clientOut.println("HTTP/1.1 404 Not Found");
                clientOut.println();
            } else {
                clientOut.println(requestLineReturn);
                clientOut.println("Content-type: application/json; charset=UTF-8");
                clientOut.println();
                clientOut.println(response);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

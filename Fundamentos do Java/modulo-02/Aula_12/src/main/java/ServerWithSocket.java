import db.IDatabase;
import db.InMemoryDatabase;
import db.MySqlDatabase;
import entities.MenuItem;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerWithSocket {
    private static final IDatabase DATABASE = new MySqlDatabase();
    static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        try {
            try (ServerSocket serverSocket = new ServerSocket(8000)) {
                System.out.println("Server started");
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    executorService.execute(() -> processRequest(clientSocket));
                }
            }
        } finally {
            executorService.shutdown();
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
            String body = requestChunks.length == 1 ? "{}" : requestChunks[1];
            String requestLineAndHeaders = requestChunks[0];
            String[] requestLineAndHeadersChunk = requestLineAndHeaders.split("\\r\\n");
            String requestLine = requestLineAndHeadersChunk[0];
            String[] requestLineChunks = requestLine.split(" ");

            String method = requestLineChunks[0];
            String requestURI = requestLineChunks[1];
            System.out.println(request);

            Thread.sleep(250);
            String response = "";
            String requestLineReturn = "";
            JSONObject jsonBody = new JSONObject(body);
            switch (method) {
                case "GET":
                    switch (requestURI) {
                        case "/items":
                            response = DATABASE.menuItemsToJson().toString();
                            break;
                        case "/items/total":
                            int total = DATABASE.totalOfItems();
                            response = new JSONObject().put("total", total).toString();
                            break;
                        default:
                            if (requestURI.startsWith("/items/")) {
                                Long id = Long.parseLong(requestURI.split("/items/")[1]);
                                Optional<MenuItem> itemOptional = DATABASE.getById(id);
                                if (itemOptional.isEmpty()) {
                                    requestLineReturn = "HTTP/1.1 404 Not Found";
                                    response = new JSONObject().put("message", "Item id (" + id + ") não existe").toString();
                                    break;
                                }
                                response = itemOptional.get().toJson().toString();
                            }

                    }
                    if (requestLineReturn.isEmpty()) requestLineReturn = "HTTP/1.1 200 OK";
                break;
                case "POST":
                    if (requestChunks.length == 1) {
                        requestLineReturn = "HTTP/1.1 400 Bad Request";
                        response = new JSONObject().put("message", "Item não cadastrado, não há dados").toString();
                        break;
                    }
                    switch (requestURI) {
                        case "/items":
                            try {
                                JSONObject itemJson = jsonBody;
                                DATABASE.add(MenuItem.fromJson(itemJson));
                                response = new JSONObject().put("message", "Item cadastrado com sucesso").toString();
                                requestLineReturn = "HTTP/1.1 201 Created";
                            } catch (Exception e) {
                                response = new JSONObject().put("message", "Item não cadastrado, tente novamente mais tarde. Erro:" + e.getMessage()).toString();
                                requestLineReturn = "HTTP/1.1 500 Internal Server Error";
                            }
                            break;
                    }
                case "DELETE":
                    if (requestURI.startsWith("/items/")) {
                            Long id = Long.parseLong(requestURI.split("/items/")[1]);
                            boolean deleted = DATABASE.removeById(id);
                            if (deleted) {
                                response = "";
                                requestLineReturn = "HTTP/1.1 204 No Content";
                                break;
                            }
                            Optional<MenuItem> itemOptional = DATABASE.getById(id);
                            if (itemOptional.isEmpty()) {
                                response = "";
                                requestLineReturn = "HTTP/1.1 404 Not Found";
                                break;
                            }
                            response = new JSONObject("message", "O item não pode ser deletado").toString();
                            requestLineReturn = "HTTP/1.1 400 Bad Request";
                        }
                    break;
                case "PATCH":
                    if (requestURI.startsWith("/items/")) {
                        Long id = Long.parseLong(requestURI.split("/items/")[1]);
                        BigDecimal newPrice = jsonBody.getBigDecimal("newPrice");
                        boolean updated = DATABASE.updatePriceById(id, newPrice);
                        if (updated) {
                            response = "";
                            requestLineReturn = "HTTP/1.1 204 No Content";
                            break;
                        }
                        Optional<MenuItem> itemOptional = DATABASE.getById(id);
                        if (itemOptional.isEmpty()) {
                            response = "";
                            requestLineReturn = "HTTP/1.1 404 Not Found";
                            break;
                        }
                        response = new JSONObject("message", "O preço do item não pode ser alterado").toString();
                        requestLineReturn = "HTTP/1.1 400 Bad Request";
                    }


            }


            OutputStream clientOS = clientSocket.getOutputStream();
            PrintStream clientOut = new PrintStream(clientOS);
            if (response.isEmpty() && requestLineReturn.isEmpty()) {
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

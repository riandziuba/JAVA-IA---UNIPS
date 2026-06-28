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
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerWithSocket {
    private static final Logger logger = Logger.getLogger(ServerWithSocket.class.getName());
    private static final IDatabase DATABASE = new MySqlDatabase();
    static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.of("pt", "BR"));
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        try {
            try (ServerSocket serverSocket = new ServerSocket(8000)) {
                logger.info("Server started");
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
            String httpVersion = requestLineChunks[2];
            logger.finest(request);
            logger.fine(() -> "Metodo: " + method);
            logger.fine(() -> "RequestURI: " + requestURI);
            logger.fine(() -> "Http version: " + httpVersion);
            Thread.sleep(250);
            String response = "";
            String requestLineReturn = "";
            JSONObject jsonBody = new JSONObject(body);
            OutputStream clientOS = clientSocket.getOutputStream();
            PrintStream clientOut = new PrintStream(clientOS);

            String contentType = "Content-type: application/json; charset=UTF-8";
            try {
                switch (method) {
                    case "GET":
                        switch (requestURI) {
                            case "/", "/en":
                                Locale locale = request.contains("/en") ? Locale.US : Locale.of("pt", "BR");
                                ResourceBundle messages = ResourceBundle.getBundle("messages", locale);
                                LocalDateTime now = LocalDateTime.now();
                                DateTimeFormatter formatterMonthYear = DateTimeFormatter.ofPattern("LLLL/yyyy").withLocale(locale);
                                DateTimeFormatter formatterDayMonthYear = DateTimeFormatter.ofPattern("d 'de' LLLL 'de' yyyy HH:mm").withLocale(locale);
                                NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
                                List<MenuItem> menuItems = DATABASE.getMenuItems();
                                StringBuilder htmlAllItems = new StringBuilder();
                                for (MenuItem menuItem : menuItems) {
                                    String htmlItemPrice;
                                    String categoryPath = "MenuCategory.MenuItem.";
                                    if (menuItem.priceWithDiscount() == null) {
                                        htmlItemPrice = "<strong>" + numberFormat.format(menuItem.price()) + "</strong>";
                                    } else {
                                        htmlItemPrice = "<mark>Em promoção</mark> <strong>" + numberFormat.format(menuItem.priceWithDiscount()) + "</strong> <s>" + numberFormat.format(menuItem.price()) + "</s>";
                                    }
                                    String htmlItem = """
                                            <article>
                                                    <kbd>%s</kbd>
                                                    <h3>%s</h3>
                                                    <p>%s</p>
                                                    %s
                                                </article>
                                            """.formatted(messages.getString(categoryPath.concat(menuItem.category().name())), menuItem.name(), menuItem.description(), htmlItemPrice);
                                    htmlAllItems.append(htmlItem);
                                }
                                contentType = "Content-type: text/html; charset=UTF-8";

                                String html = """
                                        <!DOCTYPE html>
                                        <html lang="en">
                                        <head>
                                            <meta charset="UTF-8">
                                            <title>Florinda Eats - Cardápio</title>
                                            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2.1.1/css/pico.min.css">
                                        </head>
                                        <body>
                                        
                                        <header class="container">
                                            <hgroup>
                                                <h1>Florinda Eats</h1>
                                                <p>O sabor da Vila direto pra você</p>
                                            </hgroup>
                                        </header>
                                        
                                        <main class="container">
                                            <h2>Cardápio</h2>
                                            %s
                                        </main>
                                       
                                            <footer class="container">
                                                <p><small><em>Preços de acordo com %s</em></small></p>
                                                <p><strong>Florinda Eats</strong> Todos os direitos reservados - %s</p>
                                            </footer>
                                            </body>
                                            </html>
                                        """.formatted(htmlAllItems.toString(), formatterDayMonthYear.format(now), formatterMonthYear.format(now));
                                    response = html;
                                break;

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
            } catch (Exception e) {
                clientOut.print("HTTP/1.1 500 Internal Server Error \r\n\r\n");
                clientOut.print(e.getMessage()+ "\r\n");
                logger.log(Level.SEVERE, e, () -> "Erro ao processar metodo " + method + " " + requestURI);
            }

            if (response.isEmpty() && requestLineReturn.isEmpty()) {
                clientOut.print("HTTP/1.1 404 Not Found\r\n\r\n");
            } else {
                clientOut.print(requestLineReturn + "\r\n");
                clientOut.print(contentType + "\r\n\r\n");
                clientOut.print(response + "\r\n");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

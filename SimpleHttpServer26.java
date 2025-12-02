import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
public class SimpleHttpServer26 {
    private static final int PORT = 8080;
    public static void main(String[] args) throws IOException {
        System.out.println("启动HTTP服务器...");
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0); 
        server.createContext("/", new RootHandler());
        server.createContext("/hello", new HelloHandler());
        server.createContext("/time", new TimeHandler());
        server.createContext("/info", new InfoHandler()); 
        server.start();
        System.out.println("服务器已启动: http://localhost:" + PORT);
        System.out.println("\n可用路由: /, /hello, /time, /info");
    }
    static class RootHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            if (!"GET".equals(exchange.getRequestMethod())) {
                sendError(exchange, 405, "方法不允许");
                return;
            }
            String html = createHtml("简单HTTP服务器", 
                "<h1>简单HTTP服务器</h1>" +
                "<p>运行在端口 " + PORT + "</p>" +
                "<h2>可用接口</h2>" +
                "<ul>" +
                "<li><a href='/hello'>/hello</a> - 问候</li>" +
                "<li><a href='/time'>/time</a> - 当前时间</li>" +
                "<li><a href='/info'>/info</a> - 服务器信息</li>" +
                "</ul>");
            sendResponse(exchange, html, "text/html");
        }
    }
    static class HelloHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            if (!"GET".equals(exchange.getRequestMethod())) {
                sendError(exchange, 405, "方法不允许");
                return;
            }
            Map<String, String> params = parseQuery(exchange.getRequestURI().getQuery());
            String name = params.getOrDefault("name", "访客");
            String html = createHtml("问候页面",
                "<h1>你好, " + name + "!</h1>" +
                "<p>当前时间: " + new Date() + "</p>" +
                "<a href='/'>返回首页</a>");
            sendResponse(exchange, html, "text/html");
        }
    }
    static class TimeHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            if (!"GET".equals(exchange.getRequestMethod())) {
                sendError(exchange, 405, "方法不允许");
                return;
            }
            String json = String.format(
                "{\"current_time\":\"%s\",\"timestamp\":%d}",
                new Date(), System.currentTimeMillis());
            sendResponse(exchange, json, "application/json");
        }
    }
    static class InfoHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            if (!"GET".equals(exchange.getRequestMethod())) {
                sendError(exchange, 405, "方法不允许");
                return;
            }
            Runtime rt = Runtime.getRuntime();
            String json = String.format(
                "{\"java_version\":\"%s\",\"memory\":{\"total\":%d,\"free\":%d}}",
                System.getProperty("java.version"), rt.totalMemory(), rt.freeMemory());
            sendResponse(exchange, json, "application/json");
        }
    }
    private static Map<String, String> parseQuery(String query) {
        Map<String, String> params = new HashMap<>();
        if (query != null) {
            for (String pair : query.split("&")) {
                String[] kv = pair.split("=");
                if (kv.length > 0) params.put(kv[0], kv.length > 1 ? kv[1] : "");
            }
        }
        return params;
    }
    private static String createHtml(String title, String content) {
        return "<!DOCTYPE html><html><head><title>" + title + 
               "</title><meta charset='UTF-8'><style>" +
               "body{font-family:Arial;margin:40px;}" +
               "a{color:#3498db;text-decoration:none;}" +
               "</style></head><body>" + content + "</body></html>";
    }
    private static void sendResponse(HttpExchange exchange, String content, String contentType) throws IOException {
        byte[] data = content.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", contentType + "; charset=utf-8");
        exchange.sendResponseHeaders(200, data.length);
        try (OutputStream os = exchange.getResponseBody()) { os.write(data); }
    }
    private static void sendError(HttpExchange exchange, int code, String message) throws IOException {
        String json = "{\"error\":\"" + message + "\",\"code\":" + code + "}";
        byte[] data = json.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        exchange.sendResponseHeaders(code, data.length);
        try (OutputStream os = exchange.getResponseBody()) { os.write(data); }
    }
}
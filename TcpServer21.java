import java.io.*;
import java.net.*;
public class TcpServer21 {
    private static final int PORT = 5000;
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("服务器已启动，监听端口 " + PORT + "，等待客户端连接...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("客户端已连接：" + clientSocket.getRemoteSocketAddress());
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("收到客户端: " + line);
                out.write("服务器回显: " + line);
                out.newLine();
                out.flush();
                if ("exit".equalsIgnoreCase(line.trim())) {
                    System.out.println("客户端请求退出，关闭连接。");
                    break;
                }
            }
            in.close();
            out.close();
            clientSocket.close();
            System.out.println("服务器已关闭。");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
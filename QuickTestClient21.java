import java.io.*;
import java.net.*;
import java.util.Scanner;
public class QuickTestClient21 {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8888;
        System.out.println("快速测试客户端");
        System.out.println("连接到: " + host + ":" + port);
        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(
                 new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("连接成功!");
            String welcome = in.readLine();
            if (welcome != null) {
                System.out.println("服务器: " + welcome);
            }
            System.out.print("\n输入消息 (或 'exit' 退出): ");
            while (scanner.hasNextLine()) {
                String message = scanner.nextLine();
                if ("exit".equalsIgnoreCase(message.trim())) {
                    out.println("exit");
                    break;
                }
                out.println(message);
                System.out.println("已发送: " + message);
                try {
                    socket.setSoTimeout(2000);
                    String response = in.readLine();
                    if (response != null) {
                        System.out.println("服务器回复: " + response);
                    }
                    socket.setSoTimeout(0);
                } catch (SocketTimeoutException e) {
                    System.out.println("等待回复超时");
                }
                System.out.print("\n输入消息 (或 'exit' 退出): ");
            }   
        } catch (ConnectException e) {
            System.err.println("连接失败: 请确保服务器正在运行");
        } catch (IOException e) {
            System.err.println("通信错误: " + e.getMessage());
        }
        System.out.println("客户端已关闭");
    }
}
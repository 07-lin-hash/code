import java.io.*;
import java.util.Scanner;
public class FileReadWriteSingleEnter11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入要保存到文件的内容：");
        String userInput = scanner.nextLine();
        if (userInput.trim().isEmpty()) {
            System.out.println("没有输入任何内容，程序结束。");
            scanner.close();
            return;
        }
        String fileName = "user_input.txt";
        try {
            writeToFile(fileName, userInput);
            System.out.println("\n内容已成功写入文件: " + fileName);
            System.out.println("\n从文件读取的内容");
            readFromFile(fileName);
        } catch (IOException e) {
            System.err.println("发生错误: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    private static void writeToFile(String fileName, String content) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            
            bufferedWriter.write(content);
        }
    }
    private static void readFromFile(String fileName) throws IOException {
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
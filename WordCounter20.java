import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.*;
public class WordCounter20 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        System.out.print("请输入文本文件路径: ");
        String filePath = scanner.nextLine().trim();
        if (filePath.startsWith("\"") && filePath.endsWith("\"")) {
            filePath = filePath.substring(1, filePath.length() - 1);
        }
        Path path = Paths.get(filePath).normalize();
        filePath = path.toString();
        try {
            int wordCount = countWords(filePath);
            System.out.println("文件中的单词数量: " + wordCount);
        } catch (IOException e) {
            System.err.println("读取文件时出错: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    public static int countWords(String filePath) throws IOException {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Pattern wordPattern = Pattern.compile("[a-zA-Z0-9'-]+");
            
            while ((line = reader.readLine()) != null) {
                Matcher matcher = wordPattern.matcher(line);
                while (matcher.find()) {
                    count++;
                }
            }
        }
        return count;
    }
}
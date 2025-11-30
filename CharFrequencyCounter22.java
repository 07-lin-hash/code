import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class CharFrequencyCounter22 {
    public static Map<Character, Integer> countCharFrequency(String text) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : text.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        return freqMap;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, "UTF-8");
        System.out.println("请输入要统计的字符串：");
        String input = scanner.nextLine();
        Map<Character, Integer> result = countCharFrequency(input);
        System.out.println("\n字符出现次数统计结果：");
        result.entrySet()
              .stream()
              .sorted(Map.Entry.comparingByKey())
              .forEach(entry ->
                      System.out.printf("字符 '%s'（%d）出现 %d 次%n",
                              entry.getKey(),
                              (int) entry.getKey(),
                              entry.getValue()));
        scanner.close();
    }
}
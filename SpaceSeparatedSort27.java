import java.util.Scanner;
import java.util.TreeSet;
public class SpaceSeparatedSort27 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入整数：");
        String line = scanner.nextLine().trim();
        scanner.close();
        TreeSet<Integer> set = new TreeSet<>();
        if (!line.isEmpty()) {
            String[] tokens = line.split("\\s+");
            for (String token : tokens) {
                try {
                    int num = Integer.parseInt(token);
                    set.add(num);
                } catch (NumberFormatException e) {
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Integer i : set) {
            sb.append(i).append(' ');
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        System.out.println("升序排列：");
        System.out.println(sb.toString());
    }
}
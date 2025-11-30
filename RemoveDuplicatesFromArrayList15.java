import java.util.*;
public class RemoveDuplicatesFromArrayList15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>();
        System.out.println("请输入一行整数，数字之间用空格或逗号分隔（回车结束）：");
        String line = scanner.nextLine().trim();
        if (line.isEmpty()) {
            System.out.println("未输入任何数字，程序结束。");
            scanner.close();
            return;
        }
        String[] parts = line.split("[,\\s]+");
        for (String p : parts) {
            try {
                int value = Integer.parseInt(p);
                numbers.add(value);
            } catch (NumberFormatException e) {
                System.out.println("“" + p + "” 不是合法整数，已自动忽略。");
            }
        }
        Set<Integer> set = new LinkedHashSet<>(numbers);
        List<Integer> deduped = new ArrayList<>(set);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < deduped.size(); i++) {
            sb.append(deduped.get(i));
            if (i < deduped.size() - 1) {
                sb.append(",");
            }
        }
        System.out.println(sb.toString());
        scanner.close();
    }
}
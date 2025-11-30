import java.util.*;
public class FixedArrayOneEnter13 {
    private static void printReverse(int[] data, int count) {
        System.out.println("逆序输出结果：");
        for (int i = count - 1; i >= 0; i--) {
            System.out.print(data[i]);
            if (i > 0) System.out.print(", ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        final int MAX_SIZE = 100;
        int[] numbers = new int[MAX_SIZE];
        int filled = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("用空格间隔整数，输入完后直接回车：");
        String line = sc.nextLine().trim();
        if (!line.isEmpty()) {
            String[] parts = line.split("\\s+");
            for (String p : parts) {
                if (filled >= MAX_SIZE) break;
                try {
                    numbers[filled++] = Integer.parseInt(p);
                } catch (NumberFormatException e) {
                    System.out.println("“" + p + "” 不是合法整数，已自动跳过。");
                }
            }
        }
        for (int i = 0; i < filled; i++) {
            System.out.print(numbers[i]);
            if (i < filled - 1) System.out.print(", ");
        }
        System.out.println();
        printReverse(numbers, filled);
        sc.close();
    }
}
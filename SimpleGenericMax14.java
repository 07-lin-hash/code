import java.util.Scanner;
public class SimpleGenericMax14 {
    public static <T extends Comparable<T>> T max(T[] arr) {
        if (arr == null || arr.length == 0) return null;
        T m = arr[0];
        for (T item : arr) {
            if (item.compareTo(m) > 0) m = item;
        }
        return m;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请选择数组类型:");
        System.out.println("1. 整数数组");
        System.out.println("2. 字符串数组");
        System.out.print("请输入选择 (1 或 2): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            System.out.print("请输入整数数组 (用空格分隔): ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            Integer[] nums = new Integer[parts.length];
            for (int i = 0; i < parts.length; i++) {
                nums[i] = Integer.parseInt(parts[i]);
            }
            System.out.println("数组中的最大值: " + max(nums));
        } else if (choice == 2) {
            System.out.print("请输入字符串数组: ");
            String input = scanner.nextLine();
            String[] words = input.split(" ");
            System.out.println("数组中的最大值: " + max(words));
        } else {
            System.out.println("无效选择!");
        }    
        scanner.close();
    }
}
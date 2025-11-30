import java.util.*;
public class MergeTwoSortedArraysInteractive16 {
    public static int[] merge(int[] a, int[] b) {
        if (a == null) a = new int[0];
        if (b == null) b = new int[0];

        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }
        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];
        return result;
    }
    private static int[] parseLineToIntArray(String line) {
        if (line == null || line.trim().isEmpty()) {
            return new int[0];
        }
        String[] parts = line.trim().split("[,\\s]+");
        List<Integer> list = new ArrayList<>();
        for (String p : parts) {
            try {
                list.add(Integer.parseInt(p));
            } catch (NumberFormatException e) {
                System.out.println("“" + p + "” 不是合法整数，已自动忽略。");
            }
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("第一个有序整数数组(用空格或逗号分隔)：");
        String line1 = scanner.nextLine();
        int[] arr1 = parseLineToIntArray(line1);
        System.out.println("第二个有序整数数组：");
        String line2 = scanner.nextLine();
        int[] arr2 = parseLineToIntArray(line2);
        int[] merged = merge(arr1, arr2);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < merged.length; i++) {
            sb.append(merged[i]);
            if (i < merged.length - 1) sb.append(",");
        }
        System.out.println("合并后的有序数组：");
        System.out.println(sb.toString());
        scanner.close();
    }
}
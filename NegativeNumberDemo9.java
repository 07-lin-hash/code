import java.util.Scanner;
class NegativeNumberException extends Exception {
    public NegativeNumberException() {
        super("不允许输入负数！");
    }
    public NegativeNumberException(String message) {
        super(message);
    }
}
public class NegativeNumberDemo9 {
    private static int readNonNegativeInt(Scanner sc) throws NegativeNumberException {
        System.out.print("请输入一个非负整数：");
        String line = sc.nextLine().trim();
        if (line.isEmpty()) {
            throw new IllegalArgumentException("输入不能为空！");
        }
        int value;
        try {
            value = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("输入不是合法的整数！", e);
        }
        if (value < 0) {
            throw new NegativeNumberException("检测到负数：" + value);
        }
        return value;
    }
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                try {
                    int number = readNonNegativeInt(scanner);
                    System.out.println("你输入的非负数是：" + number);
                } catch (NegativeNumberException e) {
                    System.out.println("错误" + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("错误" + e.getMessage());
                }
                System.out.print("是否继续输入？(y/n)：");
                String again = scanner.nextLine().trim().toLowerCase();
                if (!again.equals("y")) {
                    System.out.println("程序结束。");
                    break;
                }
            }
        }
    }
}
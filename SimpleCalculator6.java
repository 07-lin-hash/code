import java.util.Scanner;
public class SimpleCalculator6 {
    public static int add(int a, int b) {
        return a + b;
    }
    public static int subtract(int a, int b) {
        return a - b;
    }
    public static int multiply(int a, int b) {
        return a * b;
    }
    public static double divide(int a, int b) {
        if (b == 0) {
            System.out.println("除数不能为 0，返回 NaN");
            return Double.NaN;
        }
        return (double) a / b;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("第一个整数: ");
        int num1 = scanner.nextInt();
        System.out.print("运算符 (+ - * /): ");
        String op = scanner.next();
        System.out.print("第二个整数: ");
        int num2 = scanner.nextInt();
        double result;
        switch (op) {
            case "+":
                result = add(num1, num2);
                break;
            case "-":
                result = subtract(num1, num2);
                break;
            case "*":
                result = multiply(num1, num2);
                break;
            case "/":
                result = divide(num1, num2);
                break;
            default:
                System.out.println("不支持的运算符！");
                scanner.close();
                return;
        }
        System.out.printf("计算结果： %d %s %d = %.2f%n", num1, op, num2, result);
        scanner.close();
    }
}
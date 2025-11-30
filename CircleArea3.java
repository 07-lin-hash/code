import java.util.Scanner;

public class CircleArea3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("r：");
        double radius = scanner.nextDouble();
        double pi = 3.14;
        double area = pi * radius * radius;
        System.out.printf("%.2f %.4f", radius, area);
        scanner.close();
    }
}
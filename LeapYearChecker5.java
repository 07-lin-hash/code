import java.util.Scanner;
public class LeapYearChecker5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("年份：");
        int year = scanner.nextInt();
        boolean isLeap = false;
        if (year % 400 == 0) {
            isLeap = true;
        } else if (year % 100 == 0) {
            isLeap = false;
        } else if (year % 4 == 0) {
            isLeap = true;
        } else {
            isLeap = false;
        }
        if (isLeap) {
            System.out.println(year + " 是闰年。");
        } else {
            System.out.println(year + " 不是闰年。");
        }
        scanner.close();
    }
}
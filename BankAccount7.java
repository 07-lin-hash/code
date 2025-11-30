import java.util.Scanner;
public class BankAccount7 {
    private double balance;
    public BankAccount7(double init) {
        if (init < 0) {
            System.out.println("初始余额不能为负，已设为 0。");
            this.balance = 0;
        } else {
            this.balance = init;
        }
    }
    public void deposit(double amt) {
        if (amt <= 0) {
            System.out.println("存款金额必须大于 0。");
            return;
        }
        balance += amt;
        System.out.println("已存入 " + amt + " 元。");
    }
    public void withdraw(double amt) {
        if (amt <= 0) {
            System.out.println("取款金额必须大于 0。");
            return;
        }
        if (amt > balance) {
            System.out.println("余额不足，无法取出 " + amt + " 元。");
            return;
        }
        balance -= amt;
        System.out.println("已取出 " + amt + " 元。");
    }
    public void checkBalance() {
        System.out.printf("当前余额为：%.2f 元。\n", balance);
    }
    private static double readAmount(Scanner sc) {
        try {
            return Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("金额输入错误，请输入数字。");
            return -1;
        }
    }
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            BankAccount7 acc = new BankAccount7(0);
            while (true) {
                System.out.print("\n1. 存款  2. 取款  3. 查看余额  请选择 (1-3)：");
                String choice = sc.nextLine().trim();
                switch (choice) {
                    case "1":
                        System.out.print("请输入存款金额：");
                        double dep = readAmount(sc);
                        if (dep > 0) {
                            acc.deposit(dep);
                        }
                        break;
                    case "2":
                        System.out.print("请输入取款金额：");
                        double wit = readAmount(sc);
                        if (wit > 0) {
                            acc.withdraw(wit);
                        }
                        break;
                    case "3":
                        acc.checkBalance();
                        break;
                    default:
                        System.out.println("无效选项，请重新输入 1-3。");
                }
            }
        }
    }
}
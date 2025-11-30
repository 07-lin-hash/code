import java.io.*;
import java.util.*;
class BankAccount implements Serializable {
    private final String accountId;
    private final String username;
    private double balance;
    public BankAccount(String accountId, String username, double balance) {
        this.accountId = accountId;
        this.username = username;
        this.balance = balance;
    }
    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " 存款: +" + amount + ", 余额: " + balance);
        }
    }
    public synchronized boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " 取款: -" + amount + ", 余额: " + balance);
            return true;
        }
        System.out.println(Thread.currentThread().getName() + " 取款失败, 余额: " + balance);
        return false;
    }
    public synchronized double getBalance() { return balance; }
    public String getAccountId() { return accountId; }
    public String getUsername() { return username; }
    @Override
    public String toString() {
        return accountId + " | " + username + " | " + balance;
    }
}
public class SimpleBankSystem30 {
    private Map<String, BankAccount> accounts = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private static final String FILE_NAME = "accounts.dat";
    public static void main(String[] args) {
        new SimpleBankSystem30().run();
    }
    public void run() {
        loadAccounts();
        while (true) {
            System.out.println("\n1.开户 2.存款 3.取款 4.查询 5.列表 6.退出");
            System.out.print("选择: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: createAccount(); break;
                case 2: deposit(); break;
                case 3: withdraw(); break;
                case 4: checkBalance(); break;
                case 5: showAccounts(); break;
                case 6: saveAccounts(); return;
                default: System.out.println("无效选择");
            }
        }
    }
    private void createAccount() {
        System.out.print("账户ID: ");
        String id = scanner.nextLine();
        if (accounts.containsKey(id)) {
            System.out.println("账户已存在");
            return;
        }
        System.out.print("用户名: ");
        String name = scanner.nextLine();
        System.out.print("初始余额: ");
        double balance = scanner.nextDouble();
        accounts.put(id, new BankAccount(id, name, balance));
        saveAccounts();
    }
    private void deposit() {
        BankAccount account = getAccount();
        if (account != null) {
            System.out.print("存款金额: ");
            double amount = scanner.nextDouble();
            new Thread(() -> account.deposit(amount), "存款线程").start();
            saveAccounts();
        }
    }
    private void withdraw() {
        BankAccount account = getAccount();
        if (account != null) {
            System.out.print("取款金额: ");
            double amount = scanner.nextDouble();
            new Thread(() -> account.withdraw(amount), "取款线程").start();
            saveAccounts();
        }
    }
    private void checkBalance() {
        BankAccount account = getAccount();
        if (account != null) {
            System.out.println("余额: " + account.getBalance());
        }
    }
    private void showAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("无账户");
            return;
        }
        accounts.values().forEach(System.out::println);
    }
    private BankAccount getAccount() {
        System.out.print("账户ID: ");
        String id = scanner.nextLine();
        BankAccount account = accounts.get(id);
        if (account == null) System.out.println("账户不存在");
        return account;
    }
    @SuppressWarnings("unchecked")
    private void loadAccounts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            accounts = (Map<String, BankAccount>) ois.readObject();
            System.out.println("加载账户数据");
        } catch (Exception e) {
            System.out.println("无保存数据");
        }
    }
    private void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("保存失败");
        }
    }
}
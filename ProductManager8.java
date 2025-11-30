import java.util.*;
class Product {
    final String name;
    double price;
    int stock;
    Product(String n, double p, int s) { name = n; price = p; stock = s; }
    void add(int amount, double newPrice) {
        stock += amount;
        price = newPrice;
    }
    @Override
    public String toString() {
        return String.format("%s  —  单价: %.2f 元  —  库存: %d 件",
                name, price, stock);
    }
}
public class ProductManager8 {
    private final Map<String, Product> catalog = new HashMap<>();
    private void addProduct(Scanner sc) {
        System.out.print("商品名称：");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) { System.out.println("名称不能为空。"); return; }
        double price = readDouble(sc, "单价（元）：");
        if (price < 0) return;
        int amount = readInt(sc, "数量：");
        if (amount < 0) return;
        catalog.compute(name, (k, v) -> {
            if (v == null) return new Product(k, price, amount);
            v.add(amount, price);
            System.out.println("商品已存在，累计库存并更新单价。");
            return v;
        });
        if (!catalog.containsKey(name) || catalog.get(name).stock == amount) {
            System.out.println("商品已新增。");
        }
    }
    private void listAll() {
        if (catalog.isEmpty()) {
            System.out.println("当前暂无商品。");
            return;
        }
        System.out.println("=== 商品列表 ===");
        catalog.values().forEach(System.out::println);
    }
    private static double readDouble(Scanner sc, String prompt) {
        System.out.print(prompt);
        try { return Double.parseDouble(sc.nextLine().trim()); }
        catch (NumberFormatException e) {
            System.out.println("输入错误，请输入数字。");
            return -1;
        }
    }
    private static int readInt(Scanner sc, String prompt) {
        System.out.print(prompt);
        try { return Integer.parseInt(sc.nextLine().trim()); }
        catch (NumberFormatException e) {
            System.out.println("输入错误，请输入整数。");
            return -1;
        }
    }
    public void run() {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n请选择操作：");
                System.out.println("1. 添加商品");
                System.out.println("2. 查看全部商品");
                System.out.println("3. 退出系统");
                System.out.print("选项 (1-3)：");
                String opt = sc.nextLine().trim();
                if ("1".equals(opt)) addProduct(sc);
                else if ("2".equals(opt)) listAll();
                else if ("3".equals(opt)) { System.out.println("系统已退出，感谢使用！"); break; }
                else System.out.println("无效选项，请重新输入 1-3。");
            }
        }
    }
    public static void main(String[] args) {
        new ProductManager8().run();
    }
}
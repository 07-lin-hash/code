import java.io.*;
import java.util.*;
class Book implements Serializable {
    private String isbn;
    private String title;
    private String author;
    private int stock;
    public Book(String isbn, String title, String author, int stock) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.stock = stock;
    }
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    @Override
    public String toString() {
        return String.format("ISBN: %-15s 书名: %-20s 作者: %-15s 库存: %d", 
                           isbn, title, author, stock);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return Objects.equals(isbn, book.isbn);
    }
    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
class BookManager {
    private List<Book> books;
    private static final String FILE_NAME = "books.dat";
    private Scanner scanner;
    public BookManager() {
        books = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadBooksFromFile();
    }
    public void addBook() {
        System.out.println("\n=== 添加图书 ===");
        System.out.print("请输入ISBN: ");
        String isbn = scanner.nextLine();
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                System.out.println("该ISBN已存在！");
                return;
            }
        }
        System.out.print("请输入书名: ");
        String title = scanner.nextLine();
        System.out.print("请输入作者: ");
        String author = scanner.nextLine();
        System.out.print("请输入库存数量: ");
        int stock = scanner.nextInt();
        scanner.nextLine();
        Book newBook = new Book(isbn, title, author, stock);
        books.add(newBook);
        System.out.println("图书添加成功！");
    }
    public void showAllBooks() {
        System.out.println("\n=== 所有图书 ===");
        if (books.isEmpty()) {
            System.out.println("暂无图书信息！");
            return;
        }
        books.sort(Comparator.comparing(Book::getIsbn));
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
    }
    public void borrowBook() {
        System.out.println("\n=== 借书 ===");
        System.out.print("请输入ISBN: ");
        String isbn = scanner.nextLine();
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (book.getStock() > 0) {
                    book.setStock(book.getStock() - 1);
                    System.out.println("借书成功！");
                    System.out.println("剩余库存: " + book.getStock());
                } else {
                    System.out.println("库存不足，无法借书！");
                }
                return;
            }
        }
        System.out.println("未找到该ISBN对应的图书！");
    }
    public void returnBook() {
        System.out.println("\n=== 还书 ===");
        System.out.print("请输入ISBN: ");
        String isbn = scanner.nextLine();
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                book.setStock(book.getStock() + 1);
                System.out.println("还书成功！");
                System.out.println("当前库存: " + book.getStock());
                return;
            }
        }
        System.out.println("未找到该ISBN对应的图书！");
    }
    public void saveBooksToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(books);
            System.out.println("图书信息已保存到文件！");
        } catch (IOException e) {
            System.out.println("保存文件失败: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    private void loadBooksFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            books = (List<Book>) ois.readObject();
            System.out.println("图书信息已从文件加载！");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("加载文件失败: " + e.getMessage());
        }
    }
    public void showMenu() {
        System.out.println("\n=== 图书管理系统 ===");
        System.out.println("1. 添加图书");
        System.out.println("2. 查看所有图书");
        System.out.println("3. 借书");
        System.out.println("4. 还书");
        System.out.println("5. 保存到文件");
        System.out.println("6. 退出系统");
        System.out.print("请选择操作 (1-6): ");
    }
    public void run() {
        while (true) {
            showMenu();
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("请输入有效的数字！");
                scanner.nextLine();
                continue;
            }
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    showAllBooks();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    saveBooksToFile();
                    break;
                case 6:
                    System.out.println("感谢使用图书管理系统！");
                    return;
                default:
                    System.out.println("无效选择，请重新输入！");
            }
        }
    }
}
public class LibraryManagementSystem29 {
    public static void main(String[] args) {
        BookManager manager = new BookManager();
        manager.run();
    }
}
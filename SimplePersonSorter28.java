import java.util.*;
class Person {
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    @Override
    public String toString() {
        return String.format("姓名: %-8s 年龄: %2d", name, age);
    }
}
public class SimplePersonSorter28 {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("人员信息输入与年龄排序");
        int count = getPersonCount();
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            System.out.println("\n输入第 " + (i + 1) + " 个人员信息:");
            Person person = createPersonFromInput();
            people.add(person);
        }
        System.out.println("\n输入的所有人员:");
        printPeople(people);
        sortByAgeAscending(people);
        System.out.println("\n按年龄升序排序结果:");
        printPeople(people);
        scanner.close();
    }
    private static int getPersonCount() {
        while (true) {
            try {
                System.out.print("请输入要输入的人员数量: ");
                int count = scanner.nextInt();
                scanner.nextLine();
                if (count > 0) {
                    return count;
                } else {
                    System.out.println("人数必须大于0！");
                }
            } catch (InputMismatchException e) {
                System.out.println("输入无效，请输入整数！");
                scanner.nextLine();
            }
        }
    }
    private static Person createPersonFromInput() {
        String name = getNameFromInput();
        int age = getAgeFromInput();
        return new Person(name, age);
    }
    private static String getNameFromInput() {
        while (true) {
            System.out.print("请输入姓名: ");
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                return name;
            } else {
                System.out.println("姓名不能为空！");
            }
        }
    }
    private static int getAgeFromInput() {
        while (true) {
            try {
                System.out.print("请输入年龄: ");
                int age = scanner.nextInt();
                scanner.nextLine();
                if (age >= 0 && age <= 150) {
                    return age;
                } else {
                    System.out.println("年龄必须在0-150之间！");
                }
            } catch (InputMismatchException e) {
                System.out.println("输入无效，请输入整数！");
                scanner.nextLine();
            }
        }
    }
    private static void sortByAgeAscending(List<Person> people) {
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return Integer.compare(p1.getAge(), p2.getAge());
            }
        });
    }
    private static void printPeople(List<Person> people) {
        for (Person person : people) {
            System.out.println(person);
        }
    }
}
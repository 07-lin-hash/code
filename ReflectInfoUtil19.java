import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
public class ReflectInfoUtil19 {
    public static void printClassInfo(Class<?> clazz) {
        System.out.println("=== 类信息 ===");
        System.out.println("类全名: " + clazz.getName());
        System.out.println("修饰符: " + Modifier.toString(clazz.getModifiers()));
        System.out.println();
        System.out.println("=== 字段（属性） ===");
        Field[] fields = clazz.getDeclaredFields();
        if (fields.length == 0) {
            System.out.println("（无字段）");
        } else {
            for (Field field : fields) {
                field.setAccessible(true);
                System.out.printf("%s %s %s%n",
                        Modifier.toString(field.getModifiers()),
                        field.getType().getSimpleName(),
                        field.getName());
            }
        }
        System.out.println();
        System.out.println("=== 方法 ===");
        Method[] methods = clazz.getDeclaredMethods();
        if (methods.length == 0) {
            System.out.println("（无方法）");
        } else {
            for (Method method : methods) {
                method.setAccessible(true);
                String params = Arrays.stream(method.getParameterTypes())
                                      .map(Class::getSimpleName)
                                      .reduce((a, b) -> a + ", " + b)
                                      .orElse("");
                System.out.printf("%s %s %s(%s)%n",
                        Modifier.toString(method.getModifiers()),
                        method.getReturnType().getSimpleName(),
                        method.getName(),
                        params);
            }
        }
        System.out.println();
        System.out.println("=== 构造函数 ===");
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        if (constructors.length == 0) {
            System.out.println("（无构造函数）");
        } else {
            for (Constructor<?> ctor : constructors) {
                ctor.setAccessible(true);
                String params = Arrays.stream(ctor.getParameterTypes())
                                      .map(Class::getSimpleName)
                                      .reduce((a, b) -> a + ", " + b)
                                      .orElse("");
                System.out.printf("%s %s(%s)%n",
                        Modifier.toString(ctor.getModifiers()),
                        clazz.getSimpleName(),
                        params);
            }
        }
        System.out.println("=== 结束 ===");
    }
    public static void main(String[] args) {
        Class<?> targetClass = java.util.ArrayList.class;
        printClassInfo(targetClass);
    }
}
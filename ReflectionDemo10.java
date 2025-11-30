import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
public class ReflectionDemo10 {
    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("BankAccount");
            Constructor<?> ctor = clazz.getConstructor(double.class);
            Object accountObj = ctor.newInstance(1000.0);
            Method depositMethod = clazz.getMethod("deposit", double.class);
            depositMethod.invoke(accountObj, 250.0);
            Method getBalanceMethod = clazz.getMethod("getBalance");
            Object balanceObj = getBalanceMethod.invoke(accountObj);
            double balance = (Double) balanceObj;
            System.out.println("存款后余额 = " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
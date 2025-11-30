import java.io.*;
import java.util.Properties;
import java.util.Set;
public class PropertiesDemo24 {
    public static Properties loadProperties(String filePath) throws IOException {
        Properties props = new Properties();
        try (InputStream input = new FileInputStream(filePath)) {
            props.load(input);
        }

        return props;
    }
    public static String getString(Properties props, String key) {
        return props.getProperty(key);
    }
    public static int getInt(Properties props, String key, int defaultValue) {
        String val = props.getProperty(key);
        if (val != null) {
            try {
                return Integer.parseInt(val.trim());
            } catch (NumberFormatException ignored) {
            }
        }
        return defaultValue;
    }
    public static boolean getBoolean(Properties props, String key, boolean defaultValue) {
        String val = props.getProperty(key);
        if (val != null) {
            return Boolean.parseBoolean(val.trim());
        }
        return defaultValue;
    }
    public static void main(String[] args) {
        String configPath = "app.properties";
        try {
            Properties props = loadProperties(configPath);
            System.out.println("=== 配置文件内容 ===");
            String appName = getString(props, "app.name");
            String appVersion = getString(props, "app.version");
            int appPort = getInt(props, "app.port", 80);
            System.out.printf("应用名称: %s%n版本: %s%n端口: %d%n%n", appName, appVersion, appPort);
            String dbUrl = getString(props, "db.url");
            String dbUser = getString(props, "db.username");
            String dbPwd = getString(props, "db.password");
            System.out.printf("数据库 URL: %s%n用户名: %s%n密码: %s%n%n", dbUrl, dbUser, dbPwd);
            boolean cacheEnabled = getBoolean(props, "feature.enableCache", false);
            int maxThreadPool = getInt(props, "max.thread.pool", 10);
            System.out.printf("缓存是否开启: %b%n最大线程池大小: %d%n%n", cacheEnabled, maxThreadPool);
            System.out.println("=== 完整键值对 ===");
            Set<String> keys = props.stringPropertyNames();
            for (String key : keys) {
                System.out.println(key + " = " + props.getProperty(key));
            }
        } catch (FileNotFoundException e) {
            System.err.println("配置文件未找到，请检查路径：" + configPath);
        } catch (IOException e) {
            System.err.println("读取配置文件时出现错误：" + e.getMessage());
        }
    }
}
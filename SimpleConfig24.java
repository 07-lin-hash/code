import java.io.*;
import java.util.Properties;
public class SimpleConfig24 {
    private Properties props = new Properties();
    public SimpleConfig24(String filePath) {
        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filePath), "UTF-8")) {
            props.load(reader);
            System.out.println("配置文件加载: " + filePath);
        } catch (FileNotFoundException e) {
            System.out.println("未找到配置文件，使用默认值");
            createSampleConfig();
        } catch (IOException e) {
            System.out.println("读取配置文件错误: " + e.getMessage());
        }
    }
    private void createSampleConfig() {
        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream("app.properties"), "UTF-8")) {
            Properties sample = new Properties();
            sample.setProperty("app.name", "我的应用");
            sample.setProperty("app.version", "1.0.0");
            sample.setProperty("server.host", "localhost");
            sample.setProperty("server.port", "8080");
            sample.setProperty("debug.mode", "true");
            sample.setProperty("log.level", "INFO");
            sample.store(writer, "自动生成的示例配置文件");
            System.out.println("📝 已创建示例配置文件");
            loadFromFile();
        } catch (IOException e) {
            System.out.println("创建示例文件失败: " + e.getMessage());
        }
    }
    private void loadFromFile() {
        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream("app.properties"), "UTF-8")) {
            props.load(reader);
        } catch (IOException e) {
        }
    }
    public String get(String key, String def) {
        return props.getProperty(key, def);
    }
    public int getInt(String key, int def) {
        try {
            return Integer.parseInt(props.getProperty(key, String.valueOf(def)));
        } catch (Exception e) {
            return def;
        }
    }
    public void showAll() {
        props.forEach((k, v) -> System.out.println(k + " = " + v));
    }
    public static void main(String[] args) {
        SimpleConfig24 config = new SimpleConfig24("app.properties");
        String name = config.get("app.name", "默认应用");
        int port = config.getInt("server.port", 8080);  
        System.out.println("应用: " + name);
        System.out.println("端口: " + port);
        config.showAll();
    }
}
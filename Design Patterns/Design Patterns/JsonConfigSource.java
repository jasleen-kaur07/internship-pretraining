import java.util.HashMap;
import java.util.Map;

public class JsonConfigSource implements ConfigSource {

    private String filePath;

    public JsonConfigSource(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Map<String, String> readConfig() {

        Map<String, String> config = new HashMap<>();

        // Dummy Data
        config.put("db.url", "jdbc:mysql://localhost:3306/shop");
        config.put("db.user", "root");

        System.out.println("Reading JSON Config from " + filePath);

        return config;
    }
}
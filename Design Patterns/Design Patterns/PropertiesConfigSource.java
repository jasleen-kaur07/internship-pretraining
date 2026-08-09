import java.util.HashMap;
import java.util.Map;

public class PropertiesConfigSource implements ConfigSource {

    private String filePath;

    public PropertiesConfigSource(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Map<String, String> readConfig() {

        Map<String, String> config = new HashMap<>();

        config.put("cache.enabled", "true");
        config.put("timeout", "5000");

        System.out.println("Reading Properties Config from " + filePath);

        return config;
    }
}
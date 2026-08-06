import java.util.HashMap;
import java.util.Map;

public class YamlConfigSource implements ConfigSource {

    private String filePath;

    public YamlConfigSource(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Map<String, String> readConfig() {

        Map<String, String> config = new HashMap<>();

        config.put("server.port", "8080");
        config.put("spring.profile", "dev");

        System.out.println("Reading YAML Config from " + filePath);

        return config;
    }
}
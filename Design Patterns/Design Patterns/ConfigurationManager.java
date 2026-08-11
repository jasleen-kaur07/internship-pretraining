import java.util.HashMap;
import java.util.Map;

public class ConfigurationManager {

    private static volatile ConfigurationManager instance;

    private Map<String, String> configurations;

    private ConfigSource currentSource;

    private ConfigurationManager() {
        configurations = new HashMap<>();
    }

    public static ConfigurationManager getInstance() {

        if (instance == null) {

            synchronized (ConfigurationManager.class) {

                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }

        return instance;
    }

    public void setConfig(String key, String value) {
        configurations.put(key, value);
    }

    public String getConfig(String key) {
        return configurations.get(key);
    }

    public void loadFromSource(ConfigSource source) {

        currentSource = source;

        configurations.clear();

        configurations.putAll(source.readConfig());
    }

    // Bonus
    public void refreshConfig() {

        if (currentSource != null) {

            configurations.clear();

            configurations.putAll(currentSource.readConfig());

            System.out.println("Configuration Refreshed");
        }
    }

}
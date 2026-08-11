import java.util.Map;

public interface ConfigSource {
    Map<String, String> readConfig();
}
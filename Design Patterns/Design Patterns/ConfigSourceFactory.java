public class ConfigSourceFactory {

    public static ConfigSource createSource(String type, String filePath) {

        if (type.equalsIgnoreCase("json")) {
            return new JsonConfigSource(filePath);
        }

        else if (type.equalsIgnoreCase("yaml")) {
            return new YamlConfigSource(filePath);
        }

        else if (type.equalsIgnoreCase("properties")) {
            return new PropertiesConfigSource(filePath);
        }

        throw new IllegalArgumentException("Invalid Config Type");
    }
}
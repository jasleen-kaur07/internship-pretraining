public class Main {

    public static void main(String[] args) {

        ConfigurationManager manager =
                ConfigurationManager.getInstance();

        ConfigSource source =
                ConfigSourceFactory.createSource(
                        "json",
                        "config.json");

        manager.loadFromSource(source);

        System.out.println(manager.getConfig("db.url"));

        manager.setConfig("company", "Blibli");

        System.out.println(manager.getConfig("company"));

        manager.refreshConfig();

    }
}
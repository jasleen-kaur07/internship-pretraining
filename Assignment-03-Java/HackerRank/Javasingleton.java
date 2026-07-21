class Singleton {

    public String str;

    private static Singleton instance = null;

    private Singleton() {
    }

    public static Singleton getSingleInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

public class Javasingleton {
    public static void main(String[] args) {

        Singleton singleton = Singleton.getSingleInstance();

        singleton.str = "hello world";

        System.out.println("Hello I am a singleton! Let me say " + singleton.str + " to you");
    }
}
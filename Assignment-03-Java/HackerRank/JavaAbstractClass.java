import java.util.Scanner;

abstract class Book {

    String title;

    abstract void setTitle(String s);

    String getTitle() {
        return title;
    }
}

class MyBook extends Book {

    @Override
    void setTitle(String s) {
        title = s;
    }
}

public class JavaAbstractClass {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter book title: ");
        String title = sc.nextLine();

        MyBook newNovel = new MyBook();
        newNovel.setTitle(title);

        System.out.println("The title is: " + newNovel.getTitle());

        sc.close();
    }
}
public class Librarian extends Person {

    public Librarian(String name, int id) {
        super(name, id);
    }

    @Override
    public void displayDetails() {
        System.out.println("Librarian Name: " + name);
        System.out.println("Librarian ID: " + id);
    }
}
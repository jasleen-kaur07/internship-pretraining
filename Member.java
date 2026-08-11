public class Member extends Person {

    private int booksIssued;

    public Member(String name, int id) {
        super(name, id);
        this.booksIssued = 0;
    }

    public int getBooksIssued() {
        return booksIssued;
    }

    public void setBooksIssued(int booksIssued) {
        this.booksIssued = booksIssued;
    }

    @Override
    public void displayDetails() {
        System.out.println("Member Name" + name);
        System.out.println("Member ID" + id);
        System.out.println("Books Issued" + booksIssued);
    }
}
import java.util.ArrayList;

public class Library {

    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println(book.getTitle() + " added successfully.");
    }

    public void issueBook(String title, Member member) {
        try {
            for (Book book : books) {

                if (book.getTitle().equalsIgnoreCase(title)) {

                    if (!book.isAvailable()) {
                        throw new BookNotAvailableException("Sorry! This book is already issued.");
                    }

                    if (member.getBooksIssued() >= 3) {
                        throw new BookLimitExceededException("You cannot issue more than 3 books.");
                    }

                    book.setAvailable(false);
                    member.setBooksIssued(member.getBooksIssued() + 1);

                    System.out.println("Book issued successfully.");
                    return;
                }
            }

            System.out.println("Book not found.");

        } catch (BookNotAvailableException | BookLimitExceededException e) {
            System.out.println(e.getMessage());
        }
    }

    public void returnBook(String title, Member member) {

        for (Book book : books) {

            if (book.getTitle().equalsIgnoreCase(title)) {

                if (!book.isAvailable()) {

                    book.setAvailable(true);
                    member.setBooksIssued(member.getBooksIssued() - 1);

                    System.out.println("Book returned successfully.");
                    return;
                }

                System.out.println("This book was not issued.");
                return;
            }
        }

        System.out.println("Book not found.");
    }

    public void searchBook(String keyword) {

        boolean found = false;

        for (Book book : books) {

            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {

                book.displayBook();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching books found.");
        }
    }

    public void displayBooks() {

        System.out.println("\n-Library Books-");

        for (Book book : books) {
            book.displayBook();
        }
    }
}
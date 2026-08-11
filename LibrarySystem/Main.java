public class Main {

    public static void main(String[] args) {

        Library library = new Library();

        library.addBook(new Book("Core Java", "Herbert Schildt"));
        library.addBook(new Book("Harry Potter", "J.K. Rowling"));
        library.addBook(new Book("Python Programming", "Mark Lutz"));
        library.addBook(new Book("Data Structures", "Narasimha Karumanchi"));

        System.out.println();

        Member member = new Member("Jasleen", 101);
        Librarian librarian = new Librarian("Rahul", 201);

        System.out.println( "-Person Details-");
        member.displayDetails();
        System.out.println();
        librarian.displayDetails();

        System.out.println();

        System.out.println("-Issue Books-");
        library.issueBook("Core Java", member);
        library.issueBook("Harry Potter", member);
        library.issueBook("Python Programming", member);

        //BookLimitExceededException
        library.issueBook("Data Structures", member);

        System.out.println();

        // BookNotAvailableException
        library.issueBook("Core Java", member);

        System.out.println();

        System.out.println("-Search Result-");
        library.searchBook("java");

        System.out.println();

        // Return Book
        System.out.println("-Return Book-");
        library.returnBook("Core Java", member);

        System.out.println();


        System.out.println("-Library Books-");
        library.displayBooks();
    }
}
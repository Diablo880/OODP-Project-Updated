import java.util.ArrayList;

/**
 * Represents the library, managing books and members.
 */
public class Library {
    // Attributes
    private ArrayList<Book> books;      // List of books in the library
    private ArrayList<Member> members;  // List of registered members

    /**
     * Default constructor initializing the ArrayLists.
     */
    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    /**
     * Adds a book to the library.
     * @param book Book to add
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Removes a book from the library by ISBN.
     * @param isbn ISBN of the book to remove
     */
    public void removeBook(String isbn) {
        books.removeIf(b -> b.getIsbn().equals(isbn));
    }

    /**
     * Finds and returns a book by ISBN.
     * @param isbn ISBN to search for
     * @return Book if found, null otherwise
     */
    public Book findBook(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) return b;
        }
        return null;
    }

    /**
     * Registers a new member to the library.
     * @param member Member to register
     */
    public void registerMember(Member member) {
        members.add(member);
    }

    /**
     * Removes a member from the library by their ID.
     * @param memberID ID of the member to remove
     */
    public void removeMember(int memberID) {
        members.removeIf(m -> m.getMemberID() == memberID);
    }

    /**
     * Facilitates borrowing a book by a member if conditions are met.
     * @param memberID Member's ID
     * @param isbn     ISBN of the book to borrow
     * @return true if borrowing is successful, false otherwise
     */
    public boolean borrowBook(int memberID, String isbn) {
        Member m = getMember(memberID);
        Book b = findBook(isbn);
        if (m != null && b != null && m.addBook(b)) {
            books.remove(b);
            return true;
        }
        return false;
    }

    /**
     * Handles returning a book, calculates late fees, and removes the book from the member's list.
     * @param memberID    Member's ID
     * @param isbn        ISBN of the book to return
     * @param daysOverdue Number of overdue days
     * @return Late fee amount
     */
    public double returnBook(int memberID, String isbn, int daysOverdue) {
        Member m = getMember(memberID);
        if (m != null) {
            Book bookToReturn = null;
            for (Book b : m.getBorrowedBooks()) {
                if (b.getIsbn().equals(isbn)) {
                    bookToReturn = b;
                    break;
                }
            }
            if (bookToReturn != null) {
                m.removeBook(bookToReturn);
                addBook(bookToReturn);
                return m.calculateLateFee(bookToReturn, daysOverdue);
            }
        }
        return 0.0;
    }

    /**
     * Helper method to get a member by their ID.
     * @param memberID Member's ID
     * @return Member if found, null otherwise
     */
    private Member getMember(int memberID) {
        for (Member m : members) {
            if (m.getMemberID() == memberID) return m;
        }
        return null;
    }

    /**
     * Returns a list of all books currently in the library.
     * @return List of books
     */
    public ArrayList<Book> listBooks() {
        return new ArrayList<>(books);
    }

    /**
     * Returns a list of all registered members.
     * @return List of members
     */
    public ArrayList<Member> getMembers() {
        return new ArrayList<>(members);
    }

    /**
     * Finds and returns a member by their ID.
     * @param memberId Member's ID
     * @return Member if found, null otherwise
     */
    public Member findMember(int memberId) {
        for (Member m : members) {
            if (m.getMemberID() == memberId) {
                return m;
            }
        }
        return null;
    }
}
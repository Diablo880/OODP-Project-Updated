import java.util.ArrayList;

/**
 * Represents a member of the library.
 */
public class Member {
    // Attributes
    private int memberID;                  // Unique ID for the member
    private String name;                   // Name of the member
    private String membershipType;         // Membership type: Regular, Premium, or Guest
    private ArrayList<Book> borrowedBooks; // List of books currently borrowed

    /**
     * Default constructor initializing attributes to default values.
     */
    public Member() {
        this.memberID = 0;
        this.name = "";
        this.membershipType = "Guest";
        this.borrowedBooks = new ArrayList<>();
    }

    /**
     * Parameterized constructor to set all attributes.
     * @param memberID        Member's unique ID
     * @param name            Member's name
     * @param membershipType  Membership type (Regular, Premium, Guest)
     */
    public Member(int memberID, String name, String membershipType) {
        this.memberID = memberID;
        this.name = name;
        this.membershipType = membershipType;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getters and setters for each attribute

    public int getMemberID() { return memberID; }
    public void setMemberID(int memberID) { this.memberID = memberID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMembershipType() { return membershipType; }
    public void setMembershipType(String membershipType) { this.membershipType = membershipType; }

    public ArrayList<Book> getBorrowedBooks() { return borrowedBooks; }

    /**
     * Adds a book to the member's borrowedBooks list.
     * Guest members can only borrow 1 book at a time.
     * @param book Book to borrow
     * @return true if book was added, false otherwise
     */
    public boolean addBook(Book book) {
        if (membershipType.equalsIgnoreCase("Guest") && borrowedBooks.size() >= 1) {
            System.out.println("Guest members can only borrow 1 book at a time.");
            return false;
        }
        borrowedBooks.add(book);
        return true;
    }

    /**
     * Removes a book from the member's borrowedBooks list.
     * @param book Book to remove
     */
    public void removeBook(Book book) {
        borrowedBooks.remove(book);
    }

    /**
     * Calculates the late fee for an overdue book.
     * @param book        The overdue book
     * @param daysOverdue Number of days overdue
     * @return Late fee amount
     */
    public double calculateLateFee(Book book, int daysOverdue) {
        return book.getPrice() * 0.01 * daysOverdue;
    }

    /**
     * Returns a string representation of the member details.
     */
    @Override
    public String toString() {
        return "Member ID: " + memberID + ", Name: " + name + ", Membership: " + membershipType +
               ", Borrowed Books: " + borrowedBooks.size();
    }
}
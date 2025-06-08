import java.time.LocalDate;

/**
 * Represents a borrowing transaction in the library.
 */
public class Transaction {
    // Attributes
    private int transactionID;      // Unique ID for the transaction
    private Member member;          // Member involved in the transaction
    private Book book;              // Book involved in the transaction
    private LocalDate borrowDate;   // Date the book was borrowed
    private LocalDate returnDate;   // Date the book was returned (null if not yet returned)

    /**
     * Parameterized constructor initializing all attributes.
     * @param transactionID Unique transaction ID
     * @param member        Member who borrowed the book
     * @param book          Book being borrowed
     * @param borrowDate    Date of borrowing
     */
    public Transaction(int transactionID, Member member, Book book, LocalDate borrowDate) {
        this.transactionID = transactionID;
        this.member = member;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = null;
    }

    // Getters and setters

    public int getTransactionID() { return transactionID; }

    public Member getMember() { return member; }

    public Book getBook() { return book; }

    public LocalDate getReturnDate() { return returnDate; }

    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    /**
     * Calculates the due date for the borrowed book based on the member's type.
     * Premium: 4 weeks, Regular: 2 weeks, Guest: 1 week.
     * @return Due date as a LocalDate
     */
    public LocalDate calculateDueDate() {
        return switch (member.getMembershipType().toLowerCase()) {
            case "premium" -> borrowDate.plusWeeks(4);
            case "regular" -> borrowDate.plusWeeks(2);
            case "guest" -> borrowDate.plusWeeks(1);
            default -> borrowDate.plusWeeks(1);
        };
    }

    /**
     * Returns a string representation of the transaction details.
     */
    @Override
    public String toString() {
        return "Transaction ID: " + transactionID + ", Member: " + member.getName() +
               ", Book: " + book.getTitle() + ", Borrowed On: " + borrowDate +
               ", Due: " + calculateDueDate();
    }
}

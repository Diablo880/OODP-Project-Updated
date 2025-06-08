import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Main class for the Library Management System.
 * Handles user interaction and program flow.
 */
public class LibraryDriver {
    public static void main(String[] args) {
        // Welcome message and developer info
        System.out.println("****************************************");
        System.out.println("WELCOME TO LIBRARY MANAGEMENT SYSTEM,");
        System.out.println();
        System.out.println("Developed by:Paban Paudel(K250111)");
        System.out.println("Student ID: YourStudentID");
        System.out.println("Group Members: Bidhan Gurung(K241105), Pardip Pandey(K241102), Ashika Jaishi(K250110), Bijaya Lamichhane(K250109)");
        System.out.println("OODP101 Object Oriented Design and Programming");
        System.out.println("Assignment 2: Library Management System");
        System.out.println("Date & Time: " +
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("****************************************");

        // Initialize library and scanner
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        int nextMemberId = 1;

        // Add initial books to the library
        Book b1 = new Book("111", "Java Basics", "John Doe", "Programming", 50.0);
        Book b2 = new Book("112", "OOP Design", "Jane Smith", "Computer Science", 60.0);
        Book b3 = new Book("113", "Data Structures", "Alan Turing", "Computer Science", 55.0);
        Book b4 = new Book("114", "Algorithms", "Grace Hopper", "Computer Science", 65.0);
        Book b5 = new Book("115", "Database Systems", "Edgar Codd", "Databases", 70.0);
        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);
        library.addBook(b4);
        library.addBook(b5);

        // Main program loop
        while (true) {
            System.out.println("\nChoose an action: register / borrow / return / exit");
            String action = scanner.next();

            if (action.equalsIgnoreCase("register")) {
                // Register a new member
                System.out.print("Enter your name: ");
                String name = scanner.next();
                System.out.print("Enter membership type (Regular/Premium/Guest): ");
                String type = scanner.next();
                Member newMember = new Member(nextMemberId++, name, type);
                library.registerMember(newMember);
                System.out.println("Registered! Your Member ID is: " + newMember.getMemberID());
            } else if (action.equalsIgnoreCase("borrow")) {
                // Borrow a book
                System.out.print("Enter your Member ID: ");
                int memberId = scanner.nextInt();
                System.out.println("Available books:");
                for (Book book : library.listBooks()) {
                    System.out.println(book);
                }
                System.out.print("Enter ISBN to borrow: ");
                String isbn = scanner.next();
                if (library.borrowBook(memberId, isbn)) {
                    System.out.println("Book borrowed successfully.");
                } else {
                    System.out.println("Borrowing failed.");
                }
            } else if (action.equalsIgnoreCase("return")) {
                // Return a book
                System.out.print("Enter your Member ID: ");
                int memberId = scanner.nextInt();
                System.out.print("Enter ISBN to return: ");
                String isbn = scanner.next();
                Member member = library.findMember(memberId); // Find the member
                if (member == null) {
                    System.out.println("Member not found.");
                } else {
                    boolean hasBook = false;
                    for (Book book : member.getBorrowedBooks()) {
                        if (book.getIsbn().equals(isbn)) {
                            hasBook = true;
                            break;
                        }
                    }
                    if (!hasBook) {
                        System.out.println("You haven't borrowed this book.");
                    } else {
                        System.out.print("Enter overdue days: ");
                        int days = scanner.nextInt();
                        double fee = library.returnBook(memberId, isbn, days);
                        System.out.println("Book returned. Late fee: $" + fee);
                    }
                }
            } else if (action.equalsIgnoreCase("exit")) {
                // Exit the program
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }

        scanner.close();

        // Display which members have borrowed which books
        System.out.println("\n--- Members and Their Borrowed Books ---");
        for (Member member : library.getMembers()) {
            System.out.println(member.getName() + " (ID: " + member.getMemberID() + "):");
            if (member.getBorrowedBooks().isEmpty()) {
                System.out.println("  No books borrowed.");
            } else {
                for (Book book : member.getBorrowedBooks()) {
                    System.out.println("  " + book);
                }
            }
        }
        System.out.println("****************************************");
        System.out.println("Thank you for using the Library Management System!");
        System.out.println("****************************************");
    }
}
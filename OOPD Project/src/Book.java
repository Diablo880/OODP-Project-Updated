/**
 * Represents a book in the library.
 */
public class Book {
    // Attributes
    private String isbn;      // Unique identifier for the book
    private String title;     // Title of the book
    private String author;    // Author of the book
    private String category;  // Category/genre of the book
    private double price;     // Price of the book

    /**
     * Default constructor initializing attributes to default values.
     */
    public Book() {
        this.isbn = "";
        this.title = "";
        this.author = "";
        this.category = "";
        this.price = 0.0;
    }

    /**
     * Parameterized constructor to set all attributes.
     * @param isbn      ISBN of the book
     * @param title     Title of the book
     * @param author    Author of the book
     * @param category  Category/genre of the book
     * @param price     Price of the book
     */
    public Book(String isbn, String title, String author, String category, double price) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
    }

    // Getters and setters for each attribute

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    /**
     * Returns a string representation of the book details.
     */
    @Override
    public String toString() {
        return "ISBN: " + isbn + ", Title: " + title + ", Author: " + author +
               ", Category: " + category + ", Price: $" + price;
    }
}

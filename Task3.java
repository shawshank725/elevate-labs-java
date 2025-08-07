import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task3 {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Clean Code", "Robert C. Martin"));
        books.add(new Book(2, "Effective Java", "Joshua Bloch"));

        Library library = new Library(books);

        User user = new User(101, "Alice");

        library.issueBook(1, user);
        library.issueBook(3, user);
        library.returnBook(1, user);
        library.returnBook(2, user);
    }
}

class Library {
    private List<Book> books;
    private List<BorrowedBooks> borrowedBooks;

    public Library() {
        this.books = new ArrayList<>();
        this.borrowedBooks = new ArrayList<>();
    }

    public Library(List<Book> books) {
        this.books = books;
        this.borrowedBooks = new ArrayList<>();
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void issueBook(int bookId, User user) {
        Book bookToIssue = null;
        for (Book b : books) {
            if (b.getBookId() == bookId) {
                bookToIssue = b;
                break;
            }
        }
        if (bookToIssue == null) {
            System.out.println("Book ID " + bookId + " not found in library.");
            return;
        }
        for (BorrowedBooks bb : borrowedBooks) {
            if (bb.getBorrowedBookId() == bookId && !bb.isBookReturned()) {
                System.out.println("Book ID " + bookId + " is already issued.");
                return;
            }
        }
        BorrowedBooks record = new BorrowedBooks(bookId, user.getUserId(), LocalDateTime.now());
        borrowedBooks.add(record);
        user.getBooksBorrowed().add(bookToIssue);
        System.out.println("Book ID " + bookId + " issued to " + user.getUsername());
    }

    public void returnBook(int bookId, User user) {
        BorrowedBooks record = null;
        for (BorrowedBooks bb : borrowedBooks) {
            if (bb.getBorrowedBookId() == bookId && bb.getIssuerId() == user.getUserId() && !bb.isBookReturned()) {
                record = bb;
                break;
            }
        }
        if (record == null) {
            System.out.println("No borrowed record found for Book ID " + bookId + " and User " + user.getUsername());
            return;
        }
        record.setReturnDate(LocalDateTime.now());
        record.setBookReturned(true);
        user.getBooksBorrowed().removeIf(b -> b.getBookId() == bookId);
        System.out.println("Book ID " + bookId + " returned by " + user.getUsername());
    }
}

class BorrowedBooks {
    private int borrowedBookId;
    private int issuerId;
    private LocalDateTime issueDate;
    private LocalDateTime returnDate;
    private boolean bookReturned;

    public BorrowedBooks(int borrowedBookId, int issuerId, LocalDateTime issueDate) {
        this.borrowedBookId = borrowedBookId;
        this.issuerId = issuerId;
        this.issueDate = issueDate;
        this.bookReturned = false;
    }

    public int getBorrowedBookId() {
        return borrowedBookId;
    }

    public int getIssuerId() {
        return issuerId;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public boolean isBookReturned() {
        return bookReturned;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public void setBookReturned(boolean bookReturned) {
        this.bookReturned = bookReturned;
    }
}

class Book {
    private int bookId;
    private String bookName;
    private String bookAuthor;

    public Book() {}

    public Book(int bookId, String bookName, String bookAuthor) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
}

class User {
    private int userId;
    private String username;
    private List<Book> booksBorrowed;
    private double fine;

    public User() {}

    public User(int userId, String username) {
        this.userId = userId;
        this.username = username;
        this.fine = 0;
        this.booksBorrowed = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public List<Book> getBooksBorrowed() {
        return booksBorrowed;
    }

    public double getFine() {
        return fine;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBooksBorrowed(List<Book> booksBorrowed) {
        this.booksBorrowed = booksBorrowed;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }
}

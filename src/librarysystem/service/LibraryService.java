package librarysystem.service;

import librarysystem.exception.BookNotFoundException;
import librarysystem.exception.DuplicateBookIDException;
import librarysystem.exception.EmptyException;
import librarysystem.model.Book;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {

    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) throws DuplicateBookIDException {
        for (Book b : books) {
            if (b.getBookId().equals(book.getBookId())) {
                throw new DuplicateBookIDException("Book ID already exists: " + book.getBookId());
            }
        }
        books.add(book);
    }

    public void removeBook(String bookId) throws BookNotFoundException {
        Book book = findById(bookId);
        books.remove(book);
    }

    public Book searchBook(String bookId) throws BookNotFoundException {
        return findById(bookId);
    }

    public void borrowBook(String bookId, String studentName, String phone) throws BookNotFoundException {
        Book book = findById(bookId);
        if (book.isBorrowed()) {
            throw new BookNotFoundException("Book is already borrowed: " + bookId);
        }
        book.borrowBook(studentName, phone);
    }

    public void returnBook(String bookId) throws BookNotFoundException {
        Book book = findById(bookId);
        if (!book.isBorrowed()) {
            throw new BookNotFoundException("Book is not currently borrowed: " + bookId);
        }
        book.returnBook(bookId);
    }

    public List<Book> listAllBooks() throws EmptyException {
        if (books.isEmpty()) {
            throw new EmptyException("No books in the library.");
        }
        return new ArrayList<>(books);
    }

    public List<Book> listBorrowedBooks() throws EmptyException {
        List<Book> borrowed = new ArrayList<>();
        for (Book b : books) {
            if (b.isBorrowed()) borrowed.add(b);
        }
        if (borrowed.isEmpty()) {
            throw new EmptyException("No books are currently borrowed.");
        }
        return borrowed;
    }

    public List<Book> listAvailableBooks() throws EmptyException {
        List<Book> available = new ArrayList<>();
        for (Book b : books) {
            if (!b.isBorrowed()) available.add(b);
        }
        if (available.isEmpty()) {
            throw new EmptyException("No books are currently available.");
        }
        return available;
    }

    private Book findById(String bookId) throws BookNotFoundException {
        for (Book b : books) {
            if (b.getBookId().equals(bookId)) return b;
        }
        throw new BookNotFoundException("Book not found with ID: " + bookId);
    }
}

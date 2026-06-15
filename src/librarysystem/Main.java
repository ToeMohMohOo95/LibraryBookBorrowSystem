package librarysystem;

import librarysystem.exception.BookNotFoundException;
import librarysystem.exception.DuplicateBookIDException;
import librarysystem.exception.EmptyException;
import librarysystem.model.Book;
import librarysystem.service.LibraryService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final LibraryService service = new LibraryService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Library Book Borrow System ===");
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> addBook();
                case "2" -> removeBook();
                case "3" -> borrowBook();
                case "4" -> returnBook();
                case "5" -> searchBook();
                case "6" -> listAllBooks();
                case "7" -> listBorrowedBooks();
                case "8" -> listAvailableBooks();
                case "0" -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. Borrow Book");
        System.out.println("4. Return Book");
        System.out.println("5. Search Book by ID");
        System.out.println("6. List All Books");
        System.out.println("7. List Borrowed Books");
        System.out.println("8. List Available Books");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private static void addBook() {
        System.out.print("Book ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Book Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Author Name: ");
        String author = scanner.nextLine().trim();
        System.out.print("Category: ");
        String category = scanner.nextLine().trim();
        try {
            service.addBook(new Book(id, name, author, category));
            System.out.println("Book added successfully.");
        } catch (DuplicateBookIDException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void removeBook() {
        System.out.print("Book ID to remove: ");
        String id = scanner.nextLine().trim();
        try {
            service.removeBook(id);
            System.out.println("Book removed successfully.");
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void borrowBook() {
        System.out.print("Book ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Your Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Your Phone Number: ");
        String phone = scanner.nextLine().trim();
        try {
            service.borrowBook(id, name, phone);
            System.out.println("Book borrowed successfully.");
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void returnBook() {
        System.out.print("Book ID to return: ");
        String id = scanner.nextLine().trim();
        try {
            service.returnBook(id);
            System.out.println("Book returned successfully.");
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchBook() {
        System.out.print("Book ID: ");
        String id = scanner.nextLine().trim();
        try {
            Book book = service.searchBook(id);
            printBook(book);
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listAllBooks() {
        try {
            List<Book> books = service.listAllBooks();
            System.out.println("\n--- All Books ---");
            books.forEach(Main::printBook);
        } catch (EmptyException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listBorrowedBooks() {
        try {
            List<Book> books = service.listBorrowedBooks();
            System.out.println("\n--- Borrowed Books ---");
            books.forEach(Main::printBook);
        } catch (EmptyException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listAvailableBooks() {
        try {
            List<Book> books = service.listAvailableBooks();
            System.out.println("\n--- Available Books ---");
            books.forEach(Main::printBook);
        } catch (EmptyException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printBook(Book b) {
        System.out.printf("  [%s] %s by %s (%s) | Borrowed: %s%s%n",
                b.getBookId(), b.getBookName(), b.getAuthorName(), b.getCategory(),
                b.isBorrowed() ? "Yes" : "No",
                b.isBorrowed() ? " | By: " + b.getBorrowedBy() + " (" + b.getBorrowerPhoneNumber() + ")" : "");
    }
}

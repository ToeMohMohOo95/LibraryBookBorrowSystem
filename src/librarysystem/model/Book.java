package librarysystem.model;

public class Book {
    private String bookId;
    private String bookName;
    private String authorName;
    private String category;
    private boolean borrowed;
    private String borrowedBy;
    private String borrowerPhoneNumber;


    public Book(String bookId, String bookName, String authorName, String category){
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.category = category;
        this.borrowed = false;
        this.borrowedBy = "";
        this.borrowerPhoneNumber = "";
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(String borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    public String getBorrowerPhoneNumber() {
        return borrowerPhoneNumber;
    }

    public void setBorrowerPhoneNumber(String borrowerPhoneNumber) {
        this.borrowerPhoneNumber = borrowerPhoneNumber;
    }

    public void borrowBook(String studentName, String studentPhoneNumber) {
        this.borrowed = true;
        this.borrowedBy = studentName;
        this.borrowerPhoneNumber = studentPhoneNumber;
    }

    public void returnBook(String bookId) {
        this.borrowed = false;
        this.borrowedBy = "";
        this.borrowerPhoneNumber = "";
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", category='" + category + '\'' +
                ", borrowed=" + borrowed +
                ", borrowedBy='" + borrowedBy + '\'' +
                ", borrowerPhoneNumber='" + borrowerPhoneNumber + '\'' +
                '}';
    }


}

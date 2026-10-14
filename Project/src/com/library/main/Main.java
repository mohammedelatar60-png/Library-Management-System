package com.library.main;

import com.library.model.Book;
import com.library.model.User;
import com.library.repository.LibraryRepository;
import com.library.service.BookService;
import com.library.service.BorrowService;
import com.library.service.UserService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryRepository repository = new LibraryRepository();
        BookService bookService = new BookService(repository);
        UserService userService = new UserService(repository);
        BorrowService borrowService = new BorrowService(repository);

        // --- إضافة كتب افتراضية كثيرة ومليانة ---
        bookService.addBook("B01", "Clean Code", "Robert C. Martin", "Software Engineering");
        bookService.addBook("B02", "Design Patterns", "Erich Gamma", "Software Engineering");
        bookService.addBook("B03", "Effective Java", "Joshua Bloch", "Programming");
        bookService.addBook("B04", "Head First Design Patterns", "Eric Freeman", "Programming");
        bookService.addBook("B05", "C++ Primer", "Stanley B. Lippman", "Programming");
        bookService.addBook("B06", "1984", "George Orwell", "Dystopian Fiction");
        bookService.addBook("B07", "The Great Gatsby", "F. Scott Fitzgerald", "Classic Fiction");
        bookService.addBook("B08", "Dune", "Frank Herbert", "Sci-Fi");

        // --- إضافة مستخدمين افتراضيين ---
        userService.registerUser("U101", "Karim Youssef", "karim@email.com");
        userService.registerUser("U102", "Nourhan Adel", "nourhan@email.com");
        userService.registerUser("U103", "Ziad Tarek", "ziad@email.com");
        userService.registerUser("U104", "Mona Mahmoud", "mona@email.com");
        userService.registerUser("U105", "Omar Khaled", "omar@email.com");

        // --- تسجيل عمليات إعارة تجريبية ---
        borrowService.borrowBook("U101", "B01");
        borrowService.borrowBook("U103", "B06");

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=======================================");
            System.out.println("      LIBRARY MANAGEMENT SYSTEM        ");
            System.out.println("=======================================");
            System.out.println("1. Add New Book");
            System.out.println("2. View All Books");
            System.out.println("3. Register New User");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Search Book by Title");
            System.out.println("7. Reports (Available/Borrowed)");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input! Please enter a number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Category: ");
                    String category = scanner.nextLine();

                    bookService.addBook(id, title, author, category);
                    System.out.println("-> Book added successfully!");
                    break;

                case 2:
                    System.out.println("\n--- All Books in Library ---");
                    List<Book> books = bookService.getAllBooks();
                    if (books.isEmpty()) {
                        System.out.println("No books found.");
                    } else {
                        for (Book b : books) {
                            System.out.println(b);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter User ID: ");
                    String userId = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    userService.registerUser(userId, name, email);
                    System.out.println("-> User registered successfully!");
                    break;

                case 4:
                    System.out.print("Enter User ID: ");
                    String bUserId = scanner.nextLine();
                    System.out.print("Enter Book ID to borrow: ");
                    String bBookId = scanner.nextLine();

                    String result = borrowService.borrowBook(bUserId, bBookId);
                    System.out.println("-> " + result);
                    break;

                case 5:
                    System.out.print("Enter Book ID to return: ");
                    String rBookId = scanner.nextLine();

                    String returnResult = borrowService.returnBook(rBookId);
                    System.out.println("-> " + returnResult);
                    break;

                case 6:
                    System.out.print("Enter title keyword to search: ");
                    String keyword = scanner.nextLine();
                    List<Book> searchResults = bookService.searchByTitle(keyword);
                    if (searchResults.isEmpty()) {
                        System.out.println("-> No books found.");
                    } else {
                        for (Book b : searchResults) {
                            System.out.println(b);
                        }
                    }
                    break;

                case 7:
                    System.out.println("\n--- Available Books ---");
                    List<Book> available = bookService.getAvailableBooks();
                    if (available.isEmpty()) System.out.println("No available books.");
                    for (Book b : available) {
                        System.out.println(b);
                    }

                    System.out.println("\n--- Borrowed Books ---");
                    List<Book> borrowed = bookService.getBorrowedBooks();
                    if (borrowed.isEmpty()) System.out.println("No borrowed books.");
                    for (Book b : borrowed) {
                        System.out.println(b);
                    }
                    break;

                case 0:
                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);
        scanner.close();
    }
}
package com.library.service;

import com.library.model.Book;
import com.library.model.User;
import com.library.repository.LibraryRepository;

public class BorrowService {
    private LibraryRepository repository;

    public BorrowService(LibraryRepository repository) {
        this.repository = repository;
    }

    public String borrowBook(String userId, String bookId) {
        User user = repository.getUserById(userId);
        if (user == null) return "Error: User ID not found!";

        Book book = repository.getBookById(bookId);
        if (book == null) return "Error: Book ID not found!";

        if (!book.isAvailable()) return "Error: Book '" + book.getTitle() + "' is already borrowed!";

        book.setAvailable(false);
        return "Success: User '" + user.getName() + "' borrowed '" + book.getTitle() + "'.";
    }

    public String returnBook(String bookId) {
        Book book = repository.getBookById(bookId);
        if (book == null) return "Error: Book not found!";

        if (book.isAvailable()) return "Error: Book is already available!";

        book.setAvailable(true);
        return "Success: Book '" + book.getTitle() + "' returned successfully.";
    }
}
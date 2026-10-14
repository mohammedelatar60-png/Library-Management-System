package com.library.service;

import com.library.model.Book;
import com.library.repository.LibraryRepository;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    private LibraryRepository repository;

    public BookService(LibraryRepository repository) {
        this.repository = repository;
    }

    public void addBook(String id, String title, String author, String category) {
        Book book = new Book(id, title, author, category);
        repository.addBook(book);
    }

    public List<Book> getAllBooks() {
        return repository.getAllBooks();
    }

    public List<Book> getAvailableBooks() {
        List<Book> available = new ArrayList<>();
        for (Book b : repository.getAllBooks()) {
            if (b.isAvailable()) available.add(b);
        }
        return available;
    }

    public List<Book> getBorrowedBooks() {
        List<Book> borrowed = new ArrayList<>();
        for (Book b : repository.getAllBooks()) {
            if (!b.isAvailable()) borrowed.add(b);
        }
        return borrowed;
    }

    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book b : repository.getAllBooks()) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }
}
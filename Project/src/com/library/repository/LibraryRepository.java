package com.library.repository;

import com.library.model.Book;
import com.library.model.User;

import java.util.ArrayList;
import java.util.List;

public class LibraryRepository {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public Book getBookById(String id) {
        for (Book b : books) {
            if (b.getId().equals(id)) return b;
        }
        return null;
    }

    public User getUserById(String id) {
        for (User u : users) {
            if (u.getId().equals(id)) return u;
        }
        return null;
    }
}
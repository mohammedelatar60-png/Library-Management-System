package com.library.model;

public class BorrowRecord {
    private User user;
    private Book book;

    public BorrowRecord(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    public User getUser() { return user; }
    public Book getBook() { return book; }

    @Override
    public String toString() {
        return "User: [" + user.getName() + " (ID: " + user.getId() + ")] borrowed Book: [" + book.getTitle() + " (ID: " + book.getId() + ")]";
    }
}
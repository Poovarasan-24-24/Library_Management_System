package com.library.model;

import com.library.model.enums.BookStatus;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private String category;
    private BookStatus status;

    public Book() {}

    public Book(String isbn, String title, String author, String category) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = BookStatus.AVAILABLE;
    }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public BookStatus getStatus() { return status; }
    public void setStatus(BookStatus status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("Book [ISBN=%s, Title='%s', Author='%s', Category='%s', Status=%s]",
                isbn, title, author, category, status);
    }
}
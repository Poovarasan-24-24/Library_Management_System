package com.library.dto;

import com.library.model.enums.BookStatus;

public class BookResponse {
    private String isbn;
    private String title;
    private String author;
    private String category;
    private BookStatus status;

    public BookResponse() {}

    public BookResponse(String isbn, String title, String author, String category, BookStatus status) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = status;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public BookStatus getStatus() { return status; }
}
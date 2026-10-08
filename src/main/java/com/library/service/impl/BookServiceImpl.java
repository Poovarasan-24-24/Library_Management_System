package com.library.service.impl;

import com.library.dto.BookRequestDTO;
import com.library.dto.BookResponse;
import com.library.exception.BookNotFoundException;
import com.library.model.Book;
import com.library.model.enums.BookStatus;
import com.library.repository.BookRepository;
import com.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookResponse registerBook(BookRequestDTO request) {
        Book book = new Book(request.getIsbn(), request.getTitle(), request.getAuthor(), request.getCategory());
        bookRepository.save(book);
        return mapToResponse(book);
    }

    @Override
    public BookResponse getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ISBN: " + isbn));
        return mapToResponse(book);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public List<BookResponse> searchBooks(String query) {
        return bookRepository.findByTitleOrAuthor(query).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private BookResponse mapToResponse(Book book) {
        return new BookResponse(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStatus());
    }
}
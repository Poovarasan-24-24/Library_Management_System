package com.library.service.impl;

import com.library.dto.BookRequestDTO;
import com.library.dto.BookResponse;
import com.library.exception.BookNotFoundException;
import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookResponse registerBook(BookRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Book request cannot be null");
        }

        String isbn = normalize(request.getIsbn());
        String title = normalize(request.getTitle());
        String author = normalize(request.getAuthor());
        String category = normalize(request.getCategory());

        if (isbn.isEmpty() || title.isEmpty() || author.isEmpty() || category.isEmpty()) {
            throw new IllegalArgumentException("ISBN, title, author and category are required");
        }

        if (bookRepository.findByIsbn(isbn).isPresent()) {
            throw new IllegalArgumentException("Book already exists with ISBN: " + isbn);
        }

        Book book = new Book(isbn, title, author, category);
        Book savedBook = bookRepository.save(book);
        return mapToResponse(savedBook);
    }

    @Override
    public BookResponse getBookByIsbn(String isbn) {
        String normalizedIsbn = normalize(isbn);
        Book book = bookRepository.findByIsbn(normalizedIsbn)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ISBN: " + normalizedIsbn));
        return mapToResponse(book);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public List<BookResponse> searchBooks(String query) {
        String normalizedQuery = normalize(query);
        if (normalizedQuery.isEmpty()) {
            return getAllBooks();
        }

        return bookRepository.findByTitleOrAuthor(normalizedQuery)
                .stream()
                .filter(book -> matchesQuery(book, normalizedQuery))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private boolean matchesQuery(Book book, String query) {
        String lowerQuery = query.toLowerCase(Locale.ROOT);
        return (book.getTitle() != null && book.getTitle().toLowerCase(Locale.ROOT).contains(lowerQuery))
                || (book.getAuthor() != null && book.getAuthor().toLowerCase(Locale.ROOT).contains(lowerQuery));
    }

    private BookResponse mapToResponse(Book book) {
        return new BookResponse(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStatus());
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim();
    }
}
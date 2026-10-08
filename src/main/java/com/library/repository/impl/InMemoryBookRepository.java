package com.library.repository.impl;

import com.library.model.Book;
import com.library.repository.BookRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryBookRepository implements BookRepository {
    private final Map<String, Book> storage = new ConcurrentHashMap<>();

    @Override
    public void save(Book book) {
        storage.put(book.getIsbn(), book);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(storage.get(isbn));
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public List<Book> findByTitleOrAuthor(String query) {
        String lowerQuery = query.toLowerCase();
        return storage.values().stream()
                .filter(b -> b.getTitle().toLowerCase().contains(lowerQuery) ||
                             b.getAuthor().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }

    @Override
    public void update(Book book) {
        storage.put(book.getIsbn(), book);
    }
}
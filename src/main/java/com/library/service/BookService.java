package com.library.service;

import com.library.dto.BookRequestDTO;
import com.library.dto.BookResponse;
import java.util.List;

public interface BookService {
    BookResponse registerBook(BookRequestDTO request);
    BookResponse getBookByIsbn(String isbn);
    List<BookResponse> getAllBooks();
    List<BookResponse> searchBooks(String query);
}
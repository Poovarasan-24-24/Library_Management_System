package main.test.java.com.library.service;

import com.library.dto.BookRequestDTO;
import com.library.dto.BookResponse;
import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookServiceTest {

    private BookRepository bookRepository;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookRepository = Mockito.mock(BookRepository.class);
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    void testRegisterBook() {
        BookRequestDTO dto = new BookRequestDTO();
        dto.setIsbn("12345");
        dto.setTitle("Test Title");
        dto.setAuthor("Test Author");
        dto.setCategory("Tech");

        BookResponse response = bookService.registerBook(dto);

        assertNotNull(response);
        assertEquals("12345", response.getIsbn());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void testGetBookByIsbn() {
        Book book = new Book("12345", "Test Title", "Test Author", "Tech");
        when(bookRepository.findByIsbn("12345")).thenReturn(Optional.of(book));

        BookResponse response = bookService.getBookByIsbn("12345");

        assertNotNull(response);
        assertEquals("Test Title", response.getTitle());
    }
}
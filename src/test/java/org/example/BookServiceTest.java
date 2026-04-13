package org.example;

import org.example.model.Book;
import org.example.repository.BookRepository;
import org.example.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private BookRepository repository;
    private BookService service;

    @BeforeEach
    void setUp() {
        repository = new BookRepository();
        service = new BookService(repository);
    }

    // -------------------------------------------------------------------------
    // addBook
    // -------------------------------------------------------------------------

    @Test
    void addBook_shouldReturnBookWithCorrectFields() {
        Book book = service.addBook("1", "Clean Code", "Robert Martin", "978-0132350884", 3);

        assertNotNull(book);
        assertEquals("1", book.getId());
        assertEquals("Clean Code", book.getTitle());
        assertEquals("Robert Martin", book.getAuthor());
        assertEquals("978-0132350884", book.getIsbn());
        assertEquals(3, book.getTotalCopies());
        assertEquals(3, book.getAvailableCopies());
    }

    @Test
    void addBook_shouldPersistInRepository() {
        service.addBook("1", "Clean Code", "Robert Martin", "978-0132350884", 3);

        assertTrue(repository.findById("1").isPresent());
    }

    @Test
    void addBook_shouldThrowIfIdIsBlank() {
        assertThrows(IllegalArgumentException.class,
                () -> service.addBook("", "Title", "Author", "isbn", 1));
    }

    @Test
    void addBook_shouldThrowIfTitleIsBlank() {
        assertThrows(IllegalArgumentException.class,
                () -> service.addBook("1", "  ", "Author", "isbn", 1));
    }

    @Test
    void addBook_shouldThrowIfIsbnIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> service.addBook("1", "Title", "Author", null, 1));
    }

    @Test
    void addBook_shouldThrowIfTotalCopiesIsZero() {
        assertThrows(IllegalArgumentException.class,
                () -> service.addBook("1", "Title", "Author", "isbn", 0));
    }

    @Test
    void addBook_shouldThrowIfIdAlreadyExists() {
        service.addBook("1", "Clean Code", "Robert Martin", "isbn1", 1);
        assertThrows(IllegalArgumentException.class,
                () -> service.addBook("1", "Other Book", "Author", "isbn2", 1));
    }

    // -------------------------------------------------------------------------
    // findById
    // -------------------------------------------------------------------------

    @Test
    void findById_shouldReturnBookWhenFound() {
        service.addBook("42", "The Pragmatic Programmer", "Hunt & Thomas", "isbn", 2);

        Optional<Book> result = service.findById("42");

        assertTrue(result.isPresent());
        assertEquals("42", result.get().getId());
    }

    @Test
    void findById_shouldReturnEmptyWhenNotFound() {
        Optional<Book> result = service.findById("unknown");
        assertTrue(result.isEmpty());
    }

    // -------------------------------------------------------------------------
    // findByTitle
    // -------------------------------------------------------------------------

    @Test
    void findByTitle_shouldMatchPartialTitleCaseInsensitive() {
        service.addBook("1", "Clean Code", "Robert Martin", "isbn1", 1);
        service.addBook("2", "The Clean Coder", "Robert Martin", "isbn2", 1);
        service.addBook("3", "Refactoring", "Martin Fowler", "isbn3", 1);

        List<Book> results = service.findByTitle("clean");

        assertEquals(2, results.size());
    }

    @Test
    void findByTitle_shouldReturnEmptyListWhenNoMatch() {
        service.addBook("1", "Clean Code", "Robert Martin", "isbn1", 1);

        List<Book> results = service.findByTitle("Python");

        assertTrue(results.isEmpty());
    }

    // -------------------------------------------------------------------------
    // findByAuthor
    // -------------------------------------------------------------------------

    @Test
    void findByAuthor_shouldMatchCaseInsensitive() {
        service.addBook("1", "Clean Code", "Robert Martin", "isbn1", 1);
        service.addBook("2", "The Clean Coder", "robert martin", "isbn2", 1);
        service.addBook("3", "Refactoring", "Martin Fowler", "isbn3", 1);

        List<Book> results = service.findByAuthor("Robert Martin");

        assertEquals(2, results.size());
    }

    @Test
    void findByAuthor_shouldReturnEmptyListWhenNoMatch() {
        service.addBook("1", "Clean Code", "Robert Martin", "isbn1", 1);

        assertTrue(service.findByAuthor("Unknown Author").isEmpty());
    }

    // -------------------------------------------------------------------------
    // listAll / listAvailable
    // -------------------------------------------------------------------------

    @Test
    void listAll_shouldReturnAllBooks() {
        service.addBook("1", "Book A", "Author", "isbn1", 1);
        service.addBook("2", "Book B", "Author", "isbn2", 1);
        service.addBook("3", "Book C", "Author", "isbn3", 1);

        assertEquals(3, service.listAll().size());
    }

    @Test
    void listAvailable_shouldExcludeBooksWithNoAvailableCopies() {
        service.addBook("1", "Available Book", "Author", "isbn1", 2);
        Book noStock = service.addBook("2", "Out of Stock", "Author", "isbn2", 1);
        noStock.setAvailableCopies(0);

        List<Book> available = service.listAvailable();

        assertEquals(1, available.size());
        assertEquals("1", available.get(0).getId());
    }

    // -------------------------------------------------------------------------
    // removeBook
    // -------------------------------------------------------------------------

    @Test
    void removeBook_shouldReturnTrueAndDeleteBook() {
        service.addBook("1", "Clean Code", "Robert Martin", "isbn", 1);

        boolean removed = service.removeBook("1");

        assertTrue(removed);
        assertTrue(service.findById("1").isEmpty());
    }

    @Test
    void removeBook_shouldReturnFalseWhenBookNotFound() {
        assertFalse(service.removeBook("nonexistent"));
    }
}

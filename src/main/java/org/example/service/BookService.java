package org.example.service;

import org.example.model.Book;
import org.example.repository.BookRepository;

import java.util.List;
import java.util.Optional;

/**
 * ============================================================
 *  GROUP 1 — Book Management
 * ============================================================
 * Your task: implement every method that throws UnsupportedOperationException.
 * Do NOT modify the models, repositories, or tests.
 * Git workflow:
 *   1. Create your branch:
 *        git checkout -b feature/book-management
 *   2. Implement the TODOs below
 *   3. Run the tests:
 *        ./gradlew test --tests "org.example.BookServiceTest"
 *   4. Commit and push when all tests pass:
 *        git add .
 *        git commit -m "feat: implement BookService"
 *        git push origin feature/book-management
 *   5. Open a Pull Request targeting the main branch
 * ============================================================
 */
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    /**
     * Add a new book to the library and return it.
     * Validation rules (throw IllegalArgumentException if violated):
     *   - id, title and isbn must not be null or blank
     *   - totalCopies must be >= 1
     *   - a book with the same id must not already exist
     */
    public Book addBook(String id, String title, String author, String isbn, int totalCopies) {
        // TODO: validate parameters
        // TODO: create a new Book and save it via the repository
        // TODO: return the saved book
        BookRepository bookRepository = new BookRepository();
        Book b1 = new Book(id, title, author, isbn, totalCopies);

        bookRepository.save(b1);
        return b1;
        //throw new UnsupportedOperationException("Not implemented yet — Group 1");
    }

    /**
     * Find a book by its id.
     * Return Optional.empty() if no book has that id.
     */
    public Optional<Book> findById(String id) {
        // TODO: delegate to the repository
        throw new UnsupportedOperationException("Not implemented yet — Group 1");
    }

    /**
     * Return all books whose title contains the given string (case-insensitive).
     * Return an empty list if none match.
     */
    public List<Book> findByTitle(String title) {
        // TODO: get all books from the repository and filter by title
        throw new UnsupportedOperationException("Not implemented yet — Group 1");
    }

    /**
     * Return all books whose author matches exactly (case-insensitive).
     * Return an empty list if none match.
     */
    public List<Book> findByAuthor(String author) {
        // TODO: get all books from the repository and filter by author
        throw new UnsupportedOperationException("Not implemented yet — Group 1");
    }

    /**
     * Return all books in the library.
     */
    public List<Book> listAll() {
        // TODO: delegate to the repository
        throw new UnsupportedOperationException("Not implemented yet — Group 1");
    }

    /**
     * Return only books that have at least one available copy.
     */
    public List<Book> listAvailable() {
        // TODO: filter books where availableCopies > 0
        throw new UnsupportedOperationException("Not implemented yet — Group 1");
    }

    /**
     * Remove a book by id.
     *
     * @return true if the book existed and was removed, false if it was not found
     */
    public boolean removeBook(String id) {
        // TODO: delegate to the repository
        throw new UnsupportedOperationException("Not implemented yet — Group 1");
    }
}

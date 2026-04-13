package org.example.repository;

import org.example.model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BookRepository {

    private final Map<String, Book> store = new HashMap<>();

    public void save(Book book) {
        store.put(book.getId(), book);
    }

    public Optional<Book> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Book> findAll() {
        return new ArrayList<>(store.values());
    }

    public boolean delete(String id) {
        return store.remove(id) != null;
    }

    public void clear() {
        store.clear();
    }
}

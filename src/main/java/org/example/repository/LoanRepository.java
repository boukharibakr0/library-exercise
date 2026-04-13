package org.example.repository;

import org.example.model.Loan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LoanRepository {

    private final Map<String, Loan> store = new HashMap<>();

    public void save(Loan loan) {
        store.put(loan.getId(), loan);
    }

    public Optional<Loan> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Loan> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clear() {
        store.clear();
    }
}

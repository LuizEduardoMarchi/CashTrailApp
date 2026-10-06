package com.luizdev.cashtrail.repository;

import com.luizdev.cashtrail.model.Expense;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ExpenseRepository {
    // Repository in memory, it's gonna be replaced by a database in the future.
    private final Map<Long, Expense> expenses = new LinkedHashMap<>();

    // Last generated Id, starts with 0 and keep incrementing by 1.
    private final AtomicLong lastId = new AtomicLong(0);

    public Expense save(Expense expense) {
        // Checks if it came with an id. If it so inserts a new id and inserts into expenses.
        if(expense.getId() == null) {
            expense.setId(lastId.incrementAndGet());
            expenses.put(expense.getId(), expense);
            return expense;
        }

        // Id was provided but doesn't exist in the repository
        if(!expenses.containsKey(expense.getId())){
            throw new IllegalArgumentException("Cannot update: expense not found with id " + expense.getId());
        }

        expenses.put(expense.getId(), expense);
        return expense;

    }

    // Returns the expense if found; Optional forces the caller to handle the absent case.
    public Optional<Expense> findById(Long id) { return Optional.ofNullable(expenses.get(id)); }

    public List<Expense> findAll() { return List.copyOf(expenses.values()); }

    public void deleteById(Long id) { expenses.remove(id); }
}
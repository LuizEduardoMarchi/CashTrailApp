package com.luizdev.cashtrail.repository;

import com.luizdev.cashtrail.model.Category;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CategoryRepository {
    // In-memory storage, replaced by the database in Phase 2
    private final Map<Long, Category> categories = new LinkedHashMap<>();

    // Last generated id; starts at 0, so the first id is 1
    private final AtomicLong lastId = new AtomicLong(0);

    public Category save(Category category) {
        // Checks if category came without any id and set a new id.
        if(category.getId() == null) {
            category.setId(lastId.incrementAndGet());
            categories.put(category.getId(), category);
            return category;
        }

        // Id was provided but doesn't exist in the repository
        if(!categories.containsKey(category.getId())){
            throw new IllegalArgumentException("Cannot update: category not found with id " + category.getId());
        }

        categories.put(category.getId(), category);
        return category;
    }

    // Stable copy of all categories.
    public List<Category> findAll() { return List.copyOf(categories.values()); }

    // Returns the Category if found; Optional forces the caller to handle the absent case.
    public Optional<Category> findById(Long id) { return Optional.ofNullable(categories.get(id)); }
}

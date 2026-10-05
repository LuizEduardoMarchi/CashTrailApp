package com.luizdev.cashtrail.repository;

import com.luizdev.cashtrail.model.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CategoryRepository {
    // In-memory storage, replaced by the database in Phase 2
    private final List<Category> categories = new ArrayList<>();

    // Last generated id; starts at 0, so the first id is 1
    private final AtomicLong lastId = new AtomicLong(0);

    public Category save(Category category) {
        // Checks if category came without any id and set a new id.
        if(category.getId() == null) {
            category.setId(lastId.incrementAndGet());
            categories.add(category);
            return category;
        }

        // Search for an id that is equals to category id and replaces the existing category at the same position.
        for(int i = 0; i < categories.size(); i++) {
            if(category.getId().equals(categories.get(i).getId())) {
                categories.set(i, category);
                return category;
            }
        }

        // Id was provided but doesn't exist in the repository
        throw new IllegalArgumentException("Cannot update: category not found with id " + category.getId());
    }

    public List<Category> findAll() {
        return List.copyOf(categories); // Stable copy of all categories.
    }
}

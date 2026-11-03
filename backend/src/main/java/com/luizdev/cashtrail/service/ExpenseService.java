package com.luizdev.cashtrail.service;

import com.luizdev.cashtrail.dto.ExpenseRequest;
import com.luizdev.cashtrail.dto.ExpenseResponse;
import com.luizdev.cashtrail.exception.ResourceNotFoundException;
import com.luizdev.cashtrail.model.Expense;
import com.luizdev.cashtrail.repository.CategoryRepository;
import com.luizdev.cashtrail.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    public ExpenseService(ExpenseRepository expenseRepository, CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    public ExpenseResponse create(ExpenseRequest request) {
        // Validate before saving so we never store an expense pointing to a missing category.
        categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        Expense expense = new Expense();
        expense.setDescription(request.description());
        expense.setAmount(request.amount());
        expense.setDate(request.date());
        expense.setCategoryId(request.categoryId());

        Expense savedExpense = expenseRepository.save(expense);

        return toResponse(savedExpense);
    }

    // Convert entities to responses so the API never exposes the Expense model.
    public List<ExpenseResponse> findAll() {
        return expenseRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ExpenseResponse findById(Long id) {
        return toResponse(expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Despesa não encontrada")));
    }

    // Update the existing entity (not a new one) so the id is preserved.
    public ExpenseResponse update(Long id, ExpenseRequest request) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Despesa não encontrada"));
        categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        expense.setDescription(request.description());
        expense.setAmount(request.amount());
        expense.setDate(request.date());
        expense.setCategoryId(request.categoryId());

        Expense savedExpense = expenseRepository.save(expense);

        return toResponse(savedExpense);
    }

    public void delete(Long id) {
        expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Despesa não encontrada"));
        expenseRepository.deleteById(id);
    }

    private ExpenseResponse toResponse(Expense expense) {
        return new ExpenseResponse(
                expense.getId(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getDate(),
                expense.getCategoryId());
    }
}
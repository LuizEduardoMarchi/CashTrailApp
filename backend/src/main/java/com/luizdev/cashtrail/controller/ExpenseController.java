package com.luizdev.cashtrail.controller;

import com.luizdev.cashtrail.dto.ExpenseRequest;
import com.luizdev.cashtrail.dto.ExpenseResponse;
import com.luizdev.cashtrail.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> create(@Valid @RequestBody ExpenseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> findAll() {
        return ResponseEntity.ok(expenseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponse> update(@PathVariable Long id, @Valid @RequestBody ExpenseRequest request) {
        return ResponseEntity.ok(expenseService.update(id, request));
    }

    // 204 No Content: the resource is gone, so there is nothing to return
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        expenseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

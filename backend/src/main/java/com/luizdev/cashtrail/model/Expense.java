package com.luizdev.cashtrail.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {
    private Long id; // Object (wrapper), new expense can be null before been saved.
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private Long categoryId;

    public Expense() {} // Empty constructor (Jackson) that converts JSON into objects.
    public Expense(Long id, String description, BigDecimal amount, LocalDate date, Long categoryId) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.categoryId = categoryId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
}

package com.luizdev.cashtrail.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseResponse(
        Long id,
        String description,
        BigDecimal amount,
        LocalDate date,
        Long categoryId
) {}

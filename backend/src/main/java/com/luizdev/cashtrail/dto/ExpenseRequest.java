package com.luizdev.cashtrail.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseRequest(
        @NotBlank(message = "Descrição é obrigatória")
        String description,

        @NotNull(message = "Valor é obrigatório")
        @Positive(message = "Valor deve ser maior que zero")
        BigDecimal amount,

        @NotNull(message = "Data é obrigatória")
        LocalDate date,

        @NotNull(message = "Categoria é obrigatória")
        Long categoryId
) {}

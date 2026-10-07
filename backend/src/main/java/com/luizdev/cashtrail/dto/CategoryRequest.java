package com.luizdev.cashtrail.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank(message = "Nome é obrigatório")
        String name
) {}

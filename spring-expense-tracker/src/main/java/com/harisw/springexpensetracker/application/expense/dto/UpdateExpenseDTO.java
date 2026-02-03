package com.harisw.springexpensetracker.application.expense.dto;

import com.harisw.springexpensetracker.domain.expense.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record UpdateExpenseDTO(
        UUID publicId,
        ExpenseCategory category,
        String description,
        BigDecimal amount,
        LocalDate date
) {
}

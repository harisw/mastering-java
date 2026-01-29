package org.harisw.expensetracker.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CreateExpenseRequestDTO {
    public UUID userId;
    public BigDecimal amount;
    public String description;
    public LocalDate createdAt;
    public UUID categoryId;
}

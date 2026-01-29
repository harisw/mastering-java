package org.harisw.expensetracker.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class ExpenseResponseDTO {
    public UUID id;
    public UUID userId;
    public BigDecimal amount;
    public String description;
    public UUID categoryId;
}

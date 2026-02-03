package org.harisw.expensetracker.web.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class CreateExpenseRequestDTO {
    public Long userId;
    public BigDecimal amount;
    public String description;
    public Instant createdAt;
    public UUID categoryId;
}

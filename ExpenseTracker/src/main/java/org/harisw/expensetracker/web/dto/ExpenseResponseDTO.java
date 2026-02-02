package org.harisw.expensetracker.web.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class ExpenseResponseDTO {
    public Long id;
    public Long userId;
    public BigDecimal amount;
    public String description;
    public UUID categoryId;
    public Instant createdAt;
}

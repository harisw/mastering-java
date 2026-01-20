package org.harisw.expensetracker.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Expense {
    private final UUID id;
    private final UUID userId;
    private Money amount;
    private LocalDateTime time;
    private String description;
    private final Category category;

    public Expense(Money amount, UUID userId, Category category, String description, LocalDateTime time) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.time = time;
    }

   public UUID getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money newAmount) {
        if (newAmount == null) throw new IllegalArgumentException("Amount cannot be null");
        this.amount = newAmount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Expense{");
        sb.append("id=").append(id);
        sb.append(", amount=").append(amount);
        sb.append(", time=").append(time);
        sb.append(", description=").append(description);
        sb.append(", category=").append(category);
        sb.append('}');
        return sb.toString();
    }

}
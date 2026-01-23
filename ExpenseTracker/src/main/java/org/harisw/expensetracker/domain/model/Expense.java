package org.harisw.expensetracker.domain.model;

import java.time.LocalDate;
import java.util.UUID;

public class Expense {
    private final UUID id;
    private final UUID userId;
    private Money amount;
    private LocalDate date;
    private String description;
    private final UUID categoryId;

    public Expense(UUID userId, Money amount, UUID categoryId, String description, LocalDate date) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.amount = amount;
        this.categoryId = categoryId;
        this.description = description;
        this.date = date;
    }

    public Expense(UUID id, UUID userId, Money amount, UUID categoryId, String description, LocalDate date) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.categoryId = categoryId;
        this.description = description;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    

    public UUID getCategoryId() {
        return categoryId;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money newAmount) {
        if (newAmount == null) throw new IllegalArgumentException("Amount cannot be null");
        this.amount = newAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setTime(LocalDate date) {
        this.date = date;
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
        sb.append(", time=").append(date);
        sb.append(", description=").append(description);
        sb.append(", category=").append(categoryId);
        sb.append('}');
        return sb.toString();
    }

    public UUID getUserId() {
        return userId;
    }

}
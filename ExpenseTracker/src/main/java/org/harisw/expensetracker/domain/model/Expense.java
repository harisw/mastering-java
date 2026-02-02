package org.harisw.expensetracker.domain.model;

import java.time.Instant;
import java.util.UUID;

public class Expense {
    private final Long id;
    private final Long userId;
    private Money amount;
    private Instant createdAt;
    private String description;
    private final UUID categoryId;

    public Expense(Long id, Long userId, Money amount, UUID categoryId, String description, Instant createdAt) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.categoryId = categoryId;
        this.description = description;
        this.createdAt = createdAt;
    }

    public static Expense create(Long userId, Money amount, UUID categoryId, String description, Instant createdAt) {
        return new Expense(null, userId, amount, categoryId, description, createdAt);
    }

    public Long getId() {
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", amount=" + amount +
                ", time=" + createdAt.toString() +
                ", description=" + description +
                ", category=" + categoryId +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

}
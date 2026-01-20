package org.harisw.expensetracker.domain.model;

import java.math.BigDecimal;

public final class Money {
    private final BigDecimal amount;

    public Money(BigDecimal amount) {
        if (amount.signum() < 0) throw new IllegalArgumentException("Amount must be non-negative value");
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

}
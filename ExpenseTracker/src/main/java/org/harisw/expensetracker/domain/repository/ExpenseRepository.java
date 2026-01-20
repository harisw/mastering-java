package org.harisw.expensetracker.domain.repository;

import java.util.List;
import java.util.UUID;

import org.harisw.expensetracker.domain.model.Expense;

public interface ExpenseRepository {
    List<Expense> findByUserId(UUID userId);
    Expense save(Expense expense);
}
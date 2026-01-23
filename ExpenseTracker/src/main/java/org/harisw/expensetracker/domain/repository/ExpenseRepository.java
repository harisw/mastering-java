package org.harisw.expensetracker.domain.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.harisw.expensetracker.domain.model.Expense;

public interface ExpenseRepository {
    Expense save(Expense expense) throws SQLException;
    List<Expense> findByUserId(UUID userId) throws SQLException;
}
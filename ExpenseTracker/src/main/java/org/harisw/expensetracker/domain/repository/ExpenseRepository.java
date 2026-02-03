package org.harisw.expensetracker.domain.repository;

import org.harisw.expensetracker.domain.model.Expense;

import java.sql.SQLException;
import java.util.List;

public interface ExpenseRepository {
    Expense save(Expense expense) throws SQLException;

    List<Expense> findAll() throws SQLException;

    List<Expense> findByUserId(Long userId) throws SQLException;
}
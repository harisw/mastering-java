package org.harisw.expensetracker.domain.service;

import org.harisw.expensetracker.domain.model.Expense;
import org.harisw.expensetracker.domain.repository.ExpenseRepository;

import java.sql.SQLException;
import java.util.List;

public class ExpenseService {
    //    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;

    public ExpenseService(
//            UserRepository userRepository,
            ExpenseRepository expenseRepository) {
//        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
    }

//    public Expense addExpense(UUID userId, Expense expense) throws SQLException {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//        return expenseRepository.save(expense);
//    }

    public Expense create(Expense expense) throws SQLException {
        return expenseRepository.save(expense);
    }

    public List<Expense> findAll() throws SQLException {
        return expenseRepository.findAll();
    }
}
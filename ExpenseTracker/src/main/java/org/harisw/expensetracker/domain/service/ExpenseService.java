package org.harisw.expensetracker.domain.service;

import java.util.UUID;

import org.harisw.expensetracker.domain.model.Expense;
import org.harisw.expensetracker.domain.model.User;
import org.harisw.expensetracker.domain.repository.ExpenseRepository;
import org.harisw.expensetracker.domain.repository.UserRepository;

public class ExpenseService {
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;

    public ExpenseService(UserRepository userRepository,
                            ExpenseRepository expenseRepository) {
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
    }

    public Expense addExpense(UUID userId, Expense expense) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        return expenseRepository.save(expense);
    }
}
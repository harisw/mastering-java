package com.harisw.springexpensetracker.application.expense.service;

import com.harisw.springexpensetracker.domain.expense.Expense;
import com.harisw.springexpensetracker.domain.expense.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GetExpenseService {
    private final ExpenseRepository repository;

    public GetExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public Expense get(UUID publicId) {
        return repository.findByPublicId(publicId)
                .orElseThrow(() -> new IllegalArgumentException("Expense not found"));
    }

    public List<Expense> getAll() {
        return repository.findAll();
    }
}

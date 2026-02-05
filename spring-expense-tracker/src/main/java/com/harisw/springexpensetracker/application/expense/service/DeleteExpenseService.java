package com.harisw.springexpensetracker.application.expense.service;

import com.harisw.springexpensetracker.domain.expense.ExpenseNotFoundException;
import com.harisw.springexpensetracker.domain.expense.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class DeleteExpenseService {
    private final ExpenseRepository repository;

    public DeleteExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public void delete(UUID publicId) {
        if (repository.findByPublicId(publicId).isEmpty()) {
            throw new ExpenseNotFoundException(publicId);
        }
        repository.deleteByPublicId(publicId);
    }
}

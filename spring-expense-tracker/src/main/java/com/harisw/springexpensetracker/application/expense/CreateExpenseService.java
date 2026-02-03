package com.harisw.springexpensetracker.application.expense;

import com.harisw.springexpensetracker.application.expense.dto.CreateExpenseDTO;
import com.harisw.springexpensetracker.domain.common.Money;
import com.harisw.springexpensetracker.domain.expense.Expense;
import com.harisw.springexpensetracker.domain.expense.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@Transactional
public class CreateExpenseService {
    private final ExpenseRepository repository;

    public CreateExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public Expense create(CreateExpenseDTO dto) {
        Expense expense = new Expense(
                null,
                UUID.randomUUID(),
                dto.category(),
                dto.description(),
                new Money(dto.amount()),
                dto.date(),
                Instant.now()
        );

        return repository.save(expense);
    }
}

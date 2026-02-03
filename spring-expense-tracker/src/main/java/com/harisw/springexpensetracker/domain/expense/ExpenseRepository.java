package com.harisw.springexpensetracker.domain.expense;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpenseRepository {
    Expense save(Expense expense);

    Optional<Expense> findByPublicId(UUID publicId);

    List<Expense> findAll();

//    void deleteByPublicId(UUID publicId);

//    List<Expense> findByDateRange(LocalDate from, LocalDate to);
}

package com.harisw.springexpensetracker.infrastructure.persistence;

import com.harisw.springexpensetracker.domain.expense.Expense;
import com.harisw.springexpensetracker.domain.expense.ExpenseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ExpenseRepositoryImpl implements ExpenseRepository {

    private final ExpenseJpaRepository jpa;

    public ExpenseRepositoryImpl(ExpenseJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Expense save(Expense expense) {
        return ExpenseMapper.toDomain(
                jpa.save(ExpenseMapper.toEntity(expense))
        );
    }

    @Override
    public Optional<Expense> findByPublicId(UUID publicId) {
        return jpa.findByPublicId(publicId)
                .map(ExpenseMapper::toDomain);
    }

    @Override
    public List<Expense> findAll() {
        return jpa.findAll()
                .stream()
                .map(ExpenseMapper::toDomain)
                .toList();   // Java 16+
    }

    @Override
    public void deleteByPublicId(UUID publicId) {
        jpa.deleteByPublicId(publicId);
    }

//    @Override
//    public List<Expense> findByDateRange(LocalDate from, LocalDate to) {
//        return jpa.findByDateBetween(from, to)
//                .stream()
//                .map(ExpenseMapper::toDomain)
//                .toList();
//    }
}

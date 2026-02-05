package com.harisw.springexpensetracker.application.expense.service;

import com.harisw.springexpensetracker.domain.common.Money;
import com.harisw.springexpensetracker.domain.expense.Expense;
import com.harisw.springexpensetracker.domain.expense.ExpenseCategory;
import com.harisw.springexpensetracker.domain.expense.ExpenseNotFoundException;
import com.harisw.springexpensetracker.domain.expense.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetExpenseServiceTest {

    @Mock
    private ExpenseRepository repository;

    private GetExpenseService service;

    @BeforeEach
    void setUp() {
        service = new GetExpenseService(repository);
    }

    @Test
    void get_shouldReturnExpenseWhenFound() {
        // given
        UUID publicId = UUID.randomUUID();
        Expense expense = new Expense(1L, publicId, ExpenseCategory.FOOD, "Lunch", new Money(new BigDecimal("20.00")),
                LocalDate.now(), Instant.now());

        when(repository.findByPublicId(publicId)).thenReturn(Optional.of(expense));

        // when
        Expense result = service.get(publicId);

        // then
        assertEquals(expense, result);
        verify(repository).findByPublicId(publicId);
    }

    @Test
    void get_shouldThrowExpenseNotFoundExceptionWhenNotFound() {
        // given
        UUID publicId = UUID.randomUUID();
        when(repository.findByPublicId(publicId)).thenReturn(Optional.empty());

        // when & then
        ExpenseNotFoundException exception = assertThrows(ExpenseNotFoundException.class, () -> service.get(publicId));

        assertEquals(publicId, exception.getPublicId());
    }

    @Test
    void getAll_shouldReturnAllExpenses() {
        // given
        List<Expense> expenses = List.of(
                new Expense(1L, UUID.randomUUID(), ExpenseCategory.FOOD, "Lunch", new Money(new BigDecimal("20.00")),
                        LocalDate.now(), Instant.now()),
                new Expense(2L, UUID.randomUUID(), ExpenseCategory.TRANSPORT, "Bus", new Money(new BigDecimal("5.00")),
                        LocalDate.now(), Instant.now()));

        when(repository.findAll()).thenReturn(expenses);

        // when
        List<Expense> result = service.getAll();

        // then
        assertEquals(2, result.size());
        assertEquals(expenses, result);
        verify(repository).findAll();
    }

    @Test
    void getAll_shouldReturnEmptyListWhenNoExpenses() {
        // given
        when(repository.findAll()).thenReturn(List.of());

        // when
        List<Expense> result = service.getAll();

        // then
        assertTrue(result.isEmpty());
    }
}

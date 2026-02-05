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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteExpenseServiceTest {

    @Mock
    private ExpenseRepository repository;

    private DeleteExpenseService service;

    @BeforeEach
    void setUp() {
        service = new DeleteExpenseService(repository);
    }

    @Test
    void delete_shouldDeleteExpenseWhenExists() {
        // given
        UUID publicId = UUID.randomUUID();
        Expense expense = new Expense(1L, publicId, ExpenseCategory.FOOD, "Lunch", new Money(new BigDecimal("20.00")),
                LocalDate.now(), Instant.now());

        when(repository.findByPublicId(publicId)).thenReturn(Optional.of(expense));

        // when
        service.delete(publicId);

        // then
        verify(repository).findByPublicId(publicId);
        verify(repository).deleteByPublicId(publicId);
    }

    @Test
    void delete_shouldThrowExpenseNotFoundExceptionWhenNotExists() {
        // given
        UUID publicId = UUID.randomUUID();
        when(repository.findByPublicId(publicId)).thenReturn(Optional.empty());

        // when & then
        ExpenseNotFoundException exception = assertThrows(ExpenseNotFoundException.class,
                () -> service.delete(publicId));

        assertEquals(publicId, exception.getPublicId());
        verify(repository, never()).deleteByPublicId(any());
    }
}

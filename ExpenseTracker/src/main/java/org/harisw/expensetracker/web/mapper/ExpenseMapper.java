package org.harisw.expensetracker.web.mapper;

import org.harisw.expensetracker.domain.model.Expense;
import org.harisw.expensetracker.domain.model.Money;
import org.harisw.expensetracker.web.dto.CreateExpenseRequestDTO;
import org.harisw.expensetracker.web.dto.ExpenseResponseDTO;

public class ExpenseMapper {

    public static Expense toEntity(CreateExpenseRequestDTO dto) {
        return Expense.withDefaults(dto.userId, new Money(dto.amount),
                dto.categoryId, dto.description);
    }

    public static ExpenseResponseDTO toResponse(Expense e) {
        ExpenseResponseDTO dto = new ExpenseResponseDTO();
        dto.id = e.getId();
        dto.userId = e.getUserId();
        dto.amount = e.getAmount().getAmount();
        dto.description = e.getDescription();
        dto.categoryId = e.getCategoryId();
        return dto;
    }
}

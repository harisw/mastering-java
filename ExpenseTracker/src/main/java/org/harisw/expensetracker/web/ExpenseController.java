package org.harisw.expensetracker.web;

import io.javalin.Javalin;
import org.harisw.expensetracker.domain.service.ExpenseService;
import org.harisw.expensetracker.web.dto.CreateExpenseRequestDTO;
import org.harisw.expensetracker.web.mapper.ExpenseMapper;

public class ExpenseController {

    public static void register(Javalin app, ExpenseService service) {

        app.post("/expenses", ctx -> {
            CreateExpenseRequestDTO request =
                    ctx.bodyAsClass(CreateExpenseRequestDTO.class);

            var expense = ExpenseMapper.toEntity(request);
            var saved = service.create(expense);

            ctx.status(201)
                    .json(ExpenseMapper.toResponse(saved));
        });

        app.get("/expenses", ctx -> {
            var expenses = service.findAll()
                    .stream()
                    .map(ExpenseMapper::toResponse)
                    .toList();

            ctx.json(expenses);
        });
    }
}

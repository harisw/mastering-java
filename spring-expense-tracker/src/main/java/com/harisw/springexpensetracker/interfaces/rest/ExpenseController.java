package com.harisw.springexpensetracker.interfaces.rest;

import com.harisw.springexpensetracker.application.expense.dto.command.UpdateExpenseCommand;
import com.harisw.springexpensetracker.application.expense.dto.request.CreateExpenseRequest;
import com.harisw.springexpensetracker.application.expense.service.CreateExpenseService;
import com.harisw.springexpensetracker.application.expense.service.DeleteExpenseService;
import com.harisw.springexpensetracker.application.expense.service.GetExpenseService;
import com.harisw.springexpensetracker.application.expense.service.UpdateExpenseService;
import com.harisw.springexpensetracker.domain.expense.Expense;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final CreateExpenseService create;
    private final GetExpenseService get;
    private final UpdateExpenseService update;
    private final DeleteExpenseService delete;

    public ExpenseController(CreateExpenseService create, GetExpenseService get, UpdateExpenseService update,
                             DeleteExpenseService delete) {
        this.create = create;
        this.get = get;
        this.update = update;
        this.delete = delete;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Expense create(@RequestBody CreateExpenseRequest req) {
        return create.create(req.toCommand());
    }

    @GetMapping("/{public_id}")
    public Expense get(@PathVariable UUID public_id) {
        return get.get(public_id);
    }

    @GetMapping
    public List<Expense> getAll() {
        return get.getAll();
    }

    @PutMapping("/{public_id}")
    public Expense update(
            @PathVariable UUID public_id,
            @RequestBody CreateExpenseRequest req
    ) {
        return update.update(
                new UpdateExpenseCommand(
                        public_id,
                        req.category(),
                        req.description(),
                        req.amount(),
                        req.date()
                )
        );
    }

    @DeleteMapping("/{public_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID public_id) {
        delete.delete(public_id);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.harisw.expensetracker;

import io.javalin.Javalin;
import org.harisw.expensetracker.domain.repository.ExpenseRepository;
import org.harisw.expensetracker.domain.service.ExpenseService;
import org.harisw.expensetracker.infrastructure.repository.JdbcExpenseRepository;
import org.harisw.expensetracker.web.ExpenseController;

/**
 *
 * @author Administrator
 */
public class ExpenseTracker {

    public static void main(String[] args) {
        ExpenseRepository repository = new JdbcExpenseRepository();

        ExpenseService service = new ExpenseService(repository);

        Javalin app = Javalin.create().start(7000);

        ExpenseController.register(app, service);
    }
}

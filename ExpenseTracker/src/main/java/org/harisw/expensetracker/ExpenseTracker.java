/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.harisw.expensetracker;

import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;
import org.harisw.expensetracker.config.DbConfig;
import org.harisw.expensetracker.config.JacksonConfig;
import org.harisw.expensetracker.domain.repository.ExpenseRepository;
import org.harisw.expensetracker.domain.service.ExpenseService;
import org.harisw.expensetracker.infrastructure.db.DataSourceFactory;
import org.harisw.expensetracker.infrastructure.db.FlywayMigrator;
import org.harisw.expensetracker.infrastructure.repository.JdbcExpenseRepository;
import org.harisw.expensetracker.web.ExpenseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class ExpenseTracker {
    private static final Logger log = LoggerFactory.getLogger(ExpenseTracker.class);

    static void main(String[] args) throws SQLException {
        log.info("Starting ExpenseTracker app");

        DbConfig dbConfig = new DbConfig();
        DataSource dataSource = DataSourceFactory.create(dbConfig);
        FlywayMigrator.migrate(dataSource);

        ExpenseRepository repository = new JdbcExpenseRepository(dataSource);

        ExpenseService service = new ExpenseService(repository);

        JavalinJackson javalinJackson = new JavalinJackson(JacksonConfig.objectMapper(), true);

        Javalin app = Javalin.create(config -> {
            config.jsonMapper(javalinJackson);
        }).start(7000);

        ExpenseController.register(app, service);
    }
}

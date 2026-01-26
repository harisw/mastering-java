package org.harisw.expensetracker.infrastructure.repository;

import org.harisw.expensetracker.domain.model.Expense;
import org.harisw.expensetracker.domain.model.Money;
import org.harisw.expensetracker.domain.repository.ExpenseRepository;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.postgresql.ds.PGSimpleDataSource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JdbcExpenseRepositoryTest {

    @ClassRule
    public static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16")
                    .withDatabaseName("testdb")
                    .withUsername("test")
                    .withPassword("test");

    private ExpenseRepository expenseRepository;

    @Before
    public void setUp() throws Exception {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setUrl(postgres.getJdbcUrl());
        dataSource.setUser(postgres.getUsername());
        dataSource.setPassword(postgres.getPassword());

        expenseRepository = new JdbcExpenseRepository(dataSource);
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS expenses (" +
                            "id UUID PRIMARY KEY, " +
                            "user_id UUID NOT NULL, " +
                            "amount NUMERIC(12,2) NOT NULL, " +
                            "category_id UUID NOT NULL, " +
                            "created_at DATE NOT NULL, " +
                            "description TEXT)"
            );

            stmt.execute("DELETE FROM expenses");
        }
    }

    @Test
    public void save_shouldPersistExpense() throws SQLException {
        UUID userId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();

        Money amount = new Money(new BigDecimal("30.00"));
        Expense expense = Expense.withDefaults(
                userId,
                amount,
                categoryId,
                "Dinner"
        );
        expenseRepository.save(expense);

        List<Expense> expenses = expenseRepository.findByUserId(userId);

        assertEquals(1, expenses.size());
        assertEquals(new BigDecimal("30.00"), expenses.getFirst().getAmount().getAmount());
    }

    @Test
    public void findByUserId_shouldReturnOnlyThatUsersExpenses() throws SQLException {
        UUID user1 = UUID.randomUUID();
        UUID user2 = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();

        expenseRepository.save(Expense.withDefaults(user1, new Money(new BigDecimal("10.00")), categoryId, "Coffee"));

        expenseRepository.save(Expense.withDefaults(user2, new Money(new BigDecimal("20.00")), categoryId, "Taxi"));

        expenseRepository.save(Expense.withDefaults(user1, new Money(new BigDecimal("15.00")), categoryId, "Snack"));

        List<Expense> user1Expenses = expenseRepository.findByUserId(user1);

        assertEquals(2, user1Expenses.size());
        assertTrue(
                user1Expenses.stream()
                        .allMatch(e -> e.getUserId().equals(user1))
        );
    }
}

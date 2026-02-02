package org.harisw.expensetracker.infrastructure.repository;

import org.harisw.expensetracker.domain.model.Expense;
import org.harisw.expensetracker.domain.model.Money;
import org.harisw.expensetracker.domain.repository.ExpenseRepository;
import org.harisw.expensetracker.infrastructure.db.DbConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JdbcExpenseRepository implements ExpenseRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbcExpenseRepository.class);

    @Override
    public Expense save(Expense expense) throws SQLException {
        String sql = "INSERT INTO expenses (id, user_id, amount, category_id, description, created_at) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.get();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, expense.getId());
            ps.setObject(2, expense.getUserId());
            ps.setBigDecimal(3, expense.getAmount().getAmount());
            ps.setObject(4, expense.getCategoryId());
            ps.setString(5, expense.getDescription());
            ps.setDate(6, Date.valueOf(expense.getCreatedAt()));

            log.info("Saving expense: {}", expense.getId());
            ps.executeUpdate();
            return expense;
        }
    }

    @Override
    public List<Expense> findAll() throws SQLException {
        String sql = "SELECT * FROM expenses";
        List<Expense> result = new ArrayList<>();

        try (Connection conn = DbConnection.get();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    UUID id = (UUID) rs.getObject("id");
                    UUID userId = (UUID) rs.getObject("userId");
                    BigDecimal amount = rs.getBigDecimal("amount");
                    UUID categoryId = (UUID) rs.getObject("category_id");
                    LocalDate date = rs.getDate("created_at").toLocalDate();
                    String desc = rs.getString("description");

                    result.add(new Expense(id, userId, new Money(amount), categoryId, desc, date));
                }
            }
        }
        return result;
    }

    @Override
    public List<Expense> findByUserId(UUID userId) throws SQLException {
        String sql = "SELECT * FROM expenses WHERE user_id = ?";
        List<Expense> result = new ArrayList<>();

        try (Connection conn = DbConnection.get();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    UUID id = (UUID) rs.getObject("id");
                    BigDecimal amount = rs.getBigDecimal("amount");
                    UUID categoryId = (UUID) rs.getObject("category_id");
                    LocalDate date = rs.getDate("created_at").toLocalDate();
                    String desc = rs.getString("description");

                    result.add(new Expense(id, userId, new Money(amount), categoryId, desc, date));
                }
            }
        }
        return result;
    }
}

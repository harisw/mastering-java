package org.harisw.expensetracker.infrastructure.repository;

import org.harisw.expensetracker.domain.model.Expense;
import org.harisw.expensetracker.domain.model.Money;
import org.harisw.expensetracker.domain.repository.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JdbcExpenseRepository implements ExpenseRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbcExpenseRepository.class);
    private final DataSource dataSource;

    public JdbcExpenseRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Expense save(Expense expense) throws SQLException {
        String sql = "INSERT INTO expenses (user_id, amount, category_id, description) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, expense.getUserId());
            ps.setBigDecimal(2, expense.getAmount().getAmount());
            ps.setObject(3, expense.getCategoryId());
            ps.setString(4, expense.getDescription());

            log.info("Saving expense: {}", expense.getId());
            ps.executeUpdate();
            return expense;
        }
    }

    @Override
    public List<Expense> findAll() throws SQLException {
        String sql = "SELECT * FROM expenses";
        List<Expense> result = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    Long userId = rs.getLong("user_id");
                    BigDecimal amount = rs.getBigDecimal("amount");
                    UUID categoryId = (UUID) rs.getObject("category_id");
                    Instant createdAt = rs.getTimestamp("created_at").toInstant();
                    String desc = rs.getString("description");

                    result.add(new Expense(id, userId, new Money(amount), categoryId, desc, createdAt));
                }
            }
        }
        return result;
    }

    @Override
    public List<Expense> findByUserId(Long userId) throws SQLException {
        String sql = "SELECT * FROM expenses WHERE user_id = ?";
        List<Expense> result = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    BigDecimal amount = rs.getBigDecimal("amount");
                    UUID categoryId = (UUID) rs.getObject("category_id");
                    Instant createdAt = rs.getTimestamp("created_at").toInstant();
                    String desc = rs.getString("description");

                    result.add(new Expense(id, userId, new Money(amount), categoryId, desc, createdAt));
                }
            }
        }
        return result;
    }
}

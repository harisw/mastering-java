import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.harisw.expensetracker.domain.model.Expense;
import org.harisw.expensetracker.domain.model.Money;
import org.harisw.expensetracker.domain.repository.ExpenseRepository;
import org.harisw.expensetracker.infrastructure.db.DbConnection;

public class JdbcExpenseRepository implements ExpenseRepository {

    @Override
    public Expense save(Expense expense) throws SQLException {
        String sql = "INSERT INTO expenses (id, user_id, amount, description, created_at) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, expense.getId());
            ps.setObject(2, expense.getUserId());
            ps.setBigDecimal(3, expense.getAmount().getAmount());
            ps.setString(4, expense.getDescription());
            ps.setDate(5, Date.valueOf(expense.getDate()));

            ps.executeUpdate();
            return expense;
        }
    }

    @Override
    public List<Expense> findByUserId(UUID userId) throws SQLException {
        String sql = "SELECT * FROM expenses WHERE user_id = ?";
        List<Expense> result = new ArrayList<>();

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    UUID id = (UUID) rs.getObject("id");
                    BigDecimal amount = rs.getBigDecimal("amount");
                    LocalDate time = rs.getDate("created_at").toLocalDate();
                    String desc = rs.getString("description");

                    result.add(new Expense(id, userId, new Money(amount), time, desc));
                }
            }
        }
        return result;
    }
}

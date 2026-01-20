package org.harisw.expensetracker.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.harisw.expensetracker.domain.model.User;

public interface UserRepository {
    Optional<User> findById(UUID userId);
    User save(User user);
}
package com.harisw.springexpensetracker.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ExpenseJpaRepository extends JpaRepository<ExpenseJpaEntity, Long> {

    Optional<ExpenseJpaEntity> findByPublicId(UUID publicId);

    void deleteByPublicId(UUID publicId);
}

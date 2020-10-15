package ru.ilin.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilin.wallet.models.Operation;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
}

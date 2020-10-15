package ru.ilin.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.ilin.wallet.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}

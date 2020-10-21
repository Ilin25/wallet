package ru.ilin.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.ilin.wallet.models.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

}

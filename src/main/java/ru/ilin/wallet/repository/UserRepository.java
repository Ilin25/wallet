package ru.ilin.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.ilin.wallet.models.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    //Возвращает всех пользователей в алфавитном порядке порядке
    @Query(value = "SELECT * FROM users ORDER BY name_user", nativeQuery = true)
    List<User> getAllUser();

    //возвращает пользователя по id
    @Query(value = "SELECT * FROM users u WHERE u.id=:id",nativeQuery = true)
        User getUserById(@Param("id") int id);

    @Query(value = "INSERT INTO users u (id, name_user) VALUES (u.id = :user.id, u.name_user = :user.name)", nativeQuery = true)
    void addUser(@Param("user") User user);

}

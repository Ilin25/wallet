package ru.ilin.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.ilin.wallet.models.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    //Возвращает всех пользователей в алфавитном порядке порядке
    @Query(value = "SELECT * FROM users ORDER BY name_user", nativeQuery = true)
    List<User> getAllUser();

    //возвращает пользователя по id
    @Query(value = "SELECT * FROM users u WHERE u.id=:id",nativeQuery = true)
        User getUserById(@Param("id") int id);

    //добавляет пользователя в базу
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users(name_user) VALUES (:name)", nativeQuery = true)
    void addUser(@Param("name") String name);

    //изменяет запись пользователя в базе
    @Modifying
    @Transactional
    @Query(value = "update User u set u.name = ?2 WHERE u.id = ?1")
    void updateUserById(int id, String name);

    //удаляет пользователя из базы
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM users u WHERE u.id =:id", nativeQuery = true)
    void removeUserById(@Param("id") int id);

}

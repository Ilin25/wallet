package ru.ilin.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
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
    @Query(value = "INSERT INTO users(id,name_user) VALUES (:id,:name)", nativeQuery = true)
    void addUser(@Param("id") int id,String name);
    //изменяет запись пользователя в базе
    //удаляет пользователя из базы

}

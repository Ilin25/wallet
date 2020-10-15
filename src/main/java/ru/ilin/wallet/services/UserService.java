package ru.ilin.wallet.services;

import ru.ilin.wallet.models.User;

import java.util.List;

public interface UserService {
    User getUserId(int id);//возвращает из базы пользователя по id

    void addUser(User user);//добавляет  нового пользователя в базу

    void removeUser(int id);//удаляет пользователя из базы по id

    void updateUser(User user);//редактирует пользователя в базе

    List<User> getAllUser();//возвращает список всех пользователей из базы
}

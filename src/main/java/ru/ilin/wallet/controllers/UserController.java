package ru.ilin.wallet.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ilin.wallet.models.User;
import ru.ilin.wallet.services.UserService;

@Controller
@Getter
@Setter
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userActionMenu")//Возврат формы отображения меню пользователей
    public String userActionMenuForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.getAllUser());
        return "/wallet/users/userActionMenu";
    }

    @GetMapping("/addUser")//возврат формы добавления пользователя
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "wallet/users/addUser";
    }

    @PostMapping("/addUser")//добавление пользователя в базу
    public String addUser(User user) {
        userService.addUser(user);
        return "redirect:/user/userActionMenu";
    }

    @GetMapping("/updateUser/{id}")//принимает id редактируемого польз
    public String updateUserForm(@PathVariable("id") int id, Model model) {
        User user = userService.getUserId(id);
        model.addAttribute("user", user);//передаем в модель
        return "/wallet/users/updateUser";//возвращает форму ввода для редактирования
    }

    @PostMapping("/updateUser")
    public String updateUser(User user) {
        userService.updateUser(user);//обновляет в базе полученного из формы пользователя
        return "redirect:/user/userActionMenu";
    }

    @GetMapping("/deleteUser/{id}")//удаление пользователя по id
    public String deleteUser(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:/user/userActionMenu";
    }


}

package ru.ilin.wallet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller// контроллер основной страницы
@RequestMapping("/")
public class IndexController {


    @GetMapping("/")//возвращает основную страницу
    public String getIndexForm() {
        return "wallet/index";
    }

}

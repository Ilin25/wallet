package ru.ilin.wallet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller// контроллер основной страницы
public class IndexController {


    @GetMapping("/")//возвращает основную страницу
    public String getIndexForm() {
        return "wallet/index";
    }

}

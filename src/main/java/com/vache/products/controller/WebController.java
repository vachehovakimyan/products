package com.vache.products.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Данный контроллер отвечает за HTML страницы приложения
 */
@Controller
public class WebController {

    // Главная страница
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String dashboard() {
        return "dashboard.html";
    }

}

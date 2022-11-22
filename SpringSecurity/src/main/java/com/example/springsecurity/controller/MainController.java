package com.example.springsecurity.controller;

import com.example.springsecurity.security.PersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MainController {

    @GetMapping("/index")
    public String index() {

        //Получаем объект аутентификации с помощью Spring SecurityContextHolder и в нем вызываем метод аутентификации. Из потока для текущего пользователя мы получаем  объект, который был положен в сессию после аутентификации
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
//        System.out.println("ID= "+personDetails.getPerson().getId());
//        System.out.println("login= "+personDetails.getPerson().getLogin());
//        System.out.println("password= "+personDetails.getPerson().getPassword());


        return "index";
    }


}

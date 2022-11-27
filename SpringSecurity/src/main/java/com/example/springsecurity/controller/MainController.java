package com.example.springsecurity.controller;

import com.example.springsecurity.security.PersonDetails;
import com.example.springsecurity.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.management.relation.Role;


@Controller
public class MainController {

    private final ProductService productService;

    @Autowired
    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/index")
    public String index() {

        //Получаем объект аутентификации с помощью Spring SecurityContextHolder и в нем вызываем метод аутентификации. Из потока для текущего пользователя мы получаем  объект, который был положен в сессию после аутентификации
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        String role = personDetails.getPerson().getRole();

        if(role.equals("ROLE_ADMIN")){
            return "redirect:/admin";
        }

        return "index";
    }

    @GetMapping("/product")
    public String getAllProduct(Model model) {
        model.addAttribute("product", productService.getAllProducts());
        return "product/product";
    }

    @GetMapping("/product/info/{id}")
    public String ProductInfo(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productService.getProductId(id));
        return "product/infoProduct";
    }

}

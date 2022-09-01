package ru.chashka.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("title", "Main Page");
        return "home";
    }

    @GetMapping("/about")
    public String aboutUs(Model model){
        model.addAttribute("title", "About Us");
        return "about";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }
}

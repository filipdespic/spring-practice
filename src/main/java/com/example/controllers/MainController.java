package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/home")
    public String home(Model page) {
        page.addAttribute("username", "Katy");
        page.addAttribute("color", "red");
        return "home.html";
    }

}

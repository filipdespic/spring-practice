package com.example.controllers;

import com.example.model.Product;
import com.example.services.LoggedUserManagementService;
import com.example.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    private final ProductService productService;
    private final LoggedUserManagementService loggedUserManagementService;

    public MainController(ProductService productService, LoggedUserManagementService loggedUserManagementService) {
        this.productService = productService;
        this.loggedUserManagementService = loggedUserManagementService;
    }

    @GetMapping("/main")
    public String home(@RequestParam(required = false) String logout, Model model) {
        if (logout != null) {
            loggedUserManagementService.setUsername(null);
        }
        String username = loggedUserManagementService.getUsername();
        if (username == null) {
            return "redirect:/";
        }
        model.addAttribute("username", username);
        return "main.html";
    }

    @GetMapping("/products")
    public String viewProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);

        return "products.html";
    }

    @PostMapping("/products")
    public String addProduct(
            Product p,
            Model model
    ) {
        productService.addProduct(p);
        var products = productService.findAll();
        model.addAttribute("products", products);
        return "products.html";
    }

    @GetMapping("/home/{username}/{color}")
    public String home(@PathVariable String color, @PathVariable String username, Model page) {
        page.addAttribute("username", username);
        page.addAttribute("color", color);
        return "home.html";
    }

}

package com.docencia.tutorial01.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Welcome to Spring Boot");
        model.addAttribute("subtitle", "An Spring Boot Eafit App");
        return "home/index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About Us - Online Store");
        model.addAttribute("subtitle", "About Us");
        model.addAttribute("description", "This is a tutorial application to demonstrate Spring Boot features.");
        model.addAttribute("author", "Developed by: Paula Llanos");
        return "home/about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("title", "Contact Information");
        model.addAttribute("subtitle", "Contact Us");
        model.addAttribute("email", "paulita_estrella@gmail.com");
        model.addAttribute("address", "Cra 89 #12-34, Medellín, Colombia");
        model.addAttribute("phone", "31498572485993");
        return "home/contact";
    }

}

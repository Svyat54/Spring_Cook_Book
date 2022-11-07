package com.example.spring_cook.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Task4Controller {
    @GetMapping("/addRecipe")
    public String forthA(Authentication auth) {
        if (auth.getPrincipal() != null)
            return "addRecipe";
        else
            return "index";
    }
}



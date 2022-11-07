package com.example.spring_cook.controllers;

import com.example.spring_cook.entities.Recipe;
import com.example.spring_cook.utilites.Reader;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

@Controller
public class InitializeBaseController {
    private static LinkedList<Recipe> book = new LinkedList<>();
    private static LinkedList<String> listNames = new LinkedList<>();
    public static Set<String> setOfIngredients = new TreeSet<>();

    public static LinkedList<Recipe> getBook() {
        return book;
    }
    public static LinkedList<String> getListNames() {
        return listNames;
    }
    public static Set<String> getSetOfIngredients() {
        return setOfIngredients;
    }

    private String[] files = {"Caucasian.txt", "French.txt", "Mexican.txt", "Russian.txt"};

    @GetMapping("/")
    public String initializeBase(Model model, Authentication auth) throws IOException {
        flushBase();
        for(String path : files)
            book.addAll(Reader.getCuisine(path));
        initListNames();
        initSetOfIngredients();
        model.addAttribute("listNames", listNames);
        model.addAttribute("setOfIngredients", setOfIngredients);
        model.addAttribute("allTags", Task2Controller.getAllTags());
        model.addAttribute("allCuisine", Task3Controller.getAllCuisine());
        if (auth != null)
            model.addAttribute("isAdmin", auth.getAuthorities().toString().contains("ROLE_ADMIN"));
        return "index";
    }

    private void flushBase(){
        while (!book.isEmpty())
            book.removeFirst();
    }

    private void initListNames(){
        if(!book.isEmpty()) {
            for(Recipe n : book){
                listNames.add(n.getName());
            }
        }
    }

    private void initSetOfIngredients() {
        HashSet<String> temp = new HashSet<>();
        if (!book.isEmpty())
            for (Recipe r: book)
                if (!r.getIngredients().isEmpty())
                    temp.addAll(r.getIngredients());
        setOfIngredients.addAll(temp);
    }

}

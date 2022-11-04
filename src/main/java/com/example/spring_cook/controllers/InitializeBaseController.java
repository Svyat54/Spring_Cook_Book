package com.example.spring_cook.controllers;

import com.example.spring_cook.entities.Recipe;
import com.example.spring_cook.utilites.Reader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.LinkedList;

@Controller
public class InitializeBaseController {
    private LinkedList<Recipe> book = new LinkedList<>();
    private String[] files = {"Caucasian.txt", "French.txt", "Mexican.txt", "Russian.txt"};
    private LinkedList<String> listNames = new LinkedList<>();
    @GetMapping("/")
    public String initializeBase(Model model) throws IOException {
        flushBase();
        for(String path : files)
            book.addAll(Reader.getCuisine(path));
        initListNames();
        model.addAttribute("listNames", listNames);
        return "index";
    }
    @GetMapping("/firstTask")
    public String infoRecipe(Model model, @RequestParam(required = false) String name){
        Recipe recipe = getInfoRecipe(name);
        if(recipe != null) {
            model.addAttribute("cuisine", recipe.getCuisine());
            model.addAttribute("type", recipe.getType());
            model.addAttribute("ingredients", recipe.getIngredients());
            model.addAttribute("listNames", listNames);
        }
        return "index";
    }
    private Recipe getInfoRecipe(String name) {
        for (Recipe recipe : book)
            if (recipe.getName().equals(name))
                return recipe;
        return null;
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

}

package com.example.spring_cook.controllers;

import com.example.spring_cook.entities.Recipe;
import com.example.spring_cook.utilites.Writer;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

@Controller
public class Task4Controller {
    @GetMapping("/add")
    public String add(){
        return "addRecipe";
    }
    @GetMapping("/delete")
    public String delete(Model model){
        model.addAttribute("names", InitializeBaseController.getListNames());
        return "dellRecipe";
    }

    @GetMapping("/addRecipe")
    public String forthA(Authentication auth, @RequestParam String name, @RequestParam String cuisine,
                         @RequestParam String type, @RequestParam String addIngrTA, @RequestParam String addTagTA,
                         @RequestParam String technology, @RequestParam String link, @RequestParam int rating)
                        throws IOException {
        LinkedList<String> getTagList = getTags(cuisine, type, addTagTA);
        LinkedList<String> ingr = new LinkedList<> (Arrays.asList(addIngrTA.split(", ")));
        if (auth.getPrincipal() != null) {
            Recipe recipe = new Recipe(name, cuisine, ingr, getTagList, technology, link, rating);
            Writer.writeFile(recipe);
            return "addRecipe";
        }
        else
            return "index";
    }
    @GetMapping("/dellRecipe")
    public String forthB(Model model, Authentication auth, @RequestParam String name) throws IOException {
        InitializeBaseController.deleteRecipeByName(name);
        InitializeBaseController.initListNames();
        Writer.refreshBase(InitializeBaseController.getBook());
        model.addAttribute("names", InitializeBaseController.getListNames());
        return "dellRecipe";
    }


    private LinkedList<String> getTags(String cuisine, String type, String tags){
        LinkedList<String> listTags = new LinkedList<>();
        listTags.add(cuisine);
        listTags.add(type);
        String[] arr = tags.split(", ");
        listTags.addAll(Arrays.asList(arr));
        return listTags;
    }

}



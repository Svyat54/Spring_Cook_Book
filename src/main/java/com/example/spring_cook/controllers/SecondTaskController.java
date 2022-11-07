package com.example.spring_cook.controllers;

import com.example.spring_cook.entities.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class SecondTaskController {

    static class SortByRating implements Comparator<Recipe>{
        public int compare(Recipe a, Recipe b){
            return b.getRating() - a.getRating();
        }
    }

    @GetMapping("/secondA")
    public String secondA(Model model){
        model.addAttribute("allTags", getAllTags());
        return "Task2A";
    }

    public static Set<String> getAllTags(){
        Set<String> tagsSet = new TreeSet<>();
        for(Recipe recipe : InitializeBaseController.getBook())
            tagsSet.addAll(recipe.getTags());
        return tagsSet;
    }

    @GetMapping("/secondB")
    public String secondB(Model model, @RequestParam(required = false) String tag){
        LinkedList<Recipe> topRecipe = new LinkedList<>();
        for(Recipe recipe : InitializeBaseController.getBook())
            if(recipe.getTags().contains(tag))
                topRecipe.add(recipe);
        topRecipe.sort(new SortByRating());
        while (topRecipe.size() > 3)
            topRecipe.removeLast();
        model.addAttribute("top3", topRecipe);
        return "Task2B";
    }

    @GetMapping("/secondC")
    public String secondC(Model model) {
        String[] tags = {"первые блюда", "вторые блюда", "десерты"};
        LinkedList<Recipe> recipes = new LinkedList<>();
        for (String tag : tags)
            recipes.add(getRandomDishByTag(tag));
        model.addAttribute("dinner", recipes);
        return "Task2C";
    }

    @GetMapping("/secondD")
    public String secondD(Model model) {
        model.addAttribute("recipe", getRandomDishByTag("завтраки"));
        return "Task2D";
    }

    private static Recipe getRandomDishByTag(String tag){
        LinkedList<Recipe> recipesByTag = new LinkedList<>();
        for (Recipe recipe : InitializeBaseController.getBook())
            if(recipe.getTags().contains(tag))
                recipesByTag.add(recipe);
        int randomIndex = (int) (Math.random()*recipesByTag.size());
        return recipesByTag.get(randomIndex);
    }

}

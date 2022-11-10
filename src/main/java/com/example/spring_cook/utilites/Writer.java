package com.example.spring_cook.utilites;

import com.example.spring_cook.entities.Recipe;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;

public class Writer {
    final static String files = "Cuisine.txt";

    public static void writeFile(Recipe recipe) throws IOException {
        String string = "\n";
        string = string.concat(recipe.getName() + "\n-");
        string = string.concat(getStringFromCollection(recipe.getTags()) + "\n-");
        string = string.concat(getStringFromCollection(recipe.getIngredients()) + "\n-");
        string = string.concat(recipe.getTechnology() + "\n-");
        string = string.concat(recipe.getRating() + "\n");
        Files.writeString(Paths.get(files), string, StandardOpenOption.APPEND);
    }

    public static void refreshBase(LinkedList<Recipe> recipes) throws IOException {
        FileWriter fw = new FileWriter(files, false);
        for (int i = 0; i < recipes.size(); i++) {
            if (i == 0) {
                fw.write(getStringFromRecipe(recipes.get(i), true));
            }else{
                fw.write(getStringFromRecipe(recipes.get(i), false));
            }
        }
        fw.close();
    }

    public static String getStringFromRecipe(Recipe recipe, boolean isFirstRecipe) throws IOException {
        String string = "";
        if (!isFirstRecipe)
            string = string.concat("\n");
        string = string.concat(recipe.getName() + "\n-");
        string = string.concat(getStringFromCollection(recipe.getTags()) + "\n-");
        string = string.concat(getStringFromCollection(recipe.getIngredients()) + "\n-");
        string = string.concat(recipe.getTechnology() + "\n-");
        string = string.concat(recipe.getRating() + "\n");
        return string;
    }

    private static String getStringFromCollection(LinkedList<String> list) {
        String ingr = "";
        for (String ingredients : list) {
            ingr = ingr.concat(ingredients + ", ");
        }
        return ingr.substring(0, ingr.length() - 2);
    }
}

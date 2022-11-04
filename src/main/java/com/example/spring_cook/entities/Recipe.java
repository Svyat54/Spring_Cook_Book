package com.example.spring_cook.entities;

import lombok.Data;

import java.util.LinkedList;
@Data
public class Recipe {
    private String name;
    private String cuisine;
    private String type;
    private LinkedList<String> ingredients;
    private LinkedList<String> tags;
    private String technology;
    private String link;
    private int rating;

    public Recipe(String name, String cuisine, LinkedList<String> ingredients, LinkedList<String> tags,
                  String technology, int rating) {
        this.name = name;
        this.cuisine = cuisine;
        this.type = tags.get(1);
        this.ingredients = ingredients;
        this.tags = tags;
        this.technology = technology;
        this.rating = rating;
        this.link = "youtube.com";
    }

    public Recipe(String name, String cuisine, LinkedList<String> ingredients, LinkedList<String> tags,
                  String technology, String link, int rating) {
        this.name = name;
        this.cuisine = cuisine;
        this.type = tags.get(1);
        this.ingredients = ingredients;
        this.tags = tags;
        this.technology = technology;
        this.link = link;
        this.rating = rating;
    }

    public void printRecipe() {
        System.out.println(this.name);
        System.out.println(this.cuisine);
        System.out.println(this.type);
        System.out.println(this.ingredients);
        System.out.println(this.tags);
        System.out.println(this.technology);
        System.out.println(this.link);
        System.out.println(this.rating);
        System.out.println();
    }
}

package com.qxcode.Model;

public class Question {
    private int id;
    private String description;
    private String title;
    private int difficulty;
    private String examples;
    private int categoryId;
    private int favorite;

    public Question(int id, String description, String title, int difficulty, String examples, int categoryId, int favorite) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.difficulty = difficulty;
        this.examples = examples;
        this.categoryId = categoryId;
        this.favorite = favorite;
    }

    public Question() {};

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getExamples() {
        return examples;
    }

    public int getCategory() {
        return categoryId;
    }

    public int getFavorite() {
        return favorite;
    }

}

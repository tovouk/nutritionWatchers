package com.josehinojo.nutritionwatchers.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Food {
    @PrimaryKey (autoGenerate = true)
    public int foodID;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "category")
    public String category;

    @ColumnInfo(name = "calories")
    public int calories;

    @ColumnInfo(name = "serving_size")
    public int serving_size;

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getServing_size() {
        return serving_size;
    }

    public void setServing_size(int serving_size) {
        this.serving_size = serving_size;
    }

    public Food(int foodID, String name, String category, int calories, int serving_size) {
        this.foodID = foodID;
        this.name = name;
        this.category = category;
        this.calories = calories;
        this.serving_size = serving_size;
    }

    @Ignore
    public Food(String name, String category, int calories, int serving_size) {
        this.name = name;
        this.category = category;
        this.calories = calories;
        this.serving_size = serving_size;
    }
}

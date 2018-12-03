package com.josehinojo.nutritionwatchers.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    public int exerciseID;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "calories_burned")
    public int caloriesBurnedPerMinute;

    public int getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(int exerciseID) {
        this.exerciseID = exerciseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCaloriesBurnedPerMinute() {
        return caloriesBurnedPerMinute;
    }

    public void setCaloriesBurnedPerMinute(int caloriesBurnedPerMinute) {
        this.caloriesBurnedPerMinute = caloriesBurnedPerMinute;
    }

    public Exercise(int exerciseID, String name, int caloriesBurnedPerMinute) {
        this.exerciseID = exerciseID;
        this.name = name;
        this.caloriesBurnedPerMinute = caloriesBurnedPerMinute;
    }

    @Ignore
    public Exercise(String name, int caloriesBurnedPerMinute) {
        this.name = name;
        this.caloriesBurnedPerMinute = caloriesBurnedPerMinute;
    }
}

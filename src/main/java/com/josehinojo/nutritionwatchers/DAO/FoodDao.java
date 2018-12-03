package com.josehinojo.nutritionwatchers.DAO;

import com.josehinojo.nutritionwatchers.Entities.Food;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface FoodDao {

    @Query("Select * from food")
    List<Food> getAll();

    @Query("select * from food where name like :searchString + '%'")
    List<Food> getBySearch(String searchString);

    @Insert
    void insertAll(Food... foods);

    @Insert
    void insert(Food food);

    @Insert
    void delete(Food food);

}

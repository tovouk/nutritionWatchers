package com.josehinojo.nutritionwatchers.DAO;

import com.josehinojo.nutritionwatchers.Entities.Exercise;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ExerciseDao {

    @Query("Select * from exercise")
    List<Exercise> getAll();

    //TODO fix this query
    @Query("select * from exercise where name like :name")
    List<Exercise> getByName(String name);

    @Query("select * from exercise where exerciseID = :exerciseID")
    Exercise loadByID(int exerciseID);

    @Insert
    void insertExercises(Exercise... exercises);

    @Insert
    void insert(Exercise exercise);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateExercise(Exercise exercise);

    @Delete
    void delete(Exercise exercise);



}

package com.josehinojo.nutritionwatchers.DAO;

import com.josehinojo.nutritionwatchers.Entities.Users;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    List<Users> getAllUsers();

    @Query("select * from users where nameID = :userID")
    Users loadUserByID(int userID);

    @Query("select * from users where email = :emailString and password = :passwordString")
    Users loadUserByEmailandPassword(String emailString,String passwordString);

    @Query("update users SET name = :nameString,email = :emailString,password = :passwordString," +
            "weight = :weight,height = :height,gender = :gender,age = :age WHERE nameID = :id")
    void updateUser(int id,String nameString, String emailString,String passwordString,
                    int weight, int height,String gender, int age);

    @Query("update users set weight = :weight WHERE nameID = :id")
    void updateWeight(int id,int weight);

    @Query("update users set nameID = :id WHERE email = :email")
    void updateNameID(int id,String email);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateUser(Users user);

    @Insert
    void insertUser(Users user);


}

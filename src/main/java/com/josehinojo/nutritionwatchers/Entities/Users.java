package com.josehinojo.nutritionwatchers.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Users {
    @PrimaryKey (autoGenerate = true)
    public int nameID;

    @ColumnInfo (name = "name")
    public String name;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "weight")
    public int weight;

    @ColumnInfo(name = "height")
    public int height;

    @ColumnInfo(name = "gender")
    public String gender;

    @ColumnInfo(name = "age")
    public int age;


    public int getNameID() {
        return nameID;
    }

    public void setNameID(int nameID) {
        this.nameID = nameID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Users(int nameID, String name, String email, String password, int weight, int height, String gender, int age) {
        this.nameID = nameID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.age = age;
    }
    @Ignore
    public Users(String name, String email, String password, int weight, int height, String gender, int age) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.age = age;
    }

}

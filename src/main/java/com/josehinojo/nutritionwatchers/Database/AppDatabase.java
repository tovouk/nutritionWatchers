package com.josehinojo.nutritionwatchers.Database;

import android.content.Context;
import android.util.Log;

import com.josehinojo.nutritionwatchers.DAO.ExerciseDao;
import com.josehinojo.nutritionwatchers.DAO.FoodDao;
import com.josehinojo.nutritionwatchers.DAO.UserDao;
import com.josehinojo.nutritionwatchers.Entities.DateConverter;
import com.josehinojo.nutritionwatchers.Entities.Exercise;
import com.josehinojo.nutritionwatchers.Entities.Food;
import com.josehinojo.nutritionwatchers.Entities.Users;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Users.class,Food.class,Exercise.class},version = 1,exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME= "nutritiondb";
    private static AppDatabase databaseInstance;

    public static AppDatabase getDatabaseInstance(Context context){
        if(databaseInstance == null){
            synchronized (LOCK){
                Log.d(LOG_TAG,"Creating new database instance");
                databaseInstance = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,
                        DATABASE_NAME).allowMainThreadQueries().build();
            }
        }
        Log.d(LOG_TAG,"Getting database instance");
        return databaseInstance;
    }

    public abstract UserDao userDao();
    public abstract FoodDao foodDao();
    public abstract ExerciseDao exerciseDao();

}

package com.josehinojo.nutritionwatchers.Fitness;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.josehinojo.nutritionwatchers.Database.AppDatabase;
import com.josehinojo.nutritionwatchers.Entities.Exercise;
import com.josehinojo.nutritionwatchers.Entities.Users;
import com.josehinojo.nutritionwatchers.R;

import static com.josehinojo.nutritionwatchers.MainActivity.USERID;

public class AddExerciseActivity extends AppCompatActivity {


    @BindView(R.id.name) EditText name;
    @BindView(R.id.calories)EditText calories;

    @OnClick(R.id.submit)
    public void addExercise(View view){
        //TODO possibly create a util class to generate randomNumbers given a max number
        double randomNum = Math.random();
        double zeroToHundred = randomNum * 100;
        int finalRandom = (int)zeroToHundred +1;
        while(appDatabase.exerciseDao().loadByID(finalRandom) != null){
            randomNum = Math.random();
            zeroToHundred = randomNum * 100;
            finalRandom = (int)zeroToHundred +1;
        }

        String nameText = name.getText().toString();
        String calorieText = calories.getText().toString();

        if(nameText.length()<=0 ||  calorieText.length()<=0 ){
            Toast.makeText(getApplicationContext(), "Are you missing something?", Toast.LENGTH_SHORT).show();
        }else{
            int calorieBurn = Integer.parseInt(calorieText);
            Exercise exercise = new Exercise(finalRandom,nameText,calorieBurn);
            appDatabase.exerciseDao().insert(exercise);
            finish();
        }

    }

    private Users user;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        ButterKnife.bind(this);
        appDatabase= AppDatabase.getDatabaseInstance(getApplicationContext());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        user = appDatabase.userDao().loadUserByID(sharedPreferences.getInt(USERID,-1));


    }

    @Override
    protected void onResume() {
        super.onResume();
        onRestart();
    }
}

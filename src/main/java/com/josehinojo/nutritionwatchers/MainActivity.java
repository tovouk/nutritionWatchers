package com.josehinojo.nutritionwatchers;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.josehinojo.nutritionwatchers.Database.AppDatabase;
import com.josehinojo.nutritionwatchers.Entities.Users;
import com.josehinojo.nutritionwatchers.Fitness.ExerciseFragment;
import com.josehinojo.nutritionwatchers.Profile.ProfileFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    //TODO instantiate database, if no user is logged in force sign in activity
    //before adding food entries, exercise entries, weigh ins, daily stats
    //TODO add signout menu on toolbar


    public static String USERID = "UserID";

    @BindView(R.id.bottomnav)BottomNavigationView bottomNav;

    private Users user;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private AppDatabase appDatabase;

    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        appDatabase = AppDatabase.getDatabaseInstance(getApplicationContext());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        fragmentManager = getSupportFragmentManager();


        if(sharedPreferences.getInt(USERID,-1) == -1) {
            forceLoginScreen();
        }else{
            user = appDatabase.userDao().loadUserByID(sharedPreferences.getInt(USERID,-1));
//            Toast.makeText(this,user.getNameID() + " " + user.getName(), Toast.LENGTH_SHORT).show();
        }

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                fragmentTransaction = fragmentManager.beginTransaction();
                switch(item.getItemId()){
                    case R.id.foodmenu:

                        break;
                    case R.id.exercisemenu:
                        fragmentTransaction.replace(R.id.mainFrame,new ExerciseFragment()).commit();
                        break;
                    case R.id.profilemenu:
                        fragmentTransaction.replace(R.id.mainFrame,new ProfileFragment()).commit();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
        bottomNav.setSelectedItemId(R.id.profilemenu);
        List<Users> usersList = appDatabase.userDao().getAllUsers();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRestart();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        if(user == null){

        }else{
            getMenuInflater().inflate(R.menu.signout,menu);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.signOut:
                editor = sharedPreferences.edit();
                editor.putInt(USERID,-1);
                editor.apply();
                forceLoginScreen();
                break;

        }


        return super.onOptionsItemSelected(item);
    }
    public void forceLoginScreen(){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void chooseFragment(Fragment fragment){

    }

}

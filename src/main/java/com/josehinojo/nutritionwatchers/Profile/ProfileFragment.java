package com.josehinojo.nutritionwatchers.Profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.josehinojo.nutritionwatchers.Database.AppDatabase;
import com.josehinojo.nutritionwatchers.Entities.Users;
import com.josehinojo.nutritionwatchers.R;

import static com.josehinojo.nutritionwatchers.MainActivity.USERID;

public class ProfileFragment extends Fragment {

    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.weight)
    TextView weight;
    @BindView(R.id.height)
    TextView height;
    @BindView(R.id.gender)
    TextView gender;
    @BindView(R.id.age)
    TextView age;
    @BindView(R.id.EditButton)
    FloatingActionButton editProfile;

    @OnClick(R.id.EditButton)
    public void edit(View view){
        Toast.makeText(getContext(), "Live with it...for now.", Toast.LENGTH_SHORT).show();
    }

    private Users user;
    private SharedPreferences sharedPreferences;
    private AppDatabase appDatabase;


    public ProfileFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appDatabase = AppDatabase.getDatabaseInstance(getContext());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this,view);

        if(sharedPreferences.getInt(USERID,-1) == -1) {
            Toast.makeText(getContext(), "How did you end up here? >.>", Toast.LENGTH_SHORT).show();
        }else{
            user = appDatabase.userDao().loadUserByID(sharedPreferences.getInt(USERID,-1));
//            Toast.makeText(getContext(),user.getNameID() + " " + user.getName(), Toast.LENGTH_SHORT).show();
            name.setText(user.getName());
            email.setText(user.getEmail());
            String weightText = "Weight: " + String.valueOf(user.getWeight()) + " lbs";
            weight.setText(weightText);
            String heightText = "Height: " + String.valueOf(user.getHeight()) + "inches";
            height.setText(heightText);
            String genderText = "Gender: " + user.getGender();
            gender.setText(genderText);
            String ageText = "Age: " + String.valueOf(user.getAge()) + " years old";
            age.setText(ageText);
        }

        return view;
    }
}

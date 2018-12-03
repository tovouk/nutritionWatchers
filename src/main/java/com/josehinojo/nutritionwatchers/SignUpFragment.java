package com.josehinojo.nutritionwatchers;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.josehinojo.nutritionwatchers.Database.AppDatabase;
import com.josehinojo.nutritionwatchers.Entities.Users;

import java.util.Objects;

public class SignUpFragment extends Fragment {

    @BindView(R.id.nameText)
    EditText nameText;
    @BindView(R.id.emailText)
    EditText emailText;
    @BindView(R.id.passwordText)
    EditText passwordText;
    @BindView(R.id.weightText)
    EditText weightText;
    @BindView(R.id.heightText)
    EditText heightText;
    @BindView(R.id.genderText)
    EditText genderText;
    @BindView(R.id.ageText)
    EditText ageText;
    @BindView(R.id.signup)
    Button signUp;

    @OnClick(R.id.signup)
    public void signup(View view){
        double randomNum = Math.random();
        double zeroToHundred = randomNum * 100;
        int finalRandom = (int)zeroToHundred +1;
        while(appDatabase.userDao().loadUserByID(finalRandom) != null){
            randomNum = Math.random();
            zeroToHundred = randomNum * 100;
            finalRandom = (int)zeroToHundred +1;
        }
        String name = nameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        String textWeight = weightText.getText().toString();
        String textHeight = heightText.getText().toString();

        String gender = genderText.getText().toString();

        String textAge =ageText.getText().toString();

        if(name.length() <=0 || email.length() <= 0 || password.length() <=0 || textWeight.length() <= 0 || textHeight.length() <=0 || gender.length() <=0 || textAge.length() <= 0 ){
            Toast.makeText(getContext(), "Are you missing something?", Toast.LENGTH_SHORT).show();
        }else{
            int weight = Integer.parseInt(textWeight);
            int height = Integer.parseInt(textHeight);
            int age = Integer.parseInt(textAge);

            Users user = new Users(finalRandom,name,email,password,weight,height,gender,age);
            appDatabase.userDao().insertUser(user);
            Toast.makeText(getContext(), "Account created, go login", Toast.LENGTH_SHORT).show();
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new LoginFragment()).commit();
        }


    }

    private AppDatabase appDatabase;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        appDatabase = AppDatabase.getDatabaseInstance(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this,view);


        return view;
    }

}

package com.josehinojo.nutritionwatchers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class LoginActivity extends AppCompatActivity {

    public Fragment fragment;
    @BindView(R.id.fragment)
    FrameLayout frameLayout;
    @BindView(R.id.createAccount)
    Button createAccount;
    @BindView(R.id.loginButton)
    Button loginButton;

    @OnClick(R.id.loginButton)
    public void showLoginScreen(View view){
        fragment = new LoginFragment();
        loginButton.setVisibility(View.GONE);
        createAccount.setVisibility(View.VISIBLE);
        chooseFragment(fragment);
    }

    @OnClick(R.id.createAccount)
    public void showCreateAccount(View view){
        fragment = new SignUpFragment();
        createAccount.setVisibility(View.GONE);
        loginButton.setVisibility(View.VISIBLE);
        chooseFragment(fragment);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        fragment = new LoginFragment();
        chooseFragment(fragment);



    }

    @Override
    public void onBackPressed() {

    }

    public void chooseFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment,fragment).commit();
    }

}

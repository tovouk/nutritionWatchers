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

import static com.josehinojo.nutritionwatchers.MainActivity.USERID;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    @BindView(R.id.loginemail)
    EditText loginEmail;
    @BindView(R.id.loginpassword)
    EditText loginPassword;
    @BindView(R.id.loginSubmit)
    Button submit;


    @OnClick({R.id.loginSubmit})
    public void submit(View view){
        String email = loginEmail.getText().toString();
        String password = loginPassword.getText().toString();
        if (email.trim().equals("")){
            Toast.makeText(getContext(), "please enter an email address", Toast.LENGTH_SHORT).show();
        }else if(password.trim().equals("")){
            Toast.makeText(getContext(), "please enter a password", Toast.LENGTH_SHORT).show();
        }else {
            try {
                Users user = appDatabase.userDao().loadUserByEmailandPassword(email, password);
                editor = sharedPreferences.edit();
                editor.putInt(USERID, user.getNameID());
                editor.apply();
                Objects.requireNonNull(getActivity()).finish();
            } catch (Exception e) {
                Toast.makeText(getContext(), "no user found", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private AppDatabase appDatabase;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public LoginFragment() {

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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,view);

        // Inflate the layout for this fragment
        return view;
    }

}

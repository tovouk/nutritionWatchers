package com.josehinojo.nutritionwatchers.Fitness;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.josehinojo.nutritionwatchers.Database.AppDatabase;
import com.josehinojo.nutritionwatchers.Entities.Exercise;
import com.josehinojo.nutritionwatchers.R;

import java.util.List;


public class ExerciseFragment extends Fragment {

    //TODO add LiveData to update UI in realtime for all fragments

    @BindView(R.id.SearchBar)EditText searchBar;
    @BindView(R.id.search_button)ImageButton searchButton;
    @BindView(R.id.recyclerView)RecyclerView recyclerView;
    @BindView(R.id.addExercise)
    FloatingActionButton addExercise;

    @OnClick(R.id.addExercise)
    public void addExercise(){
        startActivity(new Intent(getActivity(),AddExerciseActivity.class));
    }

    List<Exercise> exerciseList;
    ExerciseAdapter exerciseAdapter;
    private AppDatabase appDatabase;

    public ExerciseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appDatabase = AppDatabase.getDatabaseInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);
        ButterKnife.bind(this,view);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = searchBar.getText().toString();
                exerciseList = appDatabase.exerciseDao().getByName(name);
                exerciseAdapter = new ExerciseAdapter(exerciseList);
                exerciseAdapter.setContext(getContext());
                checkExerciseList();
            }
        });

        exerciseList = appDatabase.exerciseDao().getAll();
        exerciseAdapter = new ExerciseAdapter(exerciseList);
        exerciseAdapter.setContext(getContext());
        checkExerciseList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(exerciseAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        exerciseAdapter.notifyDataSetChanged();
    }

    public void checkExerciseList(){
        if(exerciseList.size() == 0){
            Toast.makeText(getContext(), "Nothing Found", Toast.LENGTH_SHORT).show();
        }
    }

}

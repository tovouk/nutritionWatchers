package com.josehinojo.nutritionwatchers.Fitness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.josehinojo.nutritionwatchers.Entities.Exercise;
import com.josehinojo.nutritionwatchers.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ExerciseAdapter  extends RecyclerView.Adapter<ExerciseAdapter.MyViewHolder> {

    Context context;
    public List<Exercise> exerciseList;

    public void setContext(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.
                from(parent.getContext()).inflate(R.layout.exercise_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Exercise exercise = exerciseList.get(position);
        String name = "Name: " + exercise.getName();
        String calories = String.valueOf(exercise.getCaloriesBurnedPerMinute()) + " calories/min";
        holder.name.setText(name);
        holder.calories.setText(calories);
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.exerciseName)TextView name;
        @BindView(R.id.exerciseCalories)TextView calories;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public ExerciseAdapter(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }



}

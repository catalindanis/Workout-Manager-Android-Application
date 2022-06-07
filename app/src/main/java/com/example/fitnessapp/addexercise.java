package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addexercise extends AppCompatActivity {

    Button addExercise;
    EditText exerciseName,exerciseSeries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexercise);
        assignValues();

        addExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(exerciseName.getText().toString().length()>0 && exerciseSeries.getText().toString().length() >0
                        && Integer.valueOf(exerciseSeries.getText().toString()) <= 49) {
//                    Log.d("tag","check");
                    Intent intent = new Intent(getApplicationContext(), BackActivity.class);
                    intent.putExtra("name", exerciseName.getText().toString());
                    intent.putExtra("series", exerciseSeries.getText().toString());
                    setResult(1000, intent);
                    finish();
                    addexercise.super.onStop();
                    return;
                }
            }
        });
    }
    @Override
    public void onBackPressed()
    {
            Intent intent = new Intent(getApplicationContext(), BackActivity.class);
            intent.putExtra("name", "empty");
            intent.putExtra("series", "0");
            setResult(1000, intent);
            finish();
    }

    private void assignValues(){
        addExercise = (Button) findViewById(R.id.addExerciseButton);
        exerciseName = (EditText) findViewById(R.id.exerciseName);
        exerciseSeries = (EditText) findViewById(R.id.exerciseSeriesNumber);
    }
}
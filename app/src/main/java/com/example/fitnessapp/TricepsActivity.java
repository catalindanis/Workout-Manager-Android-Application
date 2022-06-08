package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class TricepsActivity extends AppCompatActivity {

    e exercise = new e();
    InputStream inputStream = null;
    Scanner read = null;
    Button addButton;
    LinearLayout myLayout;
    String exerciseName;
    int last_index = 0;
    int exerciseSeries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triceps);
        //emptyFile();
        assignValues();
        readFromFile();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToAdd();
            }
        });
    }

    @Override
    protected void onUserLeaveHint()
    {
        listData();
        super.onUserLeaveHint();
    }

    @Override
    public void onBackPressed()
    {
        //showFileContent();
        listData();
        finish();
    }

    private void assignValues(){
        addButton = (Button) findViewById(R.id.addButtonTriceps);
        myLayout = (LinearLayout) findViewById(R.id.linearLayoutTriceps);
    }

    private void readFromFile(){
//        try{
//            inputStream = getAssets().open("triceps_database.txt");
//            read = new Scanner(inputStream);
//            String exerciseName = "",series,weights;
//            int seriesNumber,reps[] = new int[50];
//            float seriesWeight[] = new float[50];
//            int numberOfExercises = 0;
//            if(read.hasNext()){
//                numberOfExercises = Integer.parseInt(read.next());
//            }
//            read.nextLine();
//            //Log.d("verify",String.valueOf(numberOfExercises));
//            for(int i=0;i<numberOfExercises;i++){
//                exerciseName = read.nextLine();
//                seriesNumber = Integer.parseInt(read.next());
////                Log.d("verify",exerciseName);
////                Log.d("verify",String.valueOf(seriesNumber));
//                read.nextLine();
//                series = read.nextLine();
//                weights = read.nextLine();
//                for(int j=0;j<seriesNumber;j++){
//                    reps[j] = Integer.parseInt(series.split(" ")[j]);
//                    seriesWeight[j] = Float.parseFloat(weights.split(" ")[j]);
//                }
//
//                addLastExercise(exerciseName,seriesNumber,reps,seriesWeight);
//            }
//        }
//        catch(IOException e){
//            Log.d("verify",e.toString());
//            //Toast.makeText(this, e.getClass().getSimpleName()+':'+e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
        try {
            String exerciseName = "", series, weights;
            int seriesNumber;
            String seriesWeight[] = new String[50];
            String reps[] = new String[50];
            int numberOfExercises = 0;

            inputStream = this.openFileInput("triceps_database.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader read = new BufferedReader(inputStreamReader);

            String n = read.readLine();
            if (n != null)
                numberOfExercises = Integer.valueOf(n);
            else numberOfExercises = 0;
            //Log.d("verify", String.valueOf(numberOfExercises));

            for (int i = 0; i < numberOfExercises; i++) {
                exerciseName = read.readLine();
                seriesNumber = Integer.parseInt(read.readLine());
                //Log.d("verify",exerciseName);
                //Log.d("verify",String.valueOf(seriesNumber));
                series = read.readLine();
                weights = read.readLine();
                //Log.d("verify",series);
                //Log.d("verify",weights);
                for(int j=0;j<seriesNumber;j++){
                    reps[j] = series.split(" ")[j];
                    seriesWeight[j] = weights.split(" ")[j];
                }

                addLastExercise(exerciseName,seriesNumber,reps,seriesWeight);
            }
        }
        catch(IOException e){
            //Log.d("verify",e.getMessage());
        }
    }

    private void switchToAdd(){
        Intent intent = new Intent(this,addexercise.class);
        startActivityForResult(intent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        exerciseName = data.getStringExtra("name");
        exerciseSeries = Integer.parseInt(data.getStringExtra("series"));
        if(exerciseSeries > 0)
            addLastExercise(exerciseName,exerciseSeries);
    }

    private void addLastExercise(String name,int series) {

        View exerciseLayout = getLayoutInflater().inflate(R.layout.exercise,null);

        TextView exerciseName = (TextView) exerciseLayout.findViewById(R.id.exerciseNameLayout);
        if(name.length() >= 25)
            exerciseName.setTextSize(20);
        exerciseName.setText(name);

        LinearLayout layoutReps = (LinearLayout) exerciseLayout.findViewById(R.id.layoutReps);
        LinearLayout layoutWeight = (LinearLayout) exerciseLayout.findViewById(R.id.layoutWeight);

        for(int i=1;i<=series;i++) {
            EditText seriesReps = new EditText(this);
            seriesReps.setWidth(150);
            seriesReps.setHeight(150);
            seriesReps.setLines(1);

            EditText seriesWeight = new EditText(this);
            seriesWeight.setWidth(150);
            seriesWeight.setHeight(150);
            seriesWeight.setLines(1);

            layoutReps.addView(seriesReps);
            layoutWeight.addView(seriesWeight);
        }

        ImageButton deleteButton = (ImageButton) exerciseLayout.findViewById(R.id.imageButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myLayout.removeView(exerciseLayout);
            }
        });

        myLayout.addView(exerciseLayout);
        last_index++;
    }

    private void addLastExercise(String name,int series,String reps[],String weights[]) {
        View exerciseLayout = getLayoutInflater().inflate(R.layout.exercise,null);

        TextView exerciseName = (TextView) exerciseLayout.findViewById(R.id.exerciseNameLayout);
        if(name.length() >= 25)
            exerciseName.setTextSize(20);
        exerciseName.setText(name);

        LinearLayout layoutReps = (LinearLayout) exerciseLayout.findViewById(R.id.layoutReps);
        LinearLayout layoutWeight = (LinearLayout) exerciseLayout.findViewById(R.id.layoutWeight);

        for(int i=0;i<series;i++) {
            EditText seriesReps = new EditText(this);
            seriesReps.setWidth(150);
            seriesReps.setHeight(150);
            seriesReps.setLines(1);
            seriesReps.setText(reps[i]);

            EditText seriesWeight = new EditText(this);
            seriesWeight.setWidth(150);
            seriesWeight.setHeight(150);
            seriesWeight.setLines(1);
            seriesWeight.setText(weights[i]);

            layoutReps.addView(seriesReps);
            layoutWeight.addView(seriesWeight);
        }

        ImageButton deleteButton = (ImageButton) exerciseLayout.findViewById(R.id.imageButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myLayout.removeView(exerciseLayout);
            }
        });

        myLayout.addView(exerciseLayout);
        last_index++;
    }

    private void listData(){
        //Log.d("verify listData()",String.valueOf(myLayout.getChildCount()));
        int numberOfExercises = myLayout.getChildCount();
        for(int i=0;i<numberOfExercises;i++){
            View e = myLayout.getChildAt(i);
            TextView exerciseName = (TextView) e.findViewById(R.id.exerciseNameLayout);
            exercise.name[i] = exerciseName.getText().toString();
            LinearLayout layoutReps = (LinearLayout) e.findViewById(R.id.layoutReps);
            LinearLayout layoutWeight = (LinearLayout) e.findViewById(R.id.layoutWeight);
            exercise.numberOfSeries[i] = layoutReps.getChildCount();
            for(int j=0;j<exercise.numberOfSeries[i];j++){
                View reps = layoutReps.getChildAt(j);
                TextView repsText = (TextView) reps;
                if(repsText.getText().toString().isEmpty())
                    exercise.reps[i][j] = "-";
                else exercise.reps[i][j] = repsText.getText().toString();

                View weight = layoutWeight.getChildAt(j);
                TextView weightText = (TextView) weight;
                if(weightText.getText().toString().isEmpty())
                    exercise.weight[i][j] = "-";
                else exercise.weight[i][j] = weightText.getText().toString();
            }
        }
        writeToFile();
    }

    private void writeToFile(){
//        try
//        {
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter
//                    (this.openFileOutput("triceps_database.txt", MODE_PRIVATE));
//            outputStreamWriter.write('1');
//            outputStreamWriter.write("\nramat cu gantera\n4\n15 15 15 12\n9 9 9 9");
//            int n = myLayout.getChildCount();
//            Log.d("verify",String.valueOf(n));
//            if(n != 0){
//                outputStreamWriter.write(String.valueOf(n));
//            }
//        }
//        catch(IOException e)
//        {
//            Log.d("verify",e.getMessage());
//        }
        //emptyFile();
        //read();
        write();
    }

    private void addExample(){
        try
        {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter
                    (this.openFileOutput("triceps_database.txt", MODE_PRIVATE));
            outputStreamWriter.write('1');
            outputStreamWriter.write("\nramat cu gantera\n4\n15 15 15 12\n9 9 9 9");
            //outputStreamWriter.write("\ntractiuni\n3\n12 10 8\n2.5 5 7.5");
            outputStreamWriter.close();

        }
        catch(IOException e)
        {
            //Log.d("verify",e.getMessage());
        }
    }

    private void emptyFile(){
        try {
            PrintWriter pw = new PrintWriter(this.openFileOutput("triceps_database.txt", MODE_PRIVATE));
            pw.print("");
            pw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showFileContent(){

        try {
            inputStream = this.openFileInput("triceps_database.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader read = new BufferedReader(inputStreamReader);

            String s = read.readLine();
            if(s==null)
                //Log.d("verify","empty");
                while (s != null) {
                    //Log.d("verify", s);
                    s = read.readLine();
                }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void write(){
        try{
            emptyFile();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter
                    (this.openFileOutput("triceps_database.txt", MODE_PRIVATE));
            String n = String.valueOf(myLayout.getChildCount());
            outputStreamWriter.write(n);

            for(int i=0;i<Integer.valueOf(n);i++){
                outputStreamWriter.write("\n"+ exercise.name[i]
                        +"\n"+exercise.numberOfSeries[i]
                        +"\n");
                for(int j = 0;j<exercise.numberOfSeries[i];j++)
                    if(j<exercise.numberOfSeries[i]-1)
                        outputStreamWriter.write(exercise.reps[i][j]+" ");
                    else
                        outputStreamWriter.write(exercise.reps[i][j]+"\n");

                for(int j = 0;j<exercise.numberOfSeries[i];j++)
                    if(j<exercise.numberOfSeries[i]-1)
                        outputStreamWriter.write(exercise.weight[i][j]+" ");
                    else
                        outputStreamWriter.write(exercise.weight[i][j]+"");
            }
            outputStreamWriter.close();
        }
        catch (IOException e){
            //Log.d("verify",e.getMessage());
        }
        read();
    }

    public void read(){
        try{
            inputStream = this.openFileInput("triceps_database.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader read = new BufferedReader(inputStreamReader);
            String s = read.readLine();
            while(s!=null){
                //Log.d("verify",s);
                s = read.readLine();
            }
        }
        catch(IOException e){
            //Log.d("verify",e.getMessage());
        }
    }
}


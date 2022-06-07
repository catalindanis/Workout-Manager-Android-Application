package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button backButton,chestButton,legsButton,shouldersButton,tricepsButton,bicepsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.d("mode",getResources().getString(R.string.mode));
        setContentView(R.layout.activity_main);
        assignButtons();
    }

    private void assignButtons(){
        backButton = (Button) findViewById(R.id.backButton);
        chestButton = (Button) findViewById(R.id.chestButton);
        legsButton = (Button) findViewById(R.id.legsButton);
        shouldersButton = (Button) findViewById(R.id.shouldersButton);
        tricepsButton = (Button) findViewById(R.id.tricepsButton);
        bicepsButton = (Button) findViewById(R.id.bicepsButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchTo("back");
            }
        });
        chestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchTo("chest");
            }
        });
        legsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchTo("legs");
            }
        });
        shouldersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchTo("shoulders");
            }
        });
        tricepsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchTo("triceps");
            }
        });
        bicepsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchTo("biceps");
            }
        });
    }

    private void switchTo(String s){
        Intent intent;
        switch (s){
            case "back":
                intent = new Intent(this,BackActivity.class);
                startActivity(intent);
                break;
            case "chest":
                intent = new Intent(this,ChestActivity.class);
                startActivity(intent);
                break;
            case "legs":
                intent = new Intent(this,LegsActivity.class);
                startActivity(intent);
                break;
            case "shoulders":
                intent = new Intent(this,ShouldersActivity.class);
                startActivity(intent);
                break;
            case "triceps":
                intent = new Intent(this,TricepsActivity.class);
                startActivity(intent);
                break;
            case "biceps":
                intent = new Intent(this,BicepsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
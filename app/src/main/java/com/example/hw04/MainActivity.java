package com.example.hw04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button btn_AddMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My Favorite Movies");


        btn_AddMovie = findViewById(R.id.btnAddMovie);

        btn_AddMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToAddMovie = new Intent(MainActivity.this, AddMovieActivity.class);
                startActivity(intentToAddMovie);
            }
        });

    }
}

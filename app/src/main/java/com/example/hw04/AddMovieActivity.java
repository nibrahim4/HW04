package com.example.hw04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddMovieActivity extends AppCompatActivity {


    public Button btn_CreateMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        setTitle("Add Movie");

        btn_CreateMovie = findViewById(R.id.btnCreateMovie);
        btn_CreateMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); 
            }
        });
    }
}

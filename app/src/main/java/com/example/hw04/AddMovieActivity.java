package com.example.hw04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddMovieActivity extends AppCompatActivity {


    public Button btn_CreateMovie;
    public EditText et_Name;
    public EditText ml_description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        setTitle("Add Movie");

        btn_CreateMovie = findViewById(R.id.btnCreateMovie);
        et_Name = findViewById(R.id.etName);
        ml_description = findViewById(R.id.ml_Description);

        String enteredName = et_Name.getText().toString();
        String enteredDescription = ml_description.getText().toString();

        //Movie movie = new Movie(enteredName, enteredDescription)
        btn_CreateMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

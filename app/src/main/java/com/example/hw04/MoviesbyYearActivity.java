package com.example.hw04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import static android.content.Intent.EXTRA_TEXT;

public class MoviesbyYearActivity extends AppCompatActivity {

    public  Bundle extrasFromMain;
    public ArrayList<Movie> moviesFromMain = new ArrayList<Movie>();
    public Button btn_finish_byYear;
    public TextView tv_titleValue_byYear;
    public TextView tv_descriptionValue_byYear;
    public TextView tv_genreValue_byYear;
    public TextView tv_ratingValue_byYear;
    public TextView tv_yearValue_byYear;
    public TextView tv_imdbValue_byYear;
    public ImageView iv_first;
    public ImageView iv_last;
    public ImageView iv_previous;
    public ImageView iv_next;
    public int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviesby_year);
        setTitle("Movies by Year");

        extrasFromMain = getIntent().getExtras();
        moviesFromMain = (ArrayList<Movie>) extrasFromMain.getSerializable(EXTRA_TEXT);

        tv_titleValue_byYear = findViewById(R.id.tv_movieName_Year);
        tv_descriptionValue_byYear = findViewById(R.id.tv_description_Year);
        tv_genreValue_byYear = findViewById(R.id.tv_genreValue_Year);
        tv_imdbValue_byYear = findViewById(R.id.tv_imdbValue_Year);
        tv_ratingValue_byYear = findViewById(R.id.tv_ratingValue_Year);
        tv_yearValue_byYear = findViewById(R.id.tv_yearValue_Year);

        if (moviesFromMain.size() > 0){
            Collections.sort(moviesFromMain, new Comparator<Movie>() {

                public int compare(Movie m1, Movie m2) {
                    return m1.getYear() - m2.getYear();
                }
            });

            setFieldsForMovie(0);

            iv_first = findViewById(R.id.iv_first);
            iv_first.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    counter =0;
                    setFieldsForMovie(counter);
                }
            });

            iv_next = findViewById(R.id.iv_next);
            iv_next.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    counter += 1;
                    try {
                        setFieldsForMovie(counter);
                    }catch(Exception e){
                        counter =0;
                        setFieldsForMovie(counter);
                    }
                }
            });

            iv_last = findViewById(R.id.iv_last);
            iv_last.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    counter = moviesFromMain.size()-1;
                   setFieldsForMovie(moviesFromMain.size()-1);
                }
            });

            iv_previous = findViewById(R.id.iv_previous);
            iv_previous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    counter -= 1;
                    try {
                        setFieldsForMovie(counter);
                    }catch(Exception e){
                        counter =moviesFromMain.size() -1;
                        setFieldsForMovie(counter);
                    }
                }
            });

        }else{
            Toast.makeText(MoviesbyYearActivity.this, "There are no movies in the list. ", Toast.LENGTH_SHORT).show();
        }





        btn_finish_byYear = findViewById(R.id.btn_Finish_Year);
        btn_finish_byYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void setFieldsForMovie(int index) {
        tv_titleValue_byYear.setText(moviesFromMain.get(index).getName());
        tv_descriptionValue_byYear.setText(moviesFromMain.get(index).getDescription());
        tv_genreValue_byYear.setText(moviesFromMain.get(index).getGenre());
        tv_imdbValue_byYear.setText(moviesFromMain.get(index).getImbd());
        tv_ratingValue_byYear.setText(Integer.toString(moviesFromMain.get(index).getRating()));
        tv_yearValue_byYear.setText(Integer.toString(moviesFromMain.get(index).getYear()));
    }
}

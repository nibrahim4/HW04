package com.example.hw04;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Button btn_AddMovie;
    public ArrayList<Movie> movieList = new ArrayList<Movie>();
    public static final String MOVIE_KEY = "movies";
    public static final int REQ_CODE = 5;
    public Bundle extrasFromAddMovie = new Bundle();
    public Movie movie;

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
                //Bundle bundle = new Bundle();
               // bundle.putSerializable(MOVIE_KEY, movieList);
               // intentToAddMovie.putExtra("toAddMovie", bundle);
                startActivityForResult(intentToAddMovie, REQ_CODE);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQ_CODE){
            if(resultCode==RESULT_OK){

                Log.d("TEST", "onActivityResult: " + data);
                extrasFromAddMovie = data.getExtras().getBundle(MOVIE_KEY);

                movie = (Movie) extrasFromAddMovie.getSerializable("addedMovie");

                movieList.add(movie);


                for (int i =0; i<movieList.size(); i++){
                    Log.d("test", "onActivityResult: "+ movieList.get(i).getName());
                }
            }
        }
    }
}

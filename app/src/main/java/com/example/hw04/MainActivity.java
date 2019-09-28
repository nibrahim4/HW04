package com.example.hw04;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Button btn_AddMovie;
    public Button btn_EditMovie;
    public ArrayList<Movie> movieList = new ArrayList<Movie>();
    public ArrayList<String> nameList = new ArrayList<String>();
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
        btn_EditMovie = findViewById(R.id.btnEdit);

        btn_AddMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToAddMovie = new Intent(MainActivity.this, AddMovieActivity.class);
                startActivityForResult(intentToAddMovie, REQ_CODE);
            }
        });


        btn_EditMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (movieList.size() > 0 && movieList != null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Pick a Movie");
                    String[] movieArray = new String[movieList.size()];
                    nameList.clear();
                    
                    for (int i=0; i<movieList.size(); i++){
                        nameList.add(movieList.get(i).getName());
                    }

                    movieArray = nameList.toArray(movieArray);

                    final String[] finalMovieArray = movieArray;
                    builder.setItems(movieArray, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d("test", "onClick: " + finalMovieArray[which]);

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else  {
                    Toast.makeText(MainActivity.this, "There are no movies to edit.", Toast.LENGTH_LONG).show();
                }

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

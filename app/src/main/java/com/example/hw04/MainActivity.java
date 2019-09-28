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
    public Button btn_DeleteMovie;
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
        btn_AddMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToAddMovie = new Intent(MainActivity.this, AddMovieActivity.class);
                startActivityForResult(intentToAddMovie, REQ_CODE);
            }
        });

        btn_EditMovie = findViewById(R.id.btnEdit);
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

                    //final String[] finalMovieArray = movieArray;
                    builder.setItems(movieArray, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intentToEdit = new Intent(MainActivity.this, EditActivity.class);
                            Bundle sentData = new Bundle();
                            sentData.putSerializable("selectedMovie", movieList.get(which));
                            intentToEdit.putExtra(MainActivity.MOVIE_KEY, sentData);
                            startActivityForResult(intentToEdit,MainActivity.REQ_CODE);
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else  {
                    Toast.makeText(MainActivity.this, "There are no movies to edit.", Toast.LENGTH_LONG).show();
                }

            }
        });

        btn_DeleteMovie = findViewById(R.id.btnDeleteMovie);
        btn_DeleteMovie.setOnClickListener(new View.OnClickListener() {
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

                    builder.setItems(movieArray, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           String movieName =  movieList.get(which).getName();
                            movieList.remove(which);
                            Toast.makeText(MainActivity.this, "This movie: " + movieName + " was successfully deleted.", Toast.LENGTH_SHORT).show();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else  {
                    Toast.makeText(MainActivity.this, "There are no movies to delete.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQ_CODE){
            if(resultCode==RESULT_OK){

                extrasFromAddMovie = data.getExtras().getBundle(MOVIE_KEY);

                movie = (Movie) extrasFromAddMovie.getSerializable("addedMovie");

                if(movieList.size() > 0 && movieList != null){
                    for (int i =0; i<movieList.size(); i++){
                        if (movieList.get(i).getId() == movie.getId()){
                            movieList.set(i,movie);
                        }
                        Log.d("test", "onActivityResult: "+ movieList.get(i).getName());
                    }
                }else{
                    movieList.add(movie);
                }

            }
        }
    }
}

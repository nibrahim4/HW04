package com.example.hw04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    public Bundle extrasFromMain;
    public Movie selectedMovie;
    public EditText et_Name_Edit;
    public EditText ml_Description_Edit;
    public EditText et_Year_Edit;
    public EditText et_IMDB_Edit;
    public TextView tv_Rating_Edit;
    public SeekBar sb_Rating_Edit;
    public Spinner sp_Genre_Edit;
    public Button btn_SaveChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setTitle("Edit Activity");

        extrasFromMain = getIntent().getExtras().getBundle(MainActivity.MOVIE_KEY);

        selectedMovie = (Movie) extrasFromMain.getSerializable("selectedMovie");

        et_Name_Edit = findViewById(R.id.etName_Edit);
        et_Name_Edit.setText(selectedMovie.getName());

        ml_Description_Edit = findViewById(R.id.ml_Description_Edit);
        ml_Description_Edit.setText(selectedMovie.getDescription());

        et_Year_Edit = findViewById(R.id.et_year_Edit);
        et_Year_Edit.setText(Integer.toString(selectedMovie.getYear()));

        et_IMDB_Edit = findViewById(R.id.et_imdb_Edit);
        et_IMDB_Edit.setText(selectedMovie.getImbd());

        tv_Rating_Edit = findViewById(R.id.tv_rating_Edit);
        tv_Rating_Edit.setText(Integer.toString(selectedMovie.getRating()));

        sb_Rating_Edit = findViewById(R.id.sb_rating_Edit);
        sb_Rating_Edit.setProgress(selectedMovie.getRating());
        sb_Rating_Edit.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv_Rating_Edit.setText(Integer.toString(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sp_Genre_Edit = findViewById(R.id.sp_genre_Edit);
        String compareValue = selectedMovie.getGenre();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditActivity.this, R.array.genreList, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_Genre_Edit.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            sp_Genre_Edit.setSelection(spinnerPosition);
        }

        btn_SaveChanges = findViewById(R.id.btnSaveChanges);
        btn_SaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedMovie.setName(et_Name_Edit.getText().toString());
                selectedMovie.setDescription(ml_Description_Edit.getText().toString());
                selectedMovie.setYear(Integer.parseInt(et_Year_Edit.getText().toString()));
                selectedMovie.setIMDB(et_IMDB_Edit.getText().toString());
                selectedMovie.setRating(sb_Rating_Edit.getProgress());
                selectedMovie.setGenre(sp_Genre_Edit.getSelectedItem().toString());

                Bundle sentData = new Bundle();
                sentData.putSerializable("addedMovie",selectedMovie);
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                intent.putExtra(MainActivity.MOVIE_KEY, sentData);

                setResult(EditActivity.RESULT_OK, intent);
                finish();
            }
        });
    }
}


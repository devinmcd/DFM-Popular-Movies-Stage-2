package com.example.dfmpopularmoviesstage1;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dfmpopularmoviesstage1.Model.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_activity);

        Movie movieDetails = getIntent().getParcelableExtra("movie_details");

        TextView movieTitle = findViewById(R.id.title);
        movieTitle.setText(movieDetails.getTitle());

        TextView movieRating = findViewById(R.id.vote_average);
        movieRating.setText(getString(R.string.rating, String.valueOf(movieDetails.getVoteAverage())));

        TextView movieReleaseDate = findViewById(R.id.release_date);
        movieReleaseDate.setText(movieDetails.getReleaseDate());

        TextView movieDescription = findViewById(R.id.overview);
        movieDescription.setText(movieDetails.getOverview());

        Picasso.get()
                .load(movieDetails.getPosterPath())
                .fit()
                .into((ImageView) findViewById(R.id.poster));

    }
}

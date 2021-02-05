package com.example.dfmpopularmoviesstage1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dfmpopularmoviesstage1.Model.Movie;
import com.example.dfmpopularmoviesstage1.Utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        spinner = findViewById(R.id.spinner);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner.getSelectedItemPosition() == 0) {
                    new MovieDataTask().execute("popular");
                } else {
                    new MovieDataTask().execute("top_rated");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private Movie[] convertResponseToMoviesArray(String responseJson) throws JSONException {
        JSONObject moviesJson = new JSONObject(responseJson);
        JSONArray moviesJsonArray = moviesJson.getJSONArray("results");

        Movie[] moviesArray = new Movie[moviesJsonArray.length()];

        for (int i = 0; i < moviesJsonArray.length(); i++) {
            moviesArray[i] = new Movie();

            JSONObject movieDetails = moviesJsonArray.getJSONObject(i);

            moviesArray[i].setTitle(movieDetails.getString("title"));
            moviesArray[i].setVoteAverage(movieDetails.getDouble("vote_average"));
            moviesArray[i].setOverview(movieDetails.getString("overview"));
            moviesArray[i].setReleaseDate(movieDetails.getString("release_date"));
            moviesArray[i].setPosterPath(movieDetails.getString("poster_path"));
        }
        return moviesArray;
    }

    private class MovieDataTask extends AsyncTask<String, Void, Movie[]> {

        @Override
        protected Movie[] doInBackground(String... params) {
            URL url = NetworkUtils.buildUrl(params[0]);
            String response;
            try {
                response = NetworkUtils.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            try {
                return convertResponseToMoviesArray(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Movie[] movies) {
            MoviesAdapter adapter = new MoviesAdapter(getApplicationContext(), movies);
            recyclerView.setAdapter(adapter);
        }
    }
}

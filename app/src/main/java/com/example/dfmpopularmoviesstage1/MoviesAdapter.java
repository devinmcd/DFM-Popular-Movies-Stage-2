package com.example.dfmpopularmoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dfmpopularmoviesstage1.Model.Movie;
import com.squareup.picasso.*;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private final Context context;
    private final Movie[] moviesArray;

    public MoviesAdapter(Context context, Movie[] movies) {
        this.context = context;
        this.moviesArray = movies;
    }

    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_view, parent, false);
        return new MoviesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.ViewHolder holder, int position) {
        Picasso.get()
                .load(moviesArray[position].getPosterPath())
                .fit()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return moviesArray.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), MovieDetailsActivity.class);
                    intent.putExtra("movie_details", moviesArray[getAdapterPosition()]);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}

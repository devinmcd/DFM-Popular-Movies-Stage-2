package com.example.dfmpopularmoviesstage1.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private String title;
    private Double voteAverage;
    private String overview;
    private String releaseDate;
    private String posterPath;
    private String trailerPath;

    private static final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185/";
    private static final String TRAILER_BASE_URL = "https://image.tmdb.org/t/p/w185/";

    public Movie(Parcel parcel) {
        title = parcel.readString();
        voteAverage = parcel.readDouble();
        overview = parcel.readString();
        releaseDate = parcel.readString();
        posterPath = parcel.readString();
    }

    public Movie() {}

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeDouble(voteAverage);
        dest.writeString(overview);
        dest.writeString(releaseDate);
        dest.writeString(posterPath);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return POSTER_BASE_URL + posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }


    public String getTrailerPath() {
        return trailerPath;
    }

    public void setTrailerPath(String trailerPath) {
        this.trailerPath = trailerPath;
    }
}

package com.ezmenutouch.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Parcelable movie class for holding all te movie information.
 * Created by ravisha
 */
public class Dish implements Parcelable{
    String title;
    String releaseDate;
    String overview;
    Double voteAverage;
    long voteCount;
    String language;
    String backdropPath;
    Double popularity;
    long movieId;

    public Dish(){

    }

    public Dish(Parcel input){
       title = input.readString();
       releaseDate = input.readString();
       overview = input.readString();
       voteAverage = input.readDouble();
       voteCount = input.readLong();
       language = input.readString();
        backdropPath =input.readString();
        popularity = input.readDouble();
        movieId = input.readLong();




    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }





    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(releaseDate);
        dest.writeString(overview);
        dest.writeDouble(voteAverage);
        dest.writeLong(voteCount);
        dest.writeString(language);
        dest.writeString(backdropPath);
        dest.writeDouble(popularity);
        dest.writeLong(movieId);
    }

    public static final Creator<Dish> CREATOR = new Creator<Dish>() {
        public Dish createFromParcel(Parcel in) {
            return new Dish(in);
        }

        public Dish[] newArray(int size) {
            return new Dish[size];
        }
    };
}

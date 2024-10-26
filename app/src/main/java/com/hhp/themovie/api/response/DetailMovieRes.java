package com.hhp.themovie.api.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DetailMovieRes {
    @SerializedName("title")
    public String title;
    @SerializedName("vote_average")
    public double voteAverage;
    @SerializedName("release_date")
    public String releaseDate;
    @SerializedName("runtime")
    public int runTime;
    @SerializedName("genres")
    public List<DetailMovieRes.Genres> genres;
    @SerializedName("backdrop_path")
    public String backdropPath;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("overview")
    public String overview;

    public class Genres {
        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;
    }

    @Override
    public String toString() {
        return "DetailMovieRes{" +
                "title='" + title + '\'' +
                ", voteAverage=" + voteAverage +
                ", releaseDate='" + releaseDate + '\'' +
                ", runTime=" + runTime +
                ", genres=" + genres +
                ", backdropPath='" + backdropPath + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", overview='" + overview + '\'' +
                '}';
    }
}

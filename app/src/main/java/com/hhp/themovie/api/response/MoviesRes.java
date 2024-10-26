package com.hhp.themovie.api.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MoviesRes implements Serializable {
    @SerializedName("page")
    public int page;
    @SerializedName("total_pages")
    public int totalPages;
    @SerializedName("results")
    public List<Result> listResults;
    @SerializedName("total_results")
    public int totalResults;

    public static class Result {
        @SerializedName("adult")
        public boolean adult;
        @SerializedName("backdrop_path")
        public String backdropPath;
        @SerializedName("id")
        public int id;
        @SerializedName("original_title")
        public String title;
        @SerializedName("overview")
        public String overview;
        @SerializedName("poster_path")
        public String posterPath;
        @SerializedName("release_date")
        public String releaseDate;
    }
}

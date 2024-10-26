package com.hhp.themovie.model;

import com.hhp.themovie.api.response.MoviesRes;

public class Movie {
    private MoviesRes.Result movie;
    private boolean isSelected;

    public MoviesRes.Result getMovie() {
        return movie;
    }

    public Movie(MoviesRes.Result movie) {
        this.movie = movie;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

}

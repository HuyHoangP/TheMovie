package com.hhp.themovie.viewmodel;

import com.hhp.themovie.api.response.MoviesRes;
import com.hhp.themovie.view.base.BaseViewModel;

public class DetailMovieFrgVM extends BaseViewModel {
    public static final String TAG = DetailMovieFrgVM.class.getName();
    public static final String KEY_GET_DETAIL_MOVIE= "KEY_GET_DETAIL_MOVIE";
    public static final String KEY_GET_VIDEO_MOVIE= "KEY_GET_VIDEO_MOVIE";
    public void getDetailMovie(MoviesRes.Result movie) {
        getAPI().getDetailMovie(movie.id).enqueue(handleResponse(KEY_GET_DETAIL_MOVIE));
    }
    public void getVideosMovie(MoviesRes.Result movie) {
        getAPI().getVideosMovie(movie.id).enqueue(handleResponse(KEY_GET_VIDEO_MOVIE));
    }


    @Override
    protected void handleSuccess(String key, Object body) {
        if(key.equals(KEY_GET_DETAIL_MOVIE) || key.equals(KEY_GET_VIDEO_MOVIE)){
            apiCallback.apiSuccess(key, body);
        }
    }
}

package com.hhp.themovie.viewmodel;

import com.hhp.themovie.api.response.MoviesRes;
import com.hhp.themovie.view.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListMoviesFrgVM extends BaseViewModel {
    public static final String TAG = ListMoviesFrgVM.class.getName();
    public static final String KEY_GET_LIST_MOVIES= "KEY_GET_LIST_MOVIES";
    private List<MoviesRes.Result> listResults = new ArrayList<>();
    private int page = 0;

    public List<MoviesRes.Result> getListResults() {
        return listResults;
    }
    public void getListMovies(){
        page++;
        getAPI().getListMovies(page).enqueue(handleResponse(KEY_GET_LIST_MOVIES));
    }

    @Override
    protected void handleSuccess(String key, Object body) {
        if(key.equals(KEY_GET_LIST_MOVIES)){
            apiCallback.apiSuccess(key, body);
        }
    }

    public void addToListResults(List<MoviesRes.Result> listResults) {
        this.listResults.addAll(listResults);
    }

}

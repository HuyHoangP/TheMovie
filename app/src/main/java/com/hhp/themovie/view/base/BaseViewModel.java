package com.hhp.themovie.view.base;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.hhp.themovie.OnAPICallback;
import com.hhp.themovie.api.API;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseViewModel extends ViewModel {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String TAG = BaseViewModel.class.getName();
    protected OnAPICallback apiCallback;

    public void setApiCallback(OnAPICallback apiCallback) {
        this.apiCallback = apiCallback;
    }

    protected API getAPI(){
        Retrofit rs = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().callTimeout(30, TimeUnit.SECONDS).build())
                .build();
        return rs.create(API.class);
    }

    protected <T> Callback<T> handleResponse(String key) {
        return new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if(response.isSuccessful()){
                    handleSuccess(key, response.body());
                } else {
                    handleFailure(key, response.code(), response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                handleException(key, t);
            }
        };
    }

    protected void handleException(String key, Throwable t) {
        apiCallback.apiError(key, t.getMessage(), 999);
    }

    protected void handleFailure(String key, int code, ResponseBody responseBody) {
        Log.e(TAG, "handleFailure: " + code);
        apiCallback.apiError(key, responseBody, code);
    }

    protected void handleSuccess(String key, Object body) {
        apiCallback.apiSuccess(key, body);
    }
}

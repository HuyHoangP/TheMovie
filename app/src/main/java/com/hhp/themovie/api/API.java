package com.hhp.themovie.api;

import com.hhp.themovie.BuildConfig;
import com.hhp.themovie.api.request.AccountReq;
import com.hhp.themovie.api.request.RequestTokenReq;
import com.hhp.themovie.api.response.AuthenRes;
import com.hhp.themovie.api.response.DetailMovieRes;
import com.hhp.themovie.api.response.MoviesRes;
import com.hhp.themovie.api.response.SessionRes;
import com.hhp.themovie.api.response.VideosRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {
    String API_KEY = BuildConfig.apiKey;
    @GET("authentication/token/new?api_key=" + API_KEY)
    @Headers("Content-Type: application/json")
    Call<AuthenRes> getAuthen();

    @POST("authentication/token/validate_with_login?api_key=" + API_KEY)
    @Headers("Content-Type: application/json")
    Call<AuthenRes> createSession(@Body AccountReq accReq);

    @POST("authentication/session/new?api_key=" + API_KEY)
    @Headers("Content-Type: application/json")
    Call<SessionRes> createSessionID(@Body RequestTokenReq tokenReq);

    @GET("discover/movie?api_key=" + API_KEY)
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<MoviesRes> getListMovies(@Query("page") int page);

    @GET("movie/{id}?api_key=" + API_KEY)
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<DetailMovieRes> getDetailMovie(@Path("id") int id);

    @GET("movie/{id}/videos?api_key=" + API_KEY)
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<VideosRes> getVideosMovie(@Path("id") int id);
}

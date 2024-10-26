package com.hhp.themovie.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.bumptech.glide.Glide;
import com.hhp.themovie.api.response.DetailMovieRes;
import com.hhp.themovie.api.response.MoviesRes;
import com.hhp.themovie.api.response.VideosRes;
import com.hhp.themovie.databinding.FragmentDetailMovieBinding;
import com.hhp.themovie.view.adapter.MovieAdapter;
import com.hhp.themovie.view.base.BaseFragment;
import com.hhp.themovie.viewmodel.DetailMovieFrgVM;

import java.util.ArrayList;
import java.util.List;

public class DetailMovieFragment extends BaseFragment<FragmentDetailMovieBinding, DetailMovieFrgVM> {
    public static final String TAG = DetailMovieFragment.class.getName();
    private static final String KEY_YOUTUBE = "https://www.youtube.com/watch?v=";
    @Override
    public void initView() {
        viewModel.getDetailMovie((MoviesRes.Result) data);
        binding.trPlayTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                playTrailer();
            }
        });

    }

    private void playTrailer() {
        viewModel.getVideosMovie((MoviesRes.Result) data);
    }

    @Override
    public Class<DetailMovieFrgVM> initViewModel() {
        return DetailMovieFrgVM.class;
    }

    @Override
    public FragmentDetailMovieBinding initViewBinding() {
        return FragmentDetailMovieBinding.inflate(getLayoutInflater());
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if(key.equals(DetailMovieFrgVM.KEY_GET_DETAIL_MOVIE)){
            initDetailMovieInfo((DetailMovieRes) data);
        } else if (key.equals(DetailMovieFrgVM.KEY_GET_VIDEO_MOVIE)) {
            playTrailerOnBrowser((VideosRes) data);
        }
    }

    private void playTrailerOnBrowser(VideosRes res) {
        List<String> listKeyYoutube = new ArrayList<>();
        for(VideosRes.Result result: res.listResults){
            if(result.type.equals("Trailer")){
                listKeyYoutube.add(result.key);
            }
        }
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(KEY_YOUTUBE + listKeyYoutube.get(0))));
    }


    private void initDetailMovieInfo(DetailMovieRes res) {
        Log.i(TAG, "apiSuccess: " + res);
        Glide.with(context).load(String.format(MovieAdapter.PATH_IMAGE + "%s", res.posterPath)).into(binding.ivAvatar);
        Glide.with(context).load(String.format(MovieAdapter.PATH_IMAGE + "%s", res.backdropPath)).into(binding.ivBackground);

        binding.tvName.setText(res.title);
        binding.tvName.setSelected(true);
        binding.tvYear.setText(String.format(" (%s)", res.releaseDate.split("-")[0]));
        binding.tvDate.setText(res.releaseDate.replace("-","/"));

        int hours = res.runTime / 60;
        int mins = res.runTime - hours * 60;
        binding.tvLength.setText(String.format("%sh %sm", hours, mins));

        StringBuilder movieGenres = new StringBuilder();
        for(int i = 0; i < res.genres.size(); i++){
            movieGenres.append(res.genres.get(i).name).append(i < res.genres.size() - 1? ", ":"");
        }
        binding.tvGenre.setText(movieGenres);

        binding.tvOverview.setText(res.overview);
        binding.tvPercentage.setText(String.format("%s%%", (int) (res.voteAverage * 10)));
        binding.progressPercent.setProgress((int) (res.voteAverage*10), true);
    }
}

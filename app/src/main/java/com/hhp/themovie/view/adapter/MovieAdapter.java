package com.hhp.themovie.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hhp.themovie.R;
import com.hhp.themovie.api.response.MoviesRes;
import com.hhp.themovie.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    public static final String PATH_IMAGE = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";
    private final Context context;
    private final List<Movie> listMovies = new ArrayList<>();
    private final MutableLiveData<Movie> movieLD = new MutableLiveData<>();

    public MovieAdapter(Context context, List<MoviesRes.Result> listResults) {
        this.context = context;
        for (MoviesRes.Result result : listResults) {
            listMovies.add(new Movie(result));
        }
    }
    public MutableLiveData<Movie> getMovieLD() {
        return movieLD;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie movie = listMovies.get(position);
        holder.tvTitle.setTag(movie);
        holder.tvTitle.setText(movie.getMovie().title);
        holder.tvDate.setText(movie.getMovie().releaseDate);
        holder.tvDesc.setText(movie.getMovie().overview);
        holder.trMovie.setBackgroundResource(movie.isSelected() ? R.color.green : R.color.light_gray);
        Glide.with(context).load(String.format(PATH_IMAGE + "%s", movie.getMovie().posterPath)).into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public void updateListResults(List<MoviesRes.Result> listResults) {
        listMovies.clear();
        for (MoviesRes.Result result : listResults) {
            listMovies.add(new Movie(result));
        }
        notifyItemRangeChanged(0, listMovies.size());
    }

    private void showMovieDetail(Object movieObj) {
        Movie movie = (Movie) movieObj;
        if(!movie.isSelected()){
            movie.setSelected(true);
            if(movieLD.getValue() != null){
                movieLD.getValue().setSelected(false);
            }
        }
        movieLD.postValue(movie);
        notifyItemRangeChanged(0, listMovies.size());
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate, tvDesc;
        ImageView ivPoster;
        TableRow trMovie;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            trMovie = itemView.findViewById(R.id.trMovie);
            trMovie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                    showMovieDetail(tvTitle.getTag());
                }
            });
        }
    }
}

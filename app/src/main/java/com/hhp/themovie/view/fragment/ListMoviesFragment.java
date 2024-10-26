package com.hhp.themovie.view.fragment;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hhp.themovie.api.response.MoviesRes;
import com.hhp.themovie.databinding.FragmentListMoviesBinding;
import com.hhp.themovie.model.Movie;
import com.hhp.themovie.view.adapter.MovieAdapter;
import com.hhp.themovie.view.base.BaseFragment;
import com.hhp.themovie.viewmodel.ListMoviesFrgVM;

public class ListMoviesFragment extends BaseFragment<FragmentListMoviesBinding, ListMoviesFrgVM> {
    public static final String TAG = ListMoviesFragment.class.getName();
    private MovieAdapter adapter;

    @Override
    public void initView() {
        viewModel.getListMovies();
        loadOnScrollRVMovies();
    }

    private void loadOnScrollRVMovies() {
        binding.rvMovies.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(!recyclerView.canScrollVertically(1) && newState == recyclerView.SCROLL_STATE_IDLE){
                    viewModel.getListMovies();
                }
            }
        });
    }


    @Override
    public Class<ListMoviesFrgVM> initViewModel() {
        return ListMoviesFrgVM.class;
    }

    @Override
    public FragmentListMoviesBinding initViewBinding() {
        return FragmentListMoviesBinding.inflate(getLayoutInflater());
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if(key.equals(ListMoviesFrgVM.KEY_GET_LIST_MOVIES)){
            MoviesRes res = (MoviesRes) data;
            if(res == null || res.listResults == null){
                Log.i(TAG, "apiSuccess: something is wrong");
                return;
            }
            viewModel.addToListResults(res.listResults);
            binding.includeBar.tvLoaded.setText(String.format("Loaded:\n%s", viewModel.getListResults().size()));
            if(adapter == null){
                adapter = new MovieAdapter(context, viewModel.getListResults());
                adapter.getMovieLD().observe(this, this::showMovieDetail);
                binding.rvMovies.setAdapter(adapter);
            } else {
                adapter.updateListResults(viewModel.getListResults());
            }
        }
    }

    private void showMovieDetail(Movie result) {
        if(result == null) return;
        callback.addFragment(DetailMovieFragment.TAG, result.getMovie(), true);
    }
}

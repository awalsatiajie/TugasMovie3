package com.awalsatiajie.tugasmovie3.movie;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.awalsatiajie.tugasmovie3.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements View.OnClickListener{

    private MovieAdapter adapter;
    private ProgressBar progressBar;
    private MovieViewModel movieViewModel;


    public MovieFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMovies().observe(this, getMovie);

        adapter = new MovieAdapter();
        adapter.notifyDataSetChanged();

        RecyclerView recyclerView = view.findViewById(R.id.recycler_movie);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progressBar);

        movieViewModel.setMovie();
        showLoading(true);

        adapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(MovieItem data) {
                showSelectedMovie(data);
            }
        });
    }

    private Observer<ArrayList<MovieItem>> getMovie = new Observer<ArrayList<MovieItem>>() {
        @Override
        public void onChanged(@Nullable ArrayList<MovieItem> movieItems) {
            if (movieItems != null) {
                adapter.setData(movieItems);
                showLoading(false);
            }
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
    }

    private void showSelectedMovie(MovieItem movie) {
        Intent movieDetail = new Intent(getActivity(), MovieDetailActivity.class);
        movieDetail.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
        startActivity(movieDetail);
    }

}

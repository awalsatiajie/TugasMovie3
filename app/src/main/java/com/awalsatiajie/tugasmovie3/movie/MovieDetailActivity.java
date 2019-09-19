package com.awalsatiajie.tugasmovie3.movie;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.awalsatiajie.tugasmovie3.R;
import com.bumptech.glide.Glide;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

    private ProgressBar progressBar;
    private MovieDetailViewModel movieDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        movieDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);
        movieDetailViewModel.getMovieDetail().observe(this, getModelMovieDetail);

        progressBar = findViewById(R.id.progressBar);

        MovieItem movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        int movieId = movie.getId();

        movieDetailViewModel.setMovieDetail(movieId);
        showLoading(true);
    }

    private Observer<MovieDetail> getModelMovieDetail = new Observer<MovieDetail>() {
        @Override
        public void onChanged(@Nullable MovieDetail movieDetail) {
            ImageView posterMovieDetail;
            TextView movieTitleDetail;
            TextView movieOverview;
            TextView movieReleaseDate;

            if (movieDetail != null) {
                String sPath = "https://image.tmdb.org/t/p/w342";

                posterMovieDetail = findViewById(R.id.poster_movienya);
                movieTitleDetail = findViewById(R.id.nama_movienya);
                movieOverview = findViewById(R.id.detail_movienya);
                movieReleaseDate= findViewById(R.id.tanggal_movienya);

                Glide.with(MovieDetailActivity.this)
                        .load(sPath + movieDetail.getPosterPath())
                        .into(posterMovieDetail);
                movieTitleDetail.setText(movieDetail.getTitle());
                movieOverview.setText(movieDetail.getOverview());
                movieReleaseDate.setText(movieDetail.getReleaseDate());

                showLoading(false);
            }
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}

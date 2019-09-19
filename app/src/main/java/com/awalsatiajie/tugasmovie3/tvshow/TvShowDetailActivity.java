package com.awalsatiajie.tugasmovie3.tvshow;

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

public class TvShowDetailActivity extends AppCompatActivity {

    public static final String EXTRA_TV_SHOW = "extra_tv_show";

    private ProgressBar progressBar;
    private TvShowDetailViewModel tvShowDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);

        tvShowDetailViewModel = ViewModelProviders.of(this).get(TvShowDetailViewModel.class);
        tvShowDetailViewModel.getTvShowDetail().observe(this, getModelTvShowDetail);

        progressBar = findViewById(R.id.progressBar);

        TvShowItem tvshow = getIntent().getParcelableExtra(EXTRA_TV_SHOW);
        int tvShowId = tvshow.getId();

        tvShowDetailViewModel.setTvShowDetail(tvShowId);
        showLoading(true);

    }

    private Observer<TvShowDetail> getModelTvShowDetail = new Observer<TvShowDetail>() {

        @Override
        public void onChanged(@Nullable TvShowDetail tvShowDetail) {
            ImageView posterTvShowDetail;
            TextView tvShowTitleDetail;
            TextView tvShowOverview;
            TextView tvShowFirstAirDate;

            if (tvShowDetail != null) {
                String sPath = "https://image.tmdb.org/t/p/w342";

                posterTvShowDetail = findViewById(R.id.poster_tv_shownya);
                tvShowTitleDetail = findViewById(R.id.nama_tv_shownya);
                tvShowOverview = findViewById(R.id.detail_tv_shownya);
                tvShowFirstAirDate= findViewById(R.id.tanggal_tv_shownya);

                Glide.with(TvShowDetailActivity.this)
                        .load(sPath + tvShowDetail.getPosterPath())
                        .into(posterTvShowDetail);
                tvShowTitleDetail.setText(tvShowDetail.getTitle());
                tvShowOverview.setText(tvShowDetail.getOverview());
                tvShowFirstAirDate.setText(tvShowDetail.getFirstAirDate());

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

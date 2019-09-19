package com.awalsatiajie.tugasmovie3.movie;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.awalsatiajie.tugasmovie3.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<MovieItem> mData = new ArrayList<>();

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public void setData(ArrayList<MovieItem> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemrow_movie, viewGroup, false);
        return new MovieViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {
        movieViewHolder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPoster;
        TextView textViewTitle;
        TextView textViewOverview;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.poster_movie);
            textViewTitle = itemView.findViewById(R.id.nama_movie);
            textViewOverview = itemView.findViewById(R.id.detail_movie);
        }

        void bind(final MovieItem movieItems) {
            textViewTitle.setText(movieItems.getTitle());
            textViewOverview.setText(movieItems.getOverview());

            Glide.with(itemView.getContext())
                    .load(movieItems.getPosterPath())
                    .into(ivPoster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickCallback.onItemClicked(movieItems);
                }
            });
        }

    }

    public interface OnItemClickCallback {
        void onItemClicked(MovieItem data);
    }

}

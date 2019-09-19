package com.awalsatiajie.tugasmovie3.tvshow;

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

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {

    private ArrayList<TvShowItem> mData = new ArrayList<>();

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(TvShowAdapter.OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public void setData(ArrayList<TvShowItem> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvShowAdapter.TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemrow_tv_show, viewGroup, false);
        return new TvShowViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder tvShowViewHolder, int position) {
        tvShowViewHolder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class TvShowViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPoster;
        TextView textViewTitle;
        TextView textViewOverview;

        TvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.poster_tv);
            textViewTitle = itemView.findViewById(R.id.nama_tv_show);
            textViewOverview = itemView.findViewById(R.id.detail_tv_show);
        }

        void bind(final TvShowItem tvShowItems) {
            textViewTitle.setText(tvShowItems.getTitle());
            textViewOverview.setText(tvShowItems.getOverview());

            Glide.with(itemView.getContext())
                    .load(tvShowItems.getPosterPath())
                    .into(ivPoster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickCallback.onItemClicked(tvShowItems);
                }
            });
        }

    }

    public interface OnItemClickCallback {
        void onItemClicked(TvShowItem data);
    }
}

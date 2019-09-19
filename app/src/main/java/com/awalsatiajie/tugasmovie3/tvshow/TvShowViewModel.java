package com.awalsatiajie.tugasmovie3.tvshow;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.awalsatiajie.tugasmovie3.BuildConfig;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TvShowViewModel extends ViewModel {

    private MutableLiveData<ArrayList<TvShowItem>> listTvShows = new MutableLiveData<>();

    void setTvShow() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TvShowItem> listItems = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/tv?api_key="+ BuildConfig.API_TMDBNYA+"&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject tvshow = list.getJSONObject(i);
                        TvShowItem tvShowItems = new TvShowItem(tvshow);
                        listItems.add(tvShowItems);
                    }
                    listTvShows.postValue(listItems);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    LiveData<ArrayList<TvShowItem>> getTvShows () {
        return listTvShows;
    }
}
package com.awalsatiajie.tugasmovie3.tvshow;

import org.json.JSONObject;

public class TvShowDetail {

    private String posterPath, title, overview, firstAirDate;

    public TvShowDetail(){
    }

    public String getPosterPath(){
        return  posterPath;
    }

    public void setPosterPath(String posterPath){
        this.posterPath = posterPath;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getOverview(){
        return overview;
    }

    public void setOverview(String overview){
        this.overview = overview;
    }

    public String getFirstAirDate(){
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate){
        this.firstAirDate = firstAirDate;
    }

    TvShowDetail(JSONObject object) {
        try {
            String posterPath = object.getString("poster_path");
            String title = object.getString("name");
            String overview = object.getString("overview");
            String firstAirDate = object.getString("first_air_date");

            this.posterPath = posterPath;
            this.title = title;
            this.overview = overview;
            this.firstAirDate = firstAirDate;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

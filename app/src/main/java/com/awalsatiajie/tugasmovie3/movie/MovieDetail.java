package com.awalsatiajie.tugasmovie3.movie;

import org.json.JSONObject;

public class MovieDetail {
    private String posterPath, title, overview, releaseDate;

    public MovieDetail(){
    }

    public String getPosterPath(){
        return posterPath;
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

    public String getReleaseDate(){
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }

    MovieDetail(JSONObject object) {
        try {
            String posterPath = object.getString("poster_path");
            String title = object.getString("title");
            String overview = object.getString("overview");
            String releaseDate = object.getString("release_date");

            this.posterPath = posterPath;
            this.title = title;
            this.overview = overview;
            this.releaseDate = releaseDate;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
